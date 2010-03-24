package org.latexlab.docs.editor.advanced.client.parts;

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
import org.latexlab.docs.client.commands.ExistingDocumentOpenCommand;
import org.latexlab.docs.client.commands.SystemRedoCommand;
import org.latexlab.docs.client.commands.SystemSelectResourcesCommand;
import org.latexlab.docs.client.commands.SystemToggleToolbarCommand;
import org.latexlab.docs.client.commands.SystemUndoCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowAboveAndBelow;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowAccents;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowArrows;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowArrowsWithCaptions;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowBinaryOperators;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowBoundaries;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowComparison;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowDiverseSymbols;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowFormatting;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowGreekLowercase;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowGreekUppercase;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowLogical;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowMath;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowOperators;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowSets;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowSubscriptAndSuperscript;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowWhiteSpacesAndDots;

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
    toolbarPanel.add(buildButton(Icons.editorIcons.OpenDocument(), "Open Document", false, new ExistingDocumentOpenCommand()));
    toolbarPanel.add(buildButton(Icons.editorIcons.Save(), "Save", false, new CurrentDocumentSaveCommand()));
    toolbarPanel.add(buildSeparator());
    toolbarPanel.add(buildButton(Icons.editorIcons.Undo(), "Undo", false, new SystemUndoCommand()));
    toolbarPanel.add(buildButton(Icons.editorIcons.Redo(), "Redo", false, new SystemRedoCommand()));
    toolbarPanel.add(buildSeparator());
    toolbarPanel.add(buildButton(Icons.editorIcons.ItemList(), "Project resources", false, new SystemSelectResourcesCommand()));
    toolbarPanel.add(buildButton(Icons.editorIcons.Compile(), "Compile", false, new CurrentDocumentCompileCommand()));
    toolbarPanel.add(buildSeparator());
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.AboveAndBelow(), ToolbarWindowAboveAndBelow.TITLE, true, new SystemToggleToolbarCommand(ToolbarWindowAboveAndBelow.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.Accents(), ToolbarWindowAccents.TITLE, true, new SystemToggleToolbarCommand(ToolbarWindowAccents.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.Arrows(), ToolbarWindowArrows.TITLE, true, new SystemToggleToolbarCommand(ToolbarWindowArrows.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.ArrowsWithCaptions()/*Icon327*/, ToolbarWindowArrowsWithCaptions.TITLE, true, new SystemToggleToolbarCommand(ToolbarWindowArrowsWithCaptions.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.BinaryOperators(), ToolbarWindowBinaryOperators.TITLE, true, new SystemToggleToolbarCommand(ToolbarWindowBinaryOperators.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.Boundaries(), ToolbarWindowBoundaries.TITLE, true, new SystemToggleToolbarCommand(ToolbarWindowBoundaries.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.Comparison(), ToolbarWindowComparison.TITLE, true, new SystemToggleToolbarCommand(ToolbarWindowComparison.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.DiverseSymbols(), ToolbarWindowDiverseSymbols.TITLE, true, new SystemToggleToolbarCommand(ToolbarWindowDiverseSymbols.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.Formatting(), ToolbarWindowFormatting.TITLE, true, new SystemToggleToolbarCommand(ToolbarWindowFormatting.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.GreekLowercaseLetters(), ToolbarWindowGreekLowercase.TITLE, true, new SystemToggleToolbarCommand(ToolbarWindowGreekLowercase.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.GreekUppercaseLetters(), ToolbarWindowGreekUppercase.TITLE, true, new SystemToggleToolbarCommand(ToolbarWindowGreekUppercase.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.Logical(), ToolbarWindowLogical.TITLE, true, new SystemToggleToolbarCommand(ToolbarWindowLogical.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.Mathematical(), ToolbarWindowMath.TITLE, true, new SystemToggleToolbarCommand(ToolbarWindowMath.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.Operators(), ToolbarWindowOperators.TITLE, true, new SystemToggleToolbarCommand(ToolbarWindowOperators.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.Sets(), ToolbarWindowSets.TITLE, true, new SystemToggleToolbarCommand(ToolbarWindowSets.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.SubscriptAndSuperscript(), ToolbarWindowSubscriptAndSuperscript.TITLE, true, new SystemToggleToolbarCommand(ToolbarWindowSubscriptAndSuperscript.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.WhiteSpacesAndDots(), ToolbarWindowWhiteSpacesAndDots.TITLE, true, new SystemToggleToolbarCommand(ToolbarWindowWhiteSpacesAndDots.TITLE)));
    //toolbarPanel.add(buildSeparator());
    //toolbarPanel.add(buildButton(Icons.editorIcons.CheckBlack(), "Test Local Compile", false, new CurrentDocumentCompileLocalCommand()));
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
