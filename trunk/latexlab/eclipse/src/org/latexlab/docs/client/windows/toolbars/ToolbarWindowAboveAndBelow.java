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
public class ToolbarWindowAboveAndBelow extends ToolbarWindow {

  /**
   * The toolbar window's title.
   */
  public final static String TITLE = "Above & Below";

  protected static ToolbarWindowAboveAndBelow instance;

  /**
   * Retrieves the single instance of this class, using asynchronous instantiation.
   * 
   * @param handler the command handler
   * @param manager the window manager
   * @param cb the asynchronous instantiation callback
   */
  public static void get(final CommandHandler handler, final WindowManager manager,
	    final AsyncInstantiationCallback<ToolbarWindowAboveAndBelow> cb) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  cb.onFailure(reason);
		}
		@Override
		public void onSuccess() {
	      if (instance == null) {
	        instance = new ToolbarWindowAboveAndBelow();
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
		  new ToolbarWindowAboveAndBelow();
		}
	});
  }
  
  protected ToolbarWindowAboveAndBelow() {
	super(TITLE);
    buildToolBar();
  }

  /**
   * Builds the toolbar contents.
   */
  private void buildToolBar() {
	addButton(Icons.latexAboveAndBelowIcons.Overline(), "Overline", false, new SystemPasteCommand("\\overline{}"));
	addButton(Icons.latexAboveAndBelowIcons.Underline(), "Underline", false, new SystemPasteCommand("\\underline{}"));
	addButton(Icons.latexAboveAndBelowIcons.WideHat(), "Wide hat", false, new SystemPasteCommand("\\widehat{}"));
	addButton(Icons.latexAboveAndBelowIcons.WideTilde(), "Wide tilde", false, new SystemPasteCommand("\\widetilde{}"));
	addButton(Icons.latexAboveAndBelowIcons.Stack(), "Stack", false, new SystemPasteCommand("\\stackrel{}{}"));
	addButton(Icons.latexAboveAndBelowIcons.OverBrace(), "Over brace", false, new SystemPasteCommand("\\overbrace{}^{}"));
	addButton(Icons.latexAboveAndBelowIcons.UnderBrace(), "Under brace", false, new SystemPasteCommand("\\underbrace{}_{}"));
    resize();
  }
  
}
