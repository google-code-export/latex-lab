package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.windows.ToolbarWindow;
import org.latexlab.docs.client.windows.WindowManager;

public class ToolbarWindowMath extends ToolbarWindow {

  public final static String TITLE = "Math";

  protected static ToolbarWindowMath instance;
  
  public static ToolbarWindowMath get(CommandHandler handler,
	    WindowManager manager) {
    if (instance == null) {
      instance = new ToolbarWindowMath();
      instance.addCommandHandler(handler);
	  instance.registeredDragController = manager.getWindowController().getPickupDragController();
	  instance.hide();
	  manager.getWindowController().makeResizable(instance);
	  manager.getBoundaryPanel().add(instance, 500, 120);
    }
    return instance;
  }

  protected ToolbarWindowMath() {
	super(TITLE);
    buildToolBar();
  }

  private void buildToolBar() {
    addButton(Icons.latexMathIcons.Fraction(), "Fraction", false, new SystemPasteCommand("\\frac{}{}"));
    addButton(Icons.latexMathIcons.Division(), "Division", false, new SystemPasteCommand("/"));
    addButton(Icons.latexMathIcons.SquareRoot(), "Square root", false, new SystemPasteCommand("\\sqrt{}"));
    addButton(Icons.latexMathIcons.NthRoot(), "Nth root", false, new SystemPasteCommand("\\sqrt[]{}"));
    resize();
  }
}
