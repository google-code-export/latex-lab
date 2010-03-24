package org.latexlab.docs.editor.simple.client.parts;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.AttachDetachException;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.latexlab.docs.client.commands.Command;
import org.latexlab.docs.client.commands.CurrentDocumentCopyCommand;
import org.latexlab.docs.client.commands.CurrentDocumentDeleteCommand;
import org.latexlab.docs.client.commands.CurrentDocumentExportCommand;
import org.latexlab.docs.client.commands.CurrentDocumentRenameCommand;
import org.latexlab.docs.client.commands.CurrentDocumentRevisionHistoryCommand;
import org.latexlab.docs.client.commands.CurrentDocumentSaveCommand;
import org.latexlab.docs.client.commands.ExistingDocumentOpenCommand;
import org.latexlab.docs.client.commands.NewDocumentStartCommand;
import org.latexlab.docs.client.commands.SystemAboutCommand;
import org.latexlab.docs.client.commands.SystemToggleFullScreenCommand;
import org.latexlab.docs.client.commands.SystemNotImplementedCommand;
import org.latexlab.docs.client.commands.SystemSelectResourcesCommand;
import org.latexlab.docs.client.commands.SystemSpecifyCompilerSettingsCommand;
import org.latexlab.docs.client.commands.SystemUploadDocumentsCommand;
import org.latexlab.docs.client.commands.SystemToggleWrapTextCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.resources.icons.Icons;

import java.util.HashMap;

/**
 * A specialized, non-reusable widget containing the main menu bar.
 */
public class MenuPart extends Composite implements HasCommandHandlers {
	
  private HandlerManager manager;
  private MenuBarExt menu;
  private HashMap<String, MenuItem> itemIndex;
  
  /**
   * Constructs a Menu part.
   */
  public MenuPart() {
    manager = new HandlerManager(this);
    itemIndex = new HashMap<String, MenuItem>();
    VerticalPanel menuPanel = new VerticalPanel();
    menuPanel.setWidth("100%");
    menuPanel.setHeight("30px");
    menuPanel.add(buildMenu());
    menuPanel.setStylePrimaryName("lab-Menu-Panel");
    initWidget(menuPanel);
  }
  
  public void close() {
	this.menu.close();
  }
  
  public MenuBar getMenuBar() {
	return this.menu;
  }
  
  /**
   * Retrieves a menu item by title.
   * 
   * @param title the title of the menu item which to retrieve
   * @return the menu item with the specified title
   */
  public MenuItem getMenuItem(String title) {
    return itemIndex.get(title);
  }
  
  /**
   * Sets the icon for a menu item, by title.
   * @param title the title of the menu item for which to set the icon image
   * @param icon the icon image
   */
  public void setMenuItemIcon(String title, AbstractImagePrototype icon) {
    MenuItem item = getMenuItem(title);
    item.setHTML(icon.getHTML() + " " + title);
  }
  
