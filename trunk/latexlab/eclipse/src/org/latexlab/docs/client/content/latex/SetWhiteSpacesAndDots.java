package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing whitespace and dot symbols.
 */
public class SetWhiteSpacesAndDots extends LatexCommandSet {

  protected static SetWhiteSpacesAndDots instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetWhiteSpacesAndDots get() {
	if (instance == null) {
	  instance = new SetWhiteSpacesAndDots();
	}
	return instance;
  }

  /**
   * The command set's title.
   */
  public static final String TITLE = "White Spaces & Dots";

  protected SetWhiteSpacesAndDots() {
	super(TITLE, new LatexCommand[]{
      new LatexCommand(Icons.latexWhiteSpacesAndDotsIcons.SmallestSpace(), "Smallest space", "\\,"),
      new LatexCommand(Icons.latexWhiteSpacesAndDotsIcons.VerySmallSpace(), "Very small space", "\\!"),
      new LatexCommand(Icons.latexWhiteSpacesAndDotsIcons.SmallSpace(), "Small space", "\\:"),
      new LatexCommand(Icons.latexWhiteSpacesAndDotsIcons.LittleSmallerSpace(), "Little smaller space", "\\;"),
      new LatexCommand(Icons.latexWhiteSpacesAndDotsIcons.NormalSpace(), "Normal space", "\\ "),
      new LatexCommand(Icons.latexWhiteSpacesAndDotsIcons.LowerDots(), "Lower dots", "\\ldots"),
      new LatexCommand(Icons.latexWhiteSpacesAndDotsIcons.CenterDots(), "Center dots", "\\cdots"),
      new LatexCommand(Icons.latexWhiteSpacesAndDotsIcons.VerticalDots(), "Vertical dots", "\\vdots"),
      new LatexCommand(Icons.latexWhiteSpacesAndDotsIcons.DiagonalDots(), "Diagonal dots", "\\ddots"),
    });
  }

}
