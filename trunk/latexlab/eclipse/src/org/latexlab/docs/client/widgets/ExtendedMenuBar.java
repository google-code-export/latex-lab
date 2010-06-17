package org.latexlab.docs.client.widgets;

import java.util.HashMap;

import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.HasCommandHandlers;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.AttachDetachException;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

/**
 * Extends the default GWT menu bar with additional features.
 */
public class ExtendedMenuBar extends MenuBar { 
	
  /**
   * Extends the GWT MenuItem to contain an icon.
   */
  public class ExtendedMenuItem extends MenuItem {

    private AbstractImagePrototype icon;
    private String text;
	  
    /**
     * Constructs a menu item.
     * 
     * @param icon the menu item's icon
     * @param text the menu item's text
     * @param cmd the command to execute when the item is selected
     */
	public ExtendedMenuItem(AbstractImagePrototype icon, String text, Command cmd) {
	  super("<span>" + icon.getHTML() + "</span> " + text, true, cmd);
	  this.text = text;
	  this.icon = icon;
	}
	
	/**
	 * Constructs a menu item.
	 * 
	 * @param icon the menu item's icon
	 * @param text the menu item's text
	 * @param submenu the child menu
	 */
	public ExtendedMenuItem(AbstractImagePrototype icon, String text, ExtendedMenuBar submenu) {
	  super("<span>" + icon.getHTML() + "</span> " + text, true, submenu);
	  this.text = text;
	  this.icon = icon;
	}

	/**
	 * Constructs a menu item.
	 * 
	 * @param icon the menu item's icon
	 * @param text the menu item's text
	 * @param cmd the command to trigger when the item is selected
	 */
	public ExtendedMenuItem(AbstractImagePrototype icon, String text,
	    final org.latexlab.docs.client.commands.Command cmd) {
	  super("<span>" + icon.getHTML() + "</span> " + text, true, new Command() {
		@Override
		public void execute() {
          CommandEvent.fire(commandSource, cmd);
		} 
	  });
	  this.text = text;
	  this.icon = icon;
	}

	/**
	 * Constructs a menu item.
	 * 
	 * @param icon the menu item's icon
	 * @param text the menu item's text
	 * @param submenu the child menu
	 */
	public ExtendedMenuItem(String text, ExtendedMenuBar submenu) {
	  super(text, true, submenu);
	  this.text = text;
	}
	
	/**
	 * Retrieves the menu item's icon.
	 * 
	 * @return the menu item's icon
	 */
	public AbstractImagePrototype getIcon() {
	  return icon;
	}

	/**
	 * Retrieves the menu item's text.
	 * 
	 * @return the menu item's text
	 */
	@Override
	public String getText() {
	  return text;
	}

	/**
	 * Sets the menu item's icon.
	 * 
	 * @param icon the menu item's icon.
	 */
	public void setIcon(AbstractImagePrototype icon) {
	  this.icon = icon;
	  super.setHTML("<span>" + icon.getHTML() + "</span> " + this.text);
	}

	/**
	 * Sets the menu item's text.
	 * 
	 * @param text the menu item's text
	 */
	@Override
	public void setText(String text) {
	  this.text = text;
	  super.setHTML("<span>" + icon.getHTML() + "</span> " + this.text);
	}
	
  }
  protected HasCommandHandlers commandSource;
  protected HashMap<String, ExtendedMenuItem> itemIndex;

  protected boolean sim = false, vertical;
  
  /**
   * Constructs an extended MenuBar.
   * 
   * @param vertical whether to orient the menu bar vertically
   * @param commandSource the command event source
   */
  public ExtendedMenuBar(boolean vertical, HasCommandHandlers commandSource) {
    super(vertical);
    this.itemIndex = new HashMap<String, ExtendedMenuItem>();
    this.vertical = vertical;
    this.commandSource = commandSource;
  }
  
  /**
   * Adds a new menu item.
   * 
   * @param icon the icon image to use in the menu item
   * @param text the text of the new menu item
   * @param command the command type for the new menu item
   * @return the new menu item
   */
  public ExtendedMenuItem addItem(AbstractImagePrototype icon, String text,
	  Command command) {
	ExtendedMenuItem item = new ExtendedMenuItem(icon, text, command);
    addItem(item);
    return item;
  }
  
