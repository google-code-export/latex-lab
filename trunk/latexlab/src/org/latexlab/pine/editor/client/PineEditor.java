package org.latexlab.pine.editor.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.latexlab.pine.client.data.DocumentReference;
import org.latexlab.pine.client.data.GDataService;
import org.latexlab.pine.client.data.GDataServiceAsync;
import org.latexlab.pine.client.dialogs.AboutDialog;
import org.latexlab.pine.client.dialogs.ErrorDialog;
import org.latexlab.pine.client.dialogs.FileListDialog;
import org.latexlab.pine.client.dialogs.LoadingDialog;
import org.latexlab.pine.client.events.CommandEvent;
import org.latexlab.pine.client.events.CommandEventListener;
import org.latexlab.pine.client.resources.icons.EditorIconsImageBundle;
import org.latexlab.pine.editor.client.parts.EditorPart;
import org.latexlab.pine.editor.client.parts.HeaderPart;
import org.latexlab.pine.editor.client.parts.MenuPart;
import org.latexlab.pine.editor.client.parts.ToolbarPart;

import java.util.Date;

/**
 * The GDBE document editor module.
 */
public class PineEditor implements EntryPoint, CommandEventListener {

  private final GDataServiceAsync gdataService = GWT.create(GDataService.class);
  private AboutDialog aboutDialog;
  private FileListDialog fileListDialog;
  private LoadingDialog loadingDialog;
  private ErrorDialog errorDialog;
  
  private EditorPart editor;
  private DocumentReference document;
  private FlexTable contentPane;
  private HeaderPart header;
  private MenuPart menu;
  private ToolbarPart toolbar;

  /**
   * Builds and initializes the editor module.
   */
  public void onModuleLoad() {
    contentPane = new FlexTable();
    contentPane.setWidth("100%");
    contentPane.setHeight("100%");
    contentPane.setCellSpacing(0);
    contentPane.setCellPadding(0);
    contentPane.setBorderWidth(0);
    contentPane.insertRow(0);
    contentPane.insertCell(0, 0);
    contentPane.getFlexCellFormatter().setHeight(0, 0, "120px");
    contentPane.insertRow(1);
    contentPane.insertCell(1, 0);
    header = new HeaderPart();
    header.addCommandEventListener(this);
    menu = new MenuPart();
    menu.addCommandEventListener(this);
    toolbar = new ToolbarPart();
    toolbar.addCommandEventListener(this);
    editor = new EditorPart();
    VerticalPanel headerPanel = new VerticalPanel();
    headerPanel.setWidth("100%");
    headerPanel.add(header);
    headerPanel.add(menu);
    headerPanel.add(toolbar);
    HorizontalPanel documentPanel = new HorizontalPanel();
    documentPanel.setVerticalAlignment(HorizontalPanel.ALIGN_TOP);
    documentPanel.setWidth("100%");
    documentPanel.setHeight("100%");
    documentPanel.add(this.editor);
    contentPane.setWidget(0, 0, headerPanel);
    contentPane.setWidget(1, 0, documentPanel);
    RootPanel.get().add(contentPane);
    aboutDialog = new AboutDialog();
    fileListDialog = new FileListDialog();
    fileListDialog.addCommandEventListener(this);
    loadingDialog = new LoadingDialog();
    errorDialog = new ErrorDialog();
    errorDialog.addCommandEventListener(this);
    loadDocument();
  }
  
  /**
   * Extracts the "docid" parameter from the URL querystring and loads the
   * respective document - the document details are loaded first, followed by
   * the document contents.
   * If "docid" is blank then a new, unsaved, document is loaded.
   */
  public void loadDocument() {
    String documentId = Window.Location.getParameter("docid");
    if (documentId == null || documentId.equals("")) {
      onCommand(new CommandEvent(this, CommandEvent.Command.GENERIC_LOAD_NEW_DOCUMENT));
    } else {
      onCommand(new CommandEvent(this, CommandEvent.Command.GENERIC_LOAD_EXISTING_DOCUMENT, new String[] { documentId }));
    }
  }
  
  /**
   * Closes the current window.
   */
  private native void close() /*-{
    $wnd.close();
  }-*/;
  
  /**
   * Sets the currently open document and updates the header info and window title
   * with the respective document information.
   * 
   * @param doc the document which to use
   */
  private void setDocument(DocumentReference doc) {
    document = doc;
    header.setAuthor(doc.getAuthor());
    header.setTitle(doc.getTitle());
    Window.setTitle(doc.getTitle() + " - GDBE");
    if (document.isStored()) {
      header.setInfo(document.getDocumentId(), document.getEdited(), document.getEditor());
    }
  }
  
