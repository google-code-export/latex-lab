package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.resources.icons.latex.LatexIcons;
import org.latexlab.docs.client.windows.ToolbarWindow;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class ToolbarWindowLogical extends ToolbarWindow{

  public ToolbarWindowLogical() {
	super("Logical");
    buildToolBar();
  }

  private void buildToolBar() {
	HorizontalPanel panel = (HorizontalPanel) contentWidget;
    panel.setStyleName("gdbe-Toolbar");
    panel.add(buildButton(LatexIcons.icons.Icon247(), " ", false, new SystemPasteCommand("\\ni")));
    panel.add(buildButton(LatexIcons.icons.Icon264(), "Exists", false, new SystemPasteCommand("\\exists")));
    panel.add(buildButton(LatexIcons.icons.Icon281(), "For all", false, new SystemPasteCommand("\\forall")));
    panel.add(buildButton(LatexIcons.icons.Icon298(), "Negation", false, new SystemPasteCommand("\\neg")));
    panel.add(buildButton(LatexIcons.icons.Icon315(), "And", false, new SystemPasteCommand("\\wedge")));
    panel.add(buildButton(LatexIcons.icons.Icon332(), "Or", false, new SystemPasteCommand("\\vee")));
  }
}
