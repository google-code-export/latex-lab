package org.latexlab.docs.client.content.dialogs;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import org.latexlab.docs.client.commands.SystemListDocumentsCommand;
import org.latexlab.docs.client.commands.SystemSetResourcesCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.gdocs.DocumentServiceEntry;
import org.latexlab.docs.client.widgets.DynamicDialog;
import org.latexlab.docs.client.widgets.ExplorerTree;
import org.latexlab.docs.client.widgets.ExplorerTree.ExplorerTreeItem;

/**
 * A dialog window for selecting project resources.
 */
public class DynamicResourcesDialog extends DynamicDialog {

  /**
   * Contains Resources Dialog contents.
   */
  protected class ResourcesDialogContents extends Composite {
	
    private VerticalPanel leftPanel, rightPanel;
    private Button ok, cancel;
    private ListBox primary;
    private ScrollPanel scroll;
    private ExplorerTree tree;
  
    /**
     * Constructs a new Resources Dialog contents.
     */
	protected ResourcesDialogContents() {
      addClickHandler(new ClickHandler() {
          public void onClick(ClickEvent event) {
            hide();
          }
      });
      HTML info = new HTML("The selected files will be compiled with the current document. " +
    		"Use the reference value beneath each item when referencing the corresponding file.");
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
      initWidget(table);
      buildLinks();
      buildForm();
	}
	  
	/**
	 * Adds a named field.
	 * 
	 * @param field the field's widget.
	 * @param name the field's label.
	 */
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
	  
	/**
	 * Builds the form.
	 */
	private void buildForm() {
      scroll = new ScrollPanel();
	  tree = new ExplorerTree(true, false, true, null);
	  tree.setSize("100%", "100%");
	  tree.setExcludedOpen(true);
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
	      CommandEvent.fire(DynamicResourcesDialog.this,
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
	  onShowContent = new Runnable() {
		@Override
		public void run() {
		  resize();
		  if (tree.getItemCount() == 0) {
		    loadEntries(true);
		  }
		} 
	  };
	}
	  
	/**
	 * Builds the links side bar.
	 */
	private void buildLinks() {
	  VerticalPanel panel = new VerticalPanel();
	  panel.setStylePrimaryName("lab-Explorer-Links");
	  Anchor refreshLink = new Anchor("Refresh");
	  refreshLink.addClickHandler(new ClickHandler() {
	      @Override
	      public void onClick(ClickEvent event) {
			event.preventDefault();
			event.stopPropagation();
			loadEntries(false);
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
	  
	/**
	 * Retrieves the selected resources.
	 * 
	 * @return the selected resources.
	 */
	private ArrayList<DocumentServiceEntry> getSelectedResources() {
      return tree.getSelectedEntries();
	}
	  
	/**
	 * Causes document entries to be loaded.
	 * 
	 * @param useCache whether to use a document cache, when available.
	 */
	private void loadEntries(boolean useCache) {
	  tree.clear();
	  primary.clear();
	  rightPanel.setStylePrimaryName("lab-Loading");
	  CommandEvent.fire(DynamicResourcesDialog.this, new SystemListDocumentsCommand(useCache,
	      new AsyncCallback<DocumentServiceEntry[]>() {
			  @Override
		      public void onFailure(Throwable caught) {
			  }
			  @Override
			  public void onSuccess(DocumentServiceEntry[] result) {
			    setEntries(result);
			  }
	      }
	  ));
	}
	  
	/**
	 * Refreshes  options in the primary-resource dropdown list field.
	 * 
	 * @param label the option's label
	 * @param value the option's value
	 * @param selected whether the option is selected
	 */
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
	  this.setWidth("720px");
	}

	/**
	 * Displays a list of document entries.
	 * 
	 * @param entries the document entries.
	 * @param excludeId the ID of the document to be excluded, if any.
	 */
	public void setEntries(DocumentServiceEntry[] entries) {
      rightPanel.setStyleName("");
	  tree.setEntries(entries);
	  refreshPrimaryList(null, null, false);
	  resize();
	}
	
  }

  protected static DynamicResourcesDialog instance;

  /**
   * Retrieves the single instance of this class, using asynchronous instantiation.
   * 
   * @param handler the command handler.
   */
  public static DynamicResourcesDialog get(final CommandHandler handler) {
    if (instance == null) {
      instance = new DynamicResourcesDialog();
      instance.addCommandHandler(handler);
    }
    return instance;
  }
  
  protected Runnable onShowContent;
  
  /**
   * Constructs a dialog window for selecting project resources.
   */
  public DynamicResourcesDialog() {
    super("Project Resources", true, "720px", null);
  }

  /**
   * Retrieves the dialog's contents asynchronously.
   * 
   * @param callback the callback carrying the content widget
   */
  @Override
  protected void getContents(final AsyncCallback<Widget> callback) {
    GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		}
		@Override
		public void onSuccess() {
	      callback.onSuccess(new ResourcesDialogContents());
	      center();
		}
    });
  }

  /**
   * Makes the dialog window visible.
   */
  @Override
  public void show() {
    super.show();
    if (onShowContent != null) {
      onShowContent.run();
    }
  }
  
}
