package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.windows.ToolbarWindow;
import org.latexlab.docs.client.windows.WindowManager;

public class ToolbarWindowArrowsWithCaptions extends ToolbarWindow {

  public final static String TITLE = "Arrows With Captions";

  protected static ToolbarWindowArrowsWithCaptions instance;
  
  public static ToolbarWindowArrowsWithCaptions get(CommandHandler handler,
	    WindowManager manager) {
    if (instance == null) {
      instance = new ToolbarWindowArrowsWithCaptions();
      instance.addCommandHandler(handler);
	  instance.registeredDragController = manager.getWindowController().getPickupDragController();
	  instance.hide();
	  manager.getWindowController().makeResizable(instance);
	  manager.getBoundaryPanel().add(instance, 500, 120);
    }
    return instance;
  }

  protected ToolbarWindowArrowsWithCaptions() {
	super(TITLE);
    buildToolBar();
  }

  private void buildToolBar() {
    addButton(Icons.latexArrowsWithCaptionsIcons.RightArrowTopCaption(), "Right arrow, top caption", false, new SystemPasteCommand("\\stackrel{}{\\rightarrow}"));
    addButton(Icons.latexArrowsWithCaptionsIcons.LeftArrowTopCaption(), "Left arrow, top caption", false, new SystemPasteCommand("\\stackrel{}{\\leftarrow}"));
    addButton(Icons.latexArrowsWithCaptionsIcons.LeftRightArrowTopCaption(), "Left/Right arrow, top caption", false, new SystemPasteCommand("\\stackrel{}{\\leftrightarrow}"));
    addButton(Icons.latexArrowsWithCaptionsIcons.RightArrowBottomCaption(), "Right arrow, bottom caption", false, new SystemPasteCommand("\\stackrel{\\rightarrow}{}"));
    addButton(Icons.latexArrowsWithCaptionsIcons.LeftArrowBottomCaption(), "Left arrow, bottom caption", false, new SystemPasteCommand("\\stackrel{\\leftarrow}{}"));
    addButton(Icons.latexArrowsWithCaptionsIcons.LeftRightArrowBottomCaption(), "Left/Right arrow, bottom caption", false, new SystemPasteCommand("\\stackrel{\\leftrightarrow}{}"));
    resize();
  }
}
