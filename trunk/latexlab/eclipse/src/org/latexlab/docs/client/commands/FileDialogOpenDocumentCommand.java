package org.latexlab.docs.client.commands;

public class FileDialogOpenDocumentCommand extends Command {

  public final static int serialUid = 35;
  
  private String documentId;

  public FileDialogOpenDocumentCommand(String documentId) {
    super("Open document.");
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
