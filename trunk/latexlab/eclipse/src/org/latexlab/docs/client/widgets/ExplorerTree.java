package org.latexlab.docs.client.widgets;

import java.util.ArrayList;
import java.util.HashMap;

import org.latexlab.docs.client.gdocs.DocumentServiceEntry;
import org.latexlab.docs.client.resources.icons.EditorIcons;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

public class ExplorerTree extends Tree {

  private boolean allowSelection;
  private String excluded;
  private HashMap<ExplorerTreeItem, DocumentServiceEntry> entries;
  
  public ExplorerTree(boolean allowSelection) {
	this.allowSelection = allowSelection;
	this.entries = new HashMap<ExplorerTreeItem, DocumentServiceEntry>();
  }
  
  public void setExcluded(String excluded) {
	this.excluded = excluded;
  }
  
  public void setEntries(DocumentServiceEntry[] entries) {
	this.entries.clear();
	this.clear();
    HashMap<String, ArrayList<DocumentServiceEntry>> hier = getEntryHierarchy(entries);
    if (hier.containsKey("")) {
      for (DocumentServiceEntry entry : hier.get("")) {
    	ExplorerTreeItem item = getEntryTreeItem(entry, hier);
    	addItem(item);
      }
    } else {
      //no items
    }
  }
  
  public ArrayList<DocumentServiceEntry> getSelectedEntries() {
	ArrayList<DocumentServiceEntry> selected = new ArrayList<DocumentServiceEntry>();
	for (ExplorerTreeItem item : entries.keySet()) {
	  if (item.isSelected()) {
	    DocumentServiceEntry entry = entries.get(item);
	    selected.add(entry);
	  }
    }
    return selected;
  }
  
  private ExplorerTreeItem getEntryTreeItem(DocumentServiceEntry entry,
    HashMap<String, ArrayList<DocumentServiceEntry>> hierarchy) {
	String type = entry.getType();
	ExplorerTreeItem item;
	if (type.equalsIgnoreCase("folder")) {
	  item = new ExplorerTreeFolderItem(entry.getTitle(), entry.getDocumentId(), false);
	} else if (type.equalsIgnoreCase("document")) {
	  item = new ExplorerTreeDocumentItem(entry.getTitle(), entry.getDocumentId(), "/docs?docid=" + entry.getDocumentId(), this.allowSelection);
	} else {
	  item = new ExplorerTreeFileItem(entry.getTitle(), entry.getDocumentId(), this.allowSelection);
	};
	if (type.equalsIgnoreCase("folder")) {
	  if (hierarchy.containsKey(entry.getTitle())) {
	    ArrayList<DocumentServiceEntry> children = hierarchy.get(entry.getTitle());
	    for (DocumentServiceEntry child : children) {
	      ExplorerTreeItem childItem = getEntryTreeItem(child, hierarchy);
	      item.addItem(childItem);
	      this.entries.put(childItem, child);
	    }
	  }
	}
	this.entries.put(item, entry);
	return item;
  }
  
  private HashMap<String, ArrayList<DocumentServiceEntry>> getEntryHierarchy(
	    DocumentServiceEntry[] entries) {
	HashMap<String, ArrayList<DocumentServiceEntry>> map =
	    new HashMap<String, ArrayList<DocumentServiceEntry>>();
	for (DocumentServiceEntry entry : entries) {
	  if (this.excluded != null && this.excluded.equals(entry.getDocumentId())) {
		continue;
	  }
	  String parent = "";
	  if (entry.getFolders().length > 0) {
		parent = entry.getFolders()[0];
	  }
	  if (!map.containsKey(parent)) {
		map.put(parent, new ArrayList<DocumentServiceEntry>());
	  }
	  map.get(parent).add(entry);
	}
	return map;
  }
  
  public static abstract class ExplorerTreeItem extends TreeItem {
	
	protected HorizontalPanel panel;
	protected String label, value;
	protected CheckBox checkBox;
	
	public ExplorerTreeItem(String label, String value, String href, boolean checkable) {
	  this.label = label;
      this.value = value;
	  this.panel = new HorizontalPanel();
	  this.panel.setSpacing(4);
	  if (checkable) {
		this.checkBox = new CheckBox(label);
		this.panel.add(this.checkBox);
	  } else {
	    if (href == null) {
		  this.panel.add(new Label(label));
	    } else {
		  Anchor link = new Anchor(label);
          link.setTarget("_blank");
          link.setHref(href);
		  this.panel.add(link);
	    }
	  }
	  setWidget(this.panel);
	}
	
	public boolean isSelected() {
	  return this.checkBox != null && this.checkBox.getValue();
	}
	
	public String getValue() {
	  return this.value;
	}
	
	public String getLabel() {
	  return this.label;
	}
	
	public void setIcon(AbstractImagePrototype icon) {
	  if (this.checkBox == null) {
        this.panel.insert(icon.createImage(), 0);
	  } else {
	    String html = icon.getHTML() + " " + this.checkBox.getText();
	    this.checkBox.setHTML(html);
	  }
	}
	
  }
  
  public static class ExplorerTreeFolderItem extends ExplorerTreeItem {

	public ExplorerTreeFolderItem(String label, String value, boolean checkable) {
	  this(label, value, null, checkable);
	}
	
	public ExplorerTreeFolderItem(String label, String value, String href, boolean checkable) {
	  super(label, value, href, checkable);
	  this.setIcon(EditorIcons.icons.Folder());
	}
	
  }
  
  public static class ExplorerTreeDocumentItem extends ExplorerTreeItem {

	public ExplorerTreeDocumentItem(String label, String value, boolean checkable) {
	  this(label, value, null, checkable);
	}
	
	public ExplorerTreeDocumentItem(String label, String value, String href, boolean checkable) {
	  super(label, value, href, checkable);
	  this.setIcon(EditorIcons.icons.Document());
	}
	
  }

  public static class ExplorerTreeFileItem extends ExplorerTreeItem {

	public ExplorerTreeFileItem(String label, String value, boolean checkable) {
	  this(label, value, null, checkable);
	}
	
	public ExplorerTreeFileItem(String label, String value, String href, boolean checkable) {
	  super(label, value, href, checkable);
	  this.setIcon(EditorIcons.icons.File());
	}
	
  }
}
