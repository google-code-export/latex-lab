package org.latexlab.docs.client.commands;

/**
 * A command for deleting the current document.
 */
public class CurrentDocumentDeleteCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 3;

  /**
   * Constructs a command for deleting the current document.
   */
  public CurrentDocumentDeleteCommand() {
	super("Delete current document.");
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