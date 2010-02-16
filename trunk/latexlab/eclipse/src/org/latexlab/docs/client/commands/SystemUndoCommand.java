package org.latexlab.docs.client.commands;

public class SystemUndoCommand extends Command {

  public final static int serialUid = 87;
  
  public SystemUndoCommand() {
	super("Undo action.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
