package org.latexlab.docs.client.gdocs;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * A GWT compatible, RPC serializable, exception type.
 */
public class DocumentServiceException extends Exception implements IsSerializable  {
  
  private static final long serialVersionUID = -5714111001747717593L;

  /**
   * Constructs a GData Service Exception.
   */
  public DocumentServiceException() {
    super();
  }
  
  /**
   * Constructs a GData Service Exception.
   * 
   * @param message the exception message
   */
  public DocumentServiceException(String message){
    super(message);
  }
  
}
