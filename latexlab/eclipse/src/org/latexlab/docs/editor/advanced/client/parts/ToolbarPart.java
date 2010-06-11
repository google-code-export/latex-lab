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
import org.latexlab.docs.client.commands.SystemRedoCommand;
import org.latexlab.docs.client.commands.SystemShowDialogCommand;
import org.latexlab.docs.client.commands.SystemToggleLatexToolbarCommand;
import org.latexlab.docs.client.commands.SystemUndoCommand;
import org.latexlab.docs.client.content.dialogs.DynamicFileListDialog;
import org.latexlab.docs.client.content.dialogs.DynamicResourcesDialog;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.content.latex.SetAboveAndBelow;
import org.latexlab.docs.client.content.latex.SetAccents;
import org.latexlab.docs.client.content.latex.SetArrows;
import org.latexlab.docs.client.content.latex.SetArrowsWithCaptions;
import org.latexlab.docs.client.content.latex.SetBinaryOperators;
import org.latexlab.docs.client.content.latex.SetBoundaries;
import org.latexlab.docs.client.content.latex.SetComparison;
import org.latexlab.docs.client.content.latex.SetDiverseSymbols;
import org.latexlab.docs.client.content.latex.SetFormatting;
import org.latexlab.docs.client.content.latex.SetGreekLowercase;
import org.latexlab.docs.client.content.latex.SetGreekUppercase;
import org.latexlab.docs.client.content.latex.SetLogical;
import org.latexlab.docs.client.content.latex.SetConstructs;
import org.latexlab.docs.client.content.latex.SetBigOperators;
import org.latexlab.docs.client.content.latex.SetSets;
import org.latexlab.docs.client.content.latex.SetSubscriptAndSuperscript;
import org.latexlab.docs.client.content.latex.SetWhiteSpacesAndDots;
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

  public void setButtonState(String title, boolean down) {
	int i = getButtonIndex(title);
	if (i >= 0) {
	  setButtonState(i, down);
	}
  }
  
  public void setButtonState(int index, boolean down) {
    Widget btn = buttons.get(index);
    if (btn instanceof ToggleButton) {
    	ToggleButton tbtn = (ToggleButton) btn;
    	tbtn.setDown(down);
    }
  }

  public void setButtonEnabled(String title, boolean down) {
	int i = getButtonIndex(title);
	if (i >= 0) {
		setButtonEnabled(i, down);
	}
  }
  
  public void setButtonEnabled(int index, boolean enabled) {
    Widget btn = buttons.get(index);
    if (btn != null) {
      if (enabled) {
    	btn.removeStyleDependentName("Disabled");
      } else {
    	btn.addStyleDependentName("Disabled");
      }
    }
  }
  
  private int getButtonIndex(String title) {
	for (int i=0; i<buttons.size(); i++) {
	  Widget w = buttons.get(i);
	  if (w.getTitle().equals(title)) {
		return i;
	  }
	}
	return -1;
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
    toolbarPanel.add(buildButton(Icons.editorIcons.Undo(), "Undo", false, new SystemUndoCommand()));
    toolbarPanel.add(buildButton(Icons.editorIcons.Redo(), "Redo", false, new SystemRedoCommand()));
    toolbarPanel.add(buildSeparator());
    toolbarPanel.add(buildButton(Icons.editorIcons.Resources(), "Project resources", false, new SystemShowDialogCommand(DynamicResourcesDialog.class)));
    toolbarPanel.add(buildButton(Icons.editorIcons.Compile(), "Compile", false, new CurrentDocumentCompileCommand()));
    toolbarPanel.add(buildSeparator());
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.AboveAndBelow(), SetAboveAndBelow.TITLE, true, new SystemToggleLatexToolbarCommand(SetAboveAndBelow.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.Accents(), SetAccents.TITLE, true, new SystemToggleLatexToolbarCommand(SetAccents.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.Arrows(), SetArrows.TITLE, true, new SystemToggleLatexToolbarCommand(SetArrows.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.ArrowsWithCaptions()/*Icon327*/, SetArrowsWithCaptions.TITLE, true, new SystemToggleLatexToolbarCommand(SetArrowsWithCaptions.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.BinaryOperators(), SetBinaryOperators.TITLE, true, new SystemToggleLatexToolbarCommand(SetBinaryOperators.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.Boundaries(), SetBoundaries.TITLE, true, new SystemToggleLatexToolbarCommand(SetBoundaries.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.Comparison(), SetComparison.TITLE, true, new SystemToggleLatexToolbarCommand(SetComparison.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.DiverseSymbols(), SetDiverseSymbols.TITLE, true, new SystemToggleLatexToolbarCommand(SetDiverseSymbols.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.Formatting(), SetFormatting.TITLE, true, new SystemToggleLatexToolbarCommand(SetFormatting.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.GreekLowercaseLetters(), SetGreekLowercase.TITLE, true, new SystemToggleLatexToolbarCommand(SetGreekLowercase.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.GreekUppercaseLetters(), SetGreekUppercase.TITLE, true, new SystemToggleLatexToolbarCommand(SetGreekUppercase.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.Logical(), SetLogical.TITLE, true, new SystemToggleLatexToolbarCommand(SetLogical.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.Mathematical(), SetConstructs.TITLE, true, new SystemToggleLatexToolbarCommand(SetConstructs.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.Operators(), SetBigOperators.TITLE, true, new SystemToggleLatexToolbarCommand(SetBigOperators.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.Sets(), SetSets.TITLE, true, new SystemToggleLatexToolbarCommand(SetSets.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.SubscriptAndSuperscript(), SetSubscriptAndSuperscript.TITLE, true, new SystemToggleLatexToolbarCommand(SetSubscriptAndSuperscript.TITLE)));
    toolbarPanel.add(buildButton(Icons.latexGroupsIcons.WhiteSpacesAndDots(), SetWhiteSpacesAndDots.TITLE, true, new SystemToggleLatexToolbarCommand(SetWhiteSpacesAndDots.TITLE)));
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
