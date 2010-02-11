package org.latexlab.docs.client.commands;

public class CurrentDocumentCloseCommand extends Command {

  public final static int serialUid = 1;
  
  public CurrentDocumentCloseCommand() {
    super("Close current document.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
