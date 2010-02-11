package com.google.gwt.gdata.ext.server.documents;

import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.MediaContent;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.media.MediaByteArraySource;
import com.google.gdata.data.media.MediaSource;
import com.google.gwt.gdata.ext.client.GDataServiceException;
import com.google.gwt.gdata.ext.client.documents.DocumentsServiceProxy;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * The server side implementation of the GData RPC service.
 */
@SuppressWarnings("serial")
public class DocumentsServiceProxyImpl extends RemoteServiceServlet implements
    DocumentsServiceProxy {

  private static final String DOCS_SCOPE = "https://docs.google.com/feeds/";
  
  public DocumentsServiceProxyImpl() {
  }
  
  /**
   * Retrieves the contents of a document by resource Id.
   * 
   * @param resourceId the resource Id
   * @throws GDataServiceException
   */
  public String getDocumentContents(String applicationName,
      String authsubToken, String documentId)
      throws GDataServiceException {
    DocsService svc = new DocsService(applicationName);
    svc.setConnectTimeout(0);
    svc.setAuthSubToken(authsubToken);
    String contentUri = DOCS_SCOPE + 
        "download/documents/Export?exportFormat=txt&docID=" + documentId;
    try {
      MediaContent mc = new MediaContent();
      mc.setUri(contentUri.toString());
      MediaSource ms = svc.getMedia(mc);
      InputStreamReader reader = null;
      try {
        reader = new InputStreamReader(ms.getInputStream());
        BufferedReader br = new BufferedReader(reader);
        StringBuilder contents = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
          contents.append(line + "\n");
        }
        /*
         * Contents seem to start with three non-ASCII characters, remove them.
         */
        if(contents.length() > 3){
          contents.delete(0, 3);
        }
        return decodeDocumentContents(contents.toString());
      }  finally {
        if (reader != null) {
          reader.close();
        }
      }
    }catch (Exception e) {
      throw new GDataServiceException(e.getMessage());
    }
  }

  /**
   * Updates the contents of a document.
   * 
   * @param applicationName the calling application identifier
   * @param authsubToken the authsub session token
   * @param documentId the document Id
   * @param etag the document's version tag
   * @param contents the document contents
   * @throws GDataServiceException 
   */
  public boolean setDocumentContents(String applicationName,
      String authsubToken, String documentId, String etag, String contents)
      throws GDataServiceException {
    DocsService svc = new DocsService(applicationName);
    svc.setConnectTimeout(0);
    svc.setAuthSubToken(authsubToken);
    svc.getRequestFactory().setHeader("If-Match", etag);
    contents = encodeDocumentContents(contents);
    MediaByteArraySource source =
      new MediaByteArraySource(contents.getBytes(), "text/plain");
    String editMediaUri =
      DOCS_SCOPE + "default/media/document%3A" + documentId;
    try {
      svc.updateMedia(new URL(editMediaUri), DocumentListEntry.class, source);
    } catch (Exception e) {
      throw new GDataServiceException(e.toString() + ":" + e.getMessage());
    }
    return true;
  }
  
  /**
   * Encodes a string for document contents.
   * The GData export script doesn't like "<" and ">" and replaces each of
   * these characters with the following bytes: EF BF BD EF BF BD
   * Since these characters are lost, use this function to encode them in a
   * reversible manner.
   * Add other encode logic to this function as needed.
   * 
   * @param value the value to encode
   * @return the encoded value
   */
  private String encodeDocumentContents(String value) {
    return value.replace("<", "&lt;").replace(">", "&gt;");
  }

  /**
   * Decodes a string for document contents.
   * 
   * @param value the value to decode
   * @return the decoded value
   */
  private String decodeDocumentContents(String value) {
    return value.replace("&lt;", "<").replace("&gt;", ">");
  }
}
