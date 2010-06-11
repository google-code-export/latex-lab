package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing math commands.
 */
public class SetConstructs extends LatexCommandSet {

  protected static SetConstructs instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetConstructs get() {
	if (instance == null) {
	  instance = new SetConstructs();
	}
	return instance;
  }

  /**
   * The command set's title.
   */
  public static final String TITLE = "Constructs";

  protected SetConstructs() {
	super(TITLE, new LatexCommand[]{
      new LatexCommand(Icons.latexMathIcons.Fraction(), "Fraction", "\\frac{}{}"),
      new LatexCommand(Icons.latexMathIcons.Division(), "Division", "/"),
      new LatexCommand(Icons.latexMathIcons.SquareRoot(), "Square root", "\\sqrt{}"),
      new LatexCommand(Icons.latexMathIcons.NthRoot(), "Nth root", "\\sqrt[]{}")
    });
  }

}
