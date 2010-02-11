package org.latexlab.docs.client.parts;

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
import org.latexlab.docs.client.commands.SystemNotImplementedCommand;
import org.latexlab.docs.client.commands.SystemToggleToolbarCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.resources.icons.EditorIcons;
import org.latexlab.docs.client.resources.icons.latex.LatexIcons;

/**
 * A specialized, non-reusable widget containing the main toolbar.
 */
public class ToolbarPart extends Composite implements HasCommandHandlers {

  private HandlerManager manager;
  private HorizontalPanel bar;
  
  /**
   * Constructs a Toolbar part.
   */
  public ToolbarPart() {
	manager = new HandlerManager(this);
    bar = new HorizontalPanel();
    bar.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
    bar.setSpacing(2);
    bar.setHeight("30px");
    bar.setWidth("100%");
    bar.setStylePrimaryName("gdbe-Tools-Panel");
    bar.add(buildToolBar());
    initWidget(bar);
  }
  
  /**
   * Builds the main toolbar.
   * 
   * @return the main toolbar
   */
  private HorizontalPanel buildToolBar() {
    HorizontalPanel toolbarPanel = new HorizontalPanel();
    toolbarPanel.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
    toolbarPanel.setStyleName("gdbe-Toolbar");
    toolbarPanel.add(buildButton(EditorIcons.icons.Save(), "Save", false, new CurrentDocumentSaveCommand()));
    toolbarPanel.add(buildButton(EditorIcons.icons.Print(), "Print", false, new SystemNotImplementedCommand()));
    toolbarPanel.add(buildButton(EditorIcons.icons.Undo(), "Undo", false, new SystemNotImplementedCommand()));
    toolbarPanel.add(buildButton(EditorIcons.icons.Redo(), "Redo", false, new SystemNotImplementedCommand()));
    toolbarPanel.add(buildSeparator());
    toolbarPanel.add(buildButton(LatexIcons.icons.Icon519(), "Compile", false, new CurrentDocumentCompileCommand()));
    toolbarPanel.add(buildSeparator());
    toolbarPanel.add(buildButton(LatexIcons.icons.Icon254(), "Formatting", true, new SystemNotImplementedCommand()));
    toolbarPanel.add(buildButton(LatexIcons.icons.Icon72(), "Comparison", true, new SystemNotImplementedCommand()));
    toolbarPanel.add(buildButton(LatexIcons.icons.Icon89(), "White Spaces & Dots", true, new SystemNotImplementedCommand()));
    toolbarPanel.add(buildButton(LatexIcons.icons.Icon106(), "Accents", true, new SystemNotImplementedCommand()));
    toolbarPanel.add(buildButton(LatexIcons.icons.Icon123(), "Binary Operators", true, new SystemNotImplementedCommand()));
    toolbarPanel.add(buildButton(LatexIcons.icons.Icon140(), "Arrows", true, new SystemToggleToolbarCommand(1)));
    toolbarPanel.add(buildButton(LatexIcons.icons.Icon157(), "Logical", true, new SystemNotImplementedCommand()));
    toolbarPanel.add(buildButton(LatexIcons.icons.Icon174(), "Sets", true, new SystemNotImplementedCommand()));
    toolbarPanel.add(buildButton(LatexIcons.icons.Icon191(), "Diverse Symbols", true, new SystemNotImplementedCommand()));
    toolbarPanel.add(buildButton(LatexIcons.icons.Icon208(), "Greek Lowercase Letters", true, new SystemNotImplementedCommand()));
    toolbarPanel.add(buildButton(LatexIcons.icons.Icon225(), "Greek Uppercase Letters", true, new SystemNotImplementedCommand()));
    toolbarPanel.add(buildButton(LatexIcons.icons.Icon242(), "Boundaries", true, new SystemNotImplementedCommand()));
    toolbarPanel.add(buildButton(LatexIcons.icons.Icon259(), "Mathematical", true, new SystemNotImplementedCommand()));
    toolbarPanel.add(buildButton(LatexIcons.icons.Icon276(), "Subscript & Superscript", true, new SystemNotImplementedCommand()));
    toolbarPanel.add(buildButton(LatexIcons.icons.Icon293(), "Operators", true, new SystemNotImplementedCommand()));
    toolbarPanel.add(buildButton(LatexIcons.icons.Icon310(), "Above & Below", true, new SystemToggleToolbarCommand(0)));
    //toolbarPanel.add(buildButton(latexIcons.Icon327(), "Arrows With Captions", true, new SystemNotImplementedCommand()));
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
          CommandEvent.fire(ToolbarPart.this, command);
        }
      });
      return btn;
    }else{
      final PushButton btn = new PushButton(icon.createImage());
      btn.setTitle(title);
      btn.addClickHandler(new ClickHandler(){
        public void onClick(ClickEvent event) {
          CommandEvent.fire(ToolbarPart.this, command);
        }
      });
      return btn;
    }
  }
  
  private Image buildSeparator() {
	Image sep = EditorIcons.icons.Separator().createImage();
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
