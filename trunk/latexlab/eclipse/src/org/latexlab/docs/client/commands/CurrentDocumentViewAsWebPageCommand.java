package org.latexlab.docs.client.commands;

public class CurrentDocumentViewAsWebPageCommand extends Command {

  public final static int serialUid = 9;
  
  public CurrentDocumentViewAsWebPageCommand() {
	super("Save current document.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
