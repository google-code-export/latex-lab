package org.latexlab.docs.client.commands;

/**
 * A command signaling that the current document has changed.
 */
public class CurrentDocumentChangedCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 171;
  
  /**
   * Constructs a command for signaling a document change.
   */
  public CurrentDocumentChangedCommand() {
	super("Set current document as changed.");
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
