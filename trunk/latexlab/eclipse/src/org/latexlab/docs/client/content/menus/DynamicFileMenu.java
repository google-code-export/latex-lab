package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.CurrentDocumentCopyCommand;
import org.latexlab.docs.client.commands.CurrentDocumentDeleteCommand;
import org.latexlab.docs.client.commands.CurrentDocumentRenameCommand;
import org.latexlab.docs.client.commands.CurrentDocumentRevisionHistoryCommand;
import org.latexlab.docs.client.commands.CurrentDocumentSaveCommand;
import org.latexlab.docs.client.commands.NewDocumentStartCommand;
import org.latexlab.docs.client.commands.SystemShowDialogCommand;
import org.latexlab.docs.client.commands.SystemUploadDocumentsCommand;
import org.latexlab.docs.client.content.dialogs.DynamicFileListDialog;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Contains a File menu with on-demand loading.
 */
public class DynamicFileMenu extends DynamicMenuBar {

  protected static DynamicFileMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   * 
   * @param commandSource the command source.
   */
  public static DynamicFileMenu get(HasCommandHandlers commandSource) {
    if (instance == null) {
      instance = new DynamicFileMenu(commandSource);
    }
    return instance;
  }
  
  /**
   * Constructs a file menu.
   * 
   * @param commandSource the command source
   */
  protected DynamicFileMenu(HasCommandHandlers commandSource) {
    super(true, commandSource);
  }

  /**
   * Asynchronously loads the MenuBar's sub items.
   * 
   * @param callback the callback carrying the sub items
   */
  @Override
  protected void getSubMenu(final AsyncCallback<ExtendedMenuItem[]> callback) {
    GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
	      callback.onFailure(reason);
		}
		@Override
		public void onSuccess() {
		  callback.onSuccess(new ExtendedMenuItem[] {
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "New", new NewDocumentStartCommand()),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Open", new SystemShowDialogCommand(DynamicFileListDialog.class)),
			new ExtendedMenuItem(Icons.editorIcons.Save(), "Save", new CurrentDocumentSaveCommand()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Save as new copy", new CurrentDocumentCopyCommand()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Rename...", new CurrentDocumentRenameCommand()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Delete...", new CurrentDocumentDeleteCommand()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Revision History", new CurrentDocumentRevisionHistoryCommand()),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Export...", DynamicExportMenu.get(commandSource)),
			null,
			new ExtendedMenuItem(Icons.editorIcons.UploadDocument(), "Upload Files...", new SystemUploadDocumentsCommand()),
		  });
		}
    });
  }

}
