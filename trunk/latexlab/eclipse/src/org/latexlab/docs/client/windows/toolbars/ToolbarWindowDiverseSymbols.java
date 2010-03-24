package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.windows.ToolbarWindow;
import org.latexlab.docs.client.windows.WindowManager;

public class ToolbarWindowDiverseSymbols extends ToolbarWindow {

  public final static String TITLE = "Diverse Symbols";

  protected static ToolbarWindowDiverseSymbols instance;
  
  public static ToolbarWindowDiverseSymbols get(CommandHandler handler,
	    WindowManager manager) {
    if (instance == null) {
      instance = new ToolbarWindowDiverseSymbols();
      instance.addCommandHandler(handler);
	  instance.registeredDragController = manager.getWindowController().getPickupDragController();
	  instance.hide();
	  manager.getWindowController().makeResizable(instance);
	  manager.getBoundaryPanel().add(instance, 500, 120);
    }
    return instance;
  }

  protected ToolbarWindowDiverseSymbols() {
	super(TITLE);
    buildToolBar();
  }

  private void buildToolBar() {
    addButton(Icons.latexDiverseSymbolsIcons.Partial(), "Partial", false, new SystemPasteCommand("\\partial"));
    addButton(Icons.latexDiverseSymbolsIcons.Nabla(), "Nabla", false, new SystemPasteCommand("\\nabla"));
    addButton(Icons.latexDiverseSymbolsIcons.Infinity(), "Infinity", false, new SystemPasteCommand("\\infty"));
    addButton(Icons.latexDiverseSymbolsIcons.Im(), " ", false, new SystemPasteCommand("\\Im"));
    addButton(Icons.latexDiverseSymbolsIcons.Re(), " ", false, new SystemPasteCommand("\\Re"));
    addButton(Icons.latexDiverseSymbolsIcons.Aleph(), " ", false, new SystemPasteCommand("\\aleph"));
    addButton(Icons.latexDiverseSymbolsIcons.Angle(), "Angle", false, new SystemPasteCommand("\\angle"));
    addButton(Icons.latexDiverseSymbolsIcons.Bot(), "Bot", false, new SystemPasteCommand("\\bot"));
    addButton(Icons.latexDiverseSymbolsIcons.Diamond(), "Diamond", false, new SystemPasteCommand("\\diamond"));
    addButton(Icons.latexDiverseSymbolsIcons.Ell(), "Ell", false, new SystemPasteCommand("\\ell"));
    addButton(Icons.latexDiverseSymbolsIcons.Wp(), "Wp", false, new SystemPasteCommand("\\wp"));
    addButton(Icons.latexDiverseSymbolsIcons.Hbar(), "Hbar", false, new SystemPasteCommand("\\hbar"));
    addButton(Icons.latexDiverseSymbolsIcons.Integral(), "Integral", false, new SystemPasteCommand("\\int"));
    addButton(Icons.latexDiverseSymbolsIcons.Sum(), "Sum", false, new SystemPasteCommand("\\sum"));
    addButton(Icons.latexDiverseSymbolsIcons.Product(), "Product", false, new SystemPasteCommand("\\prod"));
    addButton(Icons.latexDiverseSymbolsIcons.CoProduct(), "Co-Product", false, new SystemPasteCommand("\\coprod"));
    resize();
  }
}
