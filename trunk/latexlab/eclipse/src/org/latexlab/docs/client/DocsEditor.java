package org.latexlab.docs.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.gdata.client.GData;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.latexlab.docs.client.commands.Command;
import org.latexlab.docs.client.commands.CurrentDocumentCloseCommand;
import org.latexlab.docs.client.commands.CurrentDocumentCopyCommand;
import org.latexlab.docs.client.commands.CurrentDocumentDeleteCommand;
import org.latexlab.docs.client.commands.CurrentDocumentLoadContentsCommand;
import org.latexlab.docs.client.commands.CurrentDocumentRenameCommand;
import org.latexlab.docs.client.commands.CurrentDocumentRevisionHistoryCommand;
import org.latexlab.docs.client.commands.CurrentDocumentSaveAndCloseCommand;
import org.latexlab.docs.client.commands.CurrentDocumentSaveCommand;
import org.latexlab.docs.client.commands.ExistingDocumentLoadCommand;
import org.latexlab.docs.client.commands.ExistingDocumentOpenCommand;
import org.latexlab.docs.client.commands.FileDialogListDocumentsCommand;
import org.latexlab.docs.client.commands.FileDialogListStarredDocumentsCommand;
import org.latexlab.docs.client.commands.FileDialogOpenDocumentCommand;
import org.latexlab.docs.client.commands.FileDialogStarDocumentCommand;
import org.latexlab.docs.client.commands.FileDialogUnstarDocumentCommand;
import org.latexlab.docs.client.commands.NewDocumentLoadCommand;
import org.latexlab.docs.client.commands.NewDocumentStartCommand;
import org.latexlab.docs.client.commands.SystemAboutCommand;
import org.latexlab.docs.client.commands.SystemFullScreenCommand;
import org.latexlab.docs.client.commands.SystemSignOutCommand;
import org.latexlab.docs.client.data.FileSystem;
import org.latexlab.docs.client.data.FileSystemEntry;
import org.latexlab.docs.client.data.LatexLabService;
import org.latexlab.docs.client.data.LatexLabServiceAsync;
import org.latexlab.docs.client.dialogs.AboutDialog;
import org.latexlab.docs.client.dialogs.DialogManager;
import org.latexlab.docs.client.dialogs.ErrorDialog;
import org.latexlab.docs.client.dialogs.FileListDialog;
import org.latexlab.docs.client.dialogs.LoadingDialog;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.parts.BodyPart;
import org.latexlab.docs.client.parts.EditorPart;
import org.latexlab.docs.client.parts.HeaderPart;
import org.latexlab.docs.client.parts.MenuPart;
import org.latexlab.docs.client.parts.OutputPart;
import org.latexlab.docs.client.parts.PreviewerPart;
import org.latexlab.docs.client.parts.ToolbarPart;
import org.latexlab.docs.client.resources.icons.EditorIcons;

import java.util.ArrayList;
import java.util.Date;

/**
 * The GDBE document editor module.
 */
public class DocsEditor implements EntryPoint, CommandHandler {

  private final LatexLabServiceAsync compiler = GWT.create(LatexLabService.class);
  
  private DialogManager dialogManager;
  private BodyPart body;
  private EditorPart editor;
  private PreviewerPart previewer;
  private OutputPart output;
  private FlexTable contentPane;
  private HeaderPart header;
  private MenuPart menu;
  private ToolbarPart toolbar;
  private String currentUser;
  private FileSystemEntry currentDocument;

  /**
   * Builds and initializes the editor module.
   */
  public void onModuleLoad() {
	Runnable onload = new Runnable() {
      public void run() {
        boolean ok = FileSystem.login();
        if (ok) {
	      compiler.getCurrentUser(new AsyncCallback<String>() {
	  	    @Override
	  	    public void onFailure(Throwable caught) {
	  	    }
	  		@Override
	  		public void onSuccess(String result) {
	  		  currentUser = result;
              start();
	  		}
	      });
        }
      }
	};
    if (GData.isLoaded()) {
      boolean ok = FileSystem.login();
      if (ok) {
        onload.run();
      }
    } else {
      GData.loadGDataApi(null, onload);
    }
  }
  
