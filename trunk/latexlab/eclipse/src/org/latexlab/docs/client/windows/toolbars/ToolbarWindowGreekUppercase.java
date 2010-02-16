package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.resources.icons.latex.LatexIcons;
import org.latexlab.docs.client.windows.ToolbarWindow;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class ToolbarWindowGreekUppercase extends ToolbarWindow{

  public ToolbarWindowGreekUppercase() {
	super("Greek Uppercase Letters");
    buildToolBar();
  }

  private void buildToolBar() {
	HorizontalPanel panel = (HorizontalPanel) contentWidget;
    panel.setStyleName("gdbe-Toolbar");
    panel.add(buildButton(LatexIcons.icons.Icon46(), "Capital alpha", false, new SystemPasteCommand("A")));
    panel.add(buildButton(LatexIcons.icons.Icon63(), "Capital beta", false, new SystemPasteCommand("B")));
    panel.add(buildButton(LatexIcons.icons.Icon80(), "Capital chi", false, new SystemPasteCommand("X")));
    panel.add(buildButton(LatexIcons.icons.Icon97(), "Capital delta", false, new SystemPasteCommand("\\Delta")));
    panel.add(buildButton(LatexIcons.icons.Icon114(), "Capital epsilon", false, new SystemPasteCommand("E")));
    panel.add(buildButton(LatexIcons.icons.Icon131(), "Capital phi", false, new SystemPasteCommand("\\Phi")));
    panel.add(buildButton(LatexIcons.icons.Icon148(), "Capital gamma", false, new SystemPasteCommand("\\Gamma")));
    panel.add(buildButton(LatexIcons.icons.Icon165(), "Capital eta", false, new SystemPasteCommand("H")));
    panel.add(buildButton(LatexIcons.icons.Icon182(), "Capital iota", false, new SystemPasteCommand("I")));
    panel.add(buildButton(LatexIcons.icons.Icon199(), "Capital kappa", false, new SystemPasteCommand("K")));
    panel.add(buildButton(LatexIcons.icons.Icon216(), "Capital lambda", false, new SystemPasteCommand("\\Lambda")));
    panel.add(buildButton(LatexIcons.icons.Icon233(), "Capital mu", false, new SystemPasteCommand("M")));
    panel.add(buildButton(LatexIcons.icons.Icon250(), "Capital nu", false, new SystemPasteCommand("N")));
    panel.add(buildButton(LatexIcons.icons.Icon267(), "Capital o", false, new SystemPasteCommand("O")));
    panel.add(buildButton(LatexIcons.icons.Icon284(), "Capital pi", false, new SystemPasteCommand("\\Pi")));
    panel.add(buildButton(LatexIcons.icons.Icon301(), "Capital theta", false, new SystemPasteCommand("\\Theta")));
    panel.add(buildButton(LatexIcons.icons.Icon318(), "Capital rho", false, new SystemPasteCommand("P")));
    panel.add(buildButton(LatexIcons.icons.Icon335(), "Capital sigma", false, new SystemPasteCommand("\\Sigma")));
    panel.add(buildButton(LatexIcons.icons.Icon352(), "Capital tau", false, new SystemPasteCommand("T")));
    panel.add(buildButton(LatexIcons.icons.Icon369(), "Capital upsilon", false, new SystemPasteCommand("\\Upsilon")));
    panel.add(buildButton(LatexIcons.icons.Icon386(), "Capital omega", false, new SystemPasteCommand("\\Omega")));
    panel.add(buildButton(LatexIcons.icons.Icon403(), "Capital xi", false, new SystemPasteCommand("\\Xi")));
    panel.add(buildButton(LatexIcons.icons.Icon13(), "Capital psi", false, new SystemPasteCommand("\\Psi")));
    panel.add(buildButton(LatexIcons.icons.Icon30(), "Capital zeta", false, new SystemPasteCommand("Z")));
  }
}
