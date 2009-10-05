package org.latexlab.pine.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.client.http.AuthSubUtil;
import com.google.gdata.data.Link;
import com.google.gdata.data.MediaContent;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.docs.DocumentEntry;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.DocumentListFeed;
import com.google.gdata.data.media.MediaByteArraySource;
import com.google.gdata.data.media.MediaSource;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.latexlab.pine.client.data.DocumentReference;
import org.latexlab.pine.client.data.GDataService;
import org.latexlab.pine.client.data.GDataServiceException;
import org.latexlab.pine.server.models.AuthenticationToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The server side implementation of the GData RPC service.
 */
@SuppressWarnings("serial")
public class GDataServiceImpl extends RemoteServiceServlet implements
    GDataService {

  public static final String GDATA_CLIENT_APPLICATION_NAME = "gdbe-1.0";
  public static final String LOGOUT_RETURN_RELATIVE_PATH = "/";
  public static final String DOCS_SCOPE = "http://docs.google.com/feeds/";
  
  
  /**
   * Retrieves a document by Id.
   * 
   * @param documentId the document Id
   * @param callback the success/failure handler
   * @throws GDataServiceException
   */
  public DocumentReference getDocument(String documentId) throws GDataServiceException {
    DocumentListEntry entry = getDocumentEntry(documentId);
    return getDocumentReference(entry);
  }
  
  /**
   * Retrieves a new, unsaved, document.
   * 
   * @param callback the success/failure handler
   */
  public DocumentReference getNewDocument() {
    UserService userService = UserServiceFactory.getUserService();
    DocumentReference doc = new DocumentReference();
    doc.setTitle("Untitled");
    doc.setAuthor(userService.getCurrentUser().getEmail());
    doc.setEditor(userService.getCurrentUser().getNickname());
    return doc;
  }
  
  /**
   * Retrieves the contents of a document by resource Id.
   * 
   * @param resourceId the resource Id
   * @param callback the success/failure handler
   * @throws GDataServiceException
   */
  public String getDocumentContents(String resourceId) throws GDataServiceException {
    AuthenticationToken token = getToken();
    if (token == null) return null;
    DocsService svc = getDocsService(token);
    String contentUri = DOCS_SCOPE + "download/documents/Export?exportFormat=txt&docID=" + resourceId;
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
   * @param documentId the document Id
   * @param etag the document's version tag
   * @param contents the document contents
   * @param callback the success/failure handler
   * @throws GDataServiceException 
   */
  public boolean setDocumentContents(String documentId, String etag, String contents) throws GDataServiceException {
    AuthenticationToken token = getToken();
    if (token == null) return false;
    DocsService svc = getDocsService(token);
    svc.getRequestFactory().setHeader("If-Match", etag);
    contents = encodeDocumentContents(contents);
    MediaByteArraySource source = new MediaByteArraySource(contents.getBytes(), "text/plain");
    String editMediaUri = DOCS_SCOPE + "default/media/document%3A" + documentId;
    try {
      svc.updateMedia(new URL(editMediaUri), DocumentListEntry.class, source);
    } catch (Exception e) {
      throw new GDataServiceException(e.getMessage());
    }
    return true;
  }
  
  /**
   * Renames a document.
   * 
   * @param documentId the document Id
   * @param newTitle the new document title
   * @param callback the success/failure handler
   * @throws GDataServiceException
   */
  public DocumentReference renameDocument(String documentId, String newTitle) throws GDataServiceException {
    DocumentListEntry entry = getDocumentEntry(documentId);
    try {
      entry.setTitle(new PlainTextConstruct(newTitle));
      entry.update();
      return getDocument(documentId);
    } catch (Exception e) {
      throw new GDataServiceException(e.getMessage());
    }
  }
  
  /**
   * Deletes a document.
   * 
   * @param documentId the document Id
   * @param etag the document's version tag
   * @param callback the success/failure handler
   * @throws GDataServiceException
   */
  public boolean deleteDocument(String documentId, String etag) throws GDataServiceException {
    AuthenticationToken token = getToken();
    if (token == null) return false;
    DocsService svc = getDocsService(token);
    String documentUri = DOCS_SCOPE + "default/private/full/document%3A" + documentId;
    svc.getRequestFactory().setHeader("If-Match", etag);
    try {
      svc.delete(new URL(documentUri));
    } catch (Exception e) {
      throw new GDataServiceException(e.getMessage());
    }
    return true;
  }
  
  /**
   * Sets whether a document is starred.
   * 
   * @param document the document Id
   * @param starred whether the document is starred
   * @param callback the success/failure handler
   * @throws GDataServiceException
   */
  public boolean setDocumentStarred(String documentId, boolean starred) throws GDataServiceException {
    DocumentListEntry entry = getDocumentEntry(documentId);
    try {
      entry.setStarred(starred);
      entry.update();
    } catch (Exception e) {
      throw new GDataServiceException(e.getMessage());
    }
    return true;
  }
  
  /**
   * Creates a new, saved, document.
   * 
   * @param title the document's title
   * @param contents the document's contents
   * @param callback the success/failure handler
   * @throws GDataServiceException
   */
  public DocumentReference createDocument(String title, String contents) throws GDataServiceException {
    AuthenticationToken token = getToken();
    if (token == null) return null;
    DocsService svc = getDocsService(token);
    DocumentEntry newDocument = new DocumentEntry();
    newDocument.setTitle(new PlainTextConstruct(title));
    MediaByteArraySource source = new MediaByteArraySource(contents.getBytes(), "text/plain");
    newDocument.setMediaSource(source);
    DocumentEntry entry;
    try {
      entry = svc.insert(new URL(DOCS_SCOPE + "default/private/full"), newDocument);
    } catch (Exception e) {
      throw new GDataServiceException(e.getMessage());
    }
    return getDocumentReference(entry);
  }
  
  /**
   * Updates or creates a new document. If a value for documentId is
   * specified, the respective document is updated, otherwise a new 
   * document is created.
   * 
   * @param documentId the document Id
   * @param etag the document's etag
   * @param title the document's title
   * @param contents the document's contents
   * @param callback the success/failure handler
   * @throws GDataServiceException
   */
  public DocumentReference saveDocument(String documentId, String etag, String title,
      String contents) throws GDataServiceException {
    if (documentId == null || documentId.equals("")) {
      return createDocument(title, contents);
    } else {
      setDocumentContents(documentId, etag, contents);
      return getDocument(documentId);
    }
  }
  
  /**
   * Retrieves a list of a documents.
   * 
   * @param starredOnly whether to return only starred documents.
   * @param callback the success/failure handler
   * @throws GDataServiceException
   */
  public DocumentReference[] getDocuments(boolean starredOnly) throws GDataServiceException {
    AuthenticationToken token = getToken();
    if (token == null) return null;
    ArrayList<DocumentReference> docs = new ArrayList<DocumentReference>();
    DocsService svc = getDocsService(token);
    DocumentListFeed feed;
    try {
      feed = svc.getFeed(new URL(DOCS_SCOPE + "default/private/full/-/document" + (starredOnly ? "/starred" : "")), DocumentListFeed.class);
      for (DocumentListEntry entry : feed.getEntries()) {
        docs.add(getDocumentReference(entry));
      }
    } catch (Exception e) {
      throw new GDataServiceException(e.getMessage());
    }
    return docs.toArray(new DocumentReference[docs.size()]);
  }
  
  /**
   * Retrieves the currently signed on user.
   * 
   * @param callback the success/failure handler
   */
  public String getUser() {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null){
      String email = user.getEmail();
      if (AuthenticationToken.getUserToken(email) != null) {
        return email;
      }
    }
    return null;
  }
  
  /**
   * Ends the current user's session
   * 
   * @param callback the success/failure handler
   * @throws GDataServiceException
   */
  public String logout() throws GDataServiceException {
    AuthenticationToken token = getToken();
    if (token != null) {
      try {
        AuthSubUtil.revokeToken(token.getToken(), null);
        AuthenticationToken.clearUserToken(token.getEmail());
        UserService userService = UserServiceFactory.getUserService();
        URI url = new URI(this.getThreadLocalRequest().getRequestURL().toString());
        return userService.createLogoutURL("http://" + url.getAuthority() + LOGOUT_RETURN_RELATIVE_PATH);
      } catch (Exception e) {
        throw new GDataServiceException(e.getMessage());
      }
    }
    return "/";
  }
  
  /**
   * Retrieves the authentication token for the current user, if any.
   * 
   * @return the current user's Authentication token
   */
  private AuthenticationToken getToken() {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null){
      String email = user.getEmail();
      return AuthenticationToken.getUserToken(email);
    }
    return null;
  }

  /**
   * Retrieves a GData Document entry by document id.
   * 
   * @param documentId the id of the document to retrieve
   * @return the GData document entry
   * @throws GDataServiceException
   */
  private DocumentListEntry getDocumentEntry(String documentId) throws GDataServiceException {
    AuthenticationToken token = getToken();
    if (token == null) return null;
    DocsService svc = getDocsService(token);
    String documentUri = DOCS_SCOPE + "default/private/full/document%3A" + documentId;
    try {
      return svc.getEntry(new URL(documentUri), DocumentListEntry.class);
    } catch (Exception e) {
      throw new GDataServiceException(e.getMessage());
    }
  }
  
  /**
   * Builds a document reference from a document entry.
   * 
   * @param entry the document entry to reference
   * @return a document reference
   */
  private DocumentReference getDocumentReference(DocumentListEntry entry) {
    DocumentReference doc = new DocumentReference();
    doc.setDocumentId(entry.getDocId());
    doc.setResourceId(entry.getResourceId());
    doc.setTitle(entry.getTitle().getPlainText());
    doc.setAuthor(entry.getAuthors().get(0).getEmail());
    doc.setEdited(new Date(entry.getEdited().getValue()));
    doc.setEditor(entry.getLastModifiedBy().getName());
    doc.setEtag(entry.getEtag());
    doc.setStarred(entry.isStarred());
    List<Link> parents = entry.getParentLinks();
    String[] folders = new String[parents.size()];
    for (int i=0; i<parents.size(); i++) {
      folders[i] = parents.get(i).getTitle();
    }
    doc.setFolders(folders);
    return doc;
  }
  
  /**
   * Encodes a string for document contents.
   * The GData export script doesn't like "<" and ">" and replaces each of these characters
   * with the following bytes: EF BF BD EF BF BD
   * Since these characters are lost, use this function to encode them in a reversible manner.
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
  
  /**
   * Instantiates a GData Documents service. Timeout is disabled, the default
   * AppEngine timeout will be enforced.
   * @param token the Authentication token
   * @return a GData Documents service
   */
  private DocsService getDocsService(AuthenticationToken token) {
    DocsService svc = new DocsService(GDATA_CLIENT_APPLICATION_NAME);
    svc.setConnectTimeout(0);
    svc.setAuthSubToken(token.getToken());
    return svc;
  }
}
