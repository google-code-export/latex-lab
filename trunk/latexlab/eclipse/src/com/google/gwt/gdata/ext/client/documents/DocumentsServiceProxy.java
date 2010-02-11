package com.google.gwt.gdata.ext.client.documents;

import com.google.gwt.gdata.ext.client.GDataServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The GData remote service.
 */
@RemoteServiceRelativePath("gwt-gdata-ext-rpc")
public interface DocumentsServiceProxy extends RemoteService {
  
  /**
   * Retrieves the contents of a document by resource Id.
   * 
   * @param documentId the resource Id
   * @return the document contents
   * @throws GDataServiceException
   */
  String getDocumentContents(String applicationName, String authsubToken,
      String documentId) throws GDataServiceException;
  
  /**
   * Updates the contents of a document.
   * 
   * @param documentId the document Id
   * @param etag the document's version tag
   * @param contents the document contents
   * @return whether the update was successful
   * @throws GDataServiceException
   */
  boolean setDocumentContents(String applicationName, String authsubToken,
      String documentId, String etag, String contents)
  throws GDataServiceException;

}
