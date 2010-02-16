package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.resources.icons.latex.LatexIcons;
import org.latexlab.docs.client.windows.ToolbarWindow;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class ToolbarWindowWhiteSpacesAndDots extends ToolbarWindow{

  public ToolbarWindowWhiteSpacesAndDots() {
	super("White Spaces & Dots");
    buildToolBar();
  }

  private void buildToolBar() {
	HorizontalPanel panel = (HorizontalPanel) contentWidget;
    panel.setStyleName("gdbe-Toolbar");
    panel.add(buildButton(LatexIcons.icons.Icon344(), "Smallest space", false, new SystemPasteCommand("\\,")));
    panel.add(buildButton(LatexIcons.icons.Icon361(), "Very small space", false, new SystemPasteCommand("\\!")));
    panel.add(buildButton(LatexIcons.icons.Icon378(), "Small space", false, new SystemPasteCommand("\\:")));
    panel.add(buildButton(LatexIcons.icons.Icon89(), "Little smaller space", false, new SystemPasteCommand("\\;")));
    panel.add(buildButton(LatexIcons.icons.Icon5(), "Normal space", false, new SystemPasteCommand("\\ ")));
    panel.add(buildButton(LatexIcons.icons.Icon22(), "Lower dots", false, new SystemPasteCommand("\\ldots")));
    panel.add(buildButton(LatexIcons.icons.Icon39(), "Center dots", false, new SystemPasteCommand("\\cdots")));
    panel.add(buildButton(LatexIcons.icons.Icon56(), "Vertical dots", false, new SystemPasteCommand("\\vdots")));
    panel.add(buildButton(LatexIcons.icons.Icon73(), "Diagonal dots", false, new SystemPasteCommand("\\ddots")));
  }
}
