package org.latexlab.docs.client.gdocs;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DocumentUser implements IsSerializable {

  private String id, name, email;
	
  public DocumentUser() { }

  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
}
