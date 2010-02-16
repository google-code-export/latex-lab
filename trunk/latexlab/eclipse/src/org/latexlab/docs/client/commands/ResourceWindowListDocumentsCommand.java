package org.latexlab.docs.client.commands;

public class ResourceWindowListDocumentsCommand extends Command {

  public final static int serialUid = 85;
  
  public ResourceWindowListDocumentsCommand() {
    super("List documents.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }
  
}
