package org.latexlab.docs.client.windows;

import org.latexlab.docs.client.commands.Command;
import org.latexlab.docs.client.commands.SystemToggleToolbarCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.events.HasCommandHandlers;

import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;

/**
 * The base implementation of a toolbar window.
 */
public abstract class ToolbarWindow extends Window implements HasCommandHandlers {

  protected int buttonsPerRow = 10, buttonWidth = 27, buttonHeight = 27;
  protected HandlerManager manager;
  protected FlowPanel panel;
	
  /**
   * Constructs a ToolbarWindow.
   * 
   * @param title the toolbar window's title
   */
  public ToolbarWindow(String title) {
    super(title, new FlowPanel(), false);
	mainPanel.getFlexCellFormatter().setStylePrimaryName(1, 0, "lab-Toolbar");
    panel = (FlowPanel)this.contentWidget;
    panel.getElement().getStyle().setOverflow(Overflow.HIDDEN);
    manager = new HandlerManager(this);
    addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          CommandEvent.fire(ToolbarWindow.this, new SystemToggleToolbarCommand(ToolbarWindow.this.getTitle()));
	    }
	});
  }
  
  /**
   * Adds a toolbar button.
   * 
   * @param icon the button's icon image
   * @param title the button's title
   * @param isToggle whether the button is a toggle button
   * @param command the action command
   */
  protected void addButton(AbstractImagePrototype icon, String title, boolean isToggle, final Command command) {
    panel.add(buildButton(icon, title, isToggle, command));
  }
  
  @Override
  public HandlerRegistration addCommandHandler(CommandHandler handler) {
	return manager.addHandler(CommandEvent.getType(), handler);
  }
  
  /**
   * Builds a toolbar button.
   * 
   * @param icon the button's icon
   * @param title the button's tooltip text
   * @param isToggle whether the button is of type toggle or push
   * @param command the command type for the toolbar button
   * @return a toolbar button
   */
  protected Widget buildButton(AbstractImagePrototype icon, String title, boolean isToggle, final Command command){
    if(isToggle){
      final ToggleButton btn = new ToggleButton(icon.createImage());
      btn.setTitle(title);
      btn.addClickHandler(new ClickHandler(){
        public void onClick(ClickEvent event) {
          CommandEvent.fire(ToolbarWindow.this, command);
        }
      });
      return btn;
    }else{
      final PushButton btn = new PushButton(icon.createImage());
      btn.setTitle(title);
      btn.addClickHandler(new ClickHandler(){
        public void onClick(ClickEvent event) {
          CommandEvent.fire(ToolbarWindow.this, command);
        }
      });
      return btn;
    }
  }
  
  @Override
  public void fireEvent(GwtEvent<?> event) {
	manager.fireEvent(event);
  }
  
  /**
   * Resizes the toolbar window.
   */
  protected void resize() {
    int xcount = buttonsPerRow;
    if (xcount > panel.getWidgetCount()) {
      xcount = panel.getWidgetCount();
    }
    setContentSizeFinal();
  }
  
  @Override
  public void setContentSize(int width, int height) {
	int minWidth = buttonWidth * 2;
	if (width < minWidth) {
	  width = minWidth;
	}
	if (width < buttonWidth) {
	  width = buttonWidth;
	}
	if (height < buttonHeight) {
	  height = buttonHeight;
	}
	super.setContentSize(width, height);
  }
  
  @Override
  public void setContentSizeFinal() {
	int width = panel.getOffsetWidth();
	int height = panel.getOffsetHeight();
	if (width == 0) {
	  width = buttonsPerRow * buttonWidth;
	}
	int count = panel.getWidgetCount();
	int xcount = (int) Math.ceil((float) width / (float) buttonWidth);
    int ycount = (int) Math.ceil((float) count / (float) xcount);
	if (xcount > count) {
	  xcount = count;
	  ycount = 1;
	}
	width = xcount * buttonWidth;
	height = ycount * buttonHeight;
	super.setContentSize(width, height);
  }
  
  /**
   * Toggle the toolbar window's visibility.
   */
  public void toggle() {
    if (this.isVisible()) {
      this.hide();
    } else {
      this.show();
    }
  }
}
