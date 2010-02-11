package org.latexlab.docs.client.commands;

public class CurrentDocumentLoadContentsCommand extends Command {

  public final static int serialUid = 4;
  
  public CurrentDocumentLoadContentsCommand() {
	super("Load current document contents.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
