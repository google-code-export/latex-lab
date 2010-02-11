package org.latexlab.docs.client.commands;

public class SystemAboutCommand extends Command {

  public final static int serialUid = 12;
  
  public SystemAboutCommand() {
	super("About Cloudie.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
