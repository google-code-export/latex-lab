package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.windows.ToolbarWindow;
import org.latexlab.docs.client.windows.WindowManager;

public class ToolbarWindowOperators extends ToolbarWindow {

  public final static String TITLE = "Operators";

  protected static ToolbarWindowOperators instance;
  
  public static ToolbarWindowOperators get(CommandHandler handler,
	    WindowManager manager) {
    if (instance == null) {
      instance = new ToolbarWindowOperators();
      instance.addCommandHandler(handler);
	  instance.registeredDragController = manager.getWindowController().getPickupDragController();
	  instance.hide();
	  manager.getWindowController().makeResizable(instance);
	  manager.getBoundaryPanel().add(instance, 500, 120);
    }
    return instance;
  }

  protected ToolbarWindowOperators() {
	super(TITLE);
    buildToolBar();
  }

  private void buildToolBar() {
    addButton(Icons.latexOperatorsIcons.Sum(), "Sum", false, new SystemPasteCommand("\\sum"));
    addButton(Icons.latexOperatorsIcons.Integral(), "Integral", false, new SystemPasteCommand("\\int"));
    addButton(Icons.latexOperatorsIcons.OIntegral(), "O Integral", false, new SystemPasteCommand("\\oint"));
    addButton(Icons.latexOperatorsIcons.Product(), "Product", false, new SystemPasteCommand("\\prod"));
    addButton(Icons.latexOperatorsIcons.CoProduct(), "Co-product", false, new SystemPasteCommand("\\coprod"));
    resize();
  }
}
