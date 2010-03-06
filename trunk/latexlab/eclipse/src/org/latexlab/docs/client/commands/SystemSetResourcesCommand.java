package org.latexlab.docs.client.commands;

import java.util.ArrayList;

import org.latexlab.docs.client.gdocs.DocumentServiceEntry;

public class SystemSetResourcesCommand extends Command {

  public final static int serialUid = 91;
  
  private ArrayList<DocumentServiceEntry> resources;
  private DocumentServiceEntry primaryResource;
  
  public SystemSetResourcesCommand(ArrayList<DocumentServiceEntry> resources,
		  DocumentServiceEntry primaryResource) {
    super("Set resources.");
    this.resources = resources;
    this.primaryResource = primaryResource;
  }
  
  public ArrayList<DocumentServiceEntry> getResources() {
    return resources;
  }

  public void setResources(ArrayList<DocumentServiceEntry> resources) {
    this.resources = resources;
  }

  public DocumentServiceEntry getPrimaryResource() {
    return primaryResource;
  }

  public void setPrimaryResource(DocumentServiceEntry primaryResource) {
    this.primaryResource = primaryResource;
  }

  @Override
  public int getCommandId() {
    return serialUid;
  }
  
}
