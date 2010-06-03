package org.latexlab.docs.client.commands;

/**
 * A command for uploading documents.
 */
public class SystemUploadDocumentsCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 97;
  
  /**
   * Constructs a command for uploading documents.
   */
  public SystemUploadDocumentsCommand() {
	super("Upload documents to your Google Docs account.");
  }

  /**
   * Retrieves the command's unique id.
   * 
   * @return the command's unique id.
   */ 
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
