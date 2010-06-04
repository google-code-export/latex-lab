package org.latexlab.docs.editor.advanced.client;

import java.util.ArrayList;
import java.util.Date;

import org.latexlab.clsi.client.async.CompileCallback;
import org.latexlab.clsi.client.async.ReadyCallback;
import org.latexlab.clsi.client.local.ClsiLocalService;
import org.latexlab.clsi.client.protocol.ClsiOutputError;
import org.latexlab.clsi.client.protocol.ClsiResourceReference;
import org.latexlab.clsi.client.protocol.ClsiServiceCompileResponse;
import org.latexlab.clsi.client.remote.ClsiRemoteService;
import org.latexlab.docs.client.DocsEditorSettings;
import org.latexlab.docs.client.commands.Command;
import org.latexlab.docs.client.commands.CurrentDocumentChangedCommand;
import org.latexlab.docs.client.commands.CurrentDocumentCloseCommand;
import org.latexlab.docs.client.commands.CurrentDocumentCompileCommand;
import org.latexlab.docs.client.commands.CurrentDocumentCompileLocalCommand;
import org.latexlab.docs.client.commands.CurrentDocumentCopyCommand;
import org.latexlab.docs.client.commands.CurrentDocumentDeleteCommand;
import org.latexlab.docs.client.commands.CurrentDocumentExportCommand;
import org.latexlab.docs.client.commands.CurrentDocumentLoadCommonContentsCommand;
import org.latexlab.docs.client.commands.CurrentDocumentLoadContentsCommand;
import org.latexlab.docs.client.commands.CurrentDocumentRefreshCommand;
import org.latexlab.docs.client.commands.CurrentDocumentReloadCommand;
import org.latexlab.docs.client.commands.CurrentDocumentRenameCommand;
import org.latexlab.docs.client.commands.CurrentDocumentRevisionHistoryCommand;
import org.latexlab.docs.client.commands.CurrentDocumentSaveAndCloseCommand;
import org.latexlab.docs.client.commands.CurrentDocumentSaveCommand;
import org.latexlab.docs.client.commands.SystemLoadDocumentCommand;
import org.latexlab.docs.client.commands.SystemListDocumentsCommand;
import org.latexlab.docs.client.commands.SystemOpenDocumentCommand;
import org.latexlab.docs.client.commands.SystemStarDocumentCommand;
import org.latexlab.docs.client.commands.SystemUnstarDocumentCommand;
import org.latexlab.docs.client.commands.NewDocumentLoadCommand;
import org.latexlab.docs.client.commands.NewDocumentStartCommand;
import org.latexlab.docs.client.commands.SystemApplyCompilerSettingsCommand;
import org.latexlab.docs.client.commands.SystemApplyPreferencesCommand;
import org.latexlab.docs.client.commands.SystemOpenPageCommand;
import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.commands.SystemRedoCommand;
import org.latexlab.docs.client.commands.SystemRefreshResourcesCommand;
import org.latexlab.docs.client.commands.SystemToggleReuseToolbarWindowsCommand;
import org.latexlab.docs.client.commands.SystemSetResourcesCommand;
import org.latexlab.docs.client.commands.SystemSetPerspectiveCommand;
import org.latexlab.docs.client.commands.SystemShowDialogCommand;
import org.latexlab.docs.client.commands.SystemSignOutCommand;
import org.latexlab.docs.client.commands.SystemToggleColorSyntaxCommand;
import org.latexlab.docs.client.commands.SystemToggleFullScreenCommand;
import org.latexlab.docs.client.commands.SystemToggleLineNumbersCommand;
import org.latexlab.docs.client.commands.SystemToggleSpellcheckCommand;
import org.latexlab.docs.client.commands.SystemToggleToolbarCommand;
import org.latexlab.docs.client.commands.SystemToggleWrapTextCommand;
import org.latexlab.docs.client.commands.SystemUndoCommand;
import org.latexlab.docs.client.commands.SystemUploadDocumentsCommand;
import org.latexlab.docs.client.commands.SystemViewPageCommand;
import org.latexlab.docs.client.commands.SystemViewPageIndexCommand;
import org.latexlab.docs.client.commands.SystemViewPageZoomInCommand;
import org.latexlab.docs.client.commands.SystemViewPageZoomOutCommand;
import org.latexlab.docs.client.dialogs.AboutDialog;
import org.latexlab.docs.client.dialogs.ActionDialog;
import org.latexlab.docs.client.dialogs.CompilerSettingsDialog;
import org.latexlab.docs.client.dialogs.DevelopmentInfoDialog;
import org.latexlab.docs.client.dialogs.ErrorDialog;
import org.latexlab.docs.client.dialogs.EtagMismatchErrorDialog;
import org.latexlab.docs.client.dialogs.FileListDialog;
import org.latexlab.docs.client.dialogs.LoadingDialog;
import org.latexlab.docs.client.dialogs.PreferencesDialog;
import org.latexlab.docs.client.dialogs.ResourcesDialog;
import org.latexlab.docs.client.dialogs.ActionDialog.ActionDialogOption;
import org.latexlab.docs.client.events.AsyncInstantiationCallback;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.events.Scheduler;
import org.latexlab.docs.client.gdocs.DocumentService;
import org.latexlab.docs.client.gdocs.DocumentServiceAsync;
import org.latexlab.docs.client.gdocs.DocumentServiceEntry;
import org.latexlab.docs.client.gdocs.DocumentSignedLocation;
import org.latexlab.docs.client.gdocs.DocumentUser;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.editor.advanced.client.DocsAdvancedEditorView.LockFunction;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The MVC controller for the advanced document editor.
 */
public class DocsAdvancedEditorController implements CommandHandler {
	
  /**
   * A CLSI token, for use with LaTeX Lab's CLSI service instance.
   * @TODO: token should be a dynamic value
   */
  protected final static String CLSI_TOKEN = "8d3id348f34wbvew234sf4df0098";
  protected static DocsAdvancedEditorController instance;

