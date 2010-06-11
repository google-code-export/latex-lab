package org.latexlab.docs.editor.simple.client;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.latexlab.clsi.client.remote.ClsiRemoteService;
import org.latexlab.docs.client.DocsEditorSettings;
import org.latexlab.docs.client.LatexLabService;
import org.latexlab.docs.client.LatexLabServiceAsync;
import org.latexlab.docs.client.commands.Command;
import org.latexlab.docs.client.commands.SystemLoadDocumentCommand;
import org.latexlab.docs.client.commands.NewDocumentLoadCommand;
import org.latexlab.docs.client.commands.SystemApplyCompilerSettingsCommand;
import org.latexlab.docs.client.content.dialogs.StaticErrorDialog;
import org.latexlab.docs.client.content.dialogs.StaticLoadingDialog;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.gdocs.DocumentServiceEntry;
import org.latexlab.docs.client.gdocs.DocumentService;
import org.latexlab.docs.client.gdocs.DocumentServiceAsync;
import org.latexlab.docs.client.gdocs.DocumentUser;
import org.latexlab.docs.client.parts.BodyPart;
import org.latexlab.docs.client.parts.HeaderPart;
import org.latexlab.docs.client.parts.OutputPart;
import org.latexlab.docs.client.parts.PreviewerPart;
import org.latexlab.docs.editor.simple.client.parts.EditorPart;
import org.latexlab.docs.editor.simple.client.parts.MenuPart;
import org.latexlab.docs.editor.simple.client.parts.ToolbarPart;

/**
 * The GDBE document editor module.
 */
@SuppressWarnings("unused")
public class DocsSimpleEditor implements EntryPoint, CommandHandler {

private final static String CLSI_TOKEN = "8d3id348f34wbvew234sf4df0098";
  private final DocumentServiceAsync docService = GWT.create(DocumentService.class);
  private ClsiRemoteService clsiService;
  private AbsolutePanel root;
  private BodyPart body;
  private EditorPart editor;
  private PreviewerPart previewer;
  private OutputPart output;
  private FlexTable contentPane;
  private HeaderPart header;
  private MenuPart menu;
  private ToolbarPart toolbar;
  private DocumentUser currentUser;
  private DocumentServiceEntry currentDocument;
  private DocumentServiceEntry[] allDocuments;
  
  private DocsEditorSettings settings;

  /**
   * Builds and initializes the editor module.
   */
  public void onModuleLoad() {
	clsiService = new ClsiRemoteService();
	docService.getUser(new AsyncCallback<DocumentUser>() {
		@Override
		public void onFailure(Throwable caught) {
		  Window.alert("Authentication Failure: " + caught.getMessage());
		}
		@Override
		public void onSuccess(DocumentUser result) {
  		  if (result == null) {
  		    if (GWT.isClient()) {
  			  //if running in dev mode, auto-authenticate with a test account
  			  docService.setUser("non.gwt.gdata@gmail.com", "CIuortn_DxDp3Zci", new AsyncCallback<DocumentUser>() {
  				  @Override
  				  public void onFailure(Throwable caught) {
  				  }
  				  @Override
  				  public void onSuccess(DocumentUser result) {
  	  		        currentUser = result;
  	                start();
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
	if (GWT.isClient()) {
	  //if running in dev mode, auto-authenticate with a test account
	  docService.setUser("non.gwt.gdata@gmail.com", "CIuortn_DxDp3Zci", new AsyncCallback<DocumentUser>() {
		  @Override
		  public void onFailure(Throwable caught) {
			checkAuthentication();
		  }
		  @Override
		  public void onSuccess(DocumentUser result) {
			checkAuthentication();
		  }
	  });
	} else {
	  checkAuthentication();
	}
  }
  
  public void checkAuthentication() {
  }
  
  public void start() {
	settings = new DocsEditorSettings();
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
    header.setAuthor(currentUser.getEmail());
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
    
    PickupDragController dragController = new PickupDragController(root, true);
    dragController.setBehaviorConstrainedToBoundaryPanel(true);
    dragController.setBehaviorMultipleSelection(false);
    dragController.setBehaviorDragStartSensitivity(1);
    execute(new SystemApplyCompilerSettingsCommand());
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
      execute(new NewDocumentLoadCommand());
    } else {
      execute(new SystemLoadDocumentCommand(documentId));
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
  private void setDocument(DocumentServiceEntry doc) {
    currentDocument = doc;
    header.setTitle(doc.getTitle());
    Window.setTitle(doc.getTitle() + " - LaTeX Lab");
    if (currentDocument.isStored()) {
      header.setInfo(currentDocument.getDocumentId(), currentDocument.getEdited(), currentDocument.getEditor());
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
      StaticLoadingDialog loadingDialog = StaticLoadingDialog.get();
      loadingDialog.setMessage(message);
      loadingDialog.center();
    } else {
      header.setStatus(message);
    }
  }
  
  /**
   * Clears and hides any visible status messages.
   */
  private void clearStatus() {
	StaticLoadingDialog.get().hide();
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
      StaticErrorDialog errorDialog = StaticErrorDialog.get(this);
      errorDialog.update(error, command, alternate);
      errorDialog.center();
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
  }
  
}