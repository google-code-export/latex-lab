package org.latexlab.docs.client;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gdata.client.GData;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.latexlab.clsi.client.ClsiService;
import org.latexlab.clsi.client.ClsiServiceCompileResponse;
import org.latexlab.clsi.client.ResourceReference;
import org.latexlab.docs.client.commands.Command;
import org.latexlab.docs.client.commands.CurrentDocumentCloseCommand;
import org.latexlab.docs.client.commands.CurrentDocumentCompileCommand;
import org.latexlab.docs.client.commands.CurrentDocumentCopyCommand;
import org.latexlab.docs.client.commands.CurrentDocumentDeleteCommand;
import org.latexlab.docs.client.commands.CurrentDocumentExportCommand;
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
import org.latexlab.docs.client.commands.ResourceWindowListDocumentsCommand;
import org.latexlab.docs.client.commands.SystemAboutCommand;
import org.latexlab.docs.client.commands.SystemApplyCompilerSettingsCommand;
import org.latexlab.docs.client.commands.SystemFullScreenCommand;
import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.commands.SystemRedoCommand;
import org.latexlab.docs.client.commands.SystemReuseToolbarWindowsCommand;
import org.latexlab.docs.client.commands.SystemSelectResourcesCommand;
import org.latexlab.docs.client.commands.SystemSetResourcesCommand;
import org.latexlab.docs.client.commands.SystemSignOutCommand;
import org.latexlab.docs.client.commands.SystemSpecifyCompilerSettingsCommand;
import org.latexlab.docs.client.commands.SystemToggleToolbarCommand;
import org.latexlab.docs.client.commands.SystemUndoCommand;
import org.latexlab.docs.client.data.FileSystem;
import org.latexlab.docs.client.data.FileSystemEntry;
import org.latexlab.docs.client.dialogs.AboutDialog;
import org.latexlab.docs.client.dialogs.CompilerSettingsDialog;
import org.latexlab.docs.client.dialogs.DialogManager;
import org.latexlab.docs.client.dialogs.ErrorDialog;
import org.latexlab.docs.client.dialogs.FileListDialog;
import org.latexlab.docs.client.dialogs.LoadingDialog;
import org.latexlab.docs.client.dialogs.ResourcesDialog;
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
import org.latexlab.docs.client.windows.ToolbarWindow;
import org.latexlab.docs.client.windows.WindowManager;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowAboveAndBelow;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowAccents;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowArrows;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowArrowsWithCaptions;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowBinaryOperators;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowBoundaries;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowComparison;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowDiverseSymbols;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowFormatting;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowGreekLowercase;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowGreekUppercase;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowLogical;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowMath;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowOperators;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowSets;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowSubscriptAndSuperscript;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowWhiteSpacesAndDots;

import java.util.ArrayList;
import java.util.Date;

/**
 * The GDBE document editor module.
 */
public class DocsEditor implements EntryPoint, CommandHandler {
  
  private ClsiService clsiService;
  private AbsolutePanel root;
  private DialogManager dialogManager;
  private WindowManager windowManager;
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
  private ToolbarWindow[] toolbars;
  
  private DocsEditorSettings settings;

