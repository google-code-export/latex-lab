package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.windows.ToolbarWindow;
import org.latexlab.docs.client.windows.WindowManager;

public class ToolbarWindowSets extends ToolbarWindow {

  public final static String TITLE = "Sets";

  protected static ToolbarWindowSets instance;
  
  public static ToolbarWindowSets get(CommandHandler handler,
	    WindowManager manager) {
    if (instance == null) {
      instance = new ToolbarWindowSets();
      instance.addCommandHandler(handler);
	  instance.registeredDragController = manager.getWindowController().getPickupDragController();
	  instance.hide();
	  manager.getWindowController().makeResizable(instance);
	  manager.getBoundaryPanel().add(instance, 500, 120);
    }
    return instance;
  }

  protected ToolbarWindowSets() {
	super(TITLE);
    buildToolBar();
  }

  private void buildToolBar() {
    addButton(Icons.latexSetsIcons.ElementOf(), "Element of", false, new SystemPasteCommand("\\in"));
    addButton(Icons.latexSetsIcons.NotElementOf(), "Not element of", false, new SystemPasteCommand("\\notin"));
    addButton(Icons.latexSetsIcons.Union(), "Union", false, new SystemPasteCommand("\\cup"));
    addButton(Icons.latexSetsIcons.Intersection(), "Intersection", false, new SystemPasteCommand("\\cap"));
    addButton(Icons.latexSetsIcons.UnionLarge(), "Union Large", false, new SystemPasteCommand("\\bigcup"));
    addButton(Icons.latexSetsIcons.IntersectionLarge(), "Intersection Large", false, new SystemPasteCommand("\\bigcap"));
    addButton(Icons.latexSetsIcons.ContainedIn(), "Contained in", false, new SystemPasteCommand("\\subset"));
    addButton(Icons.latexSetsIcons.Contains(), "Contains", false, new SystemPasteCommand("\\supset"));
    addButton(Icons.latexSetsIcons.ContainedInOrEqualTo(), "Contained in or equal to", false, new SystemPasteCommand("\\subseteq"));
    addButton(Icons.latexSetsIcons.ContainsOrEqualTo(), "Contains or equal to", false, new SystemPasteCommand("\\supseteq"));
    resize();
  }
}
