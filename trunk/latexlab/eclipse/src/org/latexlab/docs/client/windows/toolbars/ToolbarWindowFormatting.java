package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.resources.icons.latex.LatexIcons;
import org.latexlab.docs.client.windows.ToolbarWindow;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class ToolbarWindowFormatting extends ToolbarWindow{

  public ToolbarWindowFormatting() {
	super("Formatting");
    buildToolBar();
  }

  private void buildToolBar() {
	HorizontalPanel panel = (HorizontalPanel) contentWidget;
    panel.setStyleName("gdbe-Toolbar");
    panel.add(buildButton(LatexIcons.icons.Icon135(), "Emphasize", false, new SystemPasteCommand("\\emph{ <text here> }")));
    panel.add(buildButton(LatexIcons.icons.Icon152(), "Bold", false, new SystemPasteCommand("\\textbf{ <text here> }")));
    panel.add(buildButton(LatexIcons.icons.Icon169(), "Italic", false, new SystemPasteCommand("\\textit{ <text here> }")));
    panel.add(buildButton(LatexIcons.icons.Icon186(), "Slanted", false, new SystemPasteCommand("\\textsl{ <text here> }")));
    panel.add(buildButton(LatexIcons.icons.Icon203(), "Typewriter", false, new SystemPasteCommand("\\texttt{ <text here> }")));
    panel.add(buildButton(LatexIcons.icons.Icon220(), "Small caps", false, new SystemPasteCommand("\\textsc{ <text here> }")));
    panel.add(buildButton(LatexIcons.icons.Icon237(), "Standard", false, new SystemPasteCommand("\\normalfont")));
    panel.add(buildButton(LatexIcons.icons.Icon254(), "Medium series", false, new SystemPasteCommand("\\mdseries")));
    panel.add(buildButton(LatexIcons.icons.Icon271(), "Bold series", false, new SystemPasteCommand("\\bfseries")));
    panel.add(buildButton(LatexIcons.icons.Icon288(), "Vertical form", false, new SystemPasteCommand("\\upshape")));
    panel.add(buildButton(LatexIcons.icons.Icon305(), "Italic form", false, new SystemPasteCommand("\\itshape")));
    panel.add(buildButton(LatexIcons.icons.Icon322(), "Slanted form", false, new SystemPasteCommand("\\slshape")));
    panel.add(buildButton(LatexIcons.icons.Icon339(), "Small caps", false, new SystemPasteCommand("\\scshape")));
    panel.add(buildButton(LatexIcons.icons.Icon356(), "Roman family", false, new SystemPasteCommand("\\rmfamily")));
    panel.add(buildButton(LatexIcons.icons.Icon373(), "Sans-Serif family", false, new SystemPasteCommand("\\sffamily")));
    panel.add(buildButton(LatexIcons.icons.Icon390(), "Typewriter family", false, new SystemPasteCommand("\\ttfamily")));
    panel.add(buildButton(LatexIcons.icons.Icon407(), "Tiny size", false, new SystemPasteCommand("\\tiny")));
    panel.add(buildButton(LatexIcons.icons.Icon17(), "Script size", false, new SystemPasteCommand("\\scriptsize")));
    panel.add(buildButton(LatexIcons.icons.Icon34(), "Footnote size", false, new SystemPasteCommand("\\footnotesize")));
    panel.add(buildButton(LatexIcons.icons.Icon51(), "Small size", false, new SystemPasteCommand("\\small")));
    panel.add(buildButton(LatexIcons.icons.Icon68(), "Normal size", false, new SystemPasteCommand("\\normalsize")));
    panel.add(buildButton(LatexIcons.icons.Icon85(), "Large size", false, new SystemPasteCommand("\\large")));
    panel.add(buildButton(LatexIcons.icons.Icon102(), "Larger size", false, new SystemPasteCommand("\\Large")));
    panel.add(buildButton(LatexIcons.icons.Icon119(), "Even larger size", false, new SystemPasteCommand("\\LARGE")));
    panel.add(buildButton(LatexIcons.icons.Icon136(), "Huge size", false, new SystemPasteCommand("\\huge")));
    panel.add(buildButton(LatexIcons.icons.Icon153(), "Huger size", false, new SystemPasteCommand("\\Huge")));
    panel.add(buildButton(LatexIcons.icons.Icon153()/*Icon469*/, "Align left", false, new SystemPasteCommand("\\begin{flushleft}\\end{flushleft}")));
    panel.add(buildButton(LatexIcons.icons.Icon153()/*Icon476*/, "Align center", false, new SystemPasteCommand("\\begin{center}\\end{center}")));
    panel.add(buildButton(LatexIcons.icons.Icon153()/*Icon483*/, "Align right", false, new SystemPasteCommand("\\begin{flushright}\\end{flushright}")));
  }
}
