package org.latexlab.docs.client.parts;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.latexlab.docs.client.commands.Command;
import org.latexlab.docs.client.commands.CurrentDocumentCopyCommand;
import org.latexlab.docs.client.commands.CurrentDocumentDeleteCommand;
import org.latexlab.docs.client.commands.CurrentDocumentRenameCommand;
import org.latexlab.docs.client.commands.CurrentDocumentRevisionHistoryCommand;
import org.latexlab.docs.client.commands.CurrentDocumentSaveCommand;
import org.latexlab.docs.client.commands.ExistingDocumentOpenCommand;
import org.latexlab.docs.client.commands.NewDocumentStartCommand;
import org.latexlab.docs.client.commands.SystemAboutCommand;
import org.latexlab.docs.client.commands.SystemFullScreenCommand;
import org.latexlab.docs.client.commands.SystemNotImplementedCommand;
import org.latexlab.docs.client.commands.SystemPrintCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.resources.icons.EditorIconsImageBundle;

import java.util.HashMap;

/**
 * A specialized, non-reusable widget containing the main menu bar.
 */
public class MenuPart extends Composite implements HasCommandHandlers {
	
  private HandlerManager manager;
  private MenuBar menu;
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
    menuPanel.setStylePrimaryName("gdbe-Menu-Panel");
    initWidget(menuPanel);
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
  private MenuBar buildMenu() {
    EditorIconsImageBundle EditorIcons = (EditorIconsImageBundle)GWT.create(EditorIconsImageBundle.class);
    menu = new MenuBar(false);
    MenuBar fileMenu = new MenuBar(true);
    addMenuItem(fileMenu, EditorIcons.Blank(), "New", new NewDocumentStartCommand());
    fileMenu.addSeparator();
    addMenuItem(fileMenu, EditorIcons.Blank(), "Open", new ExistingDocumentOpenCommand());
    addMenuItem(fileMenu, EditorIcons.Save(), "Save", new CurrentDocumentSaveCommand());
    addMenuItem(fileMenu, EditorIcons.Blank(), "Save as new copy", new CurrentDocumentCopyCommand());
    addMenuItem(fileMenu, EditorIcons.Blank(), "Rename...", new CurrentDocumentRenameCommand());
    addMenuItem(fileMenu, EditorIcons.Blank(), "Delete...", new CurrentDocumentDeleteCommand());
    addMenuItem(fileMenu, EditorIcons.Blank(), "Revision History", new CurrentDocumentRevisionHistoryCommand());
    fileMenu.addSeparator();
    addMenuItem(fileMenu, EditorIcons.Print(), "Print...", new SystemPrintCommand());
    this.menu.addItem("File", fileMenu);
    MenuBar editMenu = new MenuBar(true);
    addMenuItem(editMenu, EditorIcons.Undo(), "Undo", new SystemNotImplementedCommand());
    addMenuItem(editMenu, EditorIcons.Redo(), "Redo", new SystemNotImplementedCommand());
    editMenu.addSeparator();
    addMenuItem(editMenu, EditorIcons.Cut(), "Cut", new SystemNotImplementedCommand());
    addMenuItem(editMenu, EditorIcons.Copy(), "Copy", new SystemNotImplementedCommand());
    addMenuItem(editMenu, EditorIcons.Paste(), "Paste", new SystemNotImplementedCommand());
    editMenu.addSeparator();
    addMenuItem(editMenu, EditorIcons.Blank(), "Select all", new SystemNotImplementedCommand());
    this.menu.addItem("Edit", editMenu);
    MenuBar viewMenu = new MenuBar(true);
    addMenuItem(viewMenu, EditorIcons.Blank(), "Full-screen mode", new SystemFullScreenCommand());
    this.menu.addItem("View", viewMenu);
    MenuBar compilerMenu = new MenuBar(true);
    addMenuItem(compilerMenu, EditorIcons.Blank(), "Options...", new SystemNotImplementedCommand());
    this.menu.addItem("Compiler", compilerMenu);
    MenuBar helpMenu = new MenuBar(true);
    addMenuItem(helpMenu, EditorIcons.Blank(), "About", new SystemAboutCommand());
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
  private void addMenuItem(final MenuBar menuBar, AbstractImagePrototype icon, String title, final Command command) {
    MenuItem item = menuBar.addItem(icon.getHTML() + " " + title, true, new com.google.gwt.user.client.Command() {
      public void execute() {
    	CommandEvent.fire(MenuPart.this, command);
      }
    });
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
}
