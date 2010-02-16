package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.resources.icons.latex.LatexIcons;
import org.latexlab.docs.client.windows.ToolbarWindow;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class ToolbarWindowBinaryOperators extends ToolbarWindow{

  public ToolbarWindowBinaryOperators() {
	super("Binary Operators");
    buildToolBar();
  }

  private void buildToolBar() {
	HorizontalPanel panel = (HorizontalPanel) contentWidget;
    panel.setStyleName("gdbe-Toolbar");
    panel.add(buildButton(LatexIcons.icons.Icon40(), "Plus/Minus", false, new SystemPasteCommand("\\pm")));
    panel.add(buildButton(LatexIcons.icons.Icon57(), "Minus/Plus", false, new SystemPasteCommand("\\mp")));
    panel.add(buildButton(LatexIcons.icons.Icon74(), "Multiply", false, new SystemPasteCommand("\\times")));
    panel.add(buildButton(LatexIcons.icons.Icon91(), "Divide", false, new SystemPasteCommand("\\div")));
    panel.add(buildButton(LatexIcons.icons.Icon108(), "Asterisk", false, new SystemPasteCommand("\\ast")));
    panel.add(buildButton(LatexIcons.icons.Icon211(), "Center Dot", false, new SystemPasteCommand("\\cdot")));
    panel.add(buildButton(LatexIcons.icons.Icon142(), "Circle", false, new SystemPasteCommand("\\circ")));
    panel.add(buildButton(LatexIcons.icons.Icon159(), "Bullet", false, new SystemPasteCommand("\\bullet")));
    panel.add(buildButton(LatexIcons.icons.Icon176(), "Round multiply", false, new SystemPasteCommand("\\otimes")));
    panel.add(buildButton(LatexIcons.icons.Icon193(), "Round plus", false, new SystemPasteCommand("\\oplus")));
    panel.add(buildButton(LatexIcons.icons.Icon210(), "Round minus", false, new SystemPasteCommand("\\ominus")));
    panel.add(buildButton(LatexIcons.icons.Icon227(), "Round dot", false, new SystemPasteCommand("\\odot")));
    panel.add(buildButton(LatexIcons.icons.Icon244(), "Round slash", false, new SystemPasteCommand("\\oslash")));
  }
}
