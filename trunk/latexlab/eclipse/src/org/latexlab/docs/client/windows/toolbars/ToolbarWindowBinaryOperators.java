package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.windows.ToolbarWindow;
import org.latexlab.docs.client.windows.WindowManager;

public class ToolbarWindowBinaryOperators extends ToolbarWindow {

  public final static String TITLE = "Binary Operators";

  protected static ToolbarWindowBinaryOperators instance;
  
  public static ToolbarWindowBinaryOperators get(CommandHandler handler,
	    WindowManager manager) {
    if (instance == null) {
      instance = new ToolbarWindowBinaryOperators();
      instance.addCommandHandler(handler);
	  instance.registeredDragController = manager.getWindowController().getPickupDragController();
	  instance.hide();
	  manager.getWindowController().makeResizable(instance);
	  manager.getBoundaryPanel().add(instance, 500, 120);
    }
    return instance;
  }

  protected ToolbarWindowBinaryOperators() {
	super(TITLE);
    buildToolBar();
  }

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
