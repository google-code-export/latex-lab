package org.latexlab.docs.client.commands;

/**
 * A command for toggling text wrapping in the text editor.
 */
public class SystemToggleWrapTextCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 100;
  
  /**
   * Constructs a command for toggling text wrapping in the text editor.
   */
  public SystemToggleWrapTextCommand() {
	super("Update \"Wrap Text\" setting.");
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
