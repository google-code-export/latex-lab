package org.latexlab.docs.client.commands;

/**
 * A command for zooming into a preview page.
 */
public class SystemViewPageZoomInCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 117;
  
  /**
   * Constructs a command for zooming into a preview page.
   */
  public SystemViewPageZoomInCommand() {
	super("Zoom in page.");
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
