package org.latexlab.docs.editor.simple.client.parts;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;

import org.latexlab.docs.client.commands.Command;
import org.latexlab.docs.client.commands.CurrentDocumentCompileCommand;
import org.latexlab.docs.client.commands.CurrentDocumentSaveCommand;
import org.latexlab.docs.client.commands.SystemShowDialogCommand;
import org.latexlab.docs.client.content.dialogs.DynamicFileListDialog;
import org.latexlab.docs.client.content.dialogs.DynamicResourcesDialog;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.events.HasCommandHandlers;

/**
 * A specialized, non-reusable widget containing the main toolbar.
 */
public class ToolbarPart extends Composite implements HasCommandHandlers {

  private HandlerManager manager;
  private HorizontalPanel bar;
  private ArrayList<Widget> buttons;
  
  /**
   * Constructs a Toolbar part.
   */
  public ToolbarPart() {
	manager = new HandlerManager(this);
	buttons = new ArrayList<Widget>();
    bar = new HorizontalPanel();
    bar.setHeight("30px");
    bar.setWidth("100%");
    bar.setStylePrimaryName("lab-Tools-Panel");
    bar.add(buildToolBar());
    initWidget(bar);
  }
  
  public void setButtonState(int index, boolean down) {
    Widget btn = buttons.get(index);
    if (btn instanceof ToggleButton) {
    	ToggleButton tbtn = (ToggleButton) btn;
    	tbtn.setDown(down);
    }
  }
  
  /**
   * Builds the main toolbar.
   * 
   * @return the main toolbar
   */
  private HorizontalPanel buildToolBar() {
    HorizontalPanel toolbarPanel = new HorizontalPanel();
    toolbarPanel.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
    toolbarPanel.setStyleName("lab-Toolbar");
    toolbarPanel.add(buildButton(Icons.editorIcons.OpenDocument(), "Open Document", false, new SystemShowDialogCommand(DynamicFileListDialog.class)));
    toolbarPanel.add(buildButton(Icons.editorIcons.Save(), "Save", false, new CurrentDocumentSaveCommand()));
    toolbarPanel.add(buildSeparator());
    toolbarPanel.add(buildButton(Icons.editorIcons.ItemList(), "Project resources", false, new SystemShowDialogCommand(DynamicResourcesDialog.class)));
    toolbarPanel.add(buildButton(Icons.editorIcons.Compile(), "Compile", false, new CurrentDocumentCompileCommand()));
    toolbarPanel.add(buildSeparator());
    return toolbarPanel;
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
  private Widget buildButton(AbstractImagePrototype icon, String title, boolean isToggle, final Command command){
    if(isToggle){
      final ToggleButton btn = new ToggleButton(icon.createImage());
      btn.setTitle(title);
      btn.addClickHandler(new ClickHandler(){
        public void onClick(ClickEvent event) {
          btn.setFocus(false);
          btn.removeStyleName("gwt-ToggleButton-up-hovering");
          CommandEvent.fire(ToolbarPart.this, command);
        }
      });
      buttons.add(btn);
      return btn;
    }else{
      final PushButton btn = new PushButton(icon.createImage());
      btn.setTitle(title);
      btn.addClickHandler(new ClickHandler(){
        public void onClick(ClickEvent event) {
          btn.setFocus(false);
          btn.removeStyleName("gwt-PushButton-up-hovering");
          CommandEvent.fire(ToolbarPart.this, command);
        }
      });
      buttons.add(btn);
      return btn;
    }
  }
  
  private Image buildSeparator() {
	Image sep = Icons.editorIcons.Separator().createImage();
	sep.addStyleName("separator");
    return sep;
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
