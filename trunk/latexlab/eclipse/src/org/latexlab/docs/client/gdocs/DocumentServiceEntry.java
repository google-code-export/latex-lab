package org.latexlab.docs.client.gdocs;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.Date;

/**
 * A serializable class defining a document entry.
 */
public class DocumentServiceEntry implements IsSerializable {
	
  private Date created, edited;
  private String documentId, resourceId, title, identifier, type, author, editor, etag, contentLink, contentType;
  private String[] folders;
  private boolean isStarred, isDirty, isSaving, isCompiling;
  
  /**
   * Constructs an empty document entry.
   */
  public DocumentServiceEntry() {}
  
  /**
   * Determines whether the current entry is the same as a given entry.
   * 
   * @param entry the entry to compare to
   * @return whether the current entry is the same as the specified entry
   */
  public boolean equals(DocumentServiceEntry entry) {
	if (documentId == null || entry == null) {
	  return false;
	} else {
	  return documentId.equals(entry.documentId) ||
	  	resourceId.equals(entry.resourceId);
	}
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
   * Retrieves the document's content URL.
   * 
   * @return the document's content URL.
   */
  public String getContentLink() {
    return contentLink;
  }

  /**
   * Retrieves the document's content type.
   * 
   * @return the document's content type.
   */
  public String getContentType() {
    return contentType;
  }

  /**
   * Retrieves the document's create date.
   * 
   * @return the document's create date
   */
  public Date getCreated() {
    return created;
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
   * Retrieves the document's last modified date.
   * 
   * @return the document's last modified date
   */
  public Date getEdited() {
    return edited;
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
   * Retrieves the document's version tag.
   * 
   * @return the document's etag
   */
  public String getEtag() {
    return etag;
  }

  /**
   * Retrieves the document's parent folders.
   * 
   * @return the parent folders
   */
  public String[] getFolders() {
    return folders;
  }

  public String getIdentifier() {
    return identifier;
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
   * Retrieves the document's title.
   * 
   * @return the document's title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Retrieves the document's type.
   * 
   * @return the document's type.
   */
  public String getType() {
    return type;
  }

  /**
   * Whether the document is undergoing a compile operation.
   * 
   * @return whether the document is undergoing a compile operation.
   */
  public boolean isCompiling() {
    return isCompiling;
  }

  /**
   * Whether the document has unsaved changes.
   * 
   * @return whether the document has unsaved changes.
   */
  public boolean isDirty() {
    return isDirty;
  }

  /**
   * Whether the document is undergoing a save operation.
   * 
   * @return whether the document is undergoing a save operation.
   */
  public boolean isSaving() {
    return isSaving;
  }

  /**
   * Retrieves whether the document is starred.
   * 
   * @return whether the document is starred
   */
  public boolean isStarred() {
    return isStarred;
  }

  /**
   * Determines whether the document has been previously saved.
   * 
   * @return whether the document has been saved
   */
  public boolean isStored() {
    return documentId != null && !documentId.equals("");
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
   * Specifies whether the document is undergoing a compile operation.
   * 
   * @param isCompiling whether the document is undergoing a compile operation.
   */
  public void setCompiling(boolean isCompiling) {
    this.isCompiling = isCompiling;
  }

  /**
   * Sets the document's content URL.
   * 
   * @param editLink the document's content URL.
   */
  public void setContentLink(String editLink) {
    this.contentLink = editLink;
  }

  /**
   * Sets the document's content type.
   * 
   * @param contentType the document's content type.
   */
  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  /**
   * Sets the document's created date.
   * 
   * @param edited the document's created date
   */
  public void setCreated(Date created) {
    this.created = created;
  }

  /**
   * Specifies whether the document has unsaved changes.
   * 
   * @param isDirty whether the document has unsaved changes.
   */
  public void setDirty(boolean isDirty) {
    this.isDirty = isDirty;
  }

  /**
   * Sets the document's Id.
   * @param documentId the document Id
   */
  public void setDocumentId(String documentId) {
    this.documentId = documentId;
  }

  /**
   * Sets the document's last modified date.
   * 
   * @param edited the document's last modified date
   */
  public void setEdited(Date edited) {
    this.edited = edited;
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
   * Sets the document's version tag.
   * 
   * @param etag the document's etag
   */
  public void setEtag(String etag) {
    this.etag = etag;
  }

  /**
   * Sets the document's parent folders.
   * 
   * @param folders the parent folders
   */
  public void setFolders(String[] folders) {
    this.folders = folders;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
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
   * Specifies whether the document is undergoing a save operation.
   * 
   * @param isSaving whether the document is undergoing a save operation.
   */
  public void setSaving(boolean isSaving) {
    this.isSaving = isSaving;
  }

  /** Sets the document's starred value.
   * 
   * @param isStarred whether the document is starred
   */
  public void setStarred(boolean isStarred) {
    this.isStarred = isStarred;
  }

  /**
   * Sets the document's title.
   * 
   * @param title the document's title
   */
  public void setTitle(String title) {
    this.title = title;
    this.identifier = title;
  }

  /**
   * Sets the document's type.
   * 
   * @param type the document's type.
   */
  public void setType(String type) {
    this.type = type;
  }
  
}
