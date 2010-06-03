package org.latexlab.docs.client.gdocs;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * A document's signed content location.
 */
public class DocumentSignedLocation implements IsSerializable {
	
  private String url, authorization;
  
  /**
   * Constructs a signed document location.
   */
  public DocumentSignedLocation() {}
  
  /**
   * Constructs a signed document location.
   * 
   * @param url the document's content URL.
   * @param authorization the document's content URL's authorization header.
   */
  public DocumentSignedLocation(String url, String authorization) {
	  this.url = url;
	  this.authorization = authorization;
  }
  
  /**
   * Retrieves the document's content URL's authorization header.
   * 
   * @return the document's content URL's authorization header.
   */
  public String getAuthorization() {
    return authorization;
  }

  /**
   * Retrieves the document's content URL.
   * 
   * @return the document's content URL.
   */
  public String getUrl() {
    return url;
  }
  
}
