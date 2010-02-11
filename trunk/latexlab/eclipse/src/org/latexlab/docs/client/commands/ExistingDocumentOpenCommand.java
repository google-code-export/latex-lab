package org.latexlab.docs.client.commands;

public class ExistingDocumentOpenCommand extends Command {

  public final static int serialUid = 16;
  
  public ExistingDocumentOpenCommand() {
	super("Open existing document.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
