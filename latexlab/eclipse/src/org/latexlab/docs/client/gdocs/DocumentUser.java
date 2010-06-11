package org.latexlab.docs.client.gdocs;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Defines a document user.
 */
public class DocumentUser implements IsSerializable {

  private String id, name, email, token;
	
  /**
   * Constructs a document user.
   */
  public DocumentUser() { }
  
  /**
   * Retrieves the user's email.
   * 
   * @return the user's email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Retrieves the user's id.
   * 
   * @return the user's id
   */
  public String getId() {
    return id;
  }

  /**
   * Retrieves the user's name.
   * 
   * @return the user's name
   */
  public String getName() {
    return name;
  }

  /**
   * Retrieves the user's token.
   * 
   * @return the user's token
   */
  public String getToken() {
    return token;
  }

  /**
   * Sets the user's email.
   * 
   * @param email the user's email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Sets the user's id.
   * 
   * @param id the user's id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Sets the user's name.
   * 
   * @param name the user's name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the user's token.
   * 
   * @param token the user's token
   */
  public void setToken(String token) {
    this.token = token;
  }
  
}
