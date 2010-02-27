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

import org.latexlab.docs.client.commands.ResourceDialogListDocumentsCommand;
import org.latexlab.docs.client.commands.SystemSetResourcesCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.gdocs.DocumentServiceEntry;

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
  private HashMap<CheckBox, DocumentServiceEntry> entries;

  /**
   * Constructs an About dialog window.
   */
  public ResourcesDialog() {
    super("Project Resources", true);
    entries = new HashMap<CheckBox, DocumentServiceEntry>();
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
        ArrayList<DocumentServiceEntry> resources = getSelectedResources();
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
      CommandEvent.fire(this, new ResourceDialogListDocumentsCommand(true));
    }
  }

  public void setEntries(DocumentServiceEntry[] entries, String excludeId) {
	TreeItem root = new TreeItem("Home");
    for (DocumentServiceEntry entry : entries) {
      if (excludeId != null && entry.getDocumentId().equals(excludeId)) {
        continue;
      }
      CheckBox box = new CheckBox(entry.getTitle());
	  TreeItem item = new TreeItem(box);
	  root.addItem(item);
      this.entries.put(box, entry);
    }
    tree.clear();
    tree.addItem(root);
    root.setState(true);
  }
  
  private ArrayList<DocumentServiceEntry> getSelectedResources() {
	ArrayList<DocumentServiceEntry> selected = new ArrayList<DocumentServiceEntry>();
	for (CheckBox box : entries.keySet()) {
	  if (box.getValue()) {
	    DocumentServiceEntry entry = entries.get(box);
	    selected.add(entry);
	  }
    }
    return selected;
  }
  
}