  /**
   * Builds the main menu bar.
   * 
   * @return the main menu bar
   */
  private MenuBarExt buildMenu() {
    menu = new MenuBarExt(false);
    MenuBarExt fileMenu = new MenuBarExt(true);
    addMenuItem(fileMenu, Icons.editorIcons.Blank(), "New", new NewDocumentStartCommand());
    fileMenu.addSeparator();
    addMenuItem(fileMenu, Icons.editorIcons.Blank(), "Open", new ExistingDocumentOpenCommand());
    addMenuItem(fileMenu, Icons.editorIcons.Save(), "Save", new CurrentDocumentSaveCommand());
    addMenuItem(fileMenu, Icons.editorIcons.Blank(), "Save as new copy", new CurrentDocumentCopyCommand());
    addMenuItem(fileMenu, Icons.editorIcons.Blank(), "Rename...", new CurrentDocumentRenameCommand());
    addMenuItem(fileMenu, Icons.editorIcons.Blank(), "Delete...", new CurrentDocumentDeleteCommand());
    addMenuItem(fileMenu, Icons.editorIcons.Blank(), "Revision History", new CurrentDocumentRevisionHistoryCommand());
    fileMenu.addSeparator();
    addMenuItem(fileMenu, Icons.editorIcons.UploadDocument(), "Upload Files...", new SystemUploadDocumentsCommand());
    this.menu.addItem("File", fileMenu);
    MenuBarExt viewMenu = new MenuBarExt(true);
    addMenuItem(viewMenu, Icons.editorIcons.CheckBlack(), "Wrap Text", new SystemToggleWrapTextCommand());
    viewMenu.addSeparator();
    addMenuItem(viewMenu, Icons.editorIcons.Blank(), "Full-screen mode", new SystemToggleFullScreenCommand());
    this.menu.addItem("View", viewMenu);
    MenuBarExt exportMenu = new MenuBarExt(true);
    addMenuItem(exportMenu, Icons.editorIcons.Blank(), "Portable Document Format (PDF)", new CurrentDocumentExportCommand("pdf"));
    addMenuItem(exportMenu, Icons.editorIcons.Blank(), "PostScript Document (PS)", new CurrentDocumentExportCommand("ps"));
    addMenuItem(exportMenu, Icons.editorIcons.Blank(), "Device Independent Format (DVI)", new CurrentDocumentExportCommand("dvi"));
    this.menu.addItem("Export", exportMenu);
    MenuBarExt compilerMenu = new MenuBarExt(true);
    addMenuItem(compilerMenu, Icons.editorIcons.ItemList(), "Project Resources", new SystemSelectResourcesCommand());
    compilerMenu.addSeparator();
    addMenuItem(compilerMenu, Icons.editorIcons.Blank(), "Settings...", new SystemSpecifyCompilerSettingsCommand());
    this.menu.addItem("Compiler", compilerMenu);
    MenuBarExt helpMenu = new MenuBarExt(true);
    addMenuItem(helpMenu, Icons.editorIcons.Blank(), "Using LaTeX Lab", new SystemNotImplementedCommand());
    addMenuItem(helpMenu, Icons.editorIcons.Blank(), "Using a custom CLSI server", new SystemNotImplementedCommand());
    helpMenu.addSeparator();
    addMenuItem(helpMenu, Icons.editorIcons.Blank(), "About", new SystemAboutCommand());
    this.menu.addItem("Help", helpMenu);
    return menu;
  }

  /**
   * Adds a menu item to a menu bar.
   * 
   * @param menuBar the menu bar to which to add the menu item
   * @param icon the icon image to use in the menu item
   * @param title the title of the new menu item
   * @param command the command type for the new menu item
   */
  private void addMenuItem(final MenuBarExt menuBar, AbstractImagePrototype icon, String title,
	  final Command command) {
    addMenuItem(menuBar, icon, title, new com.google.gwt.user.client.Command() {
      public void execute() {
    	CommandEvent.fire(MenuPart.this, command);
      }
    });
  }
  
  private void addMenuItem(final MenuBarExt menuBar, AbstractImagePrototype icon, String title,
	  com.google.gwt.user.client.Command command) {
    MenuItem item = menuBar.addItem(icon.getHTML() + " " + title, true, command);
    itemIndex.put(title, item);
  }

  @Override
  public HandlerRegistration addCommandHandler(CommandHandler handler) {
	return manager.addHandler(CommandEvent.getType(), handler);
  }
  
  @Override
  public void fireEvent(GwtEvent<?> event) {
	manager.fireEvent(event);
  }
  
  private class MenuBarExt extends MenuBar {
    
	private boolean sim = false;
	
	public MenuBarExt(boolean vertical) {
	  super(vertical);
	}
	
	@Override
	public boolean isAttached() {
	  if (sim) {
		return false;
	  }
	  return super.isAttached();
	}
	
	/**
	 * Here we jump through some hoops just to cause the menu to close.
	 */
	public void close(){
      this.sim = true;
	  try {
	    this.onDetach();
	  } catch (AttachDetachException x) {
	  }
	  this.sim = false;
	}
	
  }
}
