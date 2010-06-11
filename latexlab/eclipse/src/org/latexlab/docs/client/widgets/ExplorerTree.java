package org.latexlab.docs.client.widgets;

import java.util.ArrayList;
import java.util.HashMap;

import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.gdocs.DocumentServiceEntry;

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
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * A tree widget displaying a hierarchical folder view.
 */
public class ExplorerTree extends Tree {

  /**
   * An ExplorerTree document item.
   */
  public static class ExplorerTreeDocumentItem extends ExplorerTreeItem {

	/**
	 * Constructs an ExplorerTree document item.
	 * 
	 * @param label the item's label
	 * @param value the item's value
	 * @param checkable whether the item is checkable
	 */
	public ExplorerTreeDocumentItem(String label, String value, boolean checkable) {
	  this(label, null, value, null, false, checkable, null);
	}
	
	/**
	 * Constructs an ExplorerTree document item.
	 * 
	 * @param label the item's label
	 * @param identifier the item's identifier
	 * @param value the item's value
	 * @param href the item's link
	 * @param starred whether the item is starred
	 * @param checkable whether the item is checkable
	 * @param starHandler the star event handler
	 */
	public ExplorerTreeDocumentItem(String label, String identifier, String value, String href, boolean starred,
	    boolean checkable, StarHandler starHandler) {
	  super(label, identifier, value, href, starred, checkable, starHandler);
	  this.setIcon(Icons.editorIcons.Document());
	}
	
  }
  /**
   * An ExplorerTree file item.
   */
  public static class ExplorerTreeFileItem extends ExplorerTreeItem {

	/**
	 * Constructs an ExplorerTree file item.
	 * 
	 * @param label the item's label
	 * @param value the item's value
	 * @param checkable whether the item is checkable
	 */
	public ExplorerTreeFileItem(String label, String value, boolean checkable) {
	  this(label, null, value, false, checkable, null);
	}
	
	/**
	 * Constructs an ExplorerTree file item.
	 * 
	 * @param label the item's label
	 * @param identifier the item's identifier
	 * @param value the item's value
	 * @param starred whether the item is starred
	 * @param checkable whether the item is checkable
	 * @param starHandler the star event handler
	 */
	public ExplorerTreeFileItem(String label, String identifier, String value, boolean starred,
	    boolean checkable, StarHandler starHandler) {
	  super(label, identifier, value, null, starred, checkable, starHandler);
	  this.setIcon(Icons.editorIcons.File());
	}
	
  }
  /**
   * An ExplorerTree folder item.
   */
  public static class ExplorerTreeFolderItem extends ExplorerTreeItem {

	/**
	 * Constructs an ExplorerTree folder item.
	 * 
	 * @param label the item's label
	 * @param value the item's value
	 * @param checkable whether the item is checkable
	 */
	public ExplorerTreeFolderItem(String label, String value, boolean checkable) {
	  this(label, value, null, checkable);
	}
	
	/**
	 * Constructs an ExplorerTree folder item.
	 * 
	 * @param label the item's label
	 * @param value the item's value
	 * @param href the item's link
	 * @param checkable whether the item is checkable
	 */
	public ExplorerTreeFolderItem(String label, String value, String href, boolean checkable) {
	  super(label, null, value, href, checkable, false, null);
	  this.setIcon(Icons.editorIcons.Folder());
	}
	
  }
  /**
   * The base implementation of an ExplorerTree item.
   */
  public static abstract class ExplorerTreeItem extends TreeItem {
	
	protected CheckBox checkBox;
	protected String label, identifier, value;
	protected HorizontalPanel panel;
	
	/**
	 * Constructs an ExplorerTree item.
	 * 
	 * @param label the item's label
	 * @param identifier the item's identifier
	 * @param value the item's value
	 * @param href the item's link
	 * @param starred whether the item is starred
	 * @param checkable whether the item is checkable
	 * @param starHandler the star event handler
	 */
	public ExplorerTreeItem(String label, String identifier, String value, String href,
		boolean starred, boolean checkable, final StarHandler starHandler) {
	  this.label = label;
      this.identifier = identifier;
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
	  if (identifier != null) {
		Label idnt = new Label("Ref: " + identifier);
		idnt.setStylePrimaryName("identifier");
		VerticalPanel tmp = new VerticalPanel();
		tmp.add(this.panel);
		tmp.add(idnt);
		setWidget(tmp);
	  } else {
	    setWidget(this.panel);
	  }
	}
	
