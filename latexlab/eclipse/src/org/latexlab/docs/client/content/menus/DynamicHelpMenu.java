package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemOpenPageCommand;
import org.latexlab.docs.client.commands.SystemShowDialogCommand;
import org.latexlab.docs.client.content.dialogs.DynamicAboutDialog;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Contains an Help menu with on-demand loading.
 */
public class DynamicHelpMenu extends DynamicMenuBar {

  protected static DynamicHelpMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   */
  public static DynamicHelpMenu get() {
    if (instance == null) {
      instance = new DynamicHelpMenu();
    }
    return instance;
  }
  
  /**
   * Constructs an help menu.
   */
  protected DynamicHelpMenu() {
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
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Using LaTeX Lab", new SystemOpenPageCommand("Help",  "http://code.google.com/p/latex-lab/wiki/UsingLaTeXLab", false)),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Using a custom CLSI server", new SystemOpenPageCommand("Help", "http://code.google.com/p/latex-lab/wiki/UsingPrivateCompiler", false)),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Submit bug or feature request", new SystemOpenPageCommand("IssueTracker", "http://code.google.com/p/latex-lab/issues/entry", false)),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "About", new SystemShowDialogCommand(DynamicAboutDialog.class))
		  });
		}
    });
  }

}
