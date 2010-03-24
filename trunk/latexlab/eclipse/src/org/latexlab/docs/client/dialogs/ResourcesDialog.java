package org.latexlab.docs.client.dialogs;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
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
  
  public static ResourcesDialog get(CommandHandler handler) {
    if (instance == null) {
      instance = new ResourcesDialog();
      instance.addCommandHandler(handler);
    }
    return instance;
  }

  private VerticalPanel leftPanel, rightPanel;
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
    leftPanel = new VerticalPanel();
    leftPanel.setWidth("100px");
    leftPanel.setVerticalAlignment(VerticalPanel.ALIGN_TOP);
    rightPanel = new VerticalPanel();
    rightPanel.setVerticalAlignment(VerticalPanel.ALIGN_TOP);
    FlexTable table = new FlexTable();
    table.insertRow(0);
    table.insertCell(0, 0);
    table.insertCell(0, 1);
    table.setWidget(0, 0, leftPanel);
    table.getCellFormatter().setVerticalAlignment(1, 0, VerticalPanel.ALIGN_TOP);
    table.setWidget(0, 1, rightPanel);
    table.getCellFormatter().setVerticalAlignment(1, 1, VerticalPanel.ALIGN_TOP);
    setContentWidget(table);
    buildLinks();
    buildForm();
  }
  
  private void buildForm() {scroll = new ScrollPanel();
    tree = new ExplorerTree(true, null);
    tree.setSize("100%", "100%");
    tree.addSelectionHandler(new SelectionHandler<TreeItem>() {
		@Override
		public void onSelection(SelectionEvent<TreeItem> event) {
		  ExplorerTreeItem item = (ExplorerTreeItem) event.getSelectedItem();
		  refreshPrimaryList(item.getLabel(), item.getValue(), !item.isSelected());
		}
    });
    scroll.add(tree);
    rightPanel.add(scroll);
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
    cancel = new Button("Cancel", new ClickHandler() {
        public void onClick(ClickEvent event) {
            hide();
          }
        }
    );
    HorizontalPanel buttons = new HorizontalPanel();
    buttons.setSpacing(10);
    buttons.add(ok);
    buttons.add(cancel);
    rightPanel.add(buttons);
  }
  
  private void buildLinks() {
    VerticalPanel panel = new VerticalPanel();
    panel.setStylePrimaryName("lab-Explorer-Links");
    Anchor refreshLink = new Anchor("Refresh");
    refreshLink.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		  tree.clear();
		  primary.clear();
		  CommandEvent.fire(ResourcesDialog.this, new ResourceDialogListDocumentsCommand(false));
		  event.preventDefault();
		  event.stopPropagation();
		}
    });
    Anchor newDocumentLink = new Anchor("New Document", "/docs", "_blank");
    Anchor acLink = new Anchor("Google Access Control", "https://www.google.com/accounts/IssuedAuthSubTokens", "_blank");
    Anchor docsLink = new Anchor("Google Documents", "http://docs.google.com/", "_blank");
    panel.add(new HTML("<br /><b>Actions</b>"));
    panel.add(refreshLink);
    panel.add(newDocumentLink);
    panel.add(new HTML("<br /><b>Links</b>"));
    panel.add(acLink);
    panel.add(docsLink);
    ScrollPanel linksPanel = new ScrollPanel(panel);
    leftPanel.add(linksPanel);
    Window.addResizeHandler(new ResizeHandler(){
        public void onResize(ResizeEvent event) {
          resize();
          if (isShowing()) center();
        }
    });
  }
  
  private void addNamedField(Widget field, String name) {
	HorizontalPanel row = new HorizontalPanel();
	row.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
	Label label = new Label(name);
	label.setStylePrimaryName("lab-Form-Label");
	field.setStylePrimaryName("lab-Form-Field");
	row.add(label);
	row.add(field);
	rightPanel.add(row);
  }
  
  private void refreshPrimaryList(String label, String value, boolean selected) {
	ArrayList<DocumentServiceEntry> entries = tree.getSelectedEntries();
	String selectedValue = "";
	if (primary.getSelectedIndex() >= 0) {
	  selectedValue = primary.getValue(primary.getSelectedIndex());
	}
	primary.clear();
	primary.addItem("Current Document", "");
	if (label != null && value != null) {
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
  }
  
  /**
   * Resizes the dialog window to match the browser window size.
   */
  private void resize() {
    int targetHeight = Window.getClientHeight() - 180;
    rightPanel.setPixelSize(600, targetHeight);
    scroll.setPixelSize(600, targetHeight - 80);
    leftPanel.setPixelSize(120, targetHeight);
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
	refreshPrimaryList(null, null, false);
    resize();
  }
  
  private ArrayList<DocumentServiceEntry> getSelectedResources() {
	return tree.getSelectedEntries();
  }
  
}
