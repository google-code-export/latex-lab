package org.latexlab.docs.client.dialogs;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.latexlab.docs.client.commands.ResourceWindowListDocumentsCommand;
import org.latexlab.docs.client.commands.SystemSetResourcesCommand;
import org.latexlab.docs.client.data.FileSystemEntry;
import org.latexlab.docs.client.data.FileSystemEntry.Types;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;

/**
 * A dialog window displaying details of the application.
 */
public class ResourcesDialog extends Dialog {

  protected static ResourcesDialog instance;
  
  public static ResourcesDialog getInstance(CommandHandler handler) {
    if (instance == null) {
      instance = new ResourcesDialog();
      instance.addCommandHandler(handler);
    }
    return instance;
  }
 
  private VerticalPanel content;
  private Tree tree;
  private Button ok, cancel;
  private HashMap<CheckBox, FileSystemEntry> entries;

  /**
   * Constructs an About dialog window.
   */
  public ResourcesDialog() {
    super("Project Resources", true);
    entries = new HashMap<CheckBox, FileSystemEntry>();
    ClickHandler cancelHandler = new ClickHandler() {
      public void onClick(ClickEvent event) {
        hide();
      }
    };
    addClickHandler(cancelHandler);
    Label info = new Label("The selected files will be compiled with the current document. Selected resources are referenceable from LaTeX by name.");
    setTopWidget(info);
    content = new VerticalPanel();
    content.setWidth("650px");
    ScrollPanel scroll = new ScrollPanel();
    scroll.setHeight("250px");
    tree = new Tree();
    tree.setSize("100%", "100%");
    scroll.add(tree);
    content.add(scroll);
    ok = new Button("OK", new ClickHandler(){
      public void onClick(ClickEvent event) {
        hide();
        ArrayList<FileSystemEntry> resources = getSelectedResources();
        CommandEvent.fire(ResourcesDialog.this, new SystemSetResourcesCommand(resources));
      }
    });
    cancel = new Button("Cancel", cancelHandler);
    HorizontalPanel buttons = new HorizontalPanel();
    buttons.setSpacing(10);
    buttons.add(ok);
    buttons.add(cancel);
    content.add(buttons);
    setContentWidget(content);
  }

  @Override
  public void show() {
    super.show();
    if (tree.getItemCount() == 0) {
      CommandEvent.fire(this, new ResourceWindowListDocumentsCommand());
    }
  }

  public void setEntries(ArrayList<FileSystemEntry> entries) {
	HashMap<String, TreeItem> added = new HashMap<String, TreeItem>();
	HashMap<String, ArrayList<TreeItem>> pending = new HashMap<String, ArrayList<TreeItem>>();
	TreeItem root = new TreeItem("Home");
    for (FileSystemEntry entry : entries) {
      final TreeItem item;
      if (entry.getType() == Types.FOLDER) {
        item = new TreeItem(new Label(entry.getName()));
      } else {
        CheckBox box = new CheckBox(entry.getName());
    	item = new TreeItem(box);
        this.entries.put(box, entry);
      }
      if (entry.getParent() == null) {
        root.addItem(item);
      } else {
    	if (added.containsKey(entry.getParent())) {
    	  added.get(entry.getParent()).addItem(item);
    	} else {
    	  if (!pending.containsKey(entry.getParent())) {
    	    pending.put(entry.getParent(), new ArrayList<TreeItem>());
    	  }
    	  pending.get(entry.getParent()).add(item);
    	}
      }
      if (entry.getType() == Types.FOLDER) {
    	added.put(entry.getName(), item);
    	if (pending.containsKey(entry.getName())) {
    	  for (TreeItem ti : pending.get(entry.getName())) {
    	    item.addItem(ti);
    	  }
    	  pending.remove(entry.getName());
    	}
      }
    }
    tree.clear();
    tree.addItem(root);
    root.setState(true);
  }
  
  private ArrayList<FileSystemEntry> getSelectedResources() {
	ArrayList<FileSystemEntry> selected = new ArrayList<FileSystemEntry>();
	for (CheckBox box : entries.keySet()) {
	  if (box.getValue()) {
	    FileSystemEntry entry = entries.get(box);
	    selected.add(entry);
	  }
    }
    return selected;
  }
  
}
