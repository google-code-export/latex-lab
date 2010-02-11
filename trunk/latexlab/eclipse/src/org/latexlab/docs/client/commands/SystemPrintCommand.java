package org.latexlab.docs.client.commands;

public class SystemPrintCommand extends Command {

  public final static int serialUid = 10;
  
  public SystemPrintCommand() {
	super("Print contents.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
