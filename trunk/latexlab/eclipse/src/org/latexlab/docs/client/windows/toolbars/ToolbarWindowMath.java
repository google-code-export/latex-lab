package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.resources.icons.latex.LatexIcons;
import org.latexlab.docs.client.windows.ToolbarWindow;

import com.google.gwt.user.client.ui.FlowPanel;

public class ToolbarWindowMath extends ToolbarWindow{

  public ToolbarWindowMath() {
	super("Math");
    buildToolBar();
  }

  private void buildToolBar() {
	FlowPanel panel = (FlowPanel) contentWidget;
    panel.setStyleName("gdbe-Toolbar");
    panel.add(buildButton(LatexIcons.icons.Icon387(), "Fraction", false, new SystemPasteCommand("\\frac{}{}")));
    panel.add(buildButton(LatexIcons.icons.Icon404(), "Division", false, new SystemPasteCommand("/")));
    panel.add(buildButton(LatexIcons.icons.Icon14(), "Square root", false, new SystemPasteCommand("\\sqrt{}")));
    panel.add(buildButton(LatexIcons.icons.Icon31(), "Nth root", false, new SystemPasteCommand("\\sqrt[]{}")));
  }
}
