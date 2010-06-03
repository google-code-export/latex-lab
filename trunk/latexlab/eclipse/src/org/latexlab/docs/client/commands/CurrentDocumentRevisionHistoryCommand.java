package org.latexlab.docs.client.commands;

/**
 * A command for displaying the current document's revision history.
 */
public class CurrentDocumentRevisionHistoryCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 11;
  
  /**
   * Constructs a command for displaying the current document's revision history.
   */
  public CurrentDocumentRevisionHistoryCommand() {
	super("View revision history.");
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
