package org.latexlab.docs.client.commands;

/**
 * A command for undoing an operation.
 */
public class SystemUndoCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 87;
  
  /**
   * Constructs a command for undoing an operation.
   */
  public SystemUndoCommand() {
	super("Undo action.");
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
