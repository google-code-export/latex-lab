package org.latexlab.docs.client.commands;

public class CurrentDocumentSaveAndCloseCommand extends Command {

  public final static int serialUid = 6;
  
  public CurrentDocumentSaveAndCloseCommand() {
	super("Save and close current document.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
