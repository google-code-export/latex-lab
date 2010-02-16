package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.resources.icons.latex.LatexIcons;
import org.latexlab.docs.client.windows.ToolbarWindow;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class ToolbarWindowComparison extends ToolbarWindow{

  public ToolbarWindowComparison() {
	super("Comparison");
    buildToolBar();
  }

  private void buildToolBar() {
	HorizontalPanel panel = (HorizontalPanel) contentWidget;
    panel.setStyleName("gdbe-Toolbar");
    panel.add(buildButton(LatexIcons.icons.Icon90(), "Lesser than or equal", false, new SystemPasteCommand("\\leq")));
    panel.add(buildButton(LatexIcons.icons.Icon107(), "Greater than or equal", false, new SystemPasteCommand("\\geq")));
    panel.add(buildButton(LatexIcons.icons.Icon124(), "Preceding", false, new SystemPasteCommand("\\prec")));
    panel.add(buildButton(LatexIcons.icons.Icon141(), "Succeding", false, new SystemPasteCommand("\\succ")));
    panel.add(buildButton(LatexIcons.icons.Icon158(), "Triangle left", false, new SystemPasteCommand("\\triangleleft")));
    panel.add(buildButton(LatexIcons.icons.Icon175(), "Triangle right", false, new SystemPasteCommand("\\triangleright")));
    panel.add(buildButton(LatexIcons.icons.Icon192(), "Not equal", false, new SystemPasteCommand("\\neq")));
    panel.add(buildButton(LatexIcons.icons.Icon209(), "Equivalent", false, new SystemPasteCommand("\\equiv")));
    panel.add(buildButton(LatexIcons.icons.Icon226(), "Approximately", false, new SystemPasteCommand("\\approx")));
    panel.add(buildButton(LatexIcons.icons.Icon243(), "Congruent", false, new SystemPasteCommand("\\cong")));
    panel.add(buildButton(LatexIcons.icons.Icon260(), "Prop to", false, new SystemPasteCommand("\\propto")));
  }
}
