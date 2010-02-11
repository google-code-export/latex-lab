package org.latexlab.docs.client.commands;

public class CurrentDocumentDeleteCommand extends Command {

  public final static int serialUid = 3;
  
  public CurrentDocumentDeleteCommand() {
	super("Delete current document.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}