package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.MenuItem;

/**
 * Contains a Code Environment menu with on-demand loading.
 */
public class DynamicCodeEnvironmentMenu extends DynamicMenuBar {

  protected static DynamicCodeEnvironmentMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   * 
   * @param commandSource the command source.
   */
  public static DynamicCodeEnvironmentMenu get(HasCommandHandlers commandSource) {
    if (instance == null) {
      instance = new DynamicCodeEnvironmentMenu(commandSource);
    }
    return instance;
  }
  
  /**
   * Constructs a code Environment menu.
   * 
   * @param commandSource the command source
   */
  protected DynamicCodeEnvironmentMenu(HasCommandHandlers commandSource) {
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
			DynamicCodeEnvironmentMenu.this.createItem(Icons.editorIcons.Blank(), "Code", new SystemPasteCommand("\\begin{verbatim}\n\\end{verbatim}")),
			DynamicCodeEnvironmentMenu.this.createItem(Icons.editorIcons.Blank(), "Code with visible White Spaces", new SystemPasteCommand("\\begin{verbatim*}\n\\end{verbatim*}")),
			DynamicCodeEnvironmentMenu.this.createItem(Icons.editorIcons.Blank(), "Embedded Code", new SystemPasteCommand("\\verb||")),
			DynamicCodeEnvironmentMenu.this.createItem(Icons.editorIcons.Blank(), "Embedded Code with visible White Spaces", new SystemPasteCommand("\\verb*||"))
		  });
		}
    });
  }

}