  /**
   * Shows a status message. If modal, a loading dialog is displayed, otherwise
   * the header status area is used.
   * 
   * @param message the status message to display
   * @param modal whether the status display is modal
   */
  private void showStatus(String message, boolean modal) {
    if (modal) {
      showDialog(loadingDialog);
      loadingDialog.center(message);
    } else {
      header.setStatus(message);
    }
  }
  
  /**
   * Clears and hides any visible status messages.
   */
  private void clearStatus() {
    loadingDialog.hide();
    header.setStatus("");
  }

  /**
   * Handles a command error.
   * 
   * @param error the error exception
   * @param command the command that triggered the error
   * @param alternate the command to trigger on cancel
   * @param retryAttempts the number of times to retry the command before displaying an error dialog
   */
  private void handleError(Throwable error, CommandEvent command, CommandEvent alternate, int retryAttempts){
    clearStatus();
    if (command.getAttemptCount() <= retryAttempts) {
      command.newAttempt();
      onCommand(command);
    } else {
      showDialog(errorDialog);
      errorDialog.showError(error, command, alternate);
    }
  }
  
  /**
   * Hides all open dialogs and shows the specified dialog.
   */
  private void showDialog(DialogBox dialog) {
    if (dialog != loadingDialog && loadingDialog.isShowing()) loadingDialog.hide();
    if (dialog != errorDialog && errorDialog.isShowing()) errorDialog.hide();
    if (dialog != fileListDialog && fileListDialog.isShowing()) fileListDialog.hide();
    if (!dialog.isShowing()) {
      dialog.center();
    }
  }
  
