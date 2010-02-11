package org.latexlab.docs.client.commands;

public class CurrentDocumentRenameCommand extends Command {

  public final static int serialUid = 5;
  
  public CurrentDocumentRenameCommand() {
	super("Rename current document.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
