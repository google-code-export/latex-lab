package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.content.latex.LatexCommand;
import org.latexlab.docs.client.content.latex.LatexCommandSet;
import org.latexlab.docs.client.content.latex.SetAboveAndBelow;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Contains a LaTeX Above and Below menu with on-demand loading.
 */
public class DynamicLatexAboveAndBelowMenu extends DynamicMenuBar {

  protected static DynamicLatexAboveAndBelowMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   * 
   * @param commandSource the command source.
   */
  public static DynamicLatexAboveAndBelowMenu get(HasCommandHandlers commandSource) {
    if (instance == null) {
      instance = new DynamicLatexAboveAndBelowMenu(commandSource);
    }
    return instance;
  }
  
  /**
   * Constructs a latex above and below menu.
   * 
   * @param commandSource the command source
   */
  protected DynamicLatexAboveAndBelowMenu(HasCommandHandlers commandSource) {
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
		  LatexCommandSet set = SetAboveAndBelow.get();
		  LatexCommand[] cmds = set.getCommands();
		  ExtendedMenuItem[] items = new ExtendedMenuItem[cmds.length];
		  for (int i=0; i<cmds.length; i++) {
			LatexCommand cmd = cmds[i];
			items[i] = new ExtendedMenuItem(cmd.getIcon(), cmd.getTitle(), new SystemPasteCommand(cmd.getText()));
		  }
		  callback.onSuccess(items);
		}
    });
  }

}
