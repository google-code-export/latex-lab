package org.latexlab.docs.client.commands;

/**
 * A command for an unimplemented behavior.
 */
public class SystemNotImplementedCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 40;
  
  /**
   * Constructs a command for an unimplemented behavior.
   */
  public SystemNotImplementedCommand() {
    super("Not implemented.");
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
