package org.latexlab.docs.client.commands;

public class NewDocumentStartCommand extends Command {

  public final static int serialUid = 23;
	  
  public NewDocumentStartCommand() {
	super("Start new document.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}