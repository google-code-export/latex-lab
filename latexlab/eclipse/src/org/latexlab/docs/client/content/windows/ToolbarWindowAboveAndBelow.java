package org.latexlab.docs.client.content.windows;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.content.latex.LatexCommand;
import org.latexlab.docs.client.content.latex.SetAboveAndBelow;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.widgets.LatexCommandToolbar;
import org.latexlab.docs.client.widgets.WindowManager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * A toolbar containing LaTeX commands.
 */
public class ToolbarWindowAboveAndBelow extends LatexCommandToolbar {

  protected static ToolbarWindowAboveAndBelow instance;

  /**
   * Retrieves the single instance of this class.
   * 
   * @param handler the command handler
   * @param manager the window manager
   */
  public static ToolbarWindowAboveAndBelow get(final CommandHandler handler, final WindowManager manager) {
    if (instance == null) {
      instance = new ToolbarWindowAboveAndBelow();
      instance.addCommandHandler(handler);
      instance.registeredDragController = manager.getWindowController().getPickupDragController();
      instance.hide();
      manager.getWindowController().makeResizable(instance);
      manager.getBoundaryPanel().add(instance, 500, 120);
    }
    return instance;
  }
  
  protected ToolbarWindowAboveAndBelow() {
	super(SetAboveAndBelow.TITLE);
  }

  @Override
  protected void getToolbarContents(final AsyncCallback<ToolbarWindowContents> callback) {
    GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  callback.onFailure(reason);
		}
		@Override
		public void onSuccess() {
		  ToolbarWindowContents contents = new ToolbarWindowContents();
		  SetAboveAndBelow commandSet = SetAboveAndBelow.get();
		  for (LatexCommand cmd : commandSet.getCommands()) {
		    contents.addButton(cmd.getIcon(), cmd.getTitle(), false,
		      new SystemPasteCommand(cmd.getText()));
		  }
		}
    });
  }
  
}
