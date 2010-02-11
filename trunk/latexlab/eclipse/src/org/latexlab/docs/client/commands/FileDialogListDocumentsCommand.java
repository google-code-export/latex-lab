package org.latexlab.docs.client.commands;

public class FileDialogListDocumentsCommand extends Command {

  public final static int serialUid = 34;
  
  public FileDialogListDocumentsCommand() {
    super("List documents.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }
  
}
