package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemNotImplementedCommand;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Contains a Floating Environment menu with on-demand loading.
 */
public class DynamicFloatingEnvironmentMenu extends DynamicMenuBar {

  protected static DynamicFloatingEnvironmentMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   */
  public static DynamicFloatingEnvironmentMenu get() {
    if (instance == null) {
      instance = new DynamicFloatingEnvironmentMenu();
    }
    return instance;
  }
  
  /**
   * Constructs a floating environment menu.
   */
  protected DynamicFloatingEnvironmentMenu() {
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
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Figure...", new SystemNotImplementedCommand()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Table...", new SystemNotImplementedCommand()),
		  });
		}
    });
  }

}
