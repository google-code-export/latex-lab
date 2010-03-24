package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.windows.ToolbarWindow;
import org.latexlab.docs.client.windows.WindowManager;

public class ToolbarWindowAboveAndBelow extends ToolbarWindow {

  public final static String TITLE = "Above & Below";

  protected static ToolbarWindowAboveAndBelow instance;
  
  public static ToolbarWindowAboveAndBelow get(CommandHandler handler,
	    WindowManager manager) {
    if (instance == null) {
      instance = new ToolbarWindowAboveAndBelow();
      instance.addCommandHandler(handler);
	  instance.registeredDragController = manager.getWindowController().getPickupDragController();
	  instance.hide();
	  manager.getWindowController().makeResizable(instance);
	  manager.getBoundaryPanel().add(instance, 500, 120);
    }
    return instance;
  }
  
  protected ToolbarWindowAboveAndBelow() {
	super(TITLE);
    buildToolBar();
  }

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
