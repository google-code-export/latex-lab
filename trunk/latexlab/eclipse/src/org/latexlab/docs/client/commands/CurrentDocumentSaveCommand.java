package org.latexlab.docs.client.commands;

public class CurrentDocumentSaveCommand extends Command {

  public final static int serialUid = 7;
  
  public CurrentDocumentSaveCommand() {
	super("Save current document.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
