package org.latexlab.docs.client.commands;

public class CurrentDocumentCompileCommand extends Command {

  public final static int serialUid = 11;
  
  public CurrentDocumentCompileCommand() {
	super("Compile current document.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
