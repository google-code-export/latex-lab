package org.latexlab.docs.client.commands;

import java.util.ArrayList;

import org.latexlab.docs.client.gdocs.DocumentServiceEntry;

public class SystemSetResourcesCommand extends Command {

  public final static int serialUid = 91;
  
  private ArrayList<DocumentServiceEntry> resources;
  
  public SystemSetResourcesCommand(ArrayList<DocumentServiceEntry> resources) {
    super("Set resources.");
    this.resources = resources;
  }
  
  public ArrayList<DocumentServiceEntry> getResources() {
    return resources;
  }

  public void setResources(ArrayList<DocumentServiceEntry> resources) {
    this.resources = resources;
  }

  @Override
  public int getCommandId() {
    return serialUid;
  }
  
}
