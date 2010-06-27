package org.latexlab.docs.client.commands;

import java.util.ArrayList;

import org.latexlab.docs.client.gdocs.DocumentServiceEntry;

/**
 * A command for adding compile resources.
 */
public class SystemAddResourcesCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 201;
  
  protected ArrayList<DocumentServiceEntry> resources;
  
  /**
   * Constructs a command for adding compile resources.
   * 
   * @param resources the compile resources to add.
   * @param primaryResource the primary resource.
   */
  public SystemAddResourcesCommand(ArrayList<DocumentServiceEntry> resources) {
    super("Add resources.");
    this.resources = resources;
  }
  
  /**
   * Retrieves the command's unique id.
   * 
   * @return the command's unique id.
   */
  @Override
  public int getCommandId() {
    return serialUid;
  }

  /**
   * Retrieves the compile resources.
   * 
   * @return the compile resources.
   */
  public ArrayList<DocumentServiceEntry> getResources() {
    return resources;
  }

  /**
   * Sets the compile resources.
   * 
   * @param resources the compile resources.
   */
  public void setResources(ArrayList<DocumentServiceEntry> resources) {
    this.resources = resources;
  }
  
}
