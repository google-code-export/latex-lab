package org.latexlab.docs.client.commands;

public class SystemUploadDocumentsCommand extends Command {

  public final static int serialUid = 97;
  
  public SystemUploadDocumentsCommand() {
	super("Upload documents to your Google Docs account.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
