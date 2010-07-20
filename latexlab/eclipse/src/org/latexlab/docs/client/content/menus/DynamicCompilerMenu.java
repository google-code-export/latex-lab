package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.CurrentDocumentCompileCommand;
import org.latexlab.docs.client.commands.SystemShowDialogCommand;
import org.latexlab.docs.client.content.dialogs.DynamicCompilerSettingsDialog;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Contains a Compiler menu with on-demand loading.
 */
public class DynamicCompilerMenu extends DynamicMenuBar {

  protected static DynamicCompilerMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   */
  public static DynamicCompilerMenu get() {
    if (instance == null) {
      instance = new DynamicCompilerMenu();
    }
    return instance;
  }
  
  /**
   * Constructs a compiler menu.
   */
  protected DynamicCompilerMenu() {
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
			new ExtendedMenuItem(Icons.editorIcons.Compile(), "Compile...", new CurrentDocumentCompileCommand()),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Settings...", new SystemShowDialogCommand(DynamicCompilerSettingsDialog.class))
		  });
		}
    });
  }

}