  /**
   * Builds and initializes the editor module.
   */
  public void onModuleLoad() {
	clsiService = new ClsiService();
	Runnable onload = new Runnable() {
      public void run() {
        boolean ok = FileSystem.login();
        if (ok) {
	      clsiService.getCurrentUser(new AsyncCallback<String>() {
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
	settings = new DocsEditorSettings();
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
    root = new AbsolutePanel();
    root.setSize("100%", "100%");
    root.add(contentPane);
    RootPanel.get().add(root);
    
    windowManager = new WindowManager(root);
    PickupDragController dragController = new PickupDragController(root, true);
    dragController.setBehaviorConstrainedToBoundaryPanel(true);
    dragController.setBehaviorMultipleSelection(false);
    dragController.setBehaviorDragStartSensitivity(1);
    toolbars = new ToolbarWindow[] {
      new ToolbarWindowAboveAndBelow(),
      new ToolbarWindowAccents(),
      new ToolbarWindowArrows(),
      new ToolbarWindowArrowsWithCaptions(),
      new ToolbarWindowBinaryOperators(),
      new ToolbarWindowBoundaries(),
      new ToolbarWindowComparison(),
      new ToolbarWindowDiverseSymbols(),
      new ToolbarWindowFormatting(),
      new ToolbarWindowGreekLowercase(),
      new ToolbarWindowGreekUppercase(),
      new ToolbarWindowLogical(),
      new ToolbarWindowMath(),
      new ToolbarWindowOperators(),
      new ToolbarWindowSets(),
      new ToolbarWindowSubscriptAndSuperscript(),
      new ToolbarWindowWhiteSpacesAndDots()
    };
    for (int i=0; i< toolbars.length; i++) {
      final int index = i;
      ToolbarWindow toolbar = toolbars[i];
      toolbar.addCommandHandler(this);
      toolbar.addClickHandler(new ClickHandler() {
  	    public void onClick(ClickEvent event) {
  	      execute(new SystemToggleToolbarCommand(index));
  	    }
  	  });
      addWindow(toolbar, dragController);
    }
    execute(new SystemApplyCompilerSettingsCommand());
    loadDocument();
  }
  
  private void addWindow(org.latexlab.docs.client.windows.Window win, PickupDragController dragController) {
	win.registeredDragController = dragController;
	windowManager.getWindowController().makeResizable(win);
	root.add(win, 500, 120);
    win.hide();
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
      execute(new SystemAboutCommand());
      editor.init();
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
    Window.setTitle(doc.getName() + " - LaTeX Lab");
    if (currentDocument.isStored()) {
      header.setInfo(currentDocument.getId(), currentDocument.getModified(), currentUser);
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
      case ResourceWindowListDocumentsCommand.serialUid:
        FileSystem.getEntries(new AsyncCallback<ArrayList<FileSystemEntry>>() {
          public void onFailure(Throwable caught) {
            handleError(caught, cmd, null, 1);
          }
          public void onSuccess(ArrayList<FileSystemEntry> result) {
        	ResourcesDialog.getInstance(DocsEditor.this).setEntries(result);
          }
        });
        break;
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
      case CurrentDocumentExportCommand.serialUid:
    	CurrentDocumentExportCommand cdeCmd = (CurrentDocumentExportCommand) cmd;
        showStatus("Exporting document...", false);
    	clsiService.compile(currentDocument.getName(), editor.getText(),
    		  FileSystem.getAuthenticationToken(), settings.getClsiServiceId(),
    		  getResourceReferences(), "latex", cdeCmd.getExportFormat(),
    	    new AsyncCallback<ClsiServiceCompileResponse>() {
    		@Override
    		public void onFailure(Throwable caught) {
              handleError(caught, cmd, null, 0);
    		}
    		@Override
    		public void onSuccess(ClsiServiceCompileResponse result) {
    		  String r = String.valueOf(new Date().getTime());
			  if (result.getOutputErrors().length == 0 && result.getOutputFiles().length > 0) {
    			String url = result.getOutputFiles()[0].getUrl() + "?r=" + r;
	    	    Window.open(url, currentDocument.getName(), "");
    		  }
    		  if (result.getOutputLogs().length > 0) {
      		    output.setUrl(result.getOutputLogs()[0].getUrl() + "?r=" + r);
    		    body.setVerticalSplitPosition("80%");
    		  } else if (result.getOutputErrors().length > 0) {
    			output.setOutput(result.getOutputErrors()[0].getMessage());
    		    body.setVerticalSplitPosition("80%");
    		  }
              clearStatus();
    		}
    	});
      	break;
      case CurrentDocumentCompileCommand.serialUid:
        showStatus("Compiling document...", false);
        previewer.clear();
        output.clear();
    	clsiService.compile(currentDocument.getName(), editor.getText(),
    		  FileSystem.getAuthenticationToken(), settings.getClsiServiceId(), getResourceReferences(), "latex", "png",
    	    new AsyncCallback<ClsiServiceCompileResponse>() {
    		@Override
    		public void onFailure(Throwable caught) {
              handleError(caught, cmd, null, 0);
    		}
    		@Override
    		public void onSuccess(ClsiServiceCompileResponse result) {
    		  String r = String.valueOf(new Date().getTime());
    		  if (result.getOutputErrors().length == 0 && result.getOutputFiles().length > 0) {
    			String[] urls =  new String[result.getOutputFiles().length];
    			for (int i=0; i<urls.length; i++) {
    			  urls[i] = result.getOutputFiles()[i].getUrl() + "?r=" + r;
    			}
    		    previewer.setUrls(urls);
    		    body.setHorizontalSplitPosition("50%");
    		  } else {
    		    previewer.setUrls(new String[] { "/images/error.png" });
    		    body.setHorizontalSplitPosition("50%");
    		  }
    		  if (result.getOutputLogs().length > 0) {
      		    output.setUrl(result.getOutputLogs()[0].getUrl() + "?r=" + r);
    		    body.setVerticalSplitPosition("80%");
    		  } else if (result.getOutputErrors().length > 0) {
    			output.setOutput(result.getOutputErrors()[0].getMessage());
    		    body.setVerticalSplitPosition("80%");
    		  }
              clearStatus();
    		}
    	});
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
      case CurrentDocumentLoadContentsCommand.serialUid:
        showStatus("Loading document contents...", true);
        FileSystem.loadDocumentContents(currentDocument.getId(),
            new AsyncCallback<String>() {
          public void onFailure(Throwable caught) {
            handleError(caught, cmd, null, 1);
          }
          public void onSuccess(String result) {
            editor.setText(result);
            editor.init();
            clearStatus();
          }
        });
        break;
      case CurrentDocumentRevisionHistoryCommand.serialUid:
        Window.open("http://docs.google.com/Revs?id=" +
            currentDocument.getId() + "&tab=revlist",
            currentDocument.getId(), "");
        break;
      case NewDocumentLoadCommand.serialUid:
		FileSystemEntry doc = new FileSystemEntry();
		doc.setName("Untitled Document");
		doc.setStored(false);
		doc.setType(FileSystemEntry.Types.FILE);
		setDocument(doc);
		editor.setText("\\documentclass[10pt]{article}\n\\begin{document}\n\nHello LaTeX Lab!! :)\n\n\\end{document}\n");
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
      case SystemSetResourcesCommand.serialUid:
    	SystemSetResourcesCommand ssrCmd = (SystemSetResourcesCommand) cmd;
    	settings.setResources(ssrCmd.getResources());
    	break;
      case SystemReuseToolbarWindowsCommand.serialUid:
    	settings.setReuseToolbarWindows(!settings.isReuseToolbarWindows());
    	if (settings.isReuseToolbarWindows()) {
    	  menu.setMenuItemIcon("Reuse toolbar windows", EditorIcons.icons.CheckBlack());
    	} else {
      	  menu.setMenuItemIcon("Reuse toolbar windows", EditorIcons.icons.Blank());
    	}
    	break;
      case SystemAboutCommand.serialUid:
    	dialogManager.showDialog(AboutDialog.getInstance(this));
        break;
      case SystemSpecifyCompilerSettingsCommand.serialUid:
    	dialogManager.showDialog(CompilerSettingsDialog.getInstance(this));
    	break;
      case SystemSelectResourcesCommand.serialUid:
    	ResourcesDialog.getInstance(this).center();
    	break;
      case SystemApplyCompilerSettingsCommand.serialUid:
    	SystemApplyCompilerSettingsCommand sacsCmd = (SystemApplyCompilerSettingsCommand) cmd;
    	if (sacsCmd.isUseDefault()) {
    	  settings.setClsiAsyncPath("http://clsi.latexlab.org/out/async/");
    	  settings.setClsiServiceToken("token555555555555");
    	  settings.setClsiServiceId("TempId" + (new Date().getTime()));
    	  settings.setClsiServiceUrl("http://clsi.latexlab.org/service.php");
    	  settings.setCompilerName("latex");
    	} else {
    	  settings.setClsiAsyncPath(sacsCmd.getClsiAsyncPath());
    	  settings.setClsiServiceToken(sacsCmd.getClsiServiceToken());
    	  settings.setClsiServiceUrl(sacsCmd.getClsiServiceUrl());
    	  settings.setCompilerName(sacsCmd.getCompilerName());
    	}
    	clsiService.setAsyncPath(settings.getClsiAsyncPath());
    	clsiService.setServiceUrl(settings.getClsiServiceUrl());
    	clsiService.setToken(settings.getClsiServiceToken());
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
      case SystemToggleToolbarCommand.serialUid:
    	SystemToggleToolbarCommand sttCmd = (SystemToggleToolbarCommand) cmd;
    	if (sttCmd.getIndex() < toolbars.length) {
    	  ToolbarWindow toolbar = toolbars[sttCmd.getIndex()];
    	  boolean toVisible = !toolbar.isVisible();
    	  if (settings.isReuseToolbarWindows()) {
    	    for (int i=0; i<toolbars.length; i++) {
    	      ToolbarWindow tb = toolbars[i];
    	      tb.hide();
    	      DocsEditor.this.toolbar.setButtonState(i + 6, false);
    	    }
    	  } else {
    	    toolbar.hide();
    	    DocsEditor.this.toolbar.setButtonState(sttCmd.getIndex() + 6, false);
    	  }
    	  if (toVisible) {
    	    toolbar.show();
    	    DocsEditor.this.toolbar.setButtonState(sttCmd.getIndex() + 6, true);
    	  }
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
      case SystemPasteCommand.serialUid:
    	SystemPasteCommand spCmd = (SystemPasteCommand) cmd;
    	editor.replaceSelection(spCmd.getText());
    	break;
      case SystemUndoCommand.serialUid:
    	editor.undo();
    	break;
      case SystemRedoCommand.serialUid:
    	editor.redo();
    	break;
      default:
        Window.alert("Not implemented");
        break;
    }
  }

  private ResourceReference[] getResourceReferences() {
	int size = settings.getResources().size();
    ResourceReference[] refs = new ResourceReference[size];
    for (int i=0; i<size; i++) {
      FileSystemEntry entry = settings.getResources().get(i);
      refs[i] = new ResourceReference(entry.getId(),
          entry.getName(), entry.getModified());
    }
    return refs;
  }
}