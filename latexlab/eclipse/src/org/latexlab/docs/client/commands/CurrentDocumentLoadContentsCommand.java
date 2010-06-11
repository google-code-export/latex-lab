package org.latexlab.docs.client.commands;

/**
 * A command for loading the contents of the current document
 */
public class CurrentDocumentLoadContentsCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 4;
  
  /**
   * Constructs a command for loading the current document contents.
   */
  public CurrentDocumentLoadContentsCommand() {
	super("Load current document contents.");
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
