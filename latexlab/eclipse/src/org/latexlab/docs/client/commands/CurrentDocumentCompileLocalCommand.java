package org.latexlab.docs.client.commands;

/**
 * A command for locally compiling the current document.
 */
public class CurrentDocumentCompileLocalCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 99;

  /**
   * Constructs a command for locally compiling the current document.
   */
  public CurrentDocumentCompileLocalCommand() {
	super("Compile current document with a local compiler (for testing purposes only).");
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
