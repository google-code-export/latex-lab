package org.latexlab.docs.client.commands;

/**
 * A command for starting a new document.
 */
public class NewDocumentStartCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 23;
	  
  /**
   * Constructs a command for starting a new document.
   */
  public NewDocumentStartCommand() {
	super("Start new document.");
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