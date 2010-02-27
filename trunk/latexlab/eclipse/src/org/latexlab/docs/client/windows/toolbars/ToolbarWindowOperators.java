package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.resources.icons.latex.LatexIcons;
import org.latexlab.docs.client.windows.ToolbarWindow;

import com.google.gwt.user.client.ui.FlowPanel;

public class ToolbarWindowOperators extends ToolbarWindow{

  public ToolbarWindowOperators() {
	super("Operators");
    buildToolBar();
  }

  private void buildToolBar() {
	FlowPanel panel = (FlowPanel) contentWidget;
    panel.setStyleName("gdbe-Toolbar");
    panel.add(buildButton(LatexIcons.icons.Icon201(), "Sum", false, new SystemPasteCommand("\\sum")));
    panel.add(buildButton(LatexIcons.icons.Icon218(), "Integral", false, new SystemPasteCommand("\\int")));
    panel.add(buildButton(LatexIcons.icons.Icon235(), "O Integral", false, new SystemPasteCommand("\\oint")));
    panel.add(buildButton(LatexIcons.icons.Icon252(), "Product", false, new SystemPasteCommand("\\prod")));
    panel.add(buildButton(LatexIcons.icons.Icon269(), "Co-product", false, new SystemPasteCommand("\\coprod")));
  }
}
