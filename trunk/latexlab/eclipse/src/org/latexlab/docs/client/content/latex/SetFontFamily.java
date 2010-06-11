package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing font-family commands.
 */
public class SetFontFamily extends LatexCommandSet {

  protected static SetFontFamily instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetFontFamily get() {
	if (instance == null) {
	  instance = new SetFontFamily();
	}
	return instance;
  }
	
  /**
   * The command set's title.
   */
  public static final String TITLE = "Font Family";
	
  protected SetFontFamily() {
	super(TITLE, new LatexCommand[]{
	  new LatexCommand(Icons.latexFormattingIcons.RomanFamily(), "Roman Family", "\\rmfamily"),
	  new LatexCommand(Icons.latexFormattingIcons.SansSerifFamily(), "Sans Serif Family", "\\sffamily"),
	  new LatexCommand(Icons.latexFormattingIcons.TypewriterFamily(), "Typewriter Family", "\\ttfamily")
	});
  }

}
