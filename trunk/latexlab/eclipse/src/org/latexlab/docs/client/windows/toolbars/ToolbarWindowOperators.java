package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.AsyncInstantiationCallback;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.windows.ToolbarWindow;
import org.latexlab.docs.client.windows.WindowManager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;

/**
 * A toolbar containing LaTeX commands.
 */
public class ToolbarWindowOperators extends ToolbarWindow {

  /**
   * The toolbar window's title.
   */
  public final static String TITLE = "Operators";

  protected static ToolbarWindowOperators instance;

  /**
   * Retrieves the single instance of this class, using asynchronous instantiation.
   * 
   * @param handler the command handler
   * @param manager the window manager
   * @param cb the asynchronous instantiation callback
   */
  public static void get(final CommandHandler handler, final WindowManager manager,
	    final AsyncInstantiationCallback<ToolbarWindowOperators> cb) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  cb.onFailure(reason);
		}
		@Override
		public void onSuccess() {
	      if (instance == null) {
	        instance = new ToolbarWindowOperators();
	        instance.addCommandHandler(handler);
		    instance.registeredDragController = manager.getWindowController().getPickupDragController();
		    instance.hide();
		    manager.getWindowController().makeResizable(instance);
		    manager.getBoundaryPanel().add(instance, 500, 120);
	      }
	      cb.onSuccess(instance);
		}
	});
  }
  
  /**
   * Causes the code for this class to be loaded.
   */
  public static void prefetch() {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) { }
		@Override
		public void onSuccess() {
		  new ToolbarWindowOperators();
		}
	});
  }

  protected ToolbarWindowOperators() {
	super(TITLE);
    buildToolBar();
  }

  /**
   * Builds the toolbar contents.
   */
  private void buildToolBar() {
    addButton(Icons.latexOperatorsIcons.Sum(), "Sum", false, new SystemPasteCommand("\\sum"));
    addButton(Icons.latexOperatorsIcons.Integral(), "Integral", false, new SystemPasteCommand("\\int"));
    addButton(Icons.latexOperatorsIcons.OIntegral(), "O Integral", false, new SystemPasteCommand("\\oint"));
    addButton(Icons.latexOperatorsIcons.Product(), "Product", false, new SystemPasteCommand("\\prod"));
    addButton(Icons.latexOperatorsIcons.CoProduct(), "Co-product", false, new SystemPasteCommand("\\coprod"));
    resize();
  }
}
