package org.latexlab.docs.client.commands;

/**
 * A command for saving the current document.
 */
public class CurrentDocumentSaveCommand extends Command {
	
  /**
   * The command's unique id.
   */
  public final static int serialUid = 7;
  
  /**
   * Constructs a command for saving the current document.
   */
  public CurrentDocumentSaveCommand() {
	super("Save current document.");
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
