package org.latexlab.docs.client.commands;

/**
 * A command for toggling the syntax coloring in the text editor.
 */
public class SystemToggleColorSyntaxCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 102;
  
  /**
   * Constructs a command for toggling the syntax coloring in the text editor.
   */
  public SystemToggleColorSyntaxCommand() {
	super("Update \"Color Syntax\" setting.");
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
