package org.latexlab.docs.server.gdocs;

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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.latexlab.docs.client.gdocs.DocumentServiceEntry;
import org.latexlab.docs.client.gdocs.DocumentService;
import org.latexlab.docs.client.gdocs.DocumentServiceException;
import org.latexlab.docs.client.gdocs.DocumentSignedLocation;
import org.latexlab.docs.client.gdocs.DocumentUser;
import org.latexlab.docs.server.auth.AuthenticationKey;
import org.latexlab.docs.server.auth.AuthenticationToken;
import org.latexlab.docs.server.auth.AuthenticationTokenStore;

/**
 * The server side implementation of the GData RPC service.
 */
@SuppressWarnings("serial")
public class DocumentServiceImpl extends RemoteServiceServlet implements
    DocumentService {

  public static final String GDATA_CLIENT_APPLICATION_NAME = "latex-lab-1.0";
  public static final String LOGOUT_RETURN_RELATIVE_PATH = "/";
  public static final String DOCS_SCOPE = "https://docs.google.com/feeds/";
  public static final String AUTH_SCOPES = DOCS_SCOPE + " https://docs.googleusercontent.com";
  
  public AuthenticationTokenStore store;
  
  public DocumentServiceImpl() {
    this.store = new AuthenticationTokenStore();
  }
  
  /**
   * Retrieves a document by Id.
   * 
   * @param documentId the document Id
   * @throws DocumentServiceException
   */
  public DocumentServiceEntry getDocument(String documentId) throws DocumentServiceException {
    DocumentListEntry entry = getDocumentEntry(documentId);
    return getDocumentReference(entry);
  }
  
  /**
   * Retrieves a new, unsaved, document.
   */
  public DocumentServiceEntry getNewDocument() {
    UserService userService = UserServiceFactory.getUserService();
    DocumentServiceEntry doc = new DocumentServiceEntry();
    doc.setTitle("Untitled");
    doc.setAuthor(userService.getCurrentUser().getEmail());
    doc.setEditor(userService.getCurrentUser().getNickname());
    return doc;
  }  
  
  @Override
  public DocumentSignedLocation[] getDocumentContentUrls(String[] documentLinks) throws DocumentServiceException {
	AuthenticationToken token = this.store.getUserToken();
	if (token == null) {
	  throw new DocumentServiceException("Service requires authentication.");
	}
	try {
	  DocumentSignedLocation[] dsls = new DocumentSignedLocation[documentLinks.length];
	  for (int i=0; i<documentLinks.length; i++) {
		String documentUrl = documentLinks[i];
	    String documentUrlSig;
		documentUrlSig = AuthSubUtil.formAuthorizationHeader(
		        token.getToken(), AuthenticationKey.getAuthSubKey(), new URL(documentUrl), "GET");
        DocumentSignedLocation dsl =
    	    new DocumentSignedLocation(documentUrl, documentUrlSig);
	    dsls[i] = dsl;
	  }
	  return dsls;
	} catch (Exception e) {
		throw new DocumentServiceException(e.getMessage());
	}
  }
  
  /**
   * Retrieves the contents of a document by resource Id.
   * 
   * @param resourceId the resource Id
   * @throws DocumentServiceException
   */
  public String getDocumentContents(String resourceId) throws DocumentServiceException {
    DocsService svc = getDocsService();
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
         * Contents exported as text start with the UTF-8 byte-order-mark. Remove them.
         */
        String txt = contents.toString();
        if(txt.length() > 3){
          txt = txt.substring(3);
        }
        return decodeDocumentContents(txt);
      }  finally {
        if (reader != null) {
          reader.close();
        }
      }
    }catch (Exception e) {
      throw new DocumentServiceException(e.getMessage());
    }
  }

  /**
   * Updates the contents of a document.
   * 
   * @param documentId the document Id
   * @param etag the document's version tag
   * @param contents the document contents
   * @throws DocumentServiceException 
   */
  public boolean setDocumentContents(String documentId, String etag, String contents) throws DocumentServiceException {
    DocsService svc = getDocsService();
    svc.getRequestFactory().setHeader("If-Match", etag);
    contents = encodeDocumentContents(contents);
    MediaByteArraySource source = new MediaByteArraySource(contents.getBytes(), "text/plain");
    String editMediaUri = DOCS_SCOPE + "default/media/document%3A" + documentId;
    try {
      svc.updateMedia(new URL(editMediaUri), DocumentListEntry.class, source);
    } catch (Exception e) {
      throw new DocumentServiceException(e.getMessage());
    }
    return true;
  }
  
  /**
   * Renames a document.
   * 
   * @param documentId the document Id
   * @param newTitle the new document title
   * @throws DocumentServiceException
   */
  public DocumentServiceEntry renameDocument(String documentId, String newTitle) throws DocumentServiceException {
    DocumentListEntry entry = getDocumentEntry(documentId);
    try {
      entry.setTitle(new PlainTextConstruct(newTitle));
      entry.update();
      return getDocument(documentId);
    } catch (Exception e) {
      throw new DocumentServiceException(e.getMessage());
    }
  }
  
  /**
   * Deletes a document.
   * 
   * @param documentId the document Id
   * @param etag the document's version tag
   * @throws DocumentServiceException
   */
  public boolean deleteDocument(String documentId, String etag) throws DocumentServiceException {
    DocsService svc = getDocsService();
    String documentUri = DOCS_SCOPE + "default/private/full/document%3A" + documentId;
    svc.getRequestFactory().setHeader("If-Match", etag);
    try {
      svc.delete(new URL(documentUri));
    } catch (Exception e) {
      throw new DocumentServiceException(e.getMessage());
    }
    return true;
  }
  
  /**
   * Sets whether a document is starred.
   * 
   * @param document the document Id
   * @param starred whether the document is starred
   * @throws DocumentServiceException
   */
  public boolean setDocumentStarred(String documentId, boolean starred) throws DocumentServiceException {
    DocumentListEntry entry = getDocumentEntry(documentId);
    try {
      entry.setStarred(starred);
      entry.update();
    } catch (Exception e) {
      throw new DocumentServiceException(e.getMessage());
    }
    return true;
  }
  
  /**
   * Creates a new, saved, document.
   * 
   * @param title the document's title
   * @param contents the document's contents
   * @throws DocumentServiceException
   */
  public DocumentServiceEntry createDocument(String title, String contents) throws DocumentServiceException {
    DocsService svc = getDocsService();
    DocumentEntry newDocument = new DocumentEntry();
    newDocument.setTitle(new PlainTextConstruct(title));
    MediaByteArraySource source = new MediaByteArraySource(contents.getBytes(), "text/plain");
    newDocument.setMediaSource(source);
    DocumentEntry entry;
    try {
      entry = svc.insert(new URL(DOCS_SCOPE + "default/private/full"), newDocument);
    } catch (Exception e) {
      throw new DocumentServiceException(e.getMessage());
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
   * @throws DocumentServiceException
   */
  public DocumentServiceEntry saveDocument(String documentId, String etag, String title,
      String contents) throws DocumentServiceException {
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
   * @throws DocumentServiceException
   */
  public DocumentServiceEntry[] getDocuments(boolean starredOnly) throws DocumentServiceException {
    ArrayList<DocumentServiceEntry> docs = new ArrayList<DocumentServiceEntry>();
    DocsService svc = getDocsService();
    DocumentListFeed feed;
    try {
      String url = DOCS_SCOPE + "default/private/full/";
      if (starredOnly) {
        url += "-/starred";
      } else {
    	url += "?showfolders=true";
      }
      feed = svc.getFeed(new URL(url), DocumentListFeed.class);
      for (DocumentListEntry entry : feed.getEntries()) {
        docs.add(getDocumentReference(entry));
      }
    } catch (Exception e) {
      throw new DocumentServiceException(e.getMessage());
    }
    Collections.sort(docs, new Comparator<DocumentServiceEntry>() {
		@Override
		public int compare(DocumentServiceEntry arg0, DocumentServiceEntry arg1) {
	      if (arg0.getType().equalsIgnoreCase(arg1.getType())) {
	        return arg0.getTitle().compareTo(arg1.getTitle());
	      } else {
	    	if (arg0.getType().equalsIgnoreCase("folder")) {
	    	  return -1;
	    	} else if (arg1.getType().equalsIgnoreCase("folder")) {
	    	  return 1;
	    	} else {
	    	  return arg0.getTitle().compareTo(arg1.getTitle());
	    	}
	      }
		}
    });
    return docs.toArray(new DocumentServiceEntry[docs.size()]);
  }
  
  /**
   * Retrieves the currently signed on user.
   */
  public DocumentUser getUser() {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null){
      String email = user.getEmail();
      if (AuthenticationToken.getUserToken(email) != null) {
        DocumentUser docUser = new DocumentUser();
        docUser.setName(user.getNickname());
        docUser.setEmail(user.getEmail());
        docUser.setId(user.getUserId());
        return docUser;
      }
    }
    return null;
  }
  
  /**
   * Ends the current user's session
   * 
   * @throws DocumentServiceException
   */
  public String logout() throws DocumentServiceException {
    AuthenticationToken token = store.getUserToken();
    if (token != null) {
      try {
        AuthSubUtil.revokeToken(token.getToken(), null);
        AuthenticationToken.clearUserToken(token.getEmail());
        UserService userService = UserServiceFactory.getUserService();
        URI url = new URI(this.getThreadLocalRequest().getRequestURL().toString());
        return userService.createLogoutURL("http://" + url.getAuthority() + LOGOUT_RETURN_RELATIVE_PATH);
      } catch (Exception e) {
        throw new DocumentServiceException(e.getMessage());
      }
    }
    return "/";
  }

  /**
   * Retrieves a GData Document entry by document id.
   * 
   * @param documentId the id of the document to retrieve
   * @return the GData document entry
   * @throws DocumentServiceException
   */
  private DocumentListEntry getDocumentEntry(String documentId) throws DocumentServiceException {
    DocsService svc = getDocsService();
    String documentUri = DOCS_SCOPE + "default/private/full/document%3A" + documentId;
    try {
      return svc.getEntry(new URL(documentUri), DocumentListEntry.class);
    } catch (Exception e) {
      throw new DocumentServiceException(e.getMessage());
    }
  }
  
  /**
   * Builds a document reference from a document entry.
   * 
   * @param entry the document entry to reference
   * @return a document reference
   */
  private DocumentServiceEntry getDocumentReference(DocumentListEntry entry) {
    DocumentServiceEntry doc = new DocumentServiceEntry();
    doc.setType(entry.getType());
    doc.setDocumentId(entry.getDocId());
    doc.setResourceId(entry.getResourceId());
    doc.setTitle(entry.getTitle().getPlainText());
    doc.setAuthor(entry.getAuthors().get(0).getEmail());
    doc.setCreated(new Date(entry.getPublished().getValue()));
    doc.setEdited(new Date(entry.getEdited().getValue()));
    doc.setEditor(entry.getLastModifiedBy().getName());
    doc.setEtag(entry.getEtag());
    doc.setStarred(entry.isStarred());
    String prefix = getResourceIdPrefix(entry.getResourceId());
    if (prefix != null && prefix.equalsIgnoreCase("document")) {
      doc.setContentType("text/plain");
      doc.setContentLink(DOCS_SCOPE + "download/documents/Export?exportFormat=txt&docID=" +
          entry.getResourceId());
    } else {
      MediaContent mc = (MediaContent) entry.getContent();
      doc.setContentType(mc.getMimeType().getMediaType());
      doc.setContentLink(mc.getUri());
    }
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
 * @throws DocumentServiceException 
   */
  private DocsService getDocsService() throws DocumentServiceException {
	AuthenticationToken token = this.store.getUserToken();
	if (token == null) {
	  throw new DocumentServiceException("Service requires authentication.");
	}
	PrivateKey key = AuthenticationKey.getAuthSubKey();
    DocsService svc = new DocsService(GDATA_CLIENT_APPLICATION_NAME);
    svc.setConnectTimeout(0);
    svc.setReadTimeout(0);
    svc.setAuthSubToken(token.getToken(), key);
    svc.setProtocolVersion(DocsService.Versions.V3);
    return svc;
  }
  
  private String getResourceIdPrefix(String resourceId) {
    if (resourceId == null) {
      return null;
    }
    if (resourceId.indexOf("%3A") != -1) {
      return resourceId.substring(0, resourceId.indexOf("%3A"));
    } else if (resourceId.indexOf(":") != -1) {
      return resourceId.substring(0, resourceId.indexOf(":"));
    } else {
      return null;
    }
  }
}
