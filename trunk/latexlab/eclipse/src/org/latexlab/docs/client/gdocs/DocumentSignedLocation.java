package org.latexlab.docs.client.gdocs;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Stores document meta-data.
 */
public class DocumentSignedLocation implements IsSerializable {
	
  private String url, authorization;
  
  /**
   * Constructs an empty DocumentReference.
   */
  public DocumentSignedLocation() {}
  
  public DocumentSignedLocation(String url, String authorization) {
	  this.url = url;
	  this.authorization = authorization;
  }
  
  public String getUrl() {
    return url;
  }

  public String getAuthorization() {
    return authorization;
  }
  
}
