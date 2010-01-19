package org.latexlab.pine.client.data;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * A GWT compatible, RPC serializable, exception type.
 */
public class GDataServiceException extends Exception implements IsSerializable  {
  
  private static final long serialVersionUID = -5714111001747717593L;

  /**
   * Constructs a GData Service Exception.
   */
  public GDataServiceException() {
    super();
  }
  
  /**
   * Constructs a GData Service Exception.
   * 
   * @param message the exception message
   */
  public GDataServiceException(String message){
    super(message);
  }
  
}
