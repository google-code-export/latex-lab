package org.latexlab.docs.client.commands;

/**
 * A command for loading an existing document.
 */
public class SystemLoadDocumentCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 15;
  
  protected String documentId;
  
  /**
   * Constructs a command for loading an existing document.
   * 
   * @param documentId the id of the document to load.
   */
  public SystemLoadDocumentCommand(String documentId) {
	super("Load existing document.");
	this.documentId = documentId;
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
   * Retrieves the id of the document to load.
   * 
   * @return the id of the document to load.
   */
  public String getDocumentId() {
    return documentId;
  }

  /**
   * Sets the id of the document to load.
   * 
   * @param documentId the id of the document to load.
   */
  public void setDocumentId(String documentId) {
    this.documentId = documentId;
  }

}
