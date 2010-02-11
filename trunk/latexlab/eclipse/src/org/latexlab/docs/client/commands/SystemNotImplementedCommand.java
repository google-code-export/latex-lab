package org.latexlab.docs.client.commands;

public class SystemNotImplementedCommand extends Command {

  public final static int serialUid = 40;
  
  public SystemNotImplementedCommand() {
    super("Not implemented.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
