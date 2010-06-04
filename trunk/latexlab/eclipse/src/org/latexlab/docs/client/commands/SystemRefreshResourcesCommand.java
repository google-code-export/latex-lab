package org.latexlab.docs.client.commands;

/**
 * A command for refreshing the compile resources.
 */
public class SystemRefreshResourcesCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 125;
  
  /**
   * Constructs a command for refreshing the compile resources.
   */
  public SystemRefreshResourcesCommand() {
    super("Refresh resources.");
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
