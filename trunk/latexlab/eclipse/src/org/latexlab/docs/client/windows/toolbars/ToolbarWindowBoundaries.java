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
public class ToolbarWindowBoundaries extends ToolbarWindow {

  /**
   * The toolbar window's title.
   */
  public final static String TITLE = "Boundaries";

  protected static ToolbarWindowBoundaries instance;

  /**
   * Retrieves the single instance of this class, using asynchronous instantiation.
   * 
   * @param handler the command handler
   * @param manager the window manager
   * @param cb the asynchronous instantiation callback
   */
  public static void get(final CommandHandler handler, final WindowManager manager,
	    final AsyncInstantiationCallback<ToolbarWindowBoundaries> cb) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  cb.onFailure(reason);
		}
		@Override
		public void onSuccess() {
	      if (instance == null) {
	        instance = new ToolbarWindowBoundaries();
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
		  new ToolbarWindowBoundaries();
		}
	});
  }

  protected ToolbarWindowBoundaries() {
	super(TITLE);
    buildToolBar();
  }

  /**
   * Builds the toolbar contents.
   */
  private void buildToolBar() {
    addButton(Icons.latexBoundariesIcons.InvisibleLeftBoundary(), "Invisible left boundary", false, new SystemPasteCommand("\\left."));
    addButton(Icons.latexBoundariesIcons.InvisibleRightBoundary(), "Invisible right boundary", false, new SystemPasteCommand("\\right."));
    addButton(Icons.latexBoundariesIcons.LeftParentheses(), "Left parentheses", false, new SystemPasteCommand("\\left("));
    addButton(Icons.latexBoundariesIcons.RightParantheses(), "Right parantheses", false, new SystemPasteCommand("\\right)"));
    addButton(Icons.latexBoundariesIcons.LeftSquareBracket(), "Left square bracket", false, new SystemPasteCommand("\\left["));
    addButton(Icons.latexBoundariesIcons.RightSquareBracket(), "Right square bracket", false, new SystemPasteCommand("\\right]"));
    addButton(Icons.latexBoundariesIcons.LeftCurlyBracket(), "Left curly bracket", false, new SystemPasteCommand("\\left\\{"));
    addButton(Icons.latexBoundariesIcons.RightCurlyBracket(), "Right curly bracket", false, new SystemPasteCommand("\\right\\}"));
    addButton(Icons.latexBoundariesIcons.LeftFloor(), "Left floor", false, new SystemPasteCommand("\\left\\lfloor"));
    addButton(Icons.latexBoundariesIcons.RightFloor(), "Right floor", false, new SystemPasteCommand("\\right\\rfloor"));
    addButton(Icons.latexBoundariesIcons.LeftCeil(), "Left ceil", false, new SystemPasteCommand("\\left\\lceil"));
    addButton(Icons.latexBoundariesIcons.RightCeil(), "Right ceil", false, new SystemPasteCommand("\\right\\rceil"));
    addButton(Icons.latexBoundariesIcons.LeftAngleBracket(), "Left angle bracket", false, new SystemPasteCommand("\\left\\langle"));
    addButton(Icons.latexBoundariesIcons.RightAngleBracket(), "Right angle bracket", false, new SystemPasteCommand("\\right\\rangle"));
    addButton(Icons.latexBoundariesIcons.LeftModulus(), "Left modulus", false, new SystemPasteCommand("\\left|"));
    addButton(Icons.latexBoundariesIcons.RightModulus(), "Right modulus", false, new SystemPasteCommand("\\right|"));
    addButton(Icons.latexBoundariesIcons.LeftDouble(), "Left double", false, new SystemPasteCommand("\\left\\|"));
    addButton(Icons.latexBoundariesIcons.RightDouble(), "Right double", false, new SystemPasteCommand("\\right\\|"));
    addButton(Icons.latexBoundariesIcons.OverBrace(), "Over brace", false, new SystemPasteCommand("\\overbrace{}^{}"));
    addButton(Icons.latexBoundariesIcons.UnderBrace(), "Under brace", false, new SystemPasteCommand("\\underbrace{}_{}"));
    resize();
  }
}
