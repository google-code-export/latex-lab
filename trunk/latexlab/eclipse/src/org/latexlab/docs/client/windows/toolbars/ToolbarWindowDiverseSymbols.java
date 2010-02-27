package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.resources.icons.latex.LatexIcons;
import org.latexlab.docs.client.windows.ToolbarWindow;

import com.google.gwt.user.client.ui.FlowPanel;

public class ToolbarWindowDiverseSymbols extends ToolbarWindow{

  public ToolbarWindowDiverseSymbols() {
	super("Diverse Symbols");
    buildToolBar();
  }

  private void buildToolBar() {
	FlowPanel panel = (FlowPanel) contentWidget;
    panel.setStyleName("gdbe-Toolbar");
    panel.add(buildButton(LatexIcons.icons.Icon112(), "Partial", false, new SystemPasteCommand("\\partial")));
    panel.add(buildButton(LatexIcons.icons.Icon129(), "Nabla", false, new SystemPasteCommand("\\nabla")));
    panel.add(buildButton(LatexIcons.icons.Icon146(), "Infinity", false, new SystemPasteCommand("\\infty")));
    panel.add(buildButton(LatexIcons.icons.Icon163(), " ", false, new SystemPasteCommand("\\Im")));
    panel.add(buildButton(LatexIcons.icons.Icon180(), " ", false, new SystemPasteCommand("\\Re")));
    panel.add(buildButton(LatexIcons.icons.Icon197(), " ", false, new SystemPasteCommand("\\aleph")));
    panel.add(buildButton(LatexIcons.icons.Icon214(), "Angle", false, new SystemPasteCommand("\\angle")));
    panel.add(buildButton(LatexIcons.icons.Icon231(), "Bot", false, new SystemPasteCommand("\\bot")));
    panel.add(buildButton(LatexIcons.icons.Icon384(), "Diamond", false, new SystemPasteCommand("\\diamond")));
    panel.add(buildButton(LatexIcons.icons.Icon401(), "Ell", false, new SystemPasteCommand("\\ell")));
    panel.add(buildButton(LatexIcons.icons.Icon11(), "Wp", false, new SystemPasteCommand("\\wp")));
    panel.add(buildButton(LatexIcons.icons.Icon28(), "Hbar", false, new SystemPasteCommand("\\hbar")));
    panel.add(buildButton(LatexIcons.icons.Icon45(), "Integral", false, new SystemPasteCommand("\\int")));
    panel.add(buildButton(LatexIcons.icons.Icon62(), "Sum", false, new SystemPasteCommand("\\sum")));
    panel.add(buildButton(LatexIcons.icons.Icon79(), "Product", false, new SystemPasteCommand("\\prod")));
    panel.add(buildButton(LatexIcons.icons.Icon96(), "Co-Product", false, new SystemPasteCommand("\\coprod")));
  }
}
