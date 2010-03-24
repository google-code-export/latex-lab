package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.windows.ToolbarWindow;
import org.latexlab.docs.client.windows.WindowManager;

public class ToolbarWindowSubscriptAndSuperscript extends ToolbarWindow {

  public final static String TITLE = "Subscript & Superscript";

  protected static ToolbarWindowSubscriptAndSuperscript instance;
  
  public static ToolbarWindowSubscriptAndSuperscript get(CommandHandler handler,
	    WindowManager manager) {
    if (instance == null) {
      instance = new ToolbarWindowSubscriptAndSuperscript();
      instance.addCommandHandler(handler);
	  instance.registeredDragController = manager.getWindowController().getPickupDragController();
	  instance.hide();
	  manager.getWindowController().makeResizable(instance);
	  manager.getBoundaryPanel().add(instance, 500, 120);
    }
    return instance;
  }

  protected ToolbarWindowSubscriptAndSuperscript() {
	super(TITLE);
    buildToolBar();
  }

  private void buildToolBar() {
    addButton(Icons.latexSubscriptAndSuperscriptIcons.RightSuperscript(), "Right superscript", false, new SystemPasteCommand("^{}"));
    addButton(Icons.latexSubscriptAndSuperscriptIcons.RightSubscript(), "Right subscript", false, new SystemPasteCommand("_{}"));
    addButton(Icons.latexSubscriptAndSuperscriptIcons.RightSubAndSuperScripts(), "Right sub & super scripts", false, new SystemPasteCommand("^{}_{}"));
    addButton(Icons.latexSubscriptAndSuperscriptIcons.LeftSuperscript(), "Left superscript", false, new SystemPasteCommand("^{}"));
    addButton(Icons.latexSubscriptAndSuperscriptIcons.LeftSubscript(), "Left subscript", false, new SystemPasteCommand("_{}"));
    addButton(Icons.latexSubscriptAndSuperscriptIcons.LeftSubAndSuperScripts(), "Left sub & super scripts", false, new SystemPasteCommand("^{}_{}"));
    addButton(Icons.latexSubscriptAndSuperscriptIcons.TopScript(), "Topscript", false, new SystemPasteCommand("^{}"));
    addButton(Icons.latexSubscriptAndSuperscriptIcons.LowScript(), "Lowscript", false, new SystemPasteCommand("_{}"));
    addButton(Icons.latexSubscriptAndSuperscriptIcons.TopAndLowScripts(), "Top & low scripts", false, new SystemPasteCommand("^{}_{}"));
    resize();
  }
}
