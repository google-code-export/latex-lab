package org.latexlab.docs.client.commands;

import java.util.ArrayList;

import org.latexlab.docs.client.gdocs.DocumentServiceEntry;

/**
 * A command for specifying the compile resources.
 */
public class SystemSetResourcesCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 91;
  
  protected DocumentServiceEntry primaryResource;
  protected ArrayList<DocumentServiceEntry> resources;
  
  /**
   * Constructs a command for specifying the compile resources.
   * 
   * @param resources the compile resources.
   * @param primaryResource the primary resource.
   */
  public SystemSetResourcesCommand(ArrayList<DocumentServiceEntry> resources,
		  DocumentServiceEntry primaryResource) {
    super("Set resources.");
    this.resources = resources;
    this.primaryResource = primaryResource;
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
   * Retrieves the primary resource.
   * 
   * @return the primary resource.
   */
  public DocumentServiceEntry getPrimaryResource() {
    return primaryResource;
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
   * Sets the primary resource.
   * 
   * @param primaryResource the primary resource.
   */
  public void setPrimaryResource(DocumentServiceEntry primaryResource) {
    this.primaryResource = primaryResource;
  }

  /**
   * Sets the primary resource.
   * 
   * @param resources the primary resource.
   */
  public void setResources(ArrayList<DocumentServiceEntry> resources) {
    this.resources = resources;
  }
  
}
