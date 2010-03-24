package org.latexlab.docs.client.commands;

public class SystemToggleFullScreenCommand extends Command {

  public final static int serialUid = 10;
  
  public SystemToggleFullScreenCommand() {
	super("Display editor in full screen view.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
