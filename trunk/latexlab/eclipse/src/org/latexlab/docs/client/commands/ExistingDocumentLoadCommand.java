package org.latexlab.docs.client.commands;

public class ExistingDocumentLoadCommand extends Command {

  public final static int serialUid = 15;
  
  private String documentId;
  
  public ExistingDocumentLoadCommand(String documentId) {
	super("Load existing document.");
	this.documentId = documentId;
  }

  public String getDocumentId() {
    return documentId;
  }

  public void setDocumentId(String documentId) {
    this.documentId = documentId;
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
