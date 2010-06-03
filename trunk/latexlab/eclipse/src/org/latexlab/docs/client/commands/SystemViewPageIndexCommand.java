package org.latexlab.docs.client.commands;

/**
 * A command for displaying the page preview index.
 */
public class SystemViewPageIndexCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 116;
  
  /**
   * Constructs a command for displaying the page preview index.
   */
  public SystemViewPageIndexCommand() {
	super("View page index.");
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
