package org.latexlab.pine.editor.client.parts;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.latexlab.pine.client.events.CommandEvent;
import org.latexlab.pine.client.events.CommandEventSource;
import org.latexlab.pine.client.resources.icons.EditorIconsImageBundle;

import java.util.HashMap;

/**
 * A specialized, non-reusable widget containing the main menu bar.
 */
public class MenuPart extends CommandEventSource {
  private MenuBar menu;
  private HashMap<String, MenuItem> itemIndex;
  
  /**
   * Constructs a Menu part.
   */
  public MenuPart() {
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
    addMenuItem(fileMenu, EditorIcons.Blank(), "New", CommandEvent.Command.GENERIC_START_NEW_DOCUMENT);
    fileMenu.addSeparator();
    addMenuItem(fileMenu, EditorIcons.Blank(), "Open", CommandEvent.Command.GENERIC_OPEN_EXISTING_DOCUMENT);
    addMenuItem(fileMenu, EditorIcons.Save(), "Save", CommandEvent.Command.GENERIC_SAVE_CURRENT_DOCUMENT);
    addMenuItem(fileMenu, EditorIcons.Blank(), "Save as new copy", CommandEvent.Command.GENERIC_SAVE_CURRENT_DOCUMENT_AS_NEW_COPY);
    addMenuItem(fileMenu, EditorIcons.Blank(), "Rename...", CommandEvent.Command.GENERIC_RENAME_CURRENT_DOCUMENT);
    addMenuItem(fileMenu, EditorIcons.Blank(), "Delete...", CommandEvent.Command.GENERIC_DELETE_CURRENT_DOCUMENT);
    addMenuItem(fileMenu, EditorIcons.Blank(), "Revision History", CommandEvent.Command.GENERIC_VIEW_REVISION_HISTORY);
    fileMenu.addSeparator();
    addMenuItem(fileMenu, EditorIcons.Print(), "Print...", CommandEvent.Command.GENERIC_PRINT);
    this.menu.addItem("File", fileMenu);
    MenuBar editMenu = new MenuBar(true);
    addMenuItem(editMenu, EditorIcons.Undo(), "Undo", CommandEvent.Command.NONE);
    addMenuItem(editMenu, EditorIcons.Redo(), "Redo", CommandEvent.Command.NONE);
    editMenu.addSeparator();
    addMenuItem(editMenu, EditorIcons.Cut(), "Cut", CommandEvent.Command.NONE);
    addMenuItem(editMenu, EditorIcons.Copy(), "Copy", CommandEvent.Command.NONE);
    addMenuItem(editMenu, EditorIcons.Paste(), "Paste", CommandEvent.Command.NONE);
    editMenu.addSeparator();
    addMenuItem(editMenu, EditorIcons.Blank(), "Select all", CommandEvent.Command.GENERIC_SELECT_ALL);
    this.menu.addItem("Edit", editMenu);
    MenuBar viewMenu = new MenuBar(true);
    addMenuItem(viewMenu, EditorIcons.Blank(), "Full-screen mode", CommandEvent.Command.GENERIC_FULL_SCREEN_VIEW);
    this.menu.addItem("View", viewMenu);
    MenuBar helpMenu = new MenuBar(true);
    addMenuItem(helpMenu, EditorIcons.Blank(), "About", CommandEvent.Command.GENERIC_ABOUT);
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
  private void addMenuItem(final MenuBar menuBar, AbstractImagePrototype icon, String title, final CommandEvent.Command command) {
    MenuItem item = menuBar.addItem(icon.getHTML() + " " + title, true, new Command() {
      public void execute() {
        fireOnCommand(new CommandEvent(menuBar, command));
      }
    });
    itemIndex.put(title, item);
  }
}
