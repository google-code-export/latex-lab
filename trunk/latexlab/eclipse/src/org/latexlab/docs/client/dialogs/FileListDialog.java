package org.latexlab.docs.client.dialogs;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.latexlab.docs.client.commands.FileDialogListDocumentsCommand;
import org.latexlab.docs.client.commands.FileDialogStarDocumentCommand;
import org.latexlab.docs.client.commands.FileDialogUnstarDocumentCommand;
import org.latexlab.docs.client.commands.SystemSignOutCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.gdocs.DocumentServiceEntry;
import org.latexlab.docs.client.resources.icons.EditorIcons;

/**
 * A dialog window displaying the user's documents.
 */
public class FileListDialog extends Dialog {

  protected static FileListDialog instance;
  
  public static FileListDialog getInstance(CommandHandler handler) {
    if (instance == null) {
      instance = new FileListDialog();
      instance.addCommandHandler(handler);
    }
    return instance;
  }
  
  private DocumentServiceEntry[] entries;
  private VerticalPanel leftPanel, rightPanel;
  private ScrollPanel linksPanel, documentsPanel;
  private TabBar tabs;
  
  /**
   * Constructs a File List dialog window.
   */
  protected FileListDialog() {
    super("Your Documents", true);
    addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        hide();
      }
    });
    documentsPanel = new ScrollPanel(new VerticalPanel());
    mainPanel.getFlexCellFormatter().setStyleName(1, 0, "");
    leftPanel = new VerticalPanel();
    leftPanel.setWidth("100px");
    leftPanel.setVerticalAlignment(VerticalPanel.ALIGN_TOP);
    rightPanel = new VerticalPanel();
    rightPanel.setVerticalAlignment(VerticalPanel.ALIGN_TOP);
    rightPanel.add(documentsPanel);
    FlexTable table = new FlexTable();
    table.insertRow(0);
    table.insertCell(0, 0);
    table.insertCell(0, 1);
    table.setWidget(0, 0, leftPanel);
    table.getCellFormatter().setVerticalAlignment(1, 0, VerticalPanel.ALIGN_TOP);
    table.setWidget(0, 1, rightPanel);
    table.getCellFormatter().setVerticalAlignment(1, 1, VerticalPanel.ALIGN_TOP);
    setContentWidget(table);
    buildTabBar();
    buildLinks();
    Window.addResizeHandler(new ResizeHandler(){
      public void onResize(ResizeEvent event) {
        resize();
        if (isShowing()) center();
      }
    });
  }
  

  /**
   * Makes the dialog window visible. Resets the tab selection to
   * display the default documents view and triggers a refresh.
   */
  @Override
  public void show() {
    super.show();
    resize();
    if (entries == null) {
      loadEntries();
    }
  }
  
  /**
   * Requests a document list refresh by firing the appropriate command event.
   */
  private void loadEntries() {
    documentsPanel.clear();
    documentsPanel.add(new Label("Loading..."));
    CommandEvent.fire(this, new FileDialogListDocumentsCommand(true));
  }
  
  /**
   * Builds the dialog's tab bar.
   */
  private void buildTabBar() {
    tabs = new TabBar();
    tabs.addTab("Starred Documents");
    tabs.addTab("All Documents");
    tabs.addTab("Current Project");
    tabs.selectTab(0);
    tabs.setWidth("100%");
    tabs.addSelectionHandler(new SelectionHandler<Integer>(){
      public void onSelection(SelectionEvent<Integer> event) {
        showEntries();
      }
    });
    setTopWidget(tabs);
  }
  
  /**
   * Builds the dialog's link panel.
   */
  private void buildLinks() {
    VerticalPanel panel = new VerticalPanel();
    panel.setStylePrimaryName("gdbe-Explorer-Links");
    final Anchor signoutLink = new Anchor("Sign Out");
    signoutLink.addClickHandler(new ClickHandler(){
      public void onClick(ClickEvent event) {
    	CommandEvent.fire(FileListDialog.this, new SystemSignOutCommand("/splash.html"));
      }
    });
    Anchor refreshLink = new Anchor("Refresh");
    refreshLink.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		  rightPanel.clear();
		  CommandEvent.fire(FileListDialog.this, new FileDialogListDocumentsCommand(false));
		  event.preventDefault();
		  event.stopPropagation();
		}
    });
    Anchor newDocumentLink = new Anchor("New Document", "/docs", "_blank");
    Anchor acLink = new Anchor("Google Access Control", "https://www.google.com/accounts/IssuedAuthSubTokens", "_blank");
    Anchor docsLink = new Anchor("Google Documents", "http://docs.google.com/", "_blank");
    Anchor projLink = new Anchor("Project Site", "http://code.google.com/p/gdbe/", "_blank");
    Anchor wikiLink = new Anchor("Project Wiki", "http://code.google.com/p/gdbe/w/list", "_blank");
    Anchor issuesLink = new Anchor("Issue Tracker", "http://code.google.com/p/gdbe/issues/list", "_blank");
    Anchor downloadsLink = new Anchor("Downloads", "http://code.google.com/p/gdbe/downloads/list", "_blank");
    panel.add(new HTML("<br /><b>Actions</b>"));
    panel.add(refreshLink);
    panel.add(newDocumentLink);
    panel.add(signoutLink);
    panel.add(new HTML("<br /><b>Links</b>"));
    panel.add(acLink);
    panel.add(docsLink);
    panel.add(new HTML("<br /><b>Project</b>"));
    panel.add(projLink);
    panel.add(wikiLink);
    panel.add(issuesLink);
    panel.add(downloadsLink);
    linksPanel = new ScrollPanel(panel);
    leftPanel.add(linksPanel);
    Image logo = EditorIcons.icons.Logo().createImage();
    leftPanel.add(logo);
  }
  
  /**
   * Resizes the dialog window to match the browser window size.
   */
  private void resize() {
    int targetHeight = Window.getClientHeight() - 180;
    rightPanel.setPixelSize(500, targetHeight);
    documentsPanel.setPixelSize(500, targetHeight);
    linksPanel.setPixelSize(120, targetHeight);
  }
  
  /**
   * Displays a document list.
   * 
   * @param documents the documents to display
   */
  public void setEntries(DocumentServiceEntry[] entries) {
    this.entries = entries;
  }
  
  public void showEntries() {
	switch(tabs.getSelectedTab()) {
	  case 0:
		showQuickView();
	    break;
	  case 1:
		showExplorerView();
		break;
	  case 2:
		break;
	}
  }
  
  private void showQuickView() {
	rightPanel.clear();
    VerticalPanel panel = new VerticalPanel();
    panel.setStylePrimaryName("gdbe-Explorer-Documents");
    int totalEntries = 0;
    for (DocumentServiceEntry entry : entries) {
      if (!entry.isStarred()) {
        continue;
      }
      final String id = entry.getDocumentId();
      FlexTable docTable = new FlexTable();
      docTable.insertRow(0);
      docTable.insertCell(0, 0);
      docTable.insertCell(0, 1);
      docTable.insertCell(0, 2);
      docTable.insertRow(1);
      docTable.insertCell(1, 0);
      docTable.insertCell(1, 1);
      docTable.insertCell(1, 2);
      docTable.insertRow(2);
      docTable.insertCell(2, 0);
      docTable.insertCell(2, 1);
      docTable.insertCell(2, 2);
      docTable.setStylePrimaryName("gdbe-Explorer-Document");
      Anchor link = new Anchor();
      link.setText(entry.getTitle());
      link.setTarget("_blank");
      link.setHref("/docs?docid=" + entry.getDocumentId());
      ToggleButton star = new ToggleButton(
          EditorIcons.icons.StarEmpty().createImage(),
          EditorIcons.icons.StarFull().createImage());
      star.setDown(entry.isStarred());
      star.addClickHandler(new ClickHandler(){
        public void onClick(ClickEvent event) {
          ToggleButton btn = (ToggleButton)event.getSource();
          if (btn.isDown()) {
            CommandEvent.fire(FileListDialog.this, new FileDialogStarDocumentCommand(id));
          } else {
            CommandEvent.fire(FileListDialog.this, new FileDialogUnstarDocumentCommand(id));
          }
        }
      });
      Label info = new Label(entry.getEdited().toString() + " by " + entry.getEditor());
      info.setStylePrimaryName("gdbe-Explorer-Document-Info");
      docTable.setWidget(0, 0, star);
      docTable.setWidget(0, 1, EditorIcons.icons.Document().createImage());
      docTable.setWidget(0, 2, link);
      docTable.setWidget(1, 2, info);
      if (entry.getFolders().length > 0) {
        Label folder = new Label(entry.getFolders()[0]);
        folder.setStylePrimaryName("gdbe-Explorer-Document-Folder");
        docTable.setWidget(2, 2, folder);
      }
      panel.add(docTable);
      totalEntries++;
    }
    if (totalEntries == 0) {
      panel.add(new Label("You have no starred documents."));
    }
    documentsPanel = new ScrollPanel(panel);
    rightPanel.add(documentsPanel);
    resize();
  }
  
  private void showExplorerView() {
    rightPanel.clear();
    Tree tree = new Tree();
    HashMap<String, ArrayList<DocumentServiceEntry>> hier = getEntryHierarchy();
    if (hier.containsKey("")) {
      for (DocumentServiceEntry entry : hier.get("")) {
    	tree.addItem(getEntryTreeItem(entry, hier));
      }
    } else {
      //no items
    }
    documentsPanel = new ScrollPanel(tree);
    rightPanel.add(documentsPanel);
    resize();
  }
  
  private TreeItem getEntryTreeItem(DocumentServiceEntry entry,
      HashMap<String, ArrayList<DocumentServiceEntry>> hierarchy) {
	HorizontalPanel panel = new HorizontalPanel();
	panel.setSpacing(4);
	String type = entry.getType();
	if (type.equalsIgnoreCase("folder")) {
	  panel.add(EditorIcons.icons.Folder().createImage());
	  panel.add(new Label(entry.getTitle()));
	} else if (type.equalsIgnoreCase("document")) {
	  panel.add(EditorIcons.icons.Document().createImage());
	  Anchor link = new Anchor();
      link.setText(entry.getTitle());
      link.setTarget("_blank");
      link.setHref("/docs?docid=" + entry.getDocumentId());
	  panel.add(link);
	} else {
	  panel.add(EditorIcons.icons.File().createImage());
	  panel.add(new Label(entry.getTitle()));
	}
	TreeItem item = new TreeItem(panel);
	if (type.equalsIgnoreCase("folder")) {
	  if (hierarchy.containsKey(entry.getTitle())) {
	    ArrayList<DocumentServiceEntry> children = hierarchy.get(entry.getTitle());
	    for (DocumentServiceEntry child : children) {
	      item.addItem(getEntryTreeItem(child, hierarchy));
	    }
	  }
	}
	return item;
  }
  
  private HashMap<String, ArrayList<DocumentServiceEntry>> getEntryHierarchy() {
	HashMap<String, ArrayList<DocumentServiceEntry>> map =
	    new HashMap<String, ArrayList<DocumentServiceEntry>>();
	for (DocumentServiceEntry entry : entries) {
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
}
