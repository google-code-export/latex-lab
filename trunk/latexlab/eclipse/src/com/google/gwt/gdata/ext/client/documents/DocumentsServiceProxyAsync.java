package com.google.gwt.gdata.ext.client.documents;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of the GData remote service.
 */
public interface DocumentsServiceProxyAsync {

  /**
   * Retrieves the contents of a document by resource Id.
   * 
   * @param resourceId the resource Id
   * @param callback the success/failure handler
   */
  void getDocumentContents(String applicationName, String authsubToken,
      String resourceId, AsyncCallback<String> callback);
  
  /**
   * Updates the contents of a document.
   * 
   * @param documentId the document Id
   * @param etag the document's version tag
   * @param contents the document contents
   * @param callback the success/failure handler
   */
  void setDocumentContents(String applicationName, String authsubToken,
      String documentId, String etag, String contents,
      AsyncCallback<Boolean> callback);
  
}
