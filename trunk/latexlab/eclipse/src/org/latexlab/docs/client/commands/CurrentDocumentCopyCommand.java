package org.latexlab.docs.client.commands;

/**
 * A command for copying the current document.
 */
public class CurrentDocumentCopyCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 2;

  /**
   * Constructs a command for copying the current document.
   */
  public CurrentDocumentCopyCommand() {
	super("Save current document as new copy.");
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
