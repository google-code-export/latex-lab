package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.resources.icons.latex.LatexIcons;
import org.latexlab.docs.client.windows.ToolbarWindow;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class ToolbarWindowSets extends ToolbarWindow{

  public ToolbarWindowSets() {
	super("Sets");
    buildToolBar();
  }

  private void buildToolBar() {
	HorizontalPanel panel = (HorizontalPanel) contentWidget;
    panel.setStyleName("gdbe-Toolbar");
    panel.add(buildButton(LatexIcons.icons.Icon349(), "Element of", false, new SystemPasteCommand("\\in")));
    panel.add(buildButton(LatexIcons.icons.Icon366(), "Not element of", false, new SystemPasteCommand("\\notin")));
    panel.add(buildButton(LatexIcons.icons.Icon383(), "Union", false, new SystemPasteCommand("\\cup")));
    panel.add(buildButton(LatexIcons.icons.Icon400(), "Intersection", false, new SystemPasteCommand("\\cap")));
    panel.add(buildButton(LatexIcons.icons.Icon10(), "Union Large", false, new SystemPasteCommand("\\bigcup")));
    panel.add(buildButton(LatexIcons.icons.Icon27(), "Intersection Large", false, new SystemPasteCommand("\\bigcap")));
    panel.add(buildButton(LatexIcons.icons.Icon44(), "Contained in", false, new SystemPasteCommand("\\subset")));
    panel.add(buildButton(LatexIcons.icons.Icon61(), "Contains", false, new SystemPasteCommand("\\supset")));
    panel.add(buildButton(LatexIcons.icons.Icon78(), "Contained in or equal to", false, new SystemPasteCommand("\\subseteq")));
    panel.add(buildButton(LatexIcons.icons.Icon95(), "Contains or equal to", false, new SystemPasteCommand("\\supseteq")));
  }
}