	/**
	 * Retrieves the item's label.
	 * 
	 * @return the item's label
	 */
	public String getLabel() {
	  return this.label;
	}
	
	/**
	 * Retrieves the item's value.
	 * 
	 * @return the item's value
	 */
	public String getValue() {
	  return this.value;
	}
	
	/**
	 * Whether the item is selected.
	 */
	public boolean isSelected() {
	  return this.checkBox != null && this.checkBox.getValue();
	}
	
	/**
	 * Sets the item's icon.
	 * 
	 * @param icon the item's icon
	 */
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
  
  /**
   * Defines a star event handler.
   */
  public static interface StarHandler {
	
	void onStar(String id);
	void onUnstar(String id);
	
  }
  
  private boolean allowSelection, showIdentifiers;
  
  private HashMap<ExplorerTreeItem, DocumentServiceEntry> entries;
  
  private boolean excludeOpen;
  
  private StarHandler starHandler;
  
  /**
   * Constructs an ExplorerTree.
   * 
   * @param allowSelection whether to allow selection of files
   * @param showIdentifiers whether to show file identifiers
   * @param starHandler the star event handler
   */
  public ExplorerTree(boolean allowSelection, boolean showIdentifiers,
	    StarHandler starHandler) {
	this.allowSelection = allowSelection;
	this.starHandler = starHandler;
	this.showIdentifiers = showIdentifiers;
	this.entries = new HashMap<ExplorerTreeItem, DocumentServiceEntry>();
  }
  
  /**
   * Compiles an entry hierarchy.
   * 
   * @param entries the entries
   * @return an entry hierarchy
   */
  private HashMap<String, ArrayList<DocumentServiceEntry>> getEntryHierarchy(
	    DocumentServiceEntry[] entries) {
	HashMap<String, ArrayList<DocumentServiceEntry>> map =
	    new HashMap<String, ArrayList<DocumentServiceEntry>>();
	for (DocumentServiceEntry entry : entries) {
	  if (this.excludeOpen && entry.isOpen()) {
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
  
  /**
   * Builds an tree item for a given entry.
   * 
   * @param entry the entry
   * @param hierarchy the entry hierarchy
   * @return the entry's tree item
   */
  private ExplorerTreeItem getEntryTreeItem(DocumentServiceEntry entry,
    HashMap<String, ArrayList<DocumentServiceEntry>> hierarchy) {
	String type = entry.getType();
	ExplorerTreeItem item;

	if (type.equalsIgnoreCase("folder")) {
	  item = new ExplorerTreeFolderItem(entry.getTitle(), entry.getDocumentId(), false);
	} else {
	  String identifier = null;
	  if (showIdentifiers && entry.getIdentifier() != null && !entry.getIdentifier().equals("")) {
		identifier = entry.getIdentifier();
	  }
	  if (type.equalsIgnoreCase("document")) {
	    item = new ExplorerTreeDocumentItem(entry.getTitle(), identifier, entry.getDocumentId(),
	      "/docs?docid=" + entry.getDocumentId(), entry.isStarred(), this.allowSelection, starHandler);
	  } else {
	    item = new ExplorerTreeFileItem(entry.getTitle(), identifier, entry.getDocumentId(),
	      entry.isStarred(), this.allowSelection, starHandler);
	  };
	}
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
  
  /**
   * Retrieves the selected entries.
   * 
   * @return the selected entries
   */
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

  /**
   * Sets the entries.
   * 
   * @param entries the entries
   */
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
  
  /**
   * Specifies whether to exclude open entries.
   * 
   * @param excludeOpen whether to exclude open entries
   */
  public void setExcludedOpen(boolean excludeOpen) {
	this.excludeOpen = excludeOpen;
  }
}
