package org.latexlab.docs.client.commands;

public class SystemRedoCommand extends Command {

  public final static int serialUid = 86;
  
  public SystemRedoCommand() {
	super("Redo action.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
