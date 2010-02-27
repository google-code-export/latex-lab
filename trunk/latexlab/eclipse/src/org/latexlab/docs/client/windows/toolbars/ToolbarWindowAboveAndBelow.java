package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.resources.icons.latex.LatexIcons;
import org.latexlab.docs.client.windows.ToolbarWindow;

import com.google.gwt.user.client.ui.FlowPanel;

public class ToolbarWindowAboveAndBelow extends ToolbarWindow{

  public ToolbarWindowAboveAndBelow() {
	super("Above & Below");
    buildToolBar();
  }

  private void buildToolBar() {
	FlowPanel panel = (FlowPanel) contentWidget;
    panel.setStyleName("gdbe-Toolbar");
    panel.add(buildButton(LatexIcons.icons.Icon286(), "Overline", false, new SystemPasteCommand("\\overline{}")));
    panel.add(buildButton(LatexIcons.icons.Icon303(), "Underline", false, new SystemPasteCommand("\\underline{}")));
    panel.add(buildButton(LatexIcons.icons.Icon320(), "Wide hat", false, new SystemPasteCommand("\\widehat{}")));
    panel.add(buildButton(LatexIcons.icons.Icon337(), "Wide tilde", false, new SystemPasteCommand("\\widetilde{}")));
    panel.add(buildButton(LatexIcons.icons.Icon354(), "Stack", false, new SystemPasteCommand("\\stackrel{}{}")));
    panel.add(buildButton(LatexIcons.icons.Icon371(), "Over brace", false, new SystemPasteCommand("\\overbrace{}^{}")));
    panel.add(buildButton(LatexIcons.icons.Icon388(), "Under brace", false, new SystemPasteCommand("\\underbrace{}_{}")));
  }
}
