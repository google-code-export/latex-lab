package org.latexlab.pine.client.data;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of the GData remote service.
 */
public interface GDataServiceAsync {
  
  /**
   * Retrieves a document by Id.
   * 
   * @param documentId the document Id
   * @param callback the success/failure handler
   */
  void getDocument(String documentId, AsyncCallback<DocumentReference> callback);
  
  /**
   * Retrieves a new, unsaved, document.
   * 
   * @param callback the success/failure handler
   */
  void getNewDocument(AsyncCallback<DocumentReference> callback);
  
  /**
   * Retrieves the contents of a document by resource Id.
   * 
   * @param resourceId the resource Id
   * @param callback the success/failure handler
   */
  void getDocumentContents(String resourceId, AsyncCallback<String> callback);
  
  /**
   * Updates the contents of a document.
   * 
   * @param documentId the document Id
   * @param etag the document's version tag
   * @param contents the document contents
   * @param callback the success/failure handler
   */
  void setDocumentContents(String documentId, String etag, String contents, AsyncCallback<Boolean> callback);
  
  /**
   * Renames a document.
   * 
   * @param documentId the document Id
   * @param newTitle the new document title
   * @param callback the success/failure handler
   */
  void renameDocument(String documentId, String newTitle, AsyncCallback<DocumentReference> callback);
  
  /**
   * Deletes a document.
   * 
   * @param documentId the document Id
   * @param etag the document's version tag
   * @param callback the success/failure handler
   */
  void deleteDocument(String documentId, String etag, AsyncCallback<Boolean> callback);
  
  /**
   * Sets whether a document is starred.
   * 
   * @param document the document Id
   * @param starred whether the document is starred
   * @param callback the success/failure handler
   */
  void setDocumentStarred(String documentId, boolean starred, AsyncCallback<Boolean> callback);
  
  /**
   * Creates a new, saved, document.
   * 
   * @param title the document's title
   * @param contents the document's contents
   * @param callback the success/failure handler
   */
  void createDocument(String title, String contents, AsyncCallback<DocumentReference> callback);
  
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
  void saveDocument(String documentId, String etag, String title, String contents, AsyncCallback<DocumentReference> callback);
  
  /**
   * Retrieves a list of a documents.
   * 
   * @param starredOnly whether to return only starred documents.
   * @param callback the success/failure handler
   */
  void getDocuments(boolean starredOnly, AsyncCallback<DocumentReference[]> callback);
  
  /**
   * Retrieves the currently signed on user.
   * 
   * @param callback the success/failure handler
   */
  void getUser(AsyncCallback<String> callback);
  
  /**
   * Ends the current user's session
   * 
   * @param callback the success/failure handler
   */
  void logout(AsyncCallback<String> callback);
  
}
