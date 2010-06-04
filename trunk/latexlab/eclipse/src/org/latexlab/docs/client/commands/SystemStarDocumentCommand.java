package org.latexlab.docs.client.commands;

/**
 * A command for starring a given document.
 */
public class SystemStarDocumentCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 32;
  
  protected String documentId;
  
  /**
   * Constructs a command for starring a given document.
   * 
   * @param documentId the id of the document to star.
   */
  public SystemStarDocumentCommand(String documentId) {
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
   * Retrieves the id of the document to star.
   * 
   * @return the id of the document to star.
   */
  public String getDocumentId() {
    return documentId;
  }

  /**
   * Sets the id of the document to star.
   * 
   * @param documentId the id of the document to star.
   */
  public void setDocumentId(String documentId) {
    this.documentId = documentId;
  }

}
