package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.resources.icons.latex.LatexIcons;
import org.latexlab.docs.client.windows.ToolbarWindow;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class ToolbarWindowGreekLowercase extends ToolbarWindow{

  public ToolbarWindowGreekLowercase() {
	super("Greek Lowercase Letters");
    buildToolBar();
  }

  private void buildToolBar() {
	HorizontalPanel panel = (HorizontalPanel) contentWidget;
    panel.setStyleName("gdbe-Toolbar");
    panel.add(buildButton(LatexIcons.icons.Icon248(), "Small alpha", false, new SystemPasteCommand("\\alpha")));
    panel.add(buildButton(LatexIcons.icons.Icon265(), "Small beta", false, new SystemPasteCommand("\\beta")));
    panel.add(buildButton(LatexIcons.icons.Icon282(), "Small chi", false, new SystemPasteCommand("\\chi")));
    panel.add(buildButton(LatexIcons.icons.Icon299(), "Small delta", false, new SystemPasteCommand("\\delta")));
    panel.add(buildButton(LatexIcons.icons.Icon316(), "Small epsilon", false, new SystemPasteCommand("\\epsilon")));
    panel.add(buildButton(LatexIcons.icons.Icon333(), "Small phi", false, new SystemPasteCommand("\\phi")));
    panel.add(buildButton(LatexIcons.icons.Icon350(), "Small variiertes phi", false, new SystemPasteCommand("\\varphi")));
    panel.add(buildButton(LatexIcons.icons.Icon367(), "Small gamma", false, new SystemPasteCommand("\\gamma")));
    panel.add(buildButton(LatexIcons.icons.Icon113(), "Small eta", false, new SystemPasteCommand("\\eta")));
    panel.add(buildButton(LatexIcons.icons.Icon130(), "Small iota", false, new SystemPasteCommand("\\iota")));
    panel.add(buildButton(LatexIcons.icons.Icon147(), "Small kappa", false, new SystemPasteCommand("\\kappa")));
    panel.add(buildButton(LatexIcons.icons.Icon164(), "Small lambda", false, new SystemPasteCommand("\\lambda")));
    panel.add(buildButton(LatexIcons.icons.Icon181(), "Small mu", false, new SystemPasteCommand("\\mu")));
    panel.add(buildButton(LatexIcons.icons.Icon198(), "Small nu", false, new SystemPasteCommand("\\nu")));
    panel.add(buildButton(LatexIcons.icons.Icon215(), "Small o", false, new SystemPasteCommand("\\o")));
    panel.add(buildButton(LatexIcons.icons.Icon232(), "Small pi", false, new SystemPasteCommand("\\pi")));
    panel.add(buildButton(LatexIcons.icons.Icon249(), "Small variiertes pi", false, new SystemPasteCommand("\\varpi")));
    panel.add(buildButton(LatexIcons.icons.Icon266(), "Small theta", false, new SystemPasteCommand("\\theta")));
    panel.add(buildButton(LatexIcons.icons.Icon283(), "Small variiertes theta", false, new SystemPasteCommand("\\vartheta")));
    panel.add(buildButton(LatexIcons.icons.Icon300(), "Small rho", false, new SystemPasteCommand("\\rho")));
    panel.add(buildButton(LatexIcons.icons.Icon317(), "Small sigma", false, new SystemPasteCommand("\\sigma")));
    panel.add(buildButton(LatexIcons.icons.Icon334(), "Small variiertes sigma", false, new SystemPasteCommand("\\varsigma")));
    panel.add(buildButton(LatexIcons.icons.Icon351(), "Small tau", false, new SystemPasteCommand("\\tau")));
    panel.add(buildButton(LatexIcons.icons.Icon368(), "Small upsilon", false, new SystemPasteCommand("\\upsilon")));
    panel.add(buildButton(LatexIcons.icons.Icon385(), "Small omega", false, new SystemPasteCommand("\\omega")));
    panel.add(buildButton(LatexIcons.icons.Icon402(), "Small xi", false, new SystemPasteCommand("\\xi")));
    panel.add(buildButton(LatexIcons.icons.Icon12(), "Small psi", false, new SystemPasteCommand("\\psi")));
    panel.add(buildButton(LatexIcons.icons.Icon29(), "Small zeta", false, new SystemPasteCommand("\\zeta")));
  }
}
