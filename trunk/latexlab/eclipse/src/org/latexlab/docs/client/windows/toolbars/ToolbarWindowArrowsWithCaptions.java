package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.resources.icons.latex.LatexIcons;
import org.latexlab.docs.client.windows.ToolbarWindow;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class ToolbarWindowArrowsWithCaptions extends ToolbarWindow{

  public ToolbarWindowArrowsWithCaptions() {
	super("Arrows With Captions");
    buildToolBar();
  }

  private void buildToolBar() {
	HorizontalPanel panel = (HorizontalPanel) contentWidget;
    panel.setStyleName("gdbe-Toolbar");
    panel.add(buildButton(LatexIcons.icons.Icon405(), "Right arrow, top caption", false, new SystemPasteCommand("\\stackrel{}{\\rightarrow}")));
    panel.add(buildButton(LatexIcons.icons.Icon15(), "Left arrow, top caption", false, new SystemPasteCommand("\\stackrel{}{\\leftarrow}")));
    panel.add(buildButton(LatexIcons.icons.Icon32(), "Left/Right arrow, top caption", false, new SystemPasteCommand("\\stackrel{}{\\leftrightarrow}")));
    panel.add(buildButton(LatexIcons.icons.Icon49(), "Right arrow, bottom caption", false, new SystemPasteCommand("\\stackrel{\\rightarrow}{}")));
    panel.add(buildButton(LatexIcons.icons.Icon66(), "Left arrow, bottom caption", false, new SystemPasteCommand("\\stackrel{\\leftarrow}{}")));
    panel.add(buildButton(LatexIcons.icons.Icon83(), "Left/Right arrow, bottom caption", false, new SystemPasteCommand("\\stackrel{\\leftrightarrow}{}")));
  }
}
