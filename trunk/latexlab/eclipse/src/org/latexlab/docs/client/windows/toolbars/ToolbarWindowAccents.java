package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.windows.ToolbarWindow;
import org.latexlab.docs.client.windows.WindowManager;

public class ToolbarWindowAccents extends ToolbarWindow {

  public final static String TITLE = "Accents";

  protected static ToolbarWindowAccents instance;
  
  public static ToolbarWindowAccents get(CommandHandler handler,
	    WindowManager manager) {
    if (instance == null) {
      instance = new ToolbarWindowAccents();
      instance.addCommandHandler(handler);
	  instance.registeredDragController = manager.getWindowController().getPickupDragController();
	  instance.hide();
	  manager.getWindowController().makeResizable(instance);
	  manager.getBoundaryPanel().add(instance, 500, 120);
    }
    return instance;
  }

  protected ToolbarWindowAccents() {
	super(TITLE);
    buildToolBar();
  }

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
