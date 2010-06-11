package org.latexlab.docs.client.commands;

/**
 * A command for compiling the current document.
 */
public class CurrentDocumentCompileCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 25;
  
  /**
   * Constructs a command for compiling the current document.
   */
  public CurrentDocumentCompileCommand() {
	super("Compile current document.");
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
