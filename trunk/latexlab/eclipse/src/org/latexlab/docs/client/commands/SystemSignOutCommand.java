package org.latexlab.docs.client.commands;

/**
 * A command for signing out.
 */
public class SystemSignOutCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 24;
	
  /**
   * Constructs a command for signing out.
   */
  public SystemSignOutCommand() {
	super("Sign out.");
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
