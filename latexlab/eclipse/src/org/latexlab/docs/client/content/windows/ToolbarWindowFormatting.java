package org.latexlab.docs.client.content.windows;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.content.latex.LatexCommand;
import org.latexlab.docs.client.content.latex.SetFormatting;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.widgets.LatexCommandToolbar;
import org.latexlab.docs.client.widgets.WindowManager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * A toolbar containing LaTeX commands.
 */
public class ToolbarWindowFormatting extends LatexCommandToolbar {
  
  protected static ToolbarWindowFormatting instance;

  /**
   * Retrieves the single instance of this class.
   * 
   * @param handler the command handler
   * @param manager the window manager
   */
  public static ToolbarWindowFormatting get(final CommandHandler handler, final WindowManager manager) {
    if (instance == null) {
      instance = new ToolbarWindowFormatting();
      instance.addCommandHandler(handler);
      instance.registeredDragController = manager.getWindowController().getPickupDragController();
      instance.hide();
      manager.getWindowController().makeResizable(instance);
      manager.getBoundaryPanel().add(instance, 500, 120);
    }
    return instance;
  }

  protected ToolbarWindowFormatting() {
	super(SetFormatting.TITLE);
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
		  SetFormatting commandSet = SetFormatting.get();
		  for (LatexCommand cmd : commandSet.getCommands()) {
		    contents.addButton(cmd.getIcon(), cmd.getTitle(), false,
		      new SystemPasteCommand(cmd.getText()));
		  }
		}
    });
  }

}
