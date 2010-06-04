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
public class ToolbarWindowComparison extends ToolbarWindow {

  /**
   * The toolbar window's title.
   */
  public final static String TITLE = "Comparison";

  protected static ToolbarWindowComparison instance;

  /**
   * Retrieves the single instance of this class, using asynchronous instantiation.
   * 
   * @param handler the command handler
   * @param manager the window manager
   * @param cb the asynchronous instantiation callback
   */
  public static void get(final CommandHandler handler, final WindowManager manager,
	    final AsyncInstantiationCallback<ToolbarWindowComparison> cb) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  cb.onFailure(reason);
		}
		@Override
		public void onSuccess() {
	      if (instance == null) {
	        instance = new ToolbarWindowComparison();
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
		  new ToolbarWindowComparison();
		}
	});
  }

  protected ToolbarWindowComparison() {
	super(TITLE);
    buildToolBar();
  }

  /**
   * Builds the toolbar contents.
   */
  private void buildToolBar() {
    addButton(Icons.latexComparisonIcons.LesserThanOrEqual(), "Lesser than or equal", false, new SystemPasteCommand("\\leq"));
    addButton(Icons.latexComparisonIcons.GreaterThanOrEqual(), "Greater than or equal", false, new SystemPasteCommand("\\geq"));
    addButton(Icons.latexComparisonIcons.Preceding(), "Preceding", false, new SystemPasteCommand("\\prec"));
    addButton(Icons.latexComparisonIcons.Succeding(), "Succeding", false, new SystemPasteCommand("\\succ"));
    addButton(Icons.latexComparisonIcons.TriangleLeft(), "Triangle left", false, new SystemPasteCommand("\\triangleleft"));
    addButton(Icons.latexComparisonIcons.TriangleRight(), "Triangle right", false, new SystemPasteCommand("\\triangleright"));
    addButton(Icons.latexComparisonIcons.NotEqual(), "Not equal", false, new SystemPasteCommand("\\neq"));
    addButton(Icons.latexComparisonIcons.Equivalent(), "Equivalent", false, new SystemPasteCommand("\\equiv"));
    addButton(Icons.latexComparisonIcons.Approximately(), "Approximately", false, new SystemPasteCommand("\\approx"));
    addButton(Icons.latexComparisonIcons.Congruent(), "Congruent", false, new SystemPasteCommand("\\cong"));
    addButton(Icons.latexComparisonIcons.PropTo(), "Prop to", false, new SystemPasteCommand("\\propto"));
    resize();
  }
}
