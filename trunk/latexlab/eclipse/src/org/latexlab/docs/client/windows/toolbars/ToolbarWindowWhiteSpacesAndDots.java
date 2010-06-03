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
public class ToolbarWindowWhiteSpacesAndDots extends ToolbarWindow {

  /**
   * The toolbar window's title.
   */
  public final static String TITLE = "White Spaces & Dots";

  protected static ToolbarWindowWhiteSpacesAndDots instance;

  /**
   * Retrieves the single instance of this class, using asynchronous instantiation.
   * 
   * @param handler the command handler
   * @param manager the window manager
   * @param cb the asynchronous instantiation callback
   */
  public static void get(final CommandHandler handler, final WindowManager manager,
	    final AsyncInstantiationCallback<ToolbarWindowWhiteSpacesAndDots> cb) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  cb.onFailure(reason);
		}
		@Override
		public void onSuccess() {
	      if (instance == null) {
	        instance = new ToolbarWindowWhiteSpacesAndDots();
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
		  new ToolbarWindowWhiteSpacesAndDots();
		}
	});
  }

  protected ToolbarWindowWhiteSpacesAndDots() {
	super(TITLE);
    buildToolBar();
  }

  /**
   * Builds the toolbar contents.
   */
  private void buildToolBar() {
    addButton(Icons.latexWhiteSpacesAndDotsIcons.SmallestSpace(), "Smallest space", false, new SystemPasteCommand("\\,"));
    addButton(Icons.latexWhiteSpacesAndDotsIcons.VerySmallSpace(), "Very small space", false, new SystemPasteCommand("\\!"));
    addButton(Icons.latexWhiteSpacesAndDotsIcons.SmallSpace(), "Small space", false, new SystemPasteCommand("\\:"));
    addButton(Icons.latexWhiteSpacesAndDotsIcons.LittleSmallerSpace(), "Little smaller space", false, new SystemPasteCommand("\\;"));
    addButton(Icons.latexWhiteSpacesAndDotsIcons.NormalSpace(), "Normal space", false, new SystemPasteCommand("\\ "));
    addButton(Icons.latexWhiteSpacesAndDotsIcons.LowerDots(), "Lower dots", false, new SystemPasteCommand("\\ldots"));
    addButton(Icons.latexWhiteSpacesAndDotsIcons.CenterDots(), "Center dots", false, new SystemPasteCommand("\\cdots"));
    addButton(Icons.latexWhiteSpacesAndDotsIcons.VerticalDots(), "Vertical dots", false, new SystemPasteCommand("\\vdots"));
    addButton(Icons.latexWhiteSpacesAndDotsIcons.DiagonalDots(), "Diagonal dots", false, new SystemPasteCommand("\\ddots"));
    resize();
  }
}
