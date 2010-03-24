package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.windows.ToolbarWindow;
import org.latexlab.docs.client.windows.WindowManager;

public class ToolbarWindowWhiteSpacesAndDots extends ToolbarWindow {

  public final static String TITLE = "White Spaces & Dots";

  protected static ToolbarWindowWhiteSpacesAndDots instance;
  
  public static ToolbarWindowWhiteSpacesAndDots get(CommandHandler handler,
	    WindowManager manager) {
    if (instance == null) {
      instance = new ToolbarWindowWhiteSpacesAndDots();
      instance.addCommandHandler(handler);
	  instance.registeredDragController = manager.getWindowController().getPickupDragController();
	  instance.hide();
	  manager.getWindowController().makeResizable(instance);
	  manager.getBoundaryPanel().add(instance, 500, 120);
    }
    return instance;
  }

  protected ToolbarWindowWhiteSpacesAndDots() {
	super(TITLE);
    buildToolBar();
  }

  private void buildToolBar() {
    addButton(Icons.latexWhiteSpacesAndDotsIcons.SmallestSpace(), "Smallest space", false, new SystemPasteCommand("\\,"));
    addButton(Icons.latexWhiteSpacesAndDotsIcons.VerySmallSpace(), "Very small space", false, new SystemPasteCommand("\\!"));
    addButton(Icons.latexWhiteSpacesAndDotsIcons.SmallSpace(), "Small space", false, new SystemPasteCommand("\\:"));
    addButton(Icons.latexWhiteSpacesAndDotsIcons.LittleSmallerSpace(), "Little smaller space", false, new SystemPasteCommand("\\;"));
    addButton(Icons.latexWhiteSpacesAndDotsIcons.NormalSpace(), "Normal space", false, new SystemPasteCommand("\\ "));
    addButton(Icons.latexWhiteSpacesAndDotsIcons.LowerDots(), "Lower dots", false, new SystemPasteCommand("\\ldots"));
    addButton(Icons.latexWhiteSpacesAndDotsIcons.CenterDots(), "Center dots", false, new SystemPasteCommand("\\cdots"));
    addButton(Icons.latexWhiteSpacesAndDotsIcons.VerticalDots(), "Vertical dots", false, new SystemPasteCommand("\\vdots"));
    addButton(Icons.latexWhiteSpacesAndDotsIcons.DiagonalDots(), "Diagonal dots", false, new SystemPasteCommand("\\ddots"));
    resize();
  }
}