  /**
   * Retrieves the single instance of this class, using asynchronous instantiation.
   * 
   * @param cb the asynchronous instantiation callback
   */
  public static void get(final AsyncInstantiationCallback<DocsAdvancedEditorController> cb) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  cb.onFailure(reason);
		}
		@Override
		public void onSuccess() {
	      if (instance == null) {
	        instance = new DocsAdvancedEditorController();
	      }
		  cb.onSuccess(instance);
		}
	});
  }
  
  /**
   * TODO: the end goal is to have the controller consist of primarily
   * reactive code, with minimal UI references. Some instance variables
   * can be moved out into specialized code.
   */
  private DocumentServiceAsync docService;
  private ClsiRemoteService clsiService;
  private DocumentServiceEntry currentDocument;
  private DocumentServiceEntry[] allDocuments;
  private DocumentUser currentUser;
  private DocsEditorSettings settings;
  private Scheduler scheduler;
  private DocsAdvancedEditorView app;
  
  /**
   * Construct a new controller. Instantiate any required service end points.
   */

  protected DocsAdvancedEditorController() {
	docService = GWT.create(DocumentService.class);
  }
  
  /**
   * Initialize by first checking authentication and retrieving the
   * logged on user.
   */
  
  public void initialize() {
    showStatus("Authenticating...", true);
	docService.getUser(new AsyncCallback<DocumentUser>() {
		@Override
		public void onFailure(Throwable caught) {
		  Window.alert("Authentication Failure: " + caught.getMessage());
		}
		@Override
		public void onSuccess(DocumentUser result) {
		  clearStatus();
  		  if (result == null) {
  		    if (!GWT.isScript()) {
  			  //if running in dev mode, auto-authenticate with a test account
  			  docService.setUser("non.gwt.gdata@gmail.com", "CIuortn_DxDZ-_vN-P____8BGKDV2d4E", new AsyncCallback<DocumentUser>() {
  				  @Override
  				  public void onFailure(Throwable caught) {
  					Window.alert("No login detected.");
  				  }
  				  @Override
  				  public void onSuccess(DocumentUser result) {
  					if (result == null) {
  					  Window.alert("No login detected.");
  					} else {
  	  		          currentUser = result;
  	                  start();
  					}
  				  }
  			  });
  			} else {
  		      Window.alert("No login detected. Ensure that any requests go through the server side, " +
  		          "to enforce authentication, rather than directly to the HTML " +
  		          "content.");
  			}
  		  } else {
  		    currentUser = result;
            start();
  		  }
		}
	});
  }
  
  /**
   * Start the app by loading the UI, asynchronously and setting up
   * additional elements, such as the scheduler.
   * 
   * Load a document for editing.
   */
  public void start() {
	showStatus("Loading components...", true);
	DocsAdvancedEditorView.get(this, currentUser.getEmail(),
	    new AsyncInstantiationCallback<DocsAdvancedEditorView>() {
			@Override
			public void onFailure(Throwable caught) {
			  handleAsyncLoadError(caught);
			}
			@Override
			public void onSuccess(DocsAdvancedEditorView result) {
			  app = result;
			  clearStatus();
			  clsiService = new ClsiRemoteService();
			  clsiService.setTimeout(30);
			  settings = new DocsEditorSettings();
		      execute(new SystemApplyCompilerSettingsCommand(), DocsAdvancedEditorController.this);
			  settings.setHasCompilerSettings(false);
		      loadDocument();
		      scheduler = new Scheduler();
		      scheduler.addCommandHandler(DocsAdvancedEditorController.this);
			  if (settings.isUseAutoSave()) {
		        scheduler.scheduleRepeating("AutoSave", settings.getAutoSaveInterval(),
		            new CurrentDocumentSaveCommand());
			  }
			}
		}
	);
  }
  
  
  /**
   * Extracts the "docid" parameter from the URL querystring and loads the
   * respective document - the document details are loaded first, followed by
   * the document contents.
   * If no querystring parameter is present, check for a hash value to use
   * as the document id.
   * If "docid" is blank then a new, unsaved, document is loaded.
   */
  public void loadDocument() {
    String documentId = Window.Location.getParameter("docid");
    if (documentId == null || documentId.equals("")) {
      documentId = Window.Location.getHash();
      if (documentId != null && documentId.startsWith("#")) {
    	documentId = documentId.substring(1);
      }
    }
    if (documentId == null || documentId.equals("")) {
      execute(new NewDocumentLoadCommand(), DocsAdvancedEditorController.this);
    } else {
      execute(new SystemLoadDocumentCommand(documentId), DocsAdvancedEditorController.this);
    }
  }
  
  
  /**
   * Closes the current window.
   * 
   * @TODO: browsers seem to be phasing out support for closing windows,
   * rethink the use of features that close the current window
   */
  private native void close() /*-{
  	$wnd.open('', '_self', '');//for chrome
    $wnd.close();
  }-*/;

  
  /**
   * Sets the currently open document and updates the header info and window title
   * with the respective document information.
   * 
   * If no docid parameter is present, set the document id as the url hash - this
   * will update the url to become specific to the current document, without
   * causing a browser refresh.
   * 
   * @param doc the document which to use
   * @param requireNewEtag whether a new Etag is expected for the specified document
   */
  private void setDocument(DocumentServiceEntry doc, boolean requireNewEtag) {
	boolean wasStored = (currentDocument != null && currentDocument.isStored());
	boolean etagSame = false;
	if (currentDocument != null && currentDocument.getEtag() != null) {
      etagSame = currentDocument.getEtag().equals(doc.getEtag());
	}
    currentDocument = doc;
    app.getHeader().setTitle(doc.getTitle());
    Window.setTitle(doc.getTitle() + " - LaTeX Lab");
    if (currentDocument.isStored()) {
      app.getHeader().setInfo(currentDocument.getDocumentId(), currentDocument.getEdited(), currentDocument.getEditor());
      if (!wasStored) {
    	String urlDocId = Window.Location.getParameter("docid");
    	if (urlDocId == null || urlDocId.equals("")) {
          History.newItem(doc.getDocumentId());
    	}
      } else {
	    if (etagSame) {
          if(etagSame && settings.isEnforceEtags() && requireNewEtag) {
        	scheduler.schedule(2000, new CurrentDocumentRefreshCommand(true, null));
          }
	    }
      }
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
      LoadingDialog loadingDialog = LoadingDialog.get();
      loadingDialog.setMessage(message);
      loadingDialog.center();
    } else {
      if (app != null) {
    	app.getHeader().setStatus(message);
      }
    }
  }
  
  
  /**
   * Clears and hides any visible status messages.
   */
  private void clearStatus() {
	LoadingDialog.get().hide();
	if (app != null) {
      app.getHeader().setStatus("");
	}
  }
  

  /**
   * Handles a command error by displaying an error dialog.
   * 
   * @param error the error exception
   * @param command the command that triggered the error
   * @param alternate the command to trigger on cancel
   * @param retryAttempts the number of times to retry the command before displaying an error dialog
   */
  private void handleError(final Throwable error, final Command command,
	  final Command alternate, int retryAttempts){
    clearStatus();
    if (command.getAttemptCount() <= retryAttempts) {
      command.newAttempt();
      execute(command, DocsAdvancedEditorController.this);
    } else {
      if (error.getMessage().contains("Mismatch: etags")) {
    	EtagMismatchErrorDialog.get(DocsAdvancedEditorController.this,
    	    new AsyncInstantiationCallback<EtagMismatchErrorDialog>() {
				@Override
				public void onFailure(Throwable caught) {
				  handleAsyncLoadError(caught);
				}
				@Override
				public void onSuccess(EtagMismatchErrorDialog dialog) {
				  dialog.update(error, command, alternate);
				  dialog.center();
				}
    	});
      } else {
        ErrorDialog errorDialog = ErrorDialog.get(this);
        errorDialog.update(error, command, alternate);
        errorDialog.center();
      }
    }
  }
  
  /**
   * Handles an async load error by displaying an alert box.
   * 
   * @param caught the error exception
   */
  private void handleAsyncLoadError(Throwable caught) {
	Window.alert("Error loading a required component.");
  }
  
  /************************************************************************************
   *************************** Begin Controller Logic *********************************
   ************************************************************************************/
  

  private void execute(final NewDocumentStartCommand cmd) {
    Date now = new Date();
    ActionDialog ad = ActionDialog.get(this);
    ad.update("New Document", "Do you want to start the new document in a new window?",
        new ActionDialogOption[] {
    	  new ActionDialogOption("Open New Window", new SystemOpenPageCommand("Untitled" + now.getTime(), "/docs", false)),
    	  new ActionDialogOption("Use Current Window", new SystemOpenPageCommand("Untitled" + now.getTime(), "/docs", true))
    	}
    );
    ad.center();
  }
  private void execute(final NewDocumentLoadCommand cmd) {
	docService.getNewDocument(new AsyncCallback<DocumentServiceEntry>() {
		@Override
		public void onFailure(Throwable caught) {
          handleError(caught, cmd, null, 1);
		}
		@Override
		public void onSuccess(DocumentServiceEntry result) {
	      setDocument(result, false);
	      execute(new CurrentDocumentLoadCommonContentsCommand("default"), DocsAdvancedEditorController.this);
	    }
	});
  }
  private void execute(final CurrentDocumentExportCommand cmd) {
	if (currentDocument != null && currentDocument.isCompiling()) {
	  return;
    }
	if (!settings.getHasCompilerSettings()) {
	  settings.setHasCompilerSettings(true);
	  CompilerSettingsDialog.get(this, cmd, new AsyncInstantiationCallback<CompilerSettingsDialog>() {
		@Override
		public void onFailure(Throwable caught) {
		  handleAsyncLoadError(caught);
		}
		@Override
		public void onSuccess(CompilerSettingsDialog dialog) {
		  dialog.center();
		}
	  });
	  return;
	}
	setFunctionLock(LockFunction.COMPILE, true);
    showStatus("Exporting document...", false);
    compile(cmd.getExportFormat(),
	    new CompileCallback() {
		@Override
		public void onFailure(Throwable caught) {
		  setFunctionLock(LockFunction.COMPILE, false);
          handleError(caught, cmd, null, 0);
		}
		@Override
		public void onSuccess(ClsiServiceCompileResponse result) {
		  setFunctionLock(LockFunction.COMPILE, false);
		  String r = String.valueOf(new Date().getTime());
		  if (result.getOutputErrors().length == 0 && result.getOutputFiles().length > 0) {
			String url = result.getOutputFiles()[0].getUrl() + "?r=" + r;
			ActionDialog actionDialog = ActionDialog.get(DocsAdvancedEditorController.this);
			actionDialog.update("View Exported Document",
			    "A " + cmd.getExportFormat() + " version of the current " +
			    "document has been compiled and is available for viewing.",
			    new ActionDialogOption[] {
				    new ActionDialogOption("View Exported Document",
			        new SystemOpenPageCommand(currentDocument.getDocumentId() + "_exp", url, false))
				}
		    );
			actionDialog.center();
		  }
		  if (result.getOutputErrors().length > 0) {
			String err = "";
			for (ClsiOutputError msg : result.getOutputErrors()) {
				err += msg.getMessage() + "\n";
			}
			app.getPreviewer().setError(err);
		  }
		  if (result.getOutputLogs().length > 0) {
  		    app.getOutput().setUrl(result.getOutputLogs()[0].getUrl() + "?r=" + r);
		  }
          clearStatus();
		}
	});
  }
  private void execute(final CurrentDocumentCompileCommand cmd) {
	if (currentDocument != null && currentDocument.isCompiling()) {
	  return;
	}
  	if (!settings.getHasCompilerSettings()) {
  	  settings.setHasCompilerSettings(true);
  	  CompilerSettingsDialog.get(this, cmd, new AsyncInstantiationCallback<CompilerSettingsDialog>() {
		@Override
		public void onFailure(Throwable caught) {
		  handleAsyncLoadError(caught);
		}
		@Override
		public void onSuccess(CompilerSettingsDialog dialog) {
		  dialog.center();
		}
  	  });
  	  return;
  	}
	setFunctionLock(LockFunction.COMPILE, true);
    showStatus("Compiling document...", false);
    app.getPreviewer().clear();
    app.getOutput().clear();
    compile("png", new CompileCallback() {
		@Override
		public void onFailure(Throwable caught) {
		  setFunctionLock(LockFunction.COMPILE, false);
          handleError(caught, cmd, null, 0);
		}
		@Override
		public void onSuccess(ClsiServiceCompileResponse result) {
		  setFunctionLock(LockFunction.COMPILE, false);
		  String r = String.valueOf(new Date().getTime());
	      String[] urls =  new String[result.getOutputFiles().length];
		  for (int i=0; i<urls.length; i++) {
			urls[i] = result.getOutputFiles()[i].getUrl() + "?r=" + r;
	      }
		  app.getPreviewer().setPages(urls);
		  app.getFooter().getPageViewer().reset(urls.length);
		  if (result.getOutputErrors().length > 0) {
			String err = "";
			for (ClsiOutputError msg : result.getOutputErrors()) {
				err += msg.getMessage() + "\n";
			}
		    app.getFooter().getPageViewer().reset(0);
			app.getPreviewer().setError(err);
		  }
		  if (result.getOutputLogs().length > 0) {
  		    app.getOutput().setUrl(result.getOutputLogs()[0].getUrl() + "?r=" + r);
		  }
		  app.getBody().setHorizontalSplitPosition("50%");
          clearStatus();
		}
	});
  }
  private void execute(final CurrentDocumentCopyCommand cmd) {
    showStatus("Copying document...", true);
    docService.createDocument("Copy of " + currentDocument.getTitle(), app.getEditor().getText(),
        new AsyncCallback<DocumentServiceEntry>(){
          public void onFailure(Throwable caught) {
            handleError(caught, cmd, null, 0);
          }
          public void onSuccess(DocumentServiceEntry result) {
            clearStatus();
            String url = "/docs?docid=" + result.getDocumentId();
            ActionDialog actionDialog = ActionDialog.get(DocsAdvancedEditorController.this);
			actionDialog.update("Open Copied Document",
			    "A copy of the current document has been created and is available for editing.",
			    new ActionDialogOption[] {
					new ActionDialogOption("Open Copied Document",
					    new SystemOpenPageCommand(result.getDocumentId(), url, false))
				}
			);
			actionDialog.center();
          }
    });
  }
  private void execute(final CurrentDocumentSaveCommand cmd, final boolean isScheduled) {
	if (currentDocument != null && currentDocument.isSaving()) {
	  return;
	}
	if (isScheduled) {
	  if (currentDocument == null ||
	      !currentDocument.isStored() ||
	      !currentDocument.isDirty()) {
	    return;
	  }
	}
	setFunctionLock(LockFunction.SAVE, true);
    docService.saveDocument(currentDocument.getDocumentId(),
    	settings.isEnforceEtags() ? currentDocument.getEtag() : "*",
    	currentDocument.getTitle(),
        app.getEditor().getText(), new AsyncCallback<DocumentServiceEntry>() {
      public void onFailure(Throwable caught) {
		setFunctionLock(LockFunction.SAVE, false);
        if (!isScheduled) {
    	  handleError(caught, cmd, null, 0);
        }
      }
      public void onSuccess(DocumentServiceEntry result) {
		setFunctionLock(LockFunction.SAVE, false);
        setDocument(result, true);
        currentDocument.setDirty(false);
      }
    });
  }
  private void execute(final CurrentDocumentSaveAndCloseCommand cmd) {
    showStatus("Saving document...", true);
    docService.saveDocument(currentDocument.getDocumentId(),
    		settings.isEnforceEtags() ? currentDocument.getEtag() : "*",
    		currentDocument.getTitle(),
    	app.getEditor().getText(), new AsyncCallback<DocumentServiceEntry>() {
      public void onFailure(Throwable caught) {
        handleError(caught, cmd, null, 0);
      }
      public void onSuccess(DocumentServiceEntry result) {
        setDocument(result, false);
        clearStatus();
        close();
      }
    });
  }
  private void execute(final CurrentDocumentRefreshCommand cmd) {
    if (!cmd.isExecuteInBackground()) {
	  showStatus("Obtaining document metadata...", true);
    }
    docService.getDocument(currentDocument.getDocumentId(), new AsyncCallback<DocumentServiceEntry>() {
      public void onFailure(Throwable caught) {
    	if (!cmd.isExecuteInBackground()) {
          handleError(caught, cmd, null, 1);
    	}
      }
      public void onSuccess(DocumentServiceEntry result) {
        setDocument(result, false);
        if (!cmd.isExecuteInBackground()) {
          clearStatus();
        }
        if (cmd.getContinueCommand() != null) {
          execute(cmd.getContinueCommand(), DocsAdvancedEditorController.this);
        }
      }
    });
  }
  private void execute(final CurrentDocumentReloadCommand cmd) {
	showStatus("Reloading document...", true);
    Window.Location.assign("/docs?docid=" + currentDocument.getDocumentId() + "&hl=en");
  }
  private void execute(final CurrentDocumentCloseCommand cmd) {
	close();
  }
  private void execute(final CurrentDocumentRenameCommand cmd) {
    final String newName = Window.prompt("Enter new document name:", currentDocument.getTitle());
    if (newName != null && !newName.equals("")) {
      if (currentDocument.isStored()) {
        showStatus("Renaming...", false);
        docService.renameDocument(currentDocument.getDocumentId(), newName,
            new AsyncCallback<DocumentServiceEntry>(){
          public void onFailure(Throwable caught) {
            handleError(caught, cmd, null, 0);
          }
          public void onSuccess(DocumentServiceEntry result) {
            setDocument(result, true);
            clearStatus();
          }
        });
      } else {
        currentDocument.setTitle(newName);
        currentDocument.setIdentifier(newName.replaceAll("[^a-zA-Z0-9_\\-\\.]", ""));
        setDocument(currentDocument, false);
      }
    }
  }
  private void execute(final CurrentDocumentDeleteCommand cmd) {
    if (Window.confirm("This document will be deleted and closed.")) {
      if (currentDocument.isStored()) {
        showStatus("Deleting document...", true);
        docService.deleteDocument(currentDocument.getDocumentId(), "*",
              new AsyncCallback<Boolean>() {
          public void onFailure(Throwable caught) {
            handleError(caught, cmd, null, 0);
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
  }
  private void execute(final CurrentDocumentLoadContentsCommand cmd) {
    showStatus("Loading document contents...", true);
    docService.getDocumentContents(currentDocument.getDocumentId(),
        new AsyncCallback<String>() {
      public void onFailure(Throwable caught) {
        handleError(caught, cmd, null, 1);
      }
      public void onSuccess(String result) {
        clearStatus();
        loadEditor(result);
      }
    });
  }
  private void execute(final CurrentDocumentLoadCommonContentsCommand cmd) {
    showStatus("Loading document contents...", true);
    docService.getCommonContents("default",
        new AsyncCallback<String>() {
      public void onFailure(Throwable caught) {
        handleError(caught, cmd, null, 1);
      }
      public void onSuccess(String result) {
        clearStatus();
        loadEditor(result);
      }
    });
  }
  private void execute(final CurrentDocumentCompileLocalCommand cmd) {
	Window.alert("Starting local service...");
	final ClsiLocalService svc = new ClsiLocalService("/editor/");
	svc.start(new ReadyCallback() {
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("On ready failure: " + caught.getMessage());
		}
		@Override
		public void onSuccess(Boolean result) {
			Window.alert("On ready succeeded.");
	    	svc.compile("name", "contents", "primary", null, "compiler", "format", new CompileCallback() {
				@Override
				public void onFailure(Throwable caught) {
				  Window.alert("Local compile command failed: " + caught.getMessage());
				}
				@Override
				public void onSuccess(ClsiServiceCompileResponse result) {
				  Window.alert("Local compile command succeeded.");
				}
	    	});
		}
	});
	  }
  private void execute(final CurrentDocumentRevisionHistoryCommand cmd) {
    Window.open("http://docs.google.com/Revs?id=" +
        currentDocument.getDocumentId() + "&tab=revlist",
        currentDocument.getDocumentId(), "");
  }
  private void execute(final SystemUnstarDocumentCommand cmd) {
    docService.setDocumentStarred(cmd.getDocumentId(),
        false,
        new AsyncCallback<Boolean>(){
          public void onFailure(Throwable caught) {
            handleError(caught, cmd, null, 1);
          }
          public void onSuccess(Boolean result) {
          }
    });
  }
  private void execute(final SystemStarDocumentCommand cmd) {
    docService.setDocumentStarred(
		cmd.getDocumentId(),
        true,
        new AsyncCallback<Boolean>(){
          public void onFailure(Throwable caught) {
            handleError(caught, cmd, null, 1);
          }
          public void onSuccess(Boolean result) {
          }
        }
    );
  }
  private void execute(final SystemListDocumentsCommand cmd, final Object source) {
	if (source instanceof FileListDialog) {
      final AsyncInstantiationCallback<FileListDialog> aic = new AsyncInstantiationCallback<FileListDialog>() {
		  @Override
		  public void onFailure(Throwable caught) {
		    handleAsyncLoadError(caught);
		  }
		  @Override
		  public void onSuccess(FileListDialog dialog) {
            dialog.setEntries(allDocuments);
            dialog.showEntries();
		  }
      };
	  if (cmd.isUseCache() && allDocuments != null) {
	    FileListDialog.get(DocsAdvancedEditorController.this, aic);
	  } else {
        docService.getDocuments(false, new AsyncCallback<DocumentServiceEntry[]>() {
          public void onFailure(Throwable caught) {
            handleError(caught, cmd, null, 1);
          }
          public void onSuccess(DocumentServiceEntry[] result) {
            allDocuments = result;
    	    FileListDialog.get(DocsAdvancedEditorController.this, aic);
          }
        });
	  }
	} else if(source instanceof ResourcesDialog) {
	  final AsyncInstantiationCallback<ResourcesDialog> aic = new AsyncInstantiationCallback<ResourcesDialog>() {
		  @Override
		  public void onFailure(Throwable caught) {
		    handleAsyncLoadError(caught);
		  }
		  @Override
		  public void onSuccess(ResourcesDialog dialog) {
		    dialog.setEntries(allDocuments, currentDocument.getDocumentId());
		  }
	  };
	  if (cmd.isUseCache() && allDocuments != null) {
	    ResourcesDialog.get(DocsAdvancedEditorController.this, aic);
	  } else {
	    docService.getDocuments(false, new AsyncCallback<DocumentServiceEntry[]>() {
	      public void onFailure(Throwable caught) {
	        handleError(caught, cmd, null, 1);
	      }
	      public void onSuccess(DocumentServiceEntry[] result) {
	        allDocuments = result;
	  	    ResourcesDialog.get(DocsAdvancedEditorController.this, aic);
	      }
	    });
      }
	}
  }
  private void execute(final SystemOpenDocumentCommand cmd) {
    Window.open("http://docs.google.com/Doc?docid=" + cmd.getDocumentId() + "&hl=en", cmd.getDocumentId(), "");
  }
  private void execute(final SystemShowDialogCommand cmd) {
	Class<?> type = cmd.getDialogType();
	if (type == PreferencesDialog.class) {
	  PreferencesDialog.get(this, settings.isUseAutoSave(), settings.getAutoSaveInterval(),
	      new AsyncInstantiationCallback<PreferencesDialog>(){
			@Override
			public void onFailure(Throwable caught) {
			  handleAsyncLoadError(caught);
			}
			@Override
			public void onSuccess(PreferencesDialog dialog) {
              dialog.center();
			}
	  });
	} else if(type == CompilerSettingsDialog.class) {
	  CompilerSettingsDialog.get(this, null,
	      new AsyncInstantiationCallback<CompilerSettingsDialog>(){
			@Override
			public void onFailure(Throwable caught) {
			  handleAsyncLoadError(caught);
			}
			@Override
			public void onSuccess(CompilerSettingsDialog dialog) {
              dialog.center();
			}
	  });
	} else if (type == AboutDialog.class) {
	  AboutDialog.get(this,
		  new AsyncInstantiationCallback<AboutDialog>(){
			@Override
			public void onFailure(Throwable caught) {
			  handleAsyncLoadError(caught);
			}
			@Override
			public void onSuccess(AboutDialog dialog) {
              dialog.center();
			}
	  });
	} else if (type == FileListDialog.class) {
	  FileListDialog.get(this, new AsyncInstantiationCallback<FileListDialog>() {
		@Override
		public void onFailure(Throwable caught) {
		  handleAsyncLoadError(caught);
		}
		@Override
		public void onSuccess(FileListDialog dialog) {
		  dialog.center();
		}
	  });
	} else if (type == ResourcesDialog.class) {
	  ResourcesDialog.get(this, new AsyncInstantiationCallback<ResourcesDialog>() {
		@Override
		public void onFailure(Throwable caught) {
		  handleAsyncLoadError(caught);
		}
		@Override
		public void onSuccess(ResourcesDialog dialog) {
		  dialog.center();
		}
	  });
	  
	}
  }
  private void execute(final SystemUploadDocumentsCommand cmd) {
    Window.open("http://docs.google.com/DocAction?action=updoc&hl=en",
        "UploadDocuments", "");
  }
  private void execute(final SystemLoadDocumentCommand cmd) {
    showStatus("Loading document...", true);
    docService.getDocument(cmd.getDocumentId(), new AsyncCallback<DocumentServiceEntry>() {
      public void onFailure(Throwable caught) {
        handleError(caught, cmd, new NewDocumentLoadCommand(), 1);
      }
      public void onSuccess(DocumentServiceEntry result) {
    	if (result == null) {
    	  handleError(new Exception("No document found with the ID " + cmd.getDocumentId()), cmd,
    	      new NewDocumentLoadCommand(), 0);
    	} else {
          setDocument(result, false);
          clearStatus();
          execute(new CurrentDocumentLoadContentsCommand(), DocsAdvancedEditorController.this);
    	}
      }
    });
  }
  private void execute(final SystemSetResourcesCommand cmd) {
	SystemSetResourcesCommand ssrCmd = (SystemSetResourcesCommand) cmd;
	settings.setResources(ssrCmd.getResources());
	settings.setPrimaryResource(ssrCmd.getPrimaryResource());
  }
  private void execute(final SystemRefreshResourcesCommand cmd) {
    showStatus("Refreshing resources...", false);
    docService.getDocuments(false, new AsyncCallback<DocumentServiceEntry[]>() {
        public void onFailure(Throwable caught) {
          handleError(caught, cmd, null, 1);
        }
        public void onSuccess(DocumentServiceEntry[] result) {
          allDocuments = result;
      	  if (settings.getResources().size() > 0) {
            ArrayList<DocumentServiceEntry> resources = new ArrayList<DocumentServiceEntry>();
            DocumentServiceEntry primary = null;
            for (DocumentServiceEntry res : settings.getResources()) {
              for (DocumentServiceEntry doc : result) {
        	    if (doc.equals(res)) {
        	      resources.add(doc);
        	    }
        	    if (doc.equals(settings.getPrimaryResource())) {
        	      primary = doc;
        	    }
              }
            }
            settings.getResources().clear();
            settings.setResources(resources);
            if (primary != null) {
              settings.setPrimaryResource(primary);
            }
      	  }
          clearStatus();
        }
    });
  }
  private void execute(final SystemToggleReuseToolbarWindowsCommand cmd) {
	settings.setReuseToolbarWindows(!settings.isReuseToolbarWindows());
	if (settings.isReuseToolbarWindows()) {
	  app.getMenu().setMenuItemIcon("Reuse toolbar windows", Icons.editorIcons.CheckBlack());
	} else {
  	  app.getMenu().setMenuItemIcon("Reuse toolbar windows", Icons.editorIcons.Blank());
	}
  }
  private void execute(final SystemApplyCompilerSettingsCommand cmd) {
	if (cmd.isUseDefault()) {
	  settings.setClsiAsyncPath("http://clsi.latexlab.org/clsi/out/<id>/");
	  settings.setClsiServiceToken(CLSI_TOKEN);
	  settings.setClsiServiceId(currentUser.getToken());
	  settings.setClsiServiceUrl("http://clsi.latexlab.org/clsi/service.php");
	  settings.setCompilerName("latex");
	} else {
	  settings.setClsiAsyncPath(cmd.getClsiAsyncPath());
	  settings.setClsiServiceToken(cmd.getClsiServiceToken());
	  settings.setClsiServiceUrl(cmd.getClsiServiceUrl());
	  settings.setCompilerName(cmd.getCompilerName());
	}
	clsiService.setAsyncPath(settings.getClsiAsyncPath());
	clsiService.setServiceUrl(settings.getClsiServiceUrl());
	clsiService.setToken(settings.getClsiServiceToken());
	clsiService.setId(settings.getClsiServiceId());
	settings.setHasCompilerSettings(true);
  }
  private void execute(final SystemApplyPreferencesCommand cmd) {
	settings.setUseAutoSave(cmd.isUseAutoSave());
	settings.setAutoSaveInterval(cmd.getAutoSaveIntervalMillisecs());
	if (settings.isUseAutoSave()) {
      scheduler.scheduleRepeating("AutoSave", settings.getAutoSaveInterval(),
    	  new CurrentDocumentSaveCommand());
	} else {
	  scheduler.cancelRepeating("AutoSave");
	}
  }
  private void execute(final SystemToggleFullScreenCommand cmd) {
    app.toggleFullScreen();
  }
  private void execute(final SystemToggleToolbarCommand cmd) {
	app.toggleToolbar(cmd.getName(), settings.isReuseToolbarWindows());
  }
  private void execute(final SystemToggleColorSyntaxCommand cmd) {
	if (app.getEditor().getColorSyntax()) {
	  app.getMenu().setMenuItemIcon("Highlight Syntax", Icons.editorIcons.Blank());
	  app.getEditor().setColorSyntax(false);
	  settings.setColorSyntax(false);
	} else {
	  app.getMenu().setMenuItemIcon("Highlight Syntax", Icons.editorIcons.CheckBlack());
	  app.getEditor().setColorSyntax(true);
	  settings.setColorSyntax(true);
	}
  }
  private void execute(final SystemToggleLineNumbersCommand cmd) {
  	if (app.getEditor().getShowLineNumbers()) {
  	  app.getMenu().setMenuItemIcon("Show Line Numbers", Icons.editorIcons.Blank());
  	  app.getEditor().setShowLineNumbers(false);
  	  settings.setShowLineNumbers(false);
  	} else {
  	  app.getMenu().setMenuItemIcon("Show Line Numbers", Icons.editorIcons.CheckBlack());
  	  app.getEditor().setShowLineNumbers(true);
  	  settings.setShowLineNumbers(true);
  	}
  }
  private void execute(final SystemToggleWrapTextCommand cmd) {
	if (app.getEditor().getWrapText()) {
	  app.getMenu().setMenuItemIcon("Wrap Text", Icons.editorIcons.Blank());
	  app.getEditor().setWrapText(false);
	  settings.setWrapText(false);
	} else {
	  app.getMenu().setMenuItemIcon("Wrap Text", Icons.editorIcons.CheckBlack());
	  app.getEditor().setWrapText(true);
	  settings.setWrapText(true);
	}
  }
  private void execute(final SystemToggleSpellcheckCommand cmd) {
	if (app.getEditor().getUseSpellChecker()) {
	  app.getMenu().setMenuItemIcon("Check Spelling", Icons.editorIcons.Blank());
	  app.getEditor().setUseSpellChecker(false);
	} else {
	  app.getMenu().setMenuItemIcon("Check Spelling", Icons.editorIcons.CheckBlack());
	  app.getEditor().setUseSpellChecker(true);
	}
  }
  private void execute(final SystemSignOutCommand cmd) {
    showStatus("Signing out...", true);
    docService.logout(new AsyncCallback<String>() {
		@Override
		public void onFailure(Throwable caught) {
          handleError(caught, cmd, null, 1);
		}
		@Override
		public void onSuccess(String result) {
          clearStatus();
          Window.Location.replace(result);
		}
    });
  }
  private void execute(final SystemPasteCommand cmd) {
	String pasteText = cmd.getText();
	String selText = app.getEditor().getSelectedText();
	if (selText != null) {
	  pasteText = pasteText.replace("<text here>", selText);
	}
	app.getEditor().replaceSelection(pasteText);
  }
  private void execute(final SystemUndoCommand cmd) {
  	app.getEditor().undo();
  }
  private void execute(final SystemRedoCommand cmd) {
  	app.getEditor().redo();
  }
  private void execute(final SystemOpenPageCommand cmd) {
	if (cmd.isUseCurrentWindow()) {
	  Window.Location.assign(cmd.getUrl());
	} else {
	  Window.open(cmd.getUrl(), cmd.getName(), "");
	}
  }
  private void execute(final SystemSetPerspectiveCommand cmd) {
	app.setPerspective(cmd.getView());
  }
  private void execute(final SystemViewPageCommand cmd) {
	app.getPreviewer().showPage(cmd.getPageNumber());
	app.getFooter().getPageViewer().setPage(cmd.getPageNumber());
  }
  private void execute(final SystemViewPageIndexCommand cmd) {
  	app.getPreviewer().showPageIndex();
  }
  private void execute(final SystemViewPageZoomInCommand cmd) {
  	app.getPreviewer().zoomIn();
  }
  private void execute(final SystemViewPageZoomOutCommand cmd) {
  	app.getPreviewer().zoomOut();
  }
  private void execute(final CurrentDocumentChangedCommand cmd) {
    if (currentDocument != null) {
	  currentDocument.setDirty(true);
	}
  }
  private void execute(final Command cmd, Object source) {
    switch (cmd.getCommandId()) {
      case SystemUnstarDocumentCommand.serialUid: execute((SystemUnstarDocumentCommand) cmd); break;
      case SystemStarDocumentCommand.serialUid: execute((SystemStarDocumentCommand) cmd); break;
      case SystemListDocumentsCommand.serialUid: execute((SystemListDocumentsCommand) cmd, source); break;
      case SystemOpenDocumentCommand.serialUid: execute((SystemOpenDocumentCommand) cmd); break;
      case NewDocumentStartCommand.serialUid: execute((NewDocumentStartCommand) cmd); break;
      case CurrentDocumentChangedCommand.serialUid: execute((CurrentDocumentChangedCommand) cmd); break;
      case CurrentDocumentExportCommand.serialUid: execute((CurrentDocumentExportCommand) cmd); break;
      case CurrentDocumentCompileCommand.serialUid: execute((CurrentDocumentCompileCommand) cmd); break;
      case CurrentDocumentCopyCommand.serialUid: execute((CurrentDocumentCopyCommand) cmd); break;
      case CurrentDocumentSaveCommand.serialUid: execute((CurrentDocumentSaveCommand) cmd, source == scheduler); break;
      case CurrentDocumentSaveAndCloseCommand.serialUid: execute((CurrentDocumentSaveAndCloseCommand) cmd); break;
      case CurrentDocumentRefreshCommand.serialUid: execute((CurrentDocumentRefreshCommand) cmd); break;
      case CurrentDocumentReloadCommand.serialUid: execute((CurrentDocumentReloadCommand) cmd); break;
      case CurrentDocumentCloseCommand.serialUid: execute((CurrentDocumentCloseCommand) cmd); break;
      case CurrentDocumentRenameCommand.serialUid: execute((CurrentDocumentRenameCommand) cmd); break;
      case CurrentDocumentDeleteCommand.serialUid: execute((CurrentDocumentDeleteCommand) cmd); break;
      case CurrentDocumentLoadContentsCommand.serialUid: execute((CurrentDocumentLoadContentsCommand) cmd); break;
      case CurrentDocumentLoadCommonContentsCommand.serialUid: execute((CurrentDocumentLoadCommonContentsCommand) cmd); break;
      case CurrentDocumentRevisionHistoryCommand.serialUid: execute((CurrentDocumentRevisionHistoryCommand) cmd); break;
      case SystemUploadDocumentsCommand.serialUid: execute((SystemUploadDocumentsCommand) cmd); break;
      case NewDocumentLoadCommand.serialUid: execute((NewDocumentLoadCommand) cmd); break;
      case SystemLoadDocumentCommand.serialUid: execute((SystemLoadDocumentCommand) cmd); break;
      case SystemShowDialogCommand.serialUid: execute((SystemShowDialogCommand) cmd); break;
      case SystemSetResourcesCommand.serialUid: execute((SystemSetResourcesCommand) cmd); break;
      case SystemRefreshResourcesCommand.serialUid: execute((SystemRefreshResourcesCommand) cmd); break;
      case SystemToggleReuseToolbarWindowsCommand.serialUid: execute((SystemToggleReuseToolbarWindowsCommand) cmd); break;
      case SystemApplyCompilerSettingsCommand.serialUid: execute((SystemApplyCompilerSettingsCommand) cmd); break;
      case SystemApplyPreferencesCommand.serialUid: execute((SystemApplyPreferencesCommand) cmd); break;
      case SystemToggleFullScreenCommand.serialUid: execute((SystemToggleFullScreenCommand) cmd); break;
      case SystemToggleToolbarCommand.serialUid: execute((SystemToggleToolbarCommand) cmd); break;
      case SystemToggleColorSyntaxCommand.serialUid: execute((SystemToggleColorSyntaxCommand) cmd); break;
      case SystemToggleLineNumbersCommand.serialUid: execute((SystemToggleLineNumbersCommand) cmd); break;
      case SystemToggleWrapTextCommand.serialUid: execute((SystemToggleWrapTextCommand) cmd); break;
      case SystemToggleSpellcheckCommand.serialUid: execute((SystemToggleSpellcheckCommand) cmd); break;
      case SystemSignOutCommand.serialUid: execute((SystemSignOutCommand) cmd); break;
      case SystemPasteCommand.serialUid: execute((SystemPasteCommand) cmd); break;
      case SystemUndoCommand.serialUid: execute((SystemUndoCommand) cmd); break;
      case SystemRedoCommand.serialUid: execute((SystemRedoCommand) cmd); break;
      case SystemOpenPageCommand.serialUid: execute((SystemOpenPageCommand) cmd); break;
      case SystemSetPerspectiveCommand.serialUid: execute((SystemSetPerspectiveCommand) cmd); break;
      case CurrentDocumentCompileLocalCommand.serialUid: execute((CurrentDocumentCompileLocalCommand) cmd); break;
      case SystemViewPageCommand.serialUid: execute((SystemViewPageCommand) cmd); break;
      case SystemViewPageIndexCommand.serialUid: execute((SystemViewPageIndexCommand) cmd); break;
      case SystemViewPageZoomInCommand.serialUid: execute((SystemViewPageZoomInCommand) cmd); break;
      case SystemViewPageZoomOutCommand.serialUid: execute((SystemViewPageZoomOutCommand) cmd); break;
      default:
        Window.alert("Not implemented");
        break;
    }
  }
  
  /************************************************************************************
   ***************************** End Controller Logic *********************************
   ************************************************************************************/
  
  /**
   * Handle a command event by executing the triggered command.
   * 
   * @param e the command event
   */
  @Override
  public void onCommand(CommandEvent e) {
	Object src = e.getSource();
	if (src == null) {
	  src = this;
	}
	execute(e.getCommand(), src);
  }

  /**
   * Retrieves the signed authorization headers for the user-selected resources.
   * 
   * @param callback the asynchronous callback which will receive the resource references
   */
  private void getResourceReferences(final AsyncCallback<ClsiResourceReference[]> callback) {
	final int size = settings.getResources().size();
	String[] docIds = new String[size];
	for (int i=0; i<size; i++) {
	  docIds[i] = settings.getResources().get(i).getContentLink();
	}
	if (docIds.length > 0) {
	  docService.getDocumentContentUrls(docIds,
	    new AsyncCallback<DocumentSignedLocation[]>() {
		  @Override
		  public void onFailure(Throwable caught) {
		    callback.onFailure(caught);
		  }
		  @Override
		  public void onSuccess(DocumentSignedLocation[] result) {
			ClsiResourceReference[] refs = new ClsiResourceReference[size];
		    for (int i=0; i<size; i++) {
		      DocumentSignedLocation dsl = result[i];
		      DocumentServiceEntry entry = settings.getResources().get(i);
		      refs[i] = ClsiResourceReference.newInstance(entry.getDocumentId(),
		          entry.getIdentifier(), dsl.getUrl(), dsl.getAuthorization(),
		          entry.getContentType(), null, entry.getEdited());
		    }
		    callback.onSuccess(refs);
		  }
	  });
	} else {
	  callback.onSuccess(new ClsiResourceReference[0]);
	}
  }
  
  /**
   * Loads the advanced text editor (a CodeMirror instance).
   * @TODO: currently, if dev mode, a dev dialog is displayed.
   * This should be moved elsewhere
   * 
   * @param value the text value to populate into the editor
   */
  private void loadEditor(final String value) {
    showStatus("Loading advanced editor...", true);
    app.loadEditor(settings.isColorSyntax(),
        settings.isWrapText(),
        settings.isShowLineNumbers(),
        settings.isCheckSpelling(),
        new LoadHandler() {
            @Override
    	    public void onLoad(LoadEvent event) {
              app.getEditor().setText(value);
              if (currentDocument != null) {
                currentDocument.setDirty(false);
              }
              clearStatus();
			  if (settings.isDevelopment()) {
			    DevelopmentInfoDialog.get(DocsAdvancedEditorController.this, new AsyncInstantiationCallback<DevelopmentInfoDialog>() {
				  @Override
				  public void onFailure(Throwable caught) {
				    handleAsyncLoadError(caught);
				  }
				  @Override
				  public void onSuccess(DevelopmentInfoDialog result) {
				    result.center();
				  }
			    });
			  }
    	    }
        }
    );
  }
  
  /**
   * Compiles the current document, with the user-selected resources, into a given format.
   * 
   * @param format the desired output format
   * @param callback the asynchronous callback which will receive the compiler response
   */
  private void compile(final String format, final CompileCallback callback) {
    getResourceReferences(new AsyncCallback<ClsiResourceReference[]>() {
		@Override
		public void onFailure(Throwable caught) {
		  callback.onFailure(caught);
		}
		@Override
		public void onSuccess(ClsiResourceReference[] result) {
		  String primary = currentDocument.getIdentifier();
		  if (settings.getPrimaryResource() != null) {
		    primary = settings.getPrimaryResource().getIdentifier();
		  }
	      clsiService.compile(currentDocument.getIdentifier(), app.getEditor().getText(),
	          primary, result, "latex", format, callback);
		}
    });
  }
  /**
   * Locks or unlocks the UI to disable or enable a given feature.
   * 
   * @param func the function to lock or unlock
   * @param locked whether to lock or unlock the function
   */
  private void setFunctionLock(LockFunction func, boolean locked) {
	switch (func) {
	case SAVE:
	  currentDocument.setSaving(locked);
	  break;
	case COMPILE:
	  currentDocument.setCompiling(locked);
	  break;
	}
	app.setFunctionLock(func, locked);
  }
}
