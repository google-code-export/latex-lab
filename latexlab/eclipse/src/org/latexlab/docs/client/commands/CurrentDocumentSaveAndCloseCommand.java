package org.latexlab.docs.client.commands;

/**
 * A command for saving and closing the current document.
 */
public class CurrentDocumentSaveAndCloseCommand extends Command {
	
  /**
   * The command's unique id.
   */
  public final static int serialUid = 6;
  
  /**
   * Constructs a command for saving and closing the current document.
   */
  public CurrentDocumentSaveAndCloseCommand() {
	super("Save and close current document.");
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
