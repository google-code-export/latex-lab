package org.latexlab.docs.client.commands;

public class SystemReuseToolbarWindowsCommand extends Command {

  public final static int serialUid = 90;
  
  public SystemReuseToolbarWindowsCommand() {
	super("Update \"Reuse Toolbar Windows\" setting.");
  }

  @Override
  public int getCommandId() {
    return serialUid;
  }

}
