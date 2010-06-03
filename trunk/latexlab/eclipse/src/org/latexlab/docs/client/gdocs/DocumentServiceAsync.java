package org.latexlab.docs.client.gdocs;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of the GData remote service.
 */
public interface DocumentServiceAsync {
  
  /**
   * Creates a new, saved, document.
   * 
   * @param title the document's title
   * @param contents the document's contents
   * @param callback the success/failure handler
   */
  void createDocument(String title, String contents, AsyncCallback<DocumentServiceEntry> callback);
  
  /**
   * Deletes a document.
   * 
   * @param documentId the document Id
   * @param etag the document's version tag
   * @param callback the success/failure handler
   */
  void deleteDocument(String documentId, String etag, AsyncCallback<Boolean> callback);

  /**
   * Retrieves the contents of a common document.
   * 
   * @param name the name of the document whose documents to retrieve
   * @param callback the callback to invoke upon completion
   */
  void getCommonContents(String name, AsyncCallback<String> callback);

  /**
   * Retrieves a document by Id.
   * 
   * @param documentId the document Id
   * @param callback the success/failure handler
   */
  void getDocument(String documentId, AsyncCallback<DocumentServiceEntry> callback);
  
  /**
   * Retrieves the contents of a document by resource Id.
   * 
   * @param resourceId the resource Id
   * @param callback the success/failure handler
   */
  void getDocumentContents(String resourceId, AsyncCallback<String> callback);
  
  /**
   * Retrieves signed URLs for retrieving the contents of the specified documents.
   * 
   * @param documentLinks the document content links.
   * @return the signed URLs for retrieving the contents of the specified documents.
   * @throws DocumentServiceException
   */
  void getDocumentContentUrls(String[] documentLinks, AsyncCallback<DocumentSignedLocation[]> callback);
  
  /**
   * Retrieves a list of a documents.
   * 
   * @param starredOnly whether to return only starred documents.
   * @param callback the success/failure handler
   */
  void getDocuments(boolean starredOnly, AsyncCallback<DocumentServiceEntry[]> callback);
  
  /**
   * Retrieves a new, unsaved, document.
   * 
   * @param callback the success/failure handler
   */
  void getNewDocument(AsyncCallback<DocumentServiceEntry> callback);
  
  /**
   * Retrieves the currently signed on user.
   * 
   * @param callback the success/failure handler
   */
  void getUser(AsyncCallback<DocumentUser> callback);
  
  /**
   * Ends the current user's session
   * 
   * @param callback the success/failure handler
   */
  void logout(AsyncCallback<String> callback);
  
  /**
   * Renames a document.
   * 
   * @param documentId the document Id
   * @param newTitle the new document title
   * @param callback the success/failure handler
   */
  void renameDocument(String documentId, String newTitle, AsyncCallback<DocumentServiceEntry> callback);
  
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
   */
  void saveDocument(String documentId, String etag, String title, String contents, AsyncCallback<DocumentServiceEntry> callback);
  
  /**
   * Updates the contents of a document.
   * 
   * @param documentId the document Id
   * @param etag the document's version tag
   * @param contents the document contents
   * @param callback the success/failure handler
   */
  void setDocumentContents(String documentId, String etag, String contents, AsyncCallback<DocumentServiceEntry> callback);

  /**
   * Sets whether a document is starred.
   * 
   * @param document the document Id
   * @param starred whether the document is starred
   * @param callback the success/failure handler
   */
  void setDocumentStarred(String documentId, boolean starred, AsyncCallback<Boolean> callback);
  
  /**
   * Sets the current user (for development purposes only).
   * 
   * @param email the user's email
   * @param token the user's token
   * @return the current user
   */
  void setUser(String email, String token, AsyncCallback<DocumentUser> callback);
  
}
