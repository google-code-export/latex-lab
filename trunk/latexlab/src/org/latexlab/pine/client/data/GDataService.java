package org.latexlab.pine.client.data;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The GData remote service.
 */
@RemoteServiceRelativePath("data")
public interface GDataService extends RemoteService {
  
  /**
   * Retrieves a document by Id.
   * 
   * @param documentId the document Id
   * @return the document reference
   * @throws GDataServiceException
   */
  DocumentReference getDocument(String documentId) throws GDataServiceException;
  
  /**
   * Retrieves a new, unsaved, document.
   * 
   * @return the new document reference
   */
  DocumentReference getNewDocument();
  
  /**
   * Retrieves the contents of a document by resource Id.
   * 
   * @param resourceId the resource Id
   * @return the document contents
   * @throws GDataServiceException
   */
  String getDocumentContents(String resourceId) throws GDataServiceException;
  
  /**
   * Updates the contents of a document.
   * 
   * @param documentId the document Id
   * @param etag the document's version tag
   * @param contents the document contents
   * @return whether the update was successful
   * @throws GDataServiceException
   */
  boolean setDocumentContents(String documentId, String etag, String contents) throws GDataServiceException;
  
  /**
   * Renames a document.
   * 
   * @param documentId the document Id
   * @param newTitle the new document title
   * @return the updated document
   * @throws GDataServiceException
   */
  DocumentReference renameDocument(String documentId, String newTitle) throws GDataServiceException;
  
  /**
   * Deletes a document.
   * 
   * @param documentId the document Id
   * @param etag the document's version tag
   * @return whether the delete was successful
   * @throws GDataServiceException
   */
  boolean deleteDocument(String documentId, String etag) throws GDataServiceException;
  
  /**
   * Sets whether a document is starred.
   * 
   * @param document the document Id
   * @param starred whether the document is starred
   * @return whether the starring was successful
   * @throws GDataServiceException
   */
  boolean setDocumentStarred(String documentId, boolean starred) throws GDataServiceException;
  
  /**
   * Creates a new, saved, document.
   * 
   * @param title the document's title
   * @param contents the document's contents
   * @return the new document reference
   * @throws GDataServiceException
   */
  DocumentReference createDocument(String title, String contents) throws GDataServiceException;
  
  /**
   * Updates or creates a new document. If a value for documentId is
   * specified, the respective document is updated, otherwise a new 
   * document is created.
   * 
   * @param documentId the document Id
   * @param etag the document's etag
   * @param title the document's title
   * @param contents the document's contents
   * @return the new or updated document reference
   * @throws GDataServiceException
   */
  DocumentReference saveDocument(String documentId, String etag, String title, String contents) throws GDataServiceException;
  
  /**
   * Retrieves a list of a documents.
   * 
   * @param starredOnly whether to return only starred documents.
   * @return the list of documents
   * @throws GDataServiceException
   */
  DocumentReference[] getDocuments(boolean starredOnly) throws GDataServiceException;
  
  /**
   * Retrieves the currently signed on user.
   * 
   * @return the current user's name
   */
  String getUser();
  
  /**
   * Ends the current user's session
   * 
   * @return the URL to which the user should be redirected in order
   * to complete the sign off process
   * @throws GDataServiceException
   */
  String logout() throws GDataServiceException;
}
