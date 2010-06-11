package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing formatting commands.
 */
public class SetFormatting extends LatexCommandSet {

  protected static SetFormatting instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetFormatting get() {
	if (instance == null) {
	  instance = new SetFormatting();
	}
	return instance;
  }

  /**
   * The command set's title.
   */
  public static final String TITLE = "Formatting";

  protected SetFormatting() {
	super(TITLE, new LatexCommand[]{
      new LatexCommand(Icons.latexFormattingIcons.Emphasize(), "Emphasize", "\\emph{ <text here> }"),
      new LatexCommand(Icons.latexFormattingIcons.Bold(), "Bold", "\\textbf{ <text here> }"),
      new LatexCommand(Icons.latexFormattingIcons.Italic(), "Italic", "\\textit{ <text here> }"),
      new LatexCommand(Icons.latexFormattingIcons.Slanted(), "Slanted", "\\textsl{ <text here> }"),
      new LatexCommand(Icons.latexFormattingIcons.Typewriter(), "Typewriter", "\\texttt{ <text here> }"),
      new LatexCommand(Icons.latexFormattingIcons.SmallCaps(), "Small caps", "\\textsc{ <text here> }"),
      new LatexCommand(Icons.latexFormattingIcons.Standard(), "Standard", "\\normalfont"),
      new LatexCommand(Icons.latexFormattingIcons.MediumSeries(), "Medium series", "\\mdseries"),
      new LatexCommand(Icons.latexFormattingIcons.Bold(), "Bold series", "\\bfseries"),
      new LatexCommand(Icons.latexFormattingIcons.VerticalForm(), "Vertical form", "\\upshape"),
      new LatexCommand(Icons.latexFormattingIcons.ItalicForm(), "Italic form", "\\itshape"),
      new LatexCommand(Icons.latexFormattingIcons.SlantedForm(), "Slanted form", "\\slshape"),
      new LatexCommand(Icons.latexFormattingIcons.SmallCaps(), "Small caps", "\\scshape"),
      new LatexCommand(Icons.latexFormattingIcons.RomanFamily(), "Roman family", "\\rmfamily"),
      new LatexCommand(Icons.latexFormattingIcons.SansSerifFamily(), "Sans-Serif family", "\\sffamily"),
      new LatexCommand(Icons.latexFormattingIcons.TypewriterFamily(), "Typewriter family", "\\ttfamily"),
      new LatexCommand(Icons.latexFormattingIcons.TinySize(), "Tiny size", "\\tiny"),
      new LatexCommand(Icons.latexFormattingIcons.ScriptSize(), "Script size", "\\scriptsize"),
      new LatexCommand(Icons.latexFormattingIcons.FootnoteSize(), "Footnote size", "\\footnotesize"),
      new LatexCommand(Icons.latexFormattingIcons.SmallSize(), "Small size", "\\small"),
      new LatexCommand(Icons.latexFormattingIcons.NormalSize(), "Normal size", "\\normalsize"),
      new LatexCommand(Icons.latexFormattingIcons.LargeSize(), "Large size", "\\large"),
      new LatexCommand(Icons.latexFormattingIcons.LargerSize(), "Larger size", "\\Large"),
      new LatexCommand(Icons.latexFormattingIcons.EvenLargerSize(), "Even larger size", "\\LARGE"),
      new LatexCommand(Icons.latexFormattingIcons.HugerSize(), "Huge size", "\\huge"),
      new LatexCommand(Icons.latexFormattingIcons.HugerSize(), "Huger size", "\\Huge"),
      new LatexCommand(Icons.latexFormattingIcons.AlignLeft(), "Align left", "\\begin{flushleft} <text here> \\end{flushleft}"),
      new LatexCommand(Icons.latexFormattingIcons.AlignCenter(), "Align center", "\\begin{center} <text here> \\end{center}"),
      new LatexCommand(Icons.latexFormattingIcons.AlignRight(), "Align right", "\\begin{flushright} <text here> \\end{flushright}")
    });
  }

}
