package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.content.latex.LatexCommand;
import org.latexlab.docs.client.content.latex.LatexCommandSet;
import org.latexlab.docs.client.content.latex.SetArrows;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.MenuItem;

/**
 * Contains a LaTeX Arrows menu with on-demand loading.
 */
public class DynamicLatexArrowsMenu extends DynamicMenuBar {

  protected static DynamicLatexArrowsMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   * 
   * @param commandSource the command source.
   */
  public static DynamicLatexArrowsMenu get(HasCommandHandlers commandSource) {
    if (instance == null) {
      instance = new DynamicLatexArrowsMenu(commandSource);
    }
    return instance;
  }
  
  /**
   * Constructs a latex arrows menu.
   * 
   * @param commandSource the command source
   */
  protected DynamicLatexArrowsMenu(HasCommandHandlers commandSource) {
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
		  LatexCommandSet set = SetArrows.get();
		  LatexCommand[] cmds = set.getCommands();
		  MenuItem[] items = new MenuItem[cmds.length];
		  for (int i=0; i<cmds.length; i++) {
			LatexCommand cmd = cmds[i];
			items[i] = DynamicLatexArrowsMenu.this.createItem(cmd.getIcon(), cmd.getTitle(), new SystemPasteCommand(cmd.getText()));
		  }
		  callback.onSuccess(items);
		}
    });
  }

}
