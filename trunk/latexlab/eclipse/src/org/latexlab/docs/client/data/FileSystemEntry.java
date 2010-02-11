package org.latexlab.docs.client.data;

import com.google.gwt.gdata.client.atom.Category;
import com.google.gwt.gdata.ext.client.documents.DocumentEntry;

import java.util.Comparator;
import java.util.Date;

public class FileSystemEntry {

  private boolean stored = false;
  private boolean starred = false;
  private String id, name, parent, modifiedBy;
  private Types type;
  private Date created, modified;
  
  public FileSystemEntry() {
    
  }
  
  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }
  
  public String getParent() {
    return parent;
  }
  
  public Types getType() {
    return type;
  }
  
  public boolean isStarred() {
    return starred;
  }
  
  public boolean isStored() {
    return stored;
  }

  public void setId(String id) {
    this.id = id;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setParent(String parent) {
    this.parent = parent;
  }
  
  public void setStarred(boolean starred) {
    this.starred = starred;
  }
  
  public void setStored(boolean stored) {
    this.stored = stored;
  }
  
  public void setType(Types type) {
    this.type = type;
  }
  
  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getModified() {
    return modified;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  public enum Types {
    FILE("File"),
    FOLDER("Folder");
    
    private String label;
    
    private Types(String label) {
      this.label = label;
    }
    
    public String getLabel() {
      return label;
    }
  }
  
  public static FileSystemEntry fromGDataEntry(DocumentEntry doc) {
    FileSystemEntry entry = new FileSystemEntry();
    entry.setId(doc.getResourceId().getValue());
    entry.setName(doc.getTitle().getText());
    entry.setCreated(doc.getPublished().getValue().getDate());
    entry.setModified(doc.getUpdated().getValue().getDate());
    entry.setModifiedBy("author");
    entry.setType(Types.FILE);
    entry.setStored(true);
    for (Category cat : doc.getCategories()) {
      String label = cat.getLabel();
      String scheme = cat.getScheme();
      String term = cat.getTerm();
      if (scheme.endsWith("#kind")) {
        if (term.endsWith("#folder")) {
          entry.setType(Types.FOLDER);
        }
      } else if(scheme.startsWith(
          "http://schemas.google.com/docs/2007/folders/")) {
        entry.setParent(label);
      } else if(scheme.equals("http://schemas.google.com/g/2005/labels")){
        if (term.equalsIgnoreCase("http://schemas.google.com/g/2005/labels#starred")) {
          entry.setStarred(true);
        }
      }
    }
    return entry;
  }
  
  public static class FileSystemEntryComparator implements Comparator<FileSystemEntry> {
    
    public FileSystemEntryComparator() {};
    
    public int compare(FileSystemEntry arg0, FileSystemEntry arg1) {
      if (arg0.getType() != arg1.getType()) {
        if (arg0.getType() == Types.FOLDER) {
          return -1;
        } else {
          return 1;
        }
      } else {
        return arg0.getName().compareTo(arg1.getName());
      }
    }
    
  }
  
}
