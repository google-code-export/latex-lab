package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.AsyncInstantiationCallback;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.windows.ToolbarWindow;
import org.latexlab.docs.client.windows.WindowManager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;

/**
 * A toolbar containing LaTeX commands.
 */
public class ToolbarWindowDiverseSymbols extends ToolbarWindow {

  /**
   * The toolbar window's title.
   */
  public final static String TITLE = "Diverse Symbols";

  protected static ToolbarWindowDiverseSymbols instance;

  /**
   * Retrieves the single instance of this class, using asynchronous instantiation.
   * 
   * @param handler the command handler
   * @param manager the window manager
   * @param cb the asynchronous instantiation callback
   */
  public static void get(final CommandHandler handler, final WindowManager manager,
	    final AsyncInstantiationCallback<ToolbarWindowDiverseSymbols> cb) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  cb.onFailure(reason);
		}
		@Override
		public void onSuccess() {
	      if (instance == null) {
	        instance = new ToolbarWindowDiverseSymbols();
	        instance.addCommandHandler(handler);
		    instance.registeredDragController = manager.getWindowController().getPickupDragController();
		    instance.hide();
		    manager.getWindowController().makeResizable(instance);
		    manager.getBoundaryPanel().add(instance, 500, 120);
	      }
	      cb.onSuccess(instance);
		}
	});
  }
  
  /**
   * Causes the code for this class to be loaded.
   */
  public static void prefetch() {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) { }
		@Override
		public void onSuccess() {
		  new ToolbarWindowDiverseSymbols();
		}
	});
  }

  protected ToolbarWindowDiverseSymbols() {
	super(TITLE);
    buildToolBar();
  }

  /**
   * Builds the toolbar contents.
   */
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
