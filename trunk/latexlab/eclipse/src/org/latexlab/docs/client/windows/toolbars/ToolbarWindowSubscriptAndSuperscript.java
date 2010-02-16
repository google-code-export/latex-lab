package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.resources.icons.latex.LatexIcons;
import org.latexlab.docs.client.windows.ToolbarWindow;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class ToolbarWindowSubscriptAndSuperscript extends ToolbarWindow{

  public ToolbarWindowSubscriptAndSuperscript() {
	super("Subscript & Superscript");
    buildToolBar();
  }

  private void buildToolBar() {
	HorizontalPanel panel = (HorizontalPanel) contentWidget;
    panel.setStyleName("gdbe-Toolbar");
    panel.add(buildButton(LatexIcons.icons.Icon48(), "Right superscript", false, new SystemPasteCommand("^{}")));
    panel.add(buildButton(LatexIcons.icons.Icon65(), "Right subscript", false, new SystemPasteCommand("_{}")));
    panel.add(buildButton(LatexIcons.icons.Icon82(), "Right sub & super scripts", false, new SystemPasteCommand("^{}_{}")));
    panel.add(buildButton(LatexIcons.icons.Icon99(), "Left superscript", false, new SystemPasteCommand("^{}")));
    panel.add(buildButton(LatexIcons.icons.Icon116(), "Left subscript", false, new SystemPasteCommand("_{}")));
    panel.add(buildButton(LatexIcons.icons.Icon133(), "Left sub & super scripts", false, new SystemPasteCommand("^{}_{}")));
    panel.add(buildButton(LatexIcons.icons.Icon150(), "Topscript", false, new SystemPasteCommand("^{}")));
    panel.add(buildButton(LatexIcons.icons.Icon167(), "Lowscript", false, new SystemPasteCommand("_{}")));
    panel.add(buildButton(LatexIcons.icons.Icon184(), "Top & low scripts", false, new SystemPasteCommand("^{}_{}")));
  }
}