  public void start() {
	dialogManager = new DialogManager();
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
    header.setAuthor(currentUser);
    header.addCommandHandler(this);
    menu = new MenuPart();
    menu.addCommandHandler(this);
    toolbar = new ToolbarPart();
    toolbar.addCommandHandler(this);
    editor = new EditorPart();
    previewer = new PreviewerPart();
    VerticalPanel headerPanel = new VerticalPanel();
    headerPanel.setWidth("100%");
    headerPanel.add(header);
    headerPanel.add(menu);
    headerPanel.add(toolbar);
    output = new OutputPart();
    output.setOutput("Initializing...");
    body = new BodyPart();
    body.setWidth("100%");
    body.setHeight("100%");
    body.setTopLeftWidget(this.editor);
    body.setTopRightWidget(this.previewer);
    body.setBottomWidget(this.output);
    contentPane.setWidget(0, 0, headerPanel);
    contentPane.setWidget(1, 0, body);
    RootPanel.get().add(contentPane);
    loadDocument();
    this.editor.init();
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
      execute(new NewDocumentLoadCommand());
    } else {
      execute(new ExistingDocumentLoadCommand(documentId));
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
  private void setDocument(FileSystemEntry doc) {
    currentDocument = doc;
    header.setTitle(doc.getName());
    Window.setTitle(doc.getName() + " - Cloudie");
    if (currentDocument.isStored()) {
      header.setInfo(currentDocument.getId(), currentDocument.getModified(), currentDocument.getModifiedBy());
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
      LoadingDialog loadingDialog = LoadingDialog.getInstance(this);
      loadingDialog.setMessage(message);
      dialogManager.showDialog(loadingDialog);
    } else {
      header.setStatus(message);
    }
  }
  
  /**
   * Clears and hides any visible status messages.
   */
  private void clearStatus() {
	LoadingDialog.getInstance(this).hide();
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
  private <E extends Command, V extends Command> void handleError(
	    Throwable error, E command, V alternate, int retryAttempts){
    clearStatus();
    if (command.getAttemptCount() <= retryAttempts) {
      command.newAttempt();
      execute(command);
    } else {
      ErrorDialog errorDialog = ErrorDialog.getInstance(this);
      errorDialog.update(error, command, alternate);
      dialogManager.showDialog(errorDialog);
    }
  }
  
  /**
   * Handles a command event. This is where all the components converge along with
   * the application logic.
   * 
   * @param the command event
   */
  
  public void onCommand(CommandEvent e) {
	execute(e.getCommand());
  }
  
  /**
   * Handles a command event. This is where all the components converge along with
   * the application logic.
   * 
   * @param the command event
   */
  public <T extends Command> void execute(final T cmd) {
        Date now = new Date();
    switch (cmd.getCommandId()) {
      case FileDialogUnstarDocumentCommand.serialUid:
	    FileSystem.setDocumentStarred(((FileDialogUnstarDocumentCommand)cmd).getDocumentId(),
	        false,
	        new AsyncCallback<FileSystemEntry>(){
	          public void onFailure(Throwable caught) {
	            handleError(caught, cmd, null, 1);
	          }
	          public void onSuccess(FileSystemEntry result) {
	          }
	    });
	    break;
      case FileDialogStarDocumentCommand.serialUid:
        FileSystem.setDocumentStarred(
            ((FileDialogStarDocumentCommand)cmd).getDocumentId(),
            true,
            new AsyncCallback<FileSystemEntry>(){
              public void onFailure(Throwable caught) {
                handleError(caught, cmd, null, 1);
              }
              public void onSuccess(FileSystemEntry result) {
              }
        });
        break;
      case FileDialogListStarredDocumentsCommand.serialUid:
    	FileSystem.getEntries(new AsyncCallback<ArrayList<FileSystemEntry>>() {
          public void onFailure(Throwable caught) {
            handleError(caught, cmd, null, 1);
          }
          public void onSuccess(ArrayList<FileSystemEntry> result) {
        	FileListDialog fileListDialog = FileListDialog.getInstance(DocsEditor.this);
            fileListDialog.setEntries(result);
            fileListDialog.showEntries(true);
          }
        });
        break;
      case FileDialogListDocumentsCommand.serialUid:
        FileSystem.getEntries(new AsyncCallback<ArrayList<FileSystemEntry>>() {
          public void onFailure(Throwable caught) {
            handleError(caught, cmd, null, 1);
          }
          public void onSuccess(ArrayList<FileSystemEntry> result) {
        	FileListDialog fileListDialog = FileListDialog.getInstance(DocsEditor.this);
            fileListDialog.setEntries(result);
            fileListDialog.showEntries(false);
          }
        });
        break;
      case FileDialogOpenDocumentCommand.serialUid:
    	FileDialogOpenDocumentCommand fdodCmd = (FileDialogOpenDocumentCommand) cmd;
        Window.open("http://docs.google.com/Doc?docid=" + fdodCmd.getDocumentId() + "&hl=en", fdodCmd.getDocumentId(), "");
        break;
      case NewDocumentStartCommand.serialUid:
        Window.open("/docs", "Untitled" + now.getTime(), "");
        break;
      case ExistingDocumentOpenCommand.serialUid:
    	dialogManager.showDialog(FileListDialog.getInstance(this));
        break;
      case CurrentDocumentCopyCommand.serialUid:
        showStatus("Copying document...", true);
        FileSystem.createDocument("Copy of " + currentDocument.getName(), editor.getText(),
            new AsyncCallback<FileSystemEntry>(){
              public void onFailure(Throwable caught) {
                handleError(caught, cmd, null, 0);
              }
              public void onSuccess(FileSystemEntry result) {
                clearStatus();
                Window.open("/docs?docid=" + result.getId(), result.getId(), "");
              }
        });
        break;
      case CurrentDocumentSaveCommand.serialUid:
        showStatus("Saving...", false);
        FileSystem.saveDocument(currentDocument.getId(), currentDocument.getName(),
            editor.getText(), new AsyncCallback<FileSystemEntry>() {
          public void onFailure(Throwable caught) {
            handleError(caught, cmd, null, 0);
          }
          public void onSuccess(FileSystemEntry result) {
            setDocument(result);
            clearStatus();
          }
        });
        break;
      case CurrentDocumentSaveAndCloseCommand.serialUid:
        showStatus("Saving document...", true);
        FileSystem.saveDocument(currentDocument.getId(), currentDocument.getName(),
        	editor.getText(), new AsyncCallback<FileSystemEntry>() {
          public void onFailure(Throwable caught) {
            handleError(caught, cmd, null, 0);
          }
          public void onSuccess(FileSystemEntry result) {
            setDocument(result);
            clearStatus();
            close();
          }
        });
        break;
      case CurrentDocumentCloseCommand.serialUid:
        close();
        break;
      case CurrentDocumentRenameCommand.serialUid:
        final String newName = Window.prompt("Enter new document name:", currentDocument.getName());
        if (newName != null && !newName.equals("")) {
          if (currentDocument.isStored()) {
            showStatus("Renaming...", false);
            FileSystem.setDocumentName(currentDocument.getId(), newName,
                new AsyncCallback<FileSystemEntry>(){
              public void onFailure(Throwable caught) {
                handleError(caught, cmd, null, 0);
              }
              public void onSuccess(FileSystemEntry result) {
                setDocument(result);
                clearStatus();
              }
            });
          } else {
            currentDocument.setName(newName);
            setDocument(currentDocument);
          }
        }
        break;
      case CurrentDocumentDeleteCommand.serialUid:
        if (Window.confirm("This document will be deleted and closed.")) {
          if (currentDocument.isStored()) {
            showStatus("Deleting document...", true);
            FileSystem.deleteDocument(currentDocument.getId(), new AsyncCallback<FileSystemEntry>() {
              public void onFailure(Throwable caught) {
                handleError(caught, cmd, null, 0);
              }
              public void onSuccess(FileSystemEntry result) {
                clearStatus();
                close();
              }
            });
          } else {
            close();
          }
        }
        break;
      case NewDocumentLoadCommand.serialUid:
		FileSystemEntry doc = new FileSystemEntry();
		doc.setName("Untitled Document");
		doc.setStored(false);
		doc.setType(FileSystemEntry.Types.FILE);
		setDocument(doc);
        break;
      case ExistingDocumentLoadCommand.serialUid:
    	ExistingDocumentLoadCommand edlCmd = (ExistingDocumentLoadCommand) cmd;
        showStatus("Loading document...", true);
        FileSystem.loadEntry(edlCmd.getDocumentId(), new AsyncCallback<FileSystemEntry>() {
          public void onFailure(Throwable caught) {
            handleError(caught, cmd, new NewDocumentLoadCommand(), 1);
          }
          public void onSuccess(FileSystemEntry result) {
            setDocument(result);
            clearStatus();
            execute(new CurrentDocumentLoadContentsCommand());
          }
        });
        break;
      case CurrentDocumentLoadContentsCommand.serialUid:
        showStatus("Loading document contents...", true);
        FileSystem.loadDocumentContents(currentDocument.getId(),
            new AsyncCallback<String>() {
          public void onFailure(Throwable caught) {
            handleError(caught, cmd, null, 1);
          }
          public void onSuccess(String result) {
            editor.setText(result);
            clearStatus();
          }
        });
        break;
      case CurrentDocumentRevisionHistoryCommand.serialUid:
        Window.open("http://docs.google.com/Revs?id=" +
            currentDocument.getId() + "&tab=revlist",
            currentDocument.getId(), "");
        break;
      case SystemAboutCommand.serialUid:
    	dialogManager.showDialog(AboutDialog.getInstance(this));
        break;
      case SystemFullScreenCommand.serialUid:
        boolean isFullScreen = !header.isVisible();
        if (isFullScreen) {
          menu.setMenuItemIcon("Full-screen mode", EditorIcons.icons.Blank());
          header.setVisible(true);
          contentPane.getFlexCellFormatter().setHeight(0, 0, "120px");
        } else {
          menu.setMenuItemIcon("Full-screen mode", EditorIcons.icons.CheckBlack());
          header.setVisible(false);
          contentPane.getFlexCellFormatter().setHeight(0, 0, "40px");
        }
        break;
      case SystemSignOutCommand.serialUid:
        showStatus("Signing out...", true);
        FileSystem.logout(new Runnable() {
          public void run() {
            Window.Location.replace(((SystemSignOutCommand)cmd).getReturnUrl());
            clearStatus();
          }
        });
        break;
      default:
        Window.alert("Not implemented");
        break;
    }
  }

}