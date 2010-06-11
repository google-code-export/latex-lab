package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemRedoCommand;
import org.latexlab.docs.client.commands.SystemShowDialogCommand;
import org.latexlab.docs.client.commands.SystemUndoCommand;
import org.latexlab.docs.client.content.dialogs.DynamicPreferencesDialog;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.MenuItem;

/**
 * Contains an Edit menu with on-demand loading.
 */
public class DynamicEditMenu extends DynamicMenuBar {

  protected static DynamicEditMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   * 
   * @param commandSource the command source.
   */
  public static DynamicEditMenu get(HasCommandHandlers commandSource) {
    if (instance == null) {
      instance = new DynamicEditMenu(commandSource);
    }
    return instance;
  }
  
  /**
   * Constructs an edit menu.
   * 
   * @param commandSource the command source
   */
  protected DynamicEditMenu(HasCommandHandlers commandSource) {
    super(true, commandSource);
  }

  /**
   * Asynchronously loads the MenuBar's sub items.
   * 
   * @param callback the callback carrying the sub items
   */
  @Override
  protected void getSubMenu(final AsyncCallback<MenuItem[]> callback) {
    GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
	      callback.onFailure(reason);
		}
		@Override
		public void onSuccess() {
		  callback.onSuccess(new MenuItem[] {
			DynamicEditMenu.this.createItem(Icons.editorIcons.Undo(), "Undo", new SystemUndoCommand()),
			DynamicEditMenu.this.createItem(Icons.editorIcons.Redo(), "Redo", new SystemRedoCommand()),
			null,
			DynamicEditMenu.this.createItem(Icons.editorIcons.Blank(), "Preferences...", new SystemShowDialogCommand(DynamicPreferencesDialog.class)),
		  });
		}
    });
  }

}
