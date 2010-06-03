package org.latexlab.docs.client.commands;

/**
 * A command for opening a document for editing.
 */
public class SystemOpenDocumentCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 35;
  
  protected String documentId;

  /**
   * Constructs a command for opening a document for editing.
   * 
   * @param documentId the id of the document to open.
   */
  public SystemOpenDocumentCommand(String documentId) {
    super("Open document.");
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
   * Retrieves the id of the document to open.
   * 
   * @return the id of the document to open.
   */
  public String getDocumentId() {
    return documentId;
  }

  /**
   * Sets the id of the document to open.
   * 
   * @param documentId the id of the document to open.
   */
  public void setDocumentId(String documentId) {
    this.documentId = documentId;
  }
  
}
