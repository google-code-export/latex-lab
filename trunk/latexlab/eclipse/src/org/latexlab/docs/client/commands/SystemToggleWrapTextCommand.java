package org.latexlab.docs.client.commands;

public class SystemToggleWrapTextCommand extends Command {

  public final static int serialUid = 100;
  
  public SystemToggleWrapTextCommand() {
	super("Update \"Wrap Text\" setting.");
  }

  @Override
  public int getCommandId() {
    return serialUid;
  }

}
