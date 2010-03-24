package org.latexlab.docs.client.widgets;

import java.util.ArrayList;
import java.util.HashMap;

import org.latexlab.docs.client.gdocs.DocumentServiceEntry;
import org.latexlab.docs.client.resources.icons.Icons;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

public class ExplorerTree extends Tree {

  private boolean allowSelection;
  private String excluded;
  private StarHandler starHandler;
  private HashMap<ExplorerTreeItem, DocumentServiceEntry> entries;
  
  public ExplorerTree(boolean allowSelection, StarHandler starHandler) {
	this.allowSelection = allowSelection;
	this.starHandler = starHandler;
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
	  item = new ExplorerTreeDocumentItem(entry.getTitle(), entry.getDocumentId(),
	    "/docs?docid=" + entry.getDocumentId(), entry.isStarred(), this.allowSelection, starHandler);
	} else {
	  item = new ExplorerTreeFileItem(entry.getTitle(), entry.getDocumentId(),
	    entry.isStarred(), this.allowSelection, starHandler);
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
	
	public ExplorerTreeItem(String label, String value, String href, boolean starred,
	    boolean checkable, final StarHandler starHandler) {
	  this.label = label;
      this.value = value;
	  this.panel = new HorizontalPanel();
	  this.panel.setSpacing(4);
	  if (checkable) {
		this.checkBox = new CheckBox(label);
		this.panel.add(this.checkBox);
	  } else {
		if (starHandler != null) {
          ToggleButton star = new ToggleButton(
              Icons.editorIcons.StarEmpty().createImage(),
              Icons.editorIcons.StarFull().createImage());
          star.setDown(starred);
          star.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
		      ToggleButton btn = (ToggleButton) event.getSource();
		      if (btn.isDown()) {
		    	starHandler.onStar(ExplorerTreeItem.this.value);
		      } else {
		    	starHandler.onUnstar(ExplorerTreeItem.this.value);
		      }
			}
          });
		  this.panel.add(star);
		}
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
		int i = 0;
		if (this.panel.getWidgetCount() > 1) {
	      i = 1;
		}
        this.panel.insert(icon.createImage(), i);
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
	  super(label, value, href, checkable, false, null);
	  this.setIcon(Icons.editorIcons.Folder());
	}
	
  }
  
  public static class ExplorerTreeDocumentItem extends ExplorerTreeItem {

	public ExplorerTreeDocumentItem(String label, String value, boolean checkable) {
	  this(label, value, null, false, checkable, null);
	}
	
	public ExplorerTreeDocumentItem(String label, String value, String href, boolean starred,
	    boolean checkable, StarHandler starHandler) {
	  super(label, value, href, starred, checkable, starHandler);
	  this.setIcon(Icons.editorIcons.Document());
	}
	
  }

  public static class ExplorerTreeFileItem extends ExplorerTreeItem {

	public ExplorerTreeFileItem(String label, String value, boolean checkable) {
	  this(label, value, false, checkable, null);
	}
	
	public ExplorerTreeFileItem(String label, String value, boolean starred,
	    boolean checkable, StarHandler starHandler) {
	  super(label, value, null, starred, checkable, starHandler);
	  this.setIcon(Icons.editorIcons.File());
	}
	
  }
  
  public static interface StarHandler {
	
	void onStar(String id);
	void onUnstar(String id);
	
  }
}
