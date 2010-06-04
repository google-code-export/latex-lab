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
public class ToolbarWindowSubscriptAndSuperscript extends ToolbarWindow {

  /**
   * The toolbar window's title.
   */
  public final static String TITLE = "Subscript & Superscript";

  protected static ToolbarWindowSubscriptAndSuperscript instance;

  /**
   * Retrieves the single instance of this class, using asynchronous instantiation.
   * 
   * @param handler the command handler
   * @param manager the window manager
   * @param cb the asynchronous instantiation callback
   */
  public static void get(final CommandHandler handler, final WindowManager manager,
	    final AsyncInstantiationCallback<ToolbarWindowSubscriptAndSuperscript> cb) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  cb.onFailure(reason);
		}
		@Override
		public void onSuccess() {
	      if (instance == null) {
	        instance = new ToolbarWindowSubscriptAndSuperscript();
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
		  new ToolbarWindowSubscriptAndSuperscript();
		}
	});
  }

  protected ToolbarWindowSubscriptAndSuperscript() {
	super(TITLE);
    buildToolBar();
  }

  /**
   * Builds the toolbar contents.
   */
  private void buildToolBar() {
    addButton(Icons.latexSubscriptAndSuperscriptIcons.RightSuperscript(), "Right superscript", false, new SystemPasteCommand("^{}"));
    addButton(Icons.latexSubscriptAndSuperscriptIcons.RightSubscript(), "Right subscript", false, new SystemPasteCommand("_{}"));
    addButton(Icons.latexSubscriptAndSuperscriptIcons.RightSubAndSuperScripts(), "Right sub & super scripts", false, new SystemPasteCommand("^{}_{}"));
    addButton(Icons.latexSubscriptAndSuperscriptIcons.LeftSuperscript(), "Left superscript", false, new SystemPasteCommand("^{}"));
    addButton(Icons.latexSubscriptAndSuperscriptIcons.LeftSubscript(), "Left subscript", false, new SystemPasteCommand("_{}"));
    addButton(Icons.latexSubscriptAndSuperscriptIcons.LeftSubAndSuperScripts(), "Left sub & super scripts", false, new SystemPasteCommand("^{}_{}"));
    addButton(Icons.latexSubscriptAndSuperscriptIcons.TopScript(), "Topscript", false, new SystemPasteCommand("^{}"));
    addButton(Icons.latexSubscriptAndSuperscriptIcons.LowScript(), "Lowscript", false, new SystemPasteCommand("_{}"));
    addButton(Icons.latexSubscriptAndSuperscriptIcons.TopAndLowScripts(), "Top & low scripts", false, new SystemPasteCommand("^{}_{}"));
    resize();
  }
}
