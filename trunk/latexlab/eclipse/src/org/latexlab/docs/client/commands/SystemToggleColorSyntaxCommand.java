package org.latexlab.docs.client.commands;

public class SystemToggleColorSyntaxCommand extends Command {

  public final static int serialUid = 102;
  
  public SystemToggleColorSyntaxCommand() {
	super("Update \"Color Syntax\" setting.");
  }

  @Override
  public int getCommandId() {
    return serialUid;
  }

}
