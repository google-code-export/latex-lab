package org.latexlab.docs.client.commands;

/**
 * A command for closing the current document.
 */
public class CurrentDocumentCloseCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 1;
  
  /**
   * Constructs a command for closing the current document.
   */
  public CurrentDocumentCloseCommand() {
    super("Close current document.");
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