  /**
   * Handles a command event. This is where all the components converge along with
   * the application logic.
   * 
   * @param the command event
   */
  public void onCommand(final CommandEvent e) {
    EditorIconsImageBundle editorIcons;
    switch (e.getCommand()) {
      case FILE_DIALOG_UNSTAR_DOCUMENT:
      case FILE_DIALOG_STAR_DOCUMENT:
        showDialog(fileListDialog);
        gdataService.setDocumentStarred(e.getParameters()[0],
            e.getCommand().equals(CommandEvent.Command.FILE_DIALOG_STAR_DOCUMENT),
            new AsyncCallback<Boolean>() {
          public void onFailure(Throwable caught) {
            handleError(caught, e, null, 1);
          }
          public void onSuccess(Boolean result) {
          }
        });
        break;
      case FILE_DIALOG_LIST_STARRED:
      case FILE_DIALOG_LIST_ALL:
        showDialog(fileListDialog);
        gdataService.getDocuments(
            e.getCommand().equals(CommandEvent.Command.FILE_DIALOG_LIST_STARRED),
            new AsyncCallback<DocumentReference[]>(){
          public void onFailure(Throwable caught) {
            handleError(caught, e, null, 1);
          }
          public void onSuccess(DocumentReference[] result) {
            fileListDialog.showDocuments(result);
          }
        });
        break;
      case GENERIC_START_NEW_DOCUMENT:
        Date now = new Date();
        Window.open("/docs", "Untitled" + now.getTime(), "");
        break;
      case GENERIC_OPEN_EXISTING_DOCUMENT:
        showDialog(fileListDialog);
        break;
      case GENERIC_SAVE_CURRENT_DOCUMENT_AS_NEW_COPY:
        showStatus("Copying document...", true);
        gdataService.saveDocument(null, null, "Copy of " + document.getTitle(), editor.getText(), new AsyncCallback<DocumentReference>() {
          public void onFailure(Throwable caught) {
            handleError(caught, e, null, 0);
          }
          public void onSuccess(DocumentReference result) {
            clearStatus();
            Window.open("/docs?docid=" + result.getDocumentId(), result.getDocumentId(), "");
          }
        });
        break;
      case GENERIC_SAVE_CURRENT_DOCUMENT:
        showStatus("Saving...", false);
        gdataService.saveDocument(document.getDocumentId(), document.getEtag(), document.getTitle(),
            editor.getText(), new AsyncCallback<DocumentReference>() {
          public void onFailure(Throwable caught) {
            handleError(caught, e, null, 0);
          }
          public void onSuccess(DocumentReference result) {
            setDocument(result);
            clearStatus();
          }
        });
        break;
      case GENERIC_SAVE_AND_CLOSE_CURRENT_DOCUMENT:
        showStatus("Saving document...", true);
        gdataService.saveDocument(document.getDocumentId(), document.getEtag(),
            document.getTitle(), editor.getText(), new AsyncCallback<DocumentReference>() {
          public void onFailure(Throwable caught) {
            handleError(caught, e, null, 0);
          }
          public void onSuccess(DocumentReference result) {
            setDocument(result);
            clearStatus();
            close();
          }
        });
        break;
      case GENERIC_CLOSE_CURRENT_DOCUMENT:
        close();
        break;
      case GENERIC_RENAME_CURRENT_DOCUMENT:
        final String newTitle = Window.prompt("Enter new document name:", document.getTitle());
        if (newTitle != null && !newTitle.equals("")) {
          if (document.isStored()) {
            showStatus("Renaming...", false);
            gdataService.renameDocument(document.getDocumentId(), newTitle,
                new AsyncCallback<DocumentReference>(){
              public void onFailure(Throwable caught) {
                handleError(caught, e, null, 0);
              }
              public void onSuccess(DocumentReference result) {
                setDocument(result);
                clearStatus();
              }
            });
          } else {
            document.setTitle(newTitle);
            setDocument(document);
          }
        }
        break;
      case GENERIC_DELETE_CURRENT_DOCUMENT:
        if (Window.confirm("This document will be deleted and closed.")) {
          if (document.isStored()) {
            showStatus("Deleting document...", true);
            gdataService.deleteDocument(document.getDocumentId(), document.getEtag(),
                new AsyncCallback<Boolean>() {
              public void onFailure(Throwable caught) {
                handleError(caught, e, null, 0);
              }
              public void onSuccess(Boolean result) {
                clearStatus();
                close();
              }
            });
          } else {
            close();
          }
        }
        break;
      case GENERIC_LOAD_NEW_DOCUMENT:
        showStatus("Loading document...", true);
        gdataService.getNewDocument(new AsyncCallback<DocumentReference>() {
          public void onFailure(Throwable caught) {
            handleError(caught, e, null, 0);
          }
          public void onSuccess(DocumentReference result) {
            setDocument(result);
            clearStatus();
          }
        });
        break;
      case GENERIC_LOAD_EXISTING_DOCUMENT:
        showStatus("Loading document...", true);
        gdataService.getDocument(e.getParameters()[0],
            new AsyncCallback<DocumentReference>() {
          public void onFailure(Throwable caught) {
            handleError(caught, e, new CommandEvent(errorDialog, CommandEvent.Command.GENERIC_LOAD_NEW_DOCUMENT), 1);
          }
          public void onSuccess(DocumentReference result) {
            setDocument(result);
            clearStatus();
            onCommand(new CommandEvent(this, CommandEvent.Command.GENERIC_LOAD_CURRENT_DOCUMENT_CONTENTS));
          }
        });
        break;
      case GENERIC_LOAD_CURRENT_DOCUMENT_CONTENTS:
        showStatus("Loading document contents...", true);
        gdataService.getDocumentContents(document.getResourceId(),
            new AsyncCallback<String>() {
          public void onFailure(Throwable caught) {
            handleError(caught, e, null, 1);
          }
          public void onSuccess(String result) {
            editor.setText(result);
            clearStatus();
          }
        });
        break;
      case GENERIC_VIEW_REVISION_HISTORY:
        Window.open("http://docs.google.com/Revs?id=" + this.document.getDocumentId() + "&tab=revlist",
            this.document.getDocumentId(), "");
        break;
      case GENERIC_SELECT_ALL:
        this.editor.selectAll();
        break;
      case GENERIC_ABOUT:
        showDialog(aboutDialog);
        break;
      case GENERIC_FULL_SCREEN_VIEW:
        boolean isFullScreen = !header.isVisible();
        editorIcons = (EditorIconsImageBundle)GWT.create(EditorIconsImageBundle.class);
        if (isFullScreen) {
          menu.setMenuItemIcon("Full-screen mode", editorIcons.Blank());
          header.setVisible(true);
          contentPane.getFlexCellFormatter().setHeight(0, 0, "120px");
        } else {
          menu.setMenuItemIcon("Full-screen mode", editorIcons.CheckBlack());
          header.setVisible(false);
          contentPane.getFlexCellFormatter().setHeight(0, 0, "40px");
        }
        break;
      case GENERIC_PRINT:
        Window.print();
        break;
      case GENERIC_VIEW_AS_WEB_PAGE:
        Window.open("http://docs.google.com/View?docID=" + document.getDocumentId() + "&revision=_latest",
            "web" + document.getDocumentId(), "");
        break;
      case GENERIC_SIGN_OUT:
        showStatus("Signing out...", true);
        gdataService.logout(new AsyncCallback<String>(){
          public void onFailure(Throwable caught) {
            handleError(caught, e, null, 1);
          }
          public void onSuccess(String result) {
            Window.Location.replace(result);
            clearStatus();
          }
        });
        break;
      case NONE:
        Window.alert("Not implemented");
        break;
    }
  }

}