package org.latexlab.docs.client.commands;

public class FileDialogUnstarDocumentCommand extends Command {

  public final static int serialUid = 31;
  
  private String documentId;
  
  public FileDialogUnstarDocumentCommand(String documentId) {
    super("Unstar document.");
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
