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
public class ToolbarWindowBinaryOperators extends ToolbarWindow {

  /**
   * The toolbar window's title.
   */
  public final static String TITLE = "Binary Operators";

  protected static ToolbarWindowBinaryOperators instance;

  /**
   * Retrieves the single instance of this class, using asynchronous instantiation.
   * 
   * @param handler the command handler
   * @param manager the window manager
   * @param cb the asynchronous instantiation callback
   */
  public static void get(final CommandHandler handler, final WindowManager manager,
	    final AsyncInstantiationCallback<ToolbarWindowBinaryOperators> cb) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  cb.onFailure(reason);
		}
		@Override
		public void onSuccess() {
	      if (instance == null) {
	        instance = new ToolbarWindowBinaryOperators();
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
		  new ToolbarWindowBinaryOperators();
		}
	});
  }
  
  protected ToolbarWindowBinaryOperators() {
	super(TITLE);
    buildToolBar();
  }

  /**
   * Builds the toolbar contents.
   */
  private void buildToolBar() {
    addButton(Icons.latexBinaryOperatorsIcons.PlusMinus(), "Plus/Minus", false, new SystemPasteCommand("\\pm"));
    addButton(Icons.latexBinaryOperatorsIcons.MinusPlus(), "Minus/Plus", false, new SystemPasteCommand("\\mp"));
    addButton(Icons.latexBinaryOperatorsIcons.Multiply(), "Multiply", false, new SystemPasteCommand("\\times"));
    addButton(Icons.latexBinaryOperatorsIcons.Divide(), "Divide", false, new SystemPasteCommand("\\div"));
    addButton(Icons.latexBinaryOperatorsIcons.Asterisk(), "Asterisk", false, new SystemPasteCommand("\\ast"));
    addButton(Icons.latexBinaryOperatorsIcons.CenterDot(), "Center Dot", false, new SystemPasteCommand("\\cdot"));
    addButton(Icons.latexBinaryOperatorsIcons.Circle(), "Circle", false, new SystemPasteCommand("\\circ"));
    addButton(Icons.latexBinaryOperatorsIcons.Bullet(), "Bullet", false, new SystemPasteCommand("\\bullet"));
    addButton(Icons.latexBinaryOperatorsIcons.RoundMultiply(), "Round multiply", false, new SystemPasteCommand("\\otimes"));
    addButton(Icons.latexBinaryOperatorsIcons.RoundPlus(), "Round plus", false, new SystemPasteCommand("\\oplus"));
    addButton(Icons.latexBinaryOperatorsIcons.RoundMinus(), "Round minus", false, new SystemPasteCommand("\\ominus"));
    addButton(Icons.latexBinaryOperatorsIcons.RoundDot(), "Round dot", false, new SystemPasteCommand("\\odot"));
    addButton(Icons.latexBinaryOperatorsIcons.RoundSlash(), "Round slash", false, new SystemPasteCommand("\\oslash"));
    resize();
  }
}
