package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemRefreshResourcesCommand;
import org.latexlab.docs.client.commands.SystemShowDialogCommand;
import org.latexlab.docs.client.content.dialogs.DynamicResourcesDialog;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Contains a Project menu with on-demand loading.
 */
public class DynamicProjectMenu extends DynamicMenuBar {

  protected static DynamicProjectMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   */
  public static DynamicProjectMenu get() {
    if (instance == null) {
      instance = new DynamicProjectMenu();
    }
    return instance;
  }
  
  /**
   * Constructs a project menu.
   */
  protected DynamicProjectMenu() {
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
			new ExtendedMenuItem(Icons.editorIcons.Resources(), "Project Resources", new SystemShowDialogCommand(DynamicResourcesDialog.class)),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Refresh Project Resources", new SystemRefreshResourcesCommand()),
		  });
		}
    });
  }

}
