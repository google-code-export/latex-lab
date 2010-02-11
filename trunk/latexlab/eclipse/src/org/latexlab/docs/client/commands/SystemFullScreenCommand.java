package org.latexlab.docs.client.commands;

public class SystemFullScreenCommand extends Command {

  public final static int serialUid = 10;
  
  public SystemFullScreenCommand() {
	super("Display editor in full screen view.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
