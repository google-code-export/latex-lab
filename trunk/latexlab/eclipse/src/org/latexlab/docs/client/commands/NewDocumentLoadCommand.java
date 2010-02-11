package org.latexlab.docs.client.commands;

public class NewDocumentLoadCommand extends Command {

  public final static int serialUid = 22;
	  
  public NewDocumentLoadCommand() {
	super("Load new document.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
