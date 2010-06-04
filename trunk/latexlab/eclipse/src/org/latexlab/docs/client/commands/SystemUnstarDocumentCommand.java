package org.latexlab.docs.client.commands;

/**
 * A command for unstarring a given document.
 */
public class SystemUnstarDocumentCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 31;
  
  protected String documentId;
  
  /**
   * Constructs a command for unstarring a given document.
   * 
   * @param documentId the id of the document to unstar.
   */
  public SystemUnstarDocumentCommand(String documentId) {
    super("Unstar document.");
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
   * Retrieves the id of the document to unstar.
   * 
   * @return the id of the document to unstar.
   */
  public String getDocumentId() {
    return documentId;
  }

  /**
   * Sets the id of the document to unstar.
   * 
   * @param documentId the id of the document to unstar.
   */
  public void setDocumentId(String documentId) {
    this.documentId = documentId;
  }

}
