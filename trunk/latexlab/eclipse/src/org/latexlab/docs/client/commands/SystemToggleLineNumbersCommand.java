package org.latexlab.docs.client.commands;

public class SystemToggleLineNumbersCommand extends Command {

  public final static int serialUid = 101;
  
  public SystemToggleLineNumbersCommand() {
	super("Update \"Show Line Numbers\" setting.");
  }

  @Override
  public int getCommandId() {
    return serialUid;
  }

}
