package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemToggleColorSyntaxCommand;
import org.latexlab.docs.client.commands.SystemToggleFullScreenCommand;
import org.latexlab.docs.client.commands.SystemToggleLineNumbersCommand;
import org.latexlab.docs.client.commands.SystemToggleReuseToolbarWindowsCommand;
import org.latexlab.docs.client.commands.SystemToggleSpellcheckCommand;
import org.latexlab.docs.client.commands.SystemToggleWrapTextCommand;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Contains a View menu with on-demand loading.
 */
public class DynamicViewMenu extends DynamicMenuBar {

  protected static DynamicViewMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   * 
   * @param commandSource the command source.
   */
  public static DynamicViewMenu get(HasCommandHandlers commandSource) {
    if (instance == null) {
      instance = new DynamicViewMenu(commandSource);
    }
    return instance;
  }
  
  /**
   * Constructs a view menu.
   * 
   * @param commandSource the command source
   */
  protected DynamicViewMenu(HasCommandHandlers commandSource) {
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
		    new ExtendedMenuItem(Icons.editorIcons.Blank(), "Toolbars", DynamicToolbarsMenu.get(commandSource)),
		    null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Check Spelling", new SystemToggleSpellcheckCommand()),
			new ExtendedMenuItem(Icons.editorIcons.CheckBlack(), "Highlight Syntax", new SystemToggleColorSyntaxCommand()),
			new ExtendedMenuItem(Icons.editorIcons.CheckBlack(), "Wrap Text", new SystemToggleWrapTextCommand()),
			new ExtendedMenuItem(Icons.editorIcons.CheckBlack(), "Show Line Numbers", new SystemToggleLineNumbersCommand()),
			null,
			new ExtendedMenuItem(Icons.editorIcons.CheckBlack(), "Reuse toolbar windows", new SystemToggleReuseToolbarWindowsCommand()),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Full-screen mode", new SystemToggleFullScreenCommand())
		  });
		}
    });
  }

}
