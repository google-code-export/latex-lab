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
public class ToolbarWindowSets extends ToolbarWindow {

  /**
   * The toolbar window's title.
   */
  public final static String TITLE = "Sets";

  protected static ToolbarWindowSets instance;

  /**
   * Retrieves the single instance of this class, using asynchronous instantiation.
   * 
   * @param handler the command handler
   * @param manager the window manager
   * @param cb the asynchronous instantiation callback
   */
  public static void get(final CommandHandler handler, final WindowManager manager,
	    final AsyncInstantiationCallback<ToolbarWindowSets> cb) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  cb.onFailure(reason);
		}
		@Override
		public void onSuccess() {
	      if (instance == null) {
	        instance = new ToolbarWindowSets();
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
		  new ToolbarWindowSets();
		}
	});
  }

  protected ToolbarWindowSets() {
	super(TITLE);
    buildToolBar();
  }

  /**
   * Builds the toolbar contents.
   */
  private void buildToolBar() {
    addButton(Icons.latexSetsIcons.ElementOf(), "Element of", false, new SystemPasteCommand("\\in"));
    addButton(Icons.latexSetsIcons.NotElementOf(), "Not element of", false, new SystemPasteCommand("\\notin"));
    addButton(Icons.latexSetsIcons.Union(), "Union", false, new SystemPasteCommand("\\cup"));
    addButton(Icons.latexSetsIcons.Intersection(), "Intersection", false, new SystemPasteCommand("\\cap"));
    addButton(Icons.latexSetsIcons.UnionLarge(), "Union Large", false, new SystemPasteCommand("\\bigcup"));
    addButton(Icons.latexSetsIcons.IntersectionLarge(), "Intersection Large", false, new SystemPasteCommand("\\bigcap"));
    addButton(Icons.latexSetsIcons.ContainedIn(), "Contained in", false, new SystemPasteCommand("\\subset"));
    addButton(Icons.latexSetsIcons.Contains(), "Contains", false, new SystemPasteCommand("\\supset"));
    addButton(Icons.latexSetsIcons.ContainedInOrEqualTo(), "Contained in or equal to", false, new SystemPasteCommand("\\subseteq"));
    addButton(Icons.latexSetsIcons.ContainsOrEqualTo(), "Contains or equal to", false, new SystemPasteCommand("\\supseteq"));
    resize();
  }
}
