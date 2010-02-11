package org.latexlab.docs.client.commands;

public class CurrentDocumentCopyCommand extends Command {

  public final static int serialUid = 2;
  
  public CurrentDocumentCopyCommand() {
	super("Save current document as new copy.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
