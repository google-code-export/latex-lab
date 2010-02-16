package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.resources.icons.latex.LatexIcons;
import org.latexlab.docs.client.windows.ToolbarWindow;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class ToolbarWindowBoundaries extends ToolbarWindow{

  public ToolbarWindowBoundaries() {
	super("Boundaries");
    buildToolBar();
  }

  private void buildToolBar() {
	HorizontalPanel panel = (HorizontalPanel) contentWidget;
    panel.setStyleName("gdbe-Toolbar");
    panel.add(buildButton(LatexIcons.icons.Icon47(), "Invisible left boundary", false, new SystemPasteCommand("\\left.")));
    panel.add(buildButton(LatexIcons.icons.Icon64(), "Invisible right boundary", false, new SystemPasteCommand("\\right.")));
    panel.add(buildButton(LatexIcons.icons.Icon81(), "Left parentheses", false, new SystemPasteCommand("\\left(")));
    panel.add(buildButton(LatexIcons.icons.Icon98(), "Right parantheses", false, new SystemPasteCommand("\\right)")));
    panel.add(buildButton(LatexIcons.icons.Icon115(), "Left square bracket", false, new SystemPasteCommand("\\left[")));
    panel.add(buildButton(LatexIcons.icons.Icon132(), "Right square bracket", false, new SystemPasteCommand("\\right]")));
    panel.add(buildButton(LatexIcons.icons.Icon149(), "Left curly bracket", false, new SystemPasteCommand("\\left\\{")));
    panel.add(buildButton(LatexIcons.icons.Icon166(), "Right curly bracket", false, new SystemPasteCommand("\\right\\}")));
    panel.add(buildButton(LatexIcons.icons.Icon183(), "Left floor", false, new SystemPasteCommand("\\left\\lfloor")));
    panel.add(buildButton(LatexIcons.icons.Icon200(), "Right floor", false, new SystemPasteCommand("\\right\\rfloor")));
    panel.add(buildButton(LatexIcons.icons.Icon217(), "Left ceil", false, new SystemPasteCommand("\\left\\lceil")));
    panel.add(buildButton(LatexIcons.icons.Icon234(), "Right ceil", false, new SystemPasteCommand("\\right\\rceil")));
    panel.add(buildButton(LatexIcons.icons.Icon251(), "Left angle bracket", false, new SystemPasteCommand("\\left\\langle")));
    panel.add(buildButton(LatexIcons.icons.Icon268(), "Right angle bracket", false, new SystemPasteCommand("\\right\\rangle")));
    panel.add(buildButton(LatexIcons.icons.Icon285(), "Left modulus", false, new SystemPasteCommand("\\left|")));
    panel.add(buildButton(LatexIcons.icons.Icon302(), "Right modulus", false, new SystemPasteCommand("\\right|")));
    panel.add(buildButton(LatexIcons.icons.Icon319(), "Left double", false, new SystemPasteCommand("\\left\\|")));
    panel.add(buildButton(LatexIcons.icons.Icon336(), "Right double", false, new SystemPasteCommand("\\right\\|")));
    panel.add(buildButton(LatexIcons.icons.Icon353(), "Over brace", false, new SystemPasteCommand("\\overbrace{}^{}")));
    panel.add(buildButton(LatexIcons.icons.Icon370(), "Under brace", false, new SystemPasteCommand("\\underbrace{}_{}")));
  }
}
