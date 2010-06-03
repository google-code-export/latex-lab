package org.latexlab.docs.client.commands;

/**
 * A command for redoing an operation.
 */
public class SystemRedoCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 86;
  
  /**
   * Constructs a command for redoing an operation.
   */
  public SystemRedoCommand() {
	super("Redo action.");
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
