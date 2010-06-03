package org.latexlab.docs.client.commands;

/**
 * A command for toggling line numbers in the text editor.
 */
public class SystemToggleLineNumbersCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 101;
  
  /**
   * Constructs a command for toggling line numbers in the text editor.
   */
  public SystemToggleLineNumbersCommand() {
	super("Update \"Show Line Numbers\" setting.");
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
