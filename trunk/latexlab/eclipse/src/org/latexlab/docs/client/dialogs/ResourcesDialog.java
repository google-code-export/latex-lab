package org.latexlab.docs.client.dialogs;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import org.latexlab.docs.client.commands.ResourceDialogListDocumentsCommand;
import org.latexlab.docs.client.commands.SystemSetResourcesCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.gdocs.DocumentServiceEntry;
import org.latexlab.docs.client.widgets.ExplorerTree;
import org.latexlab.docs.client.widgets.ExplorerTree.ExplorerTreeItem;

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
  private ScrollPanel scroll;
  private ListBox primary;
  private ExplorerTree tree;
  private Button ok, cancel;

  /**
   * Constructs an About dialog window.
   */
  public ResourcesDialog() {
    super("Project Resources", true);
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
    scroll = new ScrollPanel();
    tree = new ExplorerTree(true);
    tree.setSize("100%", "100%");
    tree.addSelectionHandler(new SelectionHandler<TreeItem>() {
		@Override
		public void onSelection(SelectionEvent<TreeItem> event) {
		  ExplorerTreeItem item = (ExplorerTreeItem) event.getSelectedItem();
		  refreshPrimaryList(item.getLabel(), item.getValue(), !item.isSelected());
		}
    });
    scroll.add(tree);
    content.add(scroll);
    primary = new ListBox();
    primary.setWidth("400px");
    primary.addItem("Current Document", "");
    addNamedField(primary, "Primary Resource: ");
    ok = new Button("OK", new ClickHandler(){
      public void onClick(ClickEvent event) {
        hide();
        String primaryResourceId = primary.getValue(primary.getSelectedIndex());
        DocumentServiceEntry primaryResource = null;
        ArrayList<DocumentServiceEntry> resources = getSelectedResources();
        if (!primaryResourceId.equals("")) {
          for (DocumentServiceEntry entry : resources) {
            if (entry.getDocumentId().equals(primaryResourceId)) {
              primaryResource = entry;
              break;
            }
          }
        }
        CommandEvent.fire(ResourcesDialog.this,
            new SystemSetResourcesCommand(resources, primaryResource));
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
  
  private void addNamedField(Widget field, String name) {
	HorizontalPanel row = new HorizontalPanel();
	row.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
	Label label = new Label(name);
	label.setStylePrimaryName("lab-Form-Label");
	field.setStylePrimaryName("lab-Form-Field");
	row.add(label);
	row.add(field);
	content.add(row);
  }
  
  private void refreshPrimaryList(String label, String value, boolean selected) {
	ArrayList<DocumentServiceEntry> entries = tree.getSelectedEntries();
	String selectedValue = primary.getValue(primary.getSelectedIndex());
	primary.clear();
	primary.addItem("Current Document", "");
	for (DocumentServiceEntry entry : entries) {
	  String id = entry.getDocumentId();
	  if (!selected && id.equals(value)) {
	    continue;
	  }
	  primary.addItem(entry.getTitle(), id);
	  if (id.equals(selectedValue)) {
		primary.setSelectedIndex(primary.getItemCount() - 1);
	  }
	}
	if (selected) {
	  primary.addItem(label, value);
	}
  }
  
  /**
   * Resizes the dialog window to match the browser window size.
   */
  private void resize() {
    int targetHeight = Window.getClientHeight() - 180;
    content.setPixelSize(620, targetHeight);
    scroll.setPixelSize(620, targetHeight - 80);
  }

  /**
   * Makes the dialog window visible. Resets the tab selection to
   * display the default documents view and triggers a refresh.
   */
  @Override
  public void show() {
    super.show();
    resize();
    if (tree.getItemCount() == 0) {
      CommandEvent.fire(this, new ResourceDialogListDocumentsCommand(true));
    }
  }

  public void setEntries(DocumentServiceEntry[] entries, String excludeId) {
	tree.setExcluded(excludeId);
	tree.setEntries(entries);
    resize();
  }
  
  private ArrayList<DocumentServiceEntry> getSelectedResources() {
	return tree.getSelectedEntries();
  }
  
}
