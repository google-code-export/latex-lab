package org.latexlab.pine.editor.client.parts;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;

import org.latexlab.pine.client.events.CommandEvent;
import org.latexlab.pine.client.events.CommandEventSource;
import org.latexlab.pine.client.resources.icons.EditorIconsImageBundle;
import org.latexlab.pine.client.resources.icons.latex.LatexIconsImageBundle;

/**
 * A specialized, non-reusable widget containing the main toolbar.
 */
public class ToolbarPart extends CommandEventSource {
  private HorizontalPanel bar;
  
  /**
   * Constructs a Toolbar part.
   */
  public ToolbarPart() {
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
    EditorIconsImageBundle editorIcons = (EditorIconsImageBundle)GWT.create(EditorIconsImageBundle.class);
    LatexIconsImageBundle latexIcons = (LatexIconsImageBundle)GWT.create(LatexIconsImageBundle.class);
    HorizontalPanel toolbarPanel = new HorizontalPanel();
    toolbarPanel.setStyleName("gdbe-Toolbar");
    toolbarPanel.add(buildButton(editorIcons.Save(), "Save", false, CommandEvent.Command.GENERIC_SAVE_CURRENT_DOCUMENT));
    toolbarPanel.add(buildButton(editorIcons.Print(), "Print", false, CommandEvent.Command.GENERIC_PRINT));
    toolbarPanel.add(buildButton(editorIcons.Undo(), "Undo", false, CommandEvent.Command.GENERIC_UNDO));
    toolbarPanel.add(buildButton(editorIcons.Redo(), "Redo", false, CommandEvent.Command.GENERIC_REDO));
    toolbarPanel.add(buildButton(latexIcons.Icon254(), "Formatting", true, CommandEvent.Command.NONE));
    toolbarPanel.add(buildButton(latexIcons.Icon72(), "Comparison", true, CommandEvent.Command.NONE));
    toolbarPanel.add(buildButton(latexIcons.Icon89(), "White Spaces & Dots", true, CommandEvent.Command.NONE));
    toolbarPanel.add(buildButton(latexIcons.Icon106(), "Accents", true, CommandEvent.Command.NONE));
    toolbarPanel.add(buildButton(latexIcons.Icon123(), "Binary Operators", true, CommandEvent.Command.NONE));
    toolbarPanel.add(buildButton(latexIcons.Icon140(), "Arrows", true, CommandEvent.Command.NONE));
    toolbarPanel.add(buildButton(latexIcons.Icon157(), "Logical", true, CommandEvent.Command.NONE));
    toolbarPanel.add(buildButton(latexIcons.Icon174(), "Sets", true, CommandEvent.Command.NONE));
    toolbarPanel.add(buildButton(latexIcons.Icon191(), "Diverse Symbols", true, CommandEvent.Command.NONE));
    toolbarPanel.add(buildButton(latexIcons.Icon208(), "Greek Lowercase Letters", true, CommandEvent.Command.NONE));
    toolbarPanel.add(buildButton(latexIcons.Icon225(), "Greek Uppercase Letters", true, CommandEvent.Command.NONE));
    toolbarPanel.add(buildButton(latexIcons.Icon242(), "Boundaries", true, CommandEvent.Command.NONE));
    toolbarPanel.add(buildButton(latexIcons.Icon259(), "Mathematical", true, CommandEvent.Command.NONE));
    toolbarPanel.add(buildButton(latexIcons.Icon276(), "Subscript & Superscript", true, CommandEvent.Command.NONE));
    toolbarPanel.add(buildButton(latexIcons.Icon293(), "Operators", true, CommandEvent.Command.NONE));
    toolbarPanel.add(buildButton(latexIcons.Icon310(), "Above & Below", true, CommandEvent.Command.NONE));
    //toolbarPanel.add(buildButton(latexIcons.Icon327(), "Arrows With Captions", true, CommandEvent.Command.NONE));
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
  private Widget buildButton(AbstractImagePrototype icon, String title, boolean isToggle, final CommandEvent.Command command){
    if(isToggle){
      final ToggleButton btn = new ToggleButton(icon.createImage());
      btn.setTitle(title);
      btn.addClickHandler(new ClickHandler(){
        public void onClick(ClickEvent event) {
          fireOnCommand(new CommandEvent(btn, command));
        }
      });
      return btn;
    }else{
      final PushButton btn = new PushButton(icon.createImage());
      btn.setTitle(title);
      btn.addClickHandler(new ClickHandler(){
        public void onClick(ClickEvent event) {
          fireOnCommand(new CommandEvent(btn, command));
        }
      });
      return btn;
    }
  }

}
