package org.latexlab.docs.client.commands;

/**
 * A command for reloading the current document's contents.
 */
public class CurrentDocumentReloadCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 112;
  
  /**
   * Constructs a command for reloading the current document's contents.
   */
  public CurrentDocumentReloadCommand() {
	super("Reload current document.");
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
