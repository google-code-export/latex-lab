package org.latexlab.docs.client.gdocs;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The GData remote service.
 */
@RemoteServiceRelativePath("doc-rpc")
public interface DocumentService extends RemoteService {
  
  /**
   * Creates a new, saved, document.
   * 
   * @param title the document's title
   * @param contents the document's contents
   * @return the new document reference
   * @throws DocumentServiceException
   */
  DocumentServiceEntry createDocument(String title, String contents) throws DocumentServiceException;
  
  /**
   * Deletes a document.
   * 
   * @param documentId the document Id
   * @param etag the document's version tag
   * @return whether the delete was successful
   * @throws DocumentServiceException
   */
  boolean deleteDocument(String documentId, String etag) throws DocumentServiceException;
  
  /**
   * Retrieves the contents of a common document.
   * 
   * @param name the name of the document whose documents to retrieve
   * @return the document's contents
   */
  String getCommonContents(String name);

  /**
   * Retrieves a document by Id.
   * 
   * @param documentId the document Id
   * @return the document reference
   * @throws DocumentServiceException
   */
  DocumentServiceEntry getDocument(String documentId) throws DocumentServiceException;
  
  /**
   * Retrieves the contents of a document by resource Id.
   * 
   * @param resourceId the resource Id
   * @return the document contents
   * @throws DocumentServiceException
   */
  String getDocumentContents(String resourceId) throws DocumentServiceException;
  
  /**
   * Retrieves signed URLs for retrieving the contents of the specified documents.
   * 
   * @param documentLinks the document content links.
   * @return the signed URLs for retrieving the contents of the specified documents.
   * @throws DocumentServiceException
   */
  DocumentSignedLocation[] getDocumentContentUrls(String[] documentLinks) throws DocumentServiceException;
  
  /**
   * Retrieves a list of a documents.
   * 
   * @param starredOnly whether to return only starred documents.
   * @return the list of documents
   * @throws DocumentServiceException
   */
  DocumentServiceEntry[] getDocuments(boolean starredOnly) throws DocumentServiceException;
  
  /**
   * Retrieves a new, unsaved, document.
   * 
   * @return the new document reference
   */
  DocumentServiceEntry getNewDocument();
  
  /**
   * Retrieves the currently signed on user.
   * 
   * @return the current user's name
   */
  DocumentUser getUser();
  
  /**
   * Ends the current user's session
   * 
   * @return the URL to which the user should be redirected in order
   * to complete the sign off process
   * @throws DocumentServiceException
   */
  String logout() throws DocumentServiceException;
  
  /**
   * Renames a document.
   * 
   * @param documentId the document Id
   * @param newTitle the new document title
   * @return the updated document
   * @throws DocumentServiceException
   */
  DocumentServiceEntry renameDocument(String documentId, String newTitle) throws DocumentServiceException;
  
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
   * @throws DocumentServiceException
   */
  DocumentServiceEntry saveDocument(String documentId, String etag, String title, String contents) throws DocumentServiceException;
  
  /**
   * Updates the contents of a document.
   * 
   * @param documentId the document Id
   * @param etag the document's version tag
   * @param contents the document contents
   * @return the updated document
   * @throws DocumentServiceException
   */
  DocumentServiceEntry setDocumentContents(String documentId, String etag, String contents) throws DocumentServiceException;
  
  /**
   * Sets whether a document is starred.
   * 
   * @param document the document Id
   * @param starred whether the document is starred
   * @return whether the starring was successful
   * @throws DocumentServiceException
   */
  boolean setDocumentStarred(String documentId, boolean starred) throws DocumentServiceException;
  
  /**
   * Sets the current user (for development purposes only).
   * 
   * @param email the user's email
   * @param token the user's token
   * @return the current user
   */
  DocumentUser setUser(String email, String token);
}
