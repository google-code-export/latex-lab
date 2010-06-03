package org.latexlab.docs.client.commands;

/**
 * A command for renaming the current document.
 */
public class CurrentDocumentRenameCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 5;
  
  /**
   * Constructs a command for renaming the current document.
   */
  public CurrentDocumentRenameCommand() {
	super("Rename current document.");
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
