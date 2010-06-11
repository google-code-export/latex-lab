package org.latexlab.docs.client.commands;

/**
 * A command for toggling full screen mode.
 */
public class SystemToggleFullScreenCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 10;
  
  /**
   * Constructs a command for toggling full screen mode.
   */
  public SystemToggleFullScreenCommand() {
	super("Display editor in full screen view.");
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
