package org.latexlab.pine.client.dialogs;

import com.google.gwt.core.client.GWT;
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
import com.google.gwt.user.client.ui.VerticalPanel;

import org.latexlab.pine.client.data.DocumentReference;
import org.latexlab.pine.client.events.CommandEvent;
import org.latexlab.pine.client.resources.icons.EditorIconsImageBundle;

/**
 * A dialog window displaying the user's documents.
 */
public class FileListDialog extends BaseDialog {
  
  private boolean loadedOnce = false;
  private VerticalPanel leftPanel, rightPanel;
  private ScrollPanel linksPanel, documentsPanel;
  private TabBar tabs;
  
  /**
   * Constructs a File List dialog window.
   */
  public FileListDialog() {
    super("Your Documents", true);
    addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        hide();
      }
    });
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
    buildTabBar();
    buildLinks();
    setDocuments(new DocumentReference[0]);
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
    if (!loadedOnce) {
      loadDocuments();
    }
  }
  
  /**
   * Requests a document list refresh by firing the appropriate command event.
   */
  private void loadDocuments() {
    documentsPanel.clear();
    documentsPanel.add(new Label("Loading..."));
    switch(tabs.getSelectedTab()) {
      case 0:
        fireOnCommand(new CommandEvent(this, CommandEvent.Command.FILE_DIALOG_LIST_STARRED));
        break;
      case 1:
        fireOnCommand(new CommandEvent(this, CommandEvent.Command.FILE_DIALOG_LIST_ALL));
        break;
    }
  }
  
  /**
   * Builds the dialog's tab bar.
   */
  private void buildTabBar() {
    tabs = new TabBar();
    tabs.addTab("Starred Documents");
    tabs.addTab("All Documents");
    tabs.selectTab(0);
    tabs.setWidth("100%");
    tabs.addSelectionHandler(new SelectionHandler<Integer>(){
      public void onSelection(SelectionEvent<Integer> event) {
        loadDocuments();
      }
    });
    setTopWidget(tabs);
  }
  
  /**
   * Builds the dialog's link panel.
   */
  private void buildLinks() {
    EditorIconsImageBundle editorIcons = (EditorIconsImageBundle)GWT.create(EditorIconsImageBundle.class);
    VerticalPanel panel = new VerticalPanel();
    panel.setStylePrimaryName("gdbe-Explorer-Links");
    final Anchor signoutLink = new Anchor("Sign Out");
    signoutLink.addClickHandler(new ClickHandler(){
      public void onClick(ClickEvent event) {
        fireOnCommand(new CommandEvent(signoutLink, CommandEvent.Command.GENERIC_SIGN_OUT));
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
    Image logo = editorIcons.Logo().createImage();
    leftPanel.add(logo);
  }
  
  /**
   * Resizes the dialog window to match the browser window size.
   */
  private void resize() {
    int targetHeight = Window.getClientHeight() - 180;
    documentsPanel.setPixelSize(500, targetHeight);
    linksPanel.setPixelSize(120, targetHeight);
  }
  
  /**
   * Displays a document list.
   * 
   * @param documents the documents to display
   */
  public void showDocuments(DocumentReference[] documents) {
    setDocuments(documents);
    loadedOnce = true;
  }
  
  private void setDocuments(DocumentReference[] documents) {
    rightPanel.clear();
    EditorIconsImageBundle editorIcons = (EditorIconsImageBundle)GWT.create(EditorIconsImageBundle.class);
    VerticalPanel panel = new VerticalPanel();
    panel.setStylePrimaryName("gdbe-Explorer-Documents");
    for(DocumentReference doc : documents) {
      final String docid = doc.getDocumentId();
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
      link.setText(doc.getTitle());
      link.setTarget("_blank");
      link.setHref("/docs?docid=" + doc.getDocumentId());
      ToggleButton star = new ToggleButton(
          editorIcons.StarEmpty().createImage(),
          editorIcons.StarFull().createImage());
      star.setDown(doc.isStarred());
      star.addClickHandler(new ClickHandler(){
        public void onClick(ClickEvent event) {
          ToggleButton btn = (ToggleButton)event.getSource();
          CommandEvent.Command cmd;
          if (btn.isDown()) {
            cmd = CommandEvent.Command.FILE_DIALOG_STAR_DOCUMENT;
          } else {
            cmd = CommandEvent.Command.FILE_DIALOG_UNSTAR_DOCUMENT;
          }
          fireOnCommand(new CommandEvent(btn, cmd, new String[] { docid }));
        }
      });
      Label info = new Label(doc.getEdited().toString() + " by " + doc.getEditor());
      info.setStylePrimaryName("gdbe-Explorer-Document-Info");
      docTable.setWidget(0, 0, star);
      docTable.setWidget(0, 1, editorIcons.Document().createImage());
      docTable.setWidget(0, 2, link);
      docTable.setWidget(1, 2, info);
      HorizontalPanel folders = new HorizontalPanel();
      for (String folder : doc.getFolders()) {
        Label item = new Label(folder);
        item.setStylePrimaryName("gdbe-Explorer-Document-Folder");
        folders.add(item);
      }
      docTable.setWidget(2, 2, folders);
      panel.add(docTable);
    }
    documentsPanel = new ScrollPanel(panel);
    rightPanel.add(documentsPanel);
    resize();
  }
}
