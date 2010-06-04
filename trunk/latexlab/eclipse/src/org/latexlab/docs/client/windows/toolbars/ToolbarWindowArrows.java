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
public class ToolbarWindowArrows extends ToolbarWindow {

  /**
   * The toolbar window's title.
   */
  public final static String TITLE = "Arrows";

  protected static ToolbarWindowArrows instance;

  /**
   * Retrieves the single instance of this class, using asynchronous instantiation.
   * 
   * @param handler the command handler
   * @param manager the window manager
   * @param cb the asynchronous instantiation callback
   */
  public static void get(final CommandHandler handler, final WindowManager manager,
	    final AsyncInstantiationCallback<ToolbarWindowArrows> cb) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  cb.onFailure(reason);
		}
		@Override
		public void onSuccess() {
	      if (instance == null) {
	        instance = new ToolbarWindowArrows();
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
		  new ToolbarWindowArrows();
		}
	});
  }

  /**
   * Builds the toolbar contents.
   */
  protected ToolbarWindowArrows() {
	super(TITLE);
    buildToolBar();
  }

  private void buildToolBar() {
    addButton(Icons.latexArrowsIcons.LeftArrow(), "Left arrow", false, new SystemPasteCommand("\\leftarrow"));
    addButton(Icons.latexArrowsIcons.RightArrow(), "Right arrow", false, new SystemPasteCommand("\\rightarrow"));
    addButton(Icons.latexArrowsIcons.LeftRightArrow(), "Left/Right arrow", false, new SystemPasteCommand("\\leftrightarrow"));
    addButton(Icons.latexArrowsIcons.DoubleLeftArrow(), "Double left arrow", false, new SystemPasteCommand("\\Leftarrow"));
    addButton(Icons.latexArrowsIcons.DoubleRightArrow(), "Double right arrow", false, new SystemPasteCommand("\\Rightarrow"));
    addButton(Icons.latexArrowsIcons.DoubleLeftRightArrow(), "Double left/right arrow", false, new SystemPasteCommand("\\Leftrightarrow"));
    addButton(Icons.latexArrowsIcons.UpArrow(), "Up arrow", false, new SystemPasteCommand("\\uparrow"));
    addButton(Icons.latexArrowsIcons.DownArrow(), "Down arrow", false, new SystemPasteCommand("\\downarrow"));
    addButton(Icons.latexArrowsIcons.UpDownArrow(), "Up/Down arrow", false, new SystemPasteCommand("\\updownarrow"));
    addButton(Icons.latexArrowsIcons.DoubleUpArrow(), "Double up arrow", false, new SystemPasteCommand("\\Uparrow"));
    addButton(Icons.latexArrowsIcons.DoubleDownArrow(), "Double down arrow", false, new SystemPasteCommand("\\Downarrow"));
    addButton(Icons.latexArrowsIcons.DoubleUpDownArrow(), "Double up/down arrow", false, new SystemPasteCommand("\\Updownarrow"));
    addButton(Icons.latexArrowsIcons.LeftHarpoonUp(), "Left harpoon up", false, new SystemPasteCommand("\\leftharpoonup"));
    addButton(Icons.latexArrowsIcons.RightHarpoonUp(), "Right harpoon up", false, new SystemPasteCommand("\\rightharpoonup"));
    addButton(Icons.latexArrowsIcons.LeftRightHarpoons(), "Left/Right harpoons", false, new SystemPasteCommand("\\rightleftharpoons"));
    addButton(Icons.latexArrowsIcons.LeftHarpoonDown(), "Left harpoon down", false, new SystemPasteCommand("\\leftharpoondown"));
    addButton(Icons.latexArrowsIcons.RightHarpoonDown(), "Right harpoon down", false, new SystemPasteCommand("\\rightharpoondown"));
    addButton(Icons.latexArrowsIcons.MapsTo(), "Maps to", false, new SystemPasteCommand("\\mapsto"));
    addButton(Icons.latexArrowsIcons.LongMapsTo(), "Long maps to", false, new SystemPasteCommand("\\longmapsto"));
    addButton(Icons.latexArrowsIcons.HookLeft(), "Hook left", false, new SystemPasteCommand("\\hookleftarrow"));
    addButton(Icons.latexArrowsIcons.HookRight(), "Hook right", false, new SystemPasteCommand("\\hookrightarrow"));
    addButton(Icons.latexArrowsIcons.NortheastArrow(), "Northeast arrow", false, new SystemPasteCommand("\\nearrow"));
    addButton(Icons.latexArrowsIcons.SoutheastArrow(), "Southeast arrow", false, new SystemPasteCommand("\\searrow"));
    addButton(Icons.latexArrowsIcons.SouthwestArrow(), "Southwest arrow", false, new SystemPasteCommand("\\swarrow"));
    addButton(Icons.latexArrowsIcons.NorthwestArrow(), "Northwest arrow", false, new SystemPasteCommand("\\nwarrow"));
    addButton(Icons.latexArrowsIcons.LongLeftArrow(), "Long left arrow", false, new SystemPasteCommand("\\longleftarrow"));
    addButton(Icons.latexArrowsIcons.LongRightArrow(), "Long right arrow", false, new SystemPasteCommand("\\longrightarrow"));
    addButton(Icons.latexArrowsIcons.LongLeftRightArrow(), "Long left/right arrow", false, new SystemPasteCommand("\\longleftrightarrow"));
    addButton(Icons.latexArrowsIcons.LongDoubleLeftArrow(), "Long double left arrow", false, new SystemPasteCommand("\\Longleftarrow"));
    addButton(Icons.latexArrowsIcons.LongDoubleRightArrow(), "Long double right arrow", false, new SystemPasteCommand("\\Longrightarrow"));
    addButton(Icons.latexArrowsIcons.LongDoubleLeftRightArrow(), "Long double left/right arrow", false, new SystemPasteCommand("\\Longleftrightarrow"));
    resize();
  }
}
