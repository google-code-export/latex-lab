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
public class ToolbarWindowAccents extends ToolbarWindow {

  /**
   * The toolbar window's title.
   */
  public final static String TITLE = "Accents";

  protected static ToolbarWindowAccents instance;

  /**
   * Retrieves the single instance of this class, using asynchronous instantiation.
   * 
   * @param handler the command handler
   * @param manager the window manager
   * @param cb the asynchronous instantiation callback
   */
  public static void get(final CommandHandler handler, final WindowManager manager,
	    final AsyncInstantiationCallback<ToolbarWindowAccents> cb) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  cb.onFailure(reason);
		}
		@Override
		public void onSuccess() {
	      if (instance == null) {
	        instance = new ToolbarWindowAccents();
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
		  new ToolbarWindowAccents();
		}
	});
  }

  protected ToolbarWindowAccents() {
	super(TITLE);
    buildToolBar();
  }

  /**
   * Builds the toolbar contents.
   */
  private void buildToolBar() {
	addButton(Icons.latexAccentsIcons.HatAccent(), "Hat accent", false, new SystemPasteCommand("\\hat{}"));
    addButton(Icons.latexAccentsIcons.CheckAccent(), "Check accent", false, new SystemPasteCommand("\\check{}"));
    addButton(Icons.latexAccentsIcons.BreveAccent(), "Breve accent", false, new SystemPasteCommand("\\breve{}"));
    addButton(Icons.latexAccentsIcons.AcuteAccent(), "Acute accent", false, new SystemPasteCommand("\\acute{}"));
    addButton(Icons.latexAccentsIcons.GraveAccent(), "Grave accent", false, new SystemPasteCommand("\\grave{}"));
    addButton(Icons.latexAccentsIcons.TildeAccent(), "Tilde accent", false, new SystemPasteCommand("\\tilde{}"));
    addButton(Icons.latexAccentsIcons.BarAccent(), "Bar accent", false, new SystemPasteCommand("\\bar{}"));
    addButton(Icons.latexAccentsIcons.VectorAccent(), "Vector accent", false, new SystemPasteCommand("\\vec{}"));
    addButton(Icons.latexAccentsIcons.DotAccent(), "Dot accent", false, new SystemPasteCommand("\\dot{}"));
    addButton(Icons.latexAccentsIcons.DoubleDotAccent(), "Double-dot accent", false, new SystemPasteCommand("\\ddot{}"));
    resize();
  }
}
