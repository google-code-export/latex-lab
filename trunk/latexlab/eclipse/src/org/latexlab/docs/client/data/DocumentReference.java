package org.latexlab.docs.client.data;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.Date;

/**
 * Stores document meta-data.
 */
public class DocumentReference implements IsSerializable {
  private String documentId, resourceId, title, author, editor, etag;
  private String[] folders;
  private boolean isStarred;
  private Date edited;
  
  /**
   * Constructs an empty DocumentReference.
   */
  public DocumentReference() {}
  
  /**
   * Determines whether the document has been previously saved.
   * 
   * @return whether the document has been saved
   */
  public boolean isStored() {
    return documentId != null && !documentId.equals("");
  }

  /**
   * Retrieves the document's Id.
   * 
   * @return the document Id
   */
  public String getDocumentId() {
    return documentId;
  }

  /**
   * Sets the document's Id.
   * @param documentId the document Id
   */
  public void setDocumentId(String documentId) {
    this.documentId = documentId;
  }

  /**
   * Retrieves the document's resource Id.
   * 
   * @return the resource Id
   */
  public String getResourceId() {
    return resourceId;
  }

  /**
   * Sets the document's resource Id.
   * 
   * @param resourceId the resource Id
   */
  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  /**
   * Retrieves the document's title.
   * 
   * @return the document's title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the document's title.
   * 
   * @param title the document's title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Retrieves the document's author.
   * 
   * @return the document's author
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Sets the document's author.
   * 
   * @param author the document's author
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * Retrieves the document's last editor.
   * 
   * @return the document's editor
   */
  public String getEditor() {
    return editor;
  }

  /**
   * Sets the document's last editor.
   * 
   * @param editor the document's editor
   */
  public void setEditor(String editor) {
    this.editor = editor;
  }

  /**
   * Retrieves the document's version tag.
   * 
   * @return the document's etag
   */
  public String getEtag() {
    return etag;
  }

  /**
   * Sets the document's version tag.
   * 
   * @param etag the document's etag
   */
  public void setEtag(String etag) {
    this.etag = etag;
  }

  /**
   * Retrieves the document's parent folders.
   * 
   * @return the parent folders
   */
  public String[] getFolders() {
    return folders;
  }

  /**
   * Sets the document's parent folders.
   * 
   * @param folders the parent folders
   */
  public void setFolders(String[] folders) {
    this.folders = folders;
  }

  /**
   * Retrieves whether the document is starred.
   * 
   * @return whether the document is starred
   */
  public boolean isStarred() {
    return isStarred;
  }

  /** Sets the document's starred value.
   * 
   * @param isStarred whether the document is starred
   */
  public void setStarred(boolean isStarred) {
    this.isStarred = isStarred;
  }

  /**
   * Retrieves the document's last modified date.
   * 
   * @return the document's last modified date
   */
  public Date getEdited() {
    return edited;
  }

  /**
   * Sets the document's last modified date.
   * 
   * @param edited the document's last modified date
   */
  public void setEdited(Date edited) {
    this.edited = edited;
  }
  
}
