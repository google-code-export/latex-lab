package org.latexlab.docs.client.widgets;

import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.HasCommandHandlers;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.AttachDetachException;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

/**
 * Extends the default GWT menu bar with additional features.
 */
public class ExtendedMenuBar extends MenuBar { 
	
  private boolean sim = false;
  protected HasCommandHandlers commandSource;

  /**
   * Constructs an extended MenuBar.
   * 
   * @param vertical whether to orient the menu bar vertically
   * @param commandSource the command event source
   */
  public ExtendedMenuBar(boolean vertical, HasCommandHandlers commandSource) {
    super(vertical);
    this.commandSource = commandSource;
  }
  
  /**
   * Adds a new menu item.
   * 
   * @param icon the icon image to use in the menu item
   * @param title the title of the new menu item
   * @param command the command type for the new menu item
   * @return the new menu item
   */
  public MenuItem addItem(AbstractImagePrototype icon, String title,
	  Command command) {
	MenuItem item = createItem(icon, title, command);
    addItem(item);
    return item;
  }

  /**
   * Adds a new menu item.
   * 
   * @param icon the icon image to use in the menu item
   * @param title the title of the new menu item
   * @param command the command type for the new menu item
   * @return the new menu item
   */
  public MenuItem addItem(AbstractImagePrototype icon, String title,
      org.latexlab.docs.client.commands.Command command) {
	MenuItem item = createItem(icon, title, command);
    addItem(item);
    return item;
  }
  
  /**
   * Adds a new menu item.
   * 
   * @param icon the icon image to use in the menu item
   * @param title the title of the new menu item
   * @param popupMenu the item's sub menu
   * @return the new menu item
   */
  public MenuItem addItem(AbstractImagePrototype icon, String title,
	  MenuBar popupMenu) {
	MenuItem item = createItem(icon, title, popupMenu);
	addItem(item);
    return item;
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
  
  /**
   * Creates a menu item.
   * 
   * @param icon the icon image to use in the menu item
   * @param title the title of the new menu item
   * @param command the command to execute when new menu item is selected
   * @return the menu item
   */
  protected MenuItem createItem(AbstractImagePrototype icon, String title,
	  Command command) {
    MenuItem item = new MenuItem(icon.getHTML() + " " + title, true, command);
    return item;
  }
  
  /**
   * Creates a menu item.
   * 
   * @param icon the icon image to use in the menu item
   * @param title the title of the new menu item
   * @param command the command type for the new menu item
   * @return the menu item
   */
  protected MenuItem createItem(AbstractImagePrototype icon, String title,
	  final org.latexlab.docs.client.commands.Command command) {
    MenuItem item = new MenuItem(icon.getHTML() + " " + title, true, new Command() {
        public void execute() {
        	CommandEvent.fire(commandSource, command);
          }
        });
    return item;
  }
  
  /**
   * Creates a menu item.
   * 
   * @param icon the icon image to use in the menu item
   * @param title the title of the new menu item
   * @param popupMenu the item's sub menu
   * @return the menu item
   */
  protected MenuItem createItem(AbstractImagePrototype icon, String title,
	  MenuBar popupMenu) {
    MenuItem item = new MenuItem(icon.getHTML() + " " + title, true, popupMenu);
    return item;
  }

  /**
   * Extends the isAttached handler to support closing.
   */
  @Override
  public boolean isAttached() {
    if (sim) {
	  return false;
    }
    return super.isAttached();
  }

}