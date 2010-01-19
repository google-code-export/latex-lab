package org.latexlab.pine.explorer.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

import org.latexlab.pine.client.data.DocumentReference;
import org.latexlab.pine.client.data.GDataService;
import org.latexlab.pine.client.data.GDataServiceAsync;
import org.latexlab.pine.client.dialogs.ErrorDialog;
import org.latexlab.pine.client.dialogs.FileListDialog;
import org.latexlab.pine.client.dialogs.LoadingDialog;
import org.latexlab.pine.client.events.CommandEvent;
import org.latexlab.pine.client.events.CommandEventListener;
import org.latexlab.pine.client.resources.icons.EditorIconsImageBundle;

public class PineExplorer implements EntryPoint, CommandEventListener {

  private final GDataServiceAsync gdataService = GWT.create(GDataService.class);
  private HorizontalPanel contentPane;
  private FileListDialog fileListDialog;
  private LoadingDialog loadingDialog;
  private ErrorDialog errorDialog;

  /**
   * Builds an initializes the document explorer module.
   */
  public void onModuleLoad() {
    contentPane = new HorizontalPanel();
    contentPane.setWidth("100%");
    contentPane.setHeight("100%");
    contentPane.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
    contentPane.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
    EditorIconsImageBundle editorIcons = (EditorIconsImageBundle)GWT.create(EditorIconsImageBundle.class);
    Image logo = editorIcons.LogoLarge().createImage();
    logo.addClickHandler(new ClickHandler(){
      public void onClick(ClickEvent event) {
        onCommand(new CommandEvent(this, CommandEvent.Command.GENERIC_OPEN_EXISTING_DOCUMENT));
      }
    });
    contentPane.add(logo);
    fileListDialog = new FileListDialog();
    fileListDialog.addCommandEventListener(this);
    loadingDialog = new LoadingDialog();
    errorDialog = new ErrorDialog();
    errorDialog.addCommandEventListener(this);
    RootPanel.get().add(contentPane);
    onCommand(new CommandEvent(this, CommandEvent.Command.GENERIC_OPEN_EXISTING_DOCUMENT));
  }
  
  /**
   * Shows a status message. If modal, a loading dialog is displayed, otherwise
   * the header status area is used.
   * 
   * @param message the status message to display
   * @param modal whether the status display is modal
   */
  private void showStatus(String message) {
    showDialog(loadingDialog);
    loadingDialog.center(message);
  }
  
  /**
   * Clears and hides any visible status messages.
   */
  private void clearStatus() {
    loadingDialog.hide();
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
    if (command.getAttemptCount() < retryAttempts) {
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
      case GENERIC_OPEN_EXISTING_DOCUMENT:
        showDialog(fileListDialog);
        break;
      case GENERIC_SIGN_OUT:
        showStatus("Signing out...");
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
    }
  }
}