  /**
   * Adds a new menu item.
   * 
   * @param icon the icon image to use in the menu item
   * @param text the text of the new menu item
   * @param popupMenu the item's sub menu
   * @return the new menu item
   */
  public ExtendedMenuItem addItem(AbstractImagePrototype icon, String text,
	  ExtendedMenuBar popupMenu) {
	ExtendedMenuItem item = new ExtendedMenuItem(icon, text, popupMenu);
	addItem(item);
    return item;
  }
  
  /**
   * Adds a new menu item.
   * 
   * @param icon the icon image to use in the menu item
   * @param text the text of the new menu item
   * @param command the command type for the new menu item
   * @return the new menu item
   */
  public ExtendedMenuItem addItem(AbstractImagePrototype icon, String text,
      org.latexlab.docs.client.commands.Command command) {
	ExtendedMenuItem item = new ExtendedMenuItem(icon, text, command);
    addItem(item);
    return item;
  }
  
  /**
   * Adds a new menu item.
   * 
   * @param text the text of the new menu item
   * @param item the menu item to add
   * @return the new menu item
   */
  public ExtendedMenuItem addItem(ExtendedMenuItem item) {
	itemIndex.put(item.getText(), item);
	super.addItem(item);
	return item;
  }
  
  /**
   * Adds a new menu item.
   * 
   * @param icon the icon image to use in the menu item
   * @param text the text of the new menu item
   * @param popupMenu the item's sub menu
   * @return the new menu item
   */
  public ExtendedMenuItem addItem(String text,
	  ExtendedMenuBar popupMenu) {
	ExtendedMenuItem item = new ExtendedMenuItem(text, popupMenu);
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
   * Computes the absolute y coordinate for a given element
   * by iterating through its offset parents.
   * 
   * @param el the element for which to compute the absolute y coordinate
   * @return the absolute y coordinate for the specified element
   */
  protected int getAbsoluteTop(Element el) {
	int top = el.getOffsetTop();
	com.google.gwt.dom.client.Element par = el;
	while ((par = par.getOffsetParent()) != null) {
	  top += par.getOffsetTop();
	}
	return top;
  }
  
  /**
   * Retrieves a menu item, by text.
   * 
   * @param text the text of the menu item to retrieve
   * @return the menu item with the specified text
   */
  public ExtendedMenuItem getItem(String text) {
	if (itemIndex.containsKey(text)) {
	  return itemIndex.get(text);
	}
	return null;
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

  /**
   * On attach (i.e. on-open) check that the menu bar is fully visible. If not, move
   * menu bar up.
   */
  @Override
  public void onAttach() {
	super.onAttach();
	new Timer() {
		@Override
		public void run() {
		  try {
			if (ExtendedMenuBar.this.vertical) {
			  if (ExtendedMenuBar.this.getItems().size() > 0) {
				Element popup = ExtendedMenuBar.this.getParent().getElement();
				int winHeight = com.google.gwt.user.client.Window.getClientHeight();
				int popupTop = getAbsoluteTop(popup);
				int popupHeight = popup.getOffsetHeight();
				if (popupHeight >= winHeight) {
			      popup.getStyle().setPosition(Position.RELATIVE);
			      popup.getStyle().setTop(-popupTop, Unit.PX);
			    } else {
			      int totalHeight = popupTop + popupHeight;
			      if (totalHeight > winHeight) {
			        int diff = totalHeight - winHeight;
			        popup.getStyle().setPosition(Position.RELATIVE);
			        popup.getStyle().setTop(-diff, Unit.PX);
			      } else {
			        popup.getStyle().setPosition(Position.RELATIVE);
			        popup.getStyle().setTop(0, Unit.PX);
			      }
			    }
			  }
			}
		  } catch (Exception e) {
		    UncaughtExceptionHandler handler = GWT.getUncaughtExceptionHandler();
		    if (handler != null) {
		      e.printStackTrace();
		      handler.onUncaughtException(e);
		    }
		  }
		}
	}.schedule(10);
  }
  
}