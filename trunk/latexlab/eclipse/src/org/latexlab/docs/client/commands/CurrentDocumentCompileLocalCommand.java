package org.latexlab.docs.client.commands;

public class CurrentDocumentCompileLocalCommand extends Command {

  public final static int serialUid = 99;
  
  public CurrentDocumentCompileLocalCommand() {
	super("Compile current document with a local compiler (for testing purposes only).");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
