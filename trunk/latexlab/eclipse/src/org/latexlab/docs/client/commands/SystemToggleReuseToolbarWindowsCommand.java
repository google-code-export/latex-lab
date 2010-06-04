package org.latexlab.docs.client.commands;

/**
 * A command for toggling the reuse-toolbar-windows setting.
 */
public class SystemToggleReuseToolbarWindowsCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 90;
  
  /**
   * Constructs a command for toggling the reuse-toolbar-windows setting.
   */
  public SystemToggleReuseToolbarWindowsCommand() {
	super("Update \"Reuse Toolbar Windows\" setting.");
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
