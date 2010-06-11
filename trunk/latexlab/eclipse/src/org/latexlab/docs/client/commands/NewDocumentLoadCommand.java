package org.latexlab.docs.client.commands;

/**
 * A command for loading a new document.
 */
public class NewDocumentLoadCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 22;

  /**
   * Constructs a command for loading a new document.
   */
  public NewDocumentLoadCommand() {
	super("Load new document.");
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
