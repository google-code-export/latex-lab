package org.latexlab.clsi.client;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.Date;

public class ResourceReference implements IsSerializable {
	
  private String documentId, name;
  private Date modified;
  
  public ResourceReference() { }
  
  public ResourceReference(String documentId, String name, Date modified) {
    this.documentId = documentId;
    this.name = name;
    this.modified = modified;
  }

  public String getDocumentId() {
    return documentId;
  }

  public void setDocumentId(String documentId) {
    this.documentId = documentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }
  
}
