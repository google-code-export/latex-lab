package org.latexlab.docs.client.commands;

/**
 * A command for zooming out of a preview page.
 */
public class SystemViewPageZoomOutCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 118;
  
  /**
   * Constructs a command for zooming out of a preview page.
   */
  public SystemViewPageZoomOutCommand() {
	super("Zoom out page.");
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
