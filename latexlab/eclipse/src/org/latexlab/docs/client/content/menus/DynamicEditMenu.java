package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemRedoCommand;
import org.latexlab.docs.client.commands.SystemShowDialogCommand;
import org.latexlab.docs.client.commands.SystemUndoCommand;
import org.latexlab.docs.client.content.dialogs.DynamicPreferencesDialog;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Contains an Edit menu with on-demand loading.
 */
public class DynamicEditMenu extends DynamicMenuBar {

  protected static DynamicEditMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   */
  public static DynamicEditMenu get() {
    if (instance == null) {
      instance = new DynamicEditMenu();
    }
    return instance;
  }
  
  /**
   * Constructs an edit menu.
   */
  protected DynamicEditMenu() {
    super(true);
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
			new ExtendedMenuItem(Icons.editorIcons.Undo(), "Undo", new SystemUndoCommand()),
			new ExtendedMenuItem(Icons.editorIcons.Redo(), "Redo", new SystemRedoCommand()),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Preferences...", new SystemShowDialogCommand(DynamicPreferencesDialog.class)),
		  });
		}
    });
  }

}
