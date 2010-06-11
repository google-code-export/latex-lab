package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing above-and-below symbols.
 */
public class SetFontSizes extends LatexCommandSet {

  protected static SetFontSizes instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetFontSizes get() {
	if (instance == null) {
	  instance = new SetFontSizes();
	}
	return instance;
  }
	
  /**
   * The command set's title.
   */
  public static final String TITLE = "Above & Below";
	
  protected SetFontSizes() {
	super(TITLE, new LatexCommand[]{
	  new LatexCommand(Icons.latexFormattingIcons.TinySize(), "Tiny", "\\tiny"),
	  new LatexCommand(Icons.latexFormattingIcons.ScriptSize(), "Script Size", "\\scriptsize"),
	  new LatexCommand(Icons.latexFormattingIcons.FootnoteSize(), "Footnote Size", "\\footnotesize}"),
	  new LatexCommand(Icons.latexFormattingIcons.SmallSize(), "Small", "\\small"),
	  new LatexCommand(Icons.latexFormattingIcons.NormalSize(), "Normal", "\\normalsize"),
	  new LatexCommand(Icons.latexFormattingIcons.LargeSize(), "Large", "\\large"),
	  new LatexCommand(Icons.latexFormattingIcons.LargerSize(), "Larger", "\\Large"),
	  new LatexCommand(Icons.latexFormattingIcons.EvenLargerSize(), "Even Larger", "\\LARGE"),
	  new LatexCommand(Icons.latexFormattingIcons.HugeSize(), "Huge", "\\huge"),
	  new LatexCommand(Icons.latexFormattingIcons.HugerSize(), "Huger", "\\Huge"),
	});
  }

}
