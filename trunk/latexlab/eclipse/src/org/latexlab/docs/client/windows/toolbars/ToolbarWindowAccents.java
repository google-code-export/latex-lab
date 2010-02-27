package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.resources.icons.latex.LatexIcons;
import org.latexlab.docs.client.windows.ToolbarWindow;

import com.google.gwt.user.client.ui.FlowPanel;

public class ToolbarWindowAccents extends ToolbarWindow{

  public ToolbarWindowAccents() {
	super("Accents");
    buildToolBar();
  }

  private void buildToolBar() {
	FlowPanel panel = (FlowPanel) contentWidget;
    panel.setStyleName("gdbe-Toolbar");
    panel.add(buildButton(LatexIcons.icons.Icon277(), "Hat accent", false, new SystemPasteCommand("\\hat{}")));
    panel.add(buildButton(LatexIcons.icons.Icon294(), "Check accent", false, new SystemPasteCommand("\\check{}")));
    panel.add(buildButton(LatexIcons.icons.Icon311(), "Breve accent", false, new SystemPasteCommand("\\breve{}")));
    panel.add(buildButton(LatexIcons.icons.Icon328(), "Acute accent", false, new SystemPasteCommand("\\acute{}")));
    panel.add(buildButton(LatexIcons.icons.Icon345(), "Grace accent", false, new SystemPasteCommand("\\grave{}")));
    panel.add(buildButton(LatexIcons.icons.Icon362(), "Tilde accent", false, new SystemPasteCommand("\\tilde{}")));
    panel.add(buildButton(LatexIcons.icons.Icon379(), "Bar accent", false, new SystemPasteCommand("\\bar{}")));
    panel.add(buildButton(LatexIcons.icons.Icon396(), "Vector accent", false, new SystemPasteCommand("\\vec{}")));
    panel.add(buildButton(LatexIcons.icons.Icon6(), "Dot accent", false, new SystemPasteCommand("\\dot{}")));
    panel.add(buildButton(LatexIcons.icons.Icon23(), "Double-dot accent", false, new SystemPasteCommand("\\ddot{}")));
  }
}
