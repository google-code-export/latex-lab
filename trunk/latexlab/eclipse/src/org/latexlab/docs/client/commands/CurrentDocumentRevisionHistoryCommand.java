package org.latexlab.docs.client.commands;

public class CurrentDocumentRevisionHistoryCommand extends Command {

  public final static int serialUid = 11;
  
  public CurrentDocumentRevisionHistoryCommand() {
	super("View revision history.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
