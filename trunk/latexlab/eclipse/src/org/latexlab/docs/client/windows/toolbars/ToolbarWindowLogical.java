package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.windows.ToolbarWindow;
import org.latexlab.docs.client.windows.WindowManager;

public class ToolbarWindowLogical extends ToolbarWindow {

  public final static String TITLE = "Logical";

  protected static ToolbarWindowLogical instance;
  
  public static ToolbarWindowLogical get(CommandHandler handler,
	    WindowManager manager) {
    if (instance == null) {
      instance = new ToolbarWindowLogical();
      instance.addCommandHandler(handler);
	  instance.registeredDragController = manager.getWindowController().getPickupDragController();
	  instance.hide();
	  manager.getWindowController().makeResizable(instance);
	  manager.getBoundaryPanel().add(instance, 500, 120);
    }
    return instance;
  }

  protected ToolbarWindowLogical() {
	super(TITLE);
    buildToolBar();
  }

  private void buildToolBar() {
    addButton(Icons.latexLogicalIcons.NotIn(), " ", false, new SystemPasteCommand("\\ni"));
    addButton(Icons.latexLogicalIcons.Exists(), "Exists", false, new SystemPasteCommand("\\exists"));
    addButton(Icons.latexLogicalIcons.ForAll(), "For all", false, new SystemPasteCommand("\\forall"));
    addButton(Icons.latexLogicalIcons.Negation(), "Negation", false, new SystemPasteCommand("\\neg"));
    addButton(Icons.latexLogicalIcons.And(), "And", false, new SystemPasteCommand("\\wedge"));
    addButton(Icons.latexLogicalIcons.Or(), "Or", false, new SystemPasteCommand("\\vee"));
    resize();
  }
}
