package org.latexlab.docs.client.commands;

public class FileDialogListStarredDocumentsCommand extends Command {

  public final static int serialUid = 33;
  
  public FileDialogListStarredDocumentsCommand() {
    super("List starred documents.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }
  
}
