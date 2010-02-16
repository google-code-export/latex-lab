package org.latexlab.docs.client.commands;

import java.util.ArrayList;

import org.latexlab.docs.client.data.FileSystemEntry;

public class SystemSetResourcesCommand extends Command {

  public final static int serialUid = 91;
  
  private ArrayList<FileSystemEntry> resources;
  
  public SystemSetResourcesCommand(ArrayList<FileSystemEntry> resources) {
    super("Set resources.");
    this.resources = resources;
  }
  
  public ArrayList<FileSystemEntry> getResources() {
    return resources;
  }

  public void setResources(ArrayList<FileSystemEntry> resources) {
    this.resources = resources;
  }

  @Override
  public int getCommandId() {
    return serialUid;
  }
  
}
