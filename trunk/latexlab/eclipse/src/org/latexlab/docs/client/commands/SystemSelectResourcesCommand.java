package org.latexlab.docs.client.commands;

public class SystemSelectResourcesCommand extends Command {

  public final static int serialUid = 88;
  
  public SystemSelectResourcesCommand() {
	super("Select the project resources to use when compiling.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
