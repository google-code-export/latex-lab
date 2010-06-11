package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing operator symbols.
 */
public class SetBigOperators extends LatexCommandSet {

  protected static SetBigOperators instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetBigOperators get() {
	if (instance == null) {
	  instance = new SetBigOperators();
	}
	return instance;
  }

  /**
   * The command set's title.
   */
  public static final String TITLE = "Big Operators";

  protected SetBigOperators() {
	super(TITLE, new LatexCommand[]{
      new LatexCommand(Icons.latexOperatorsIcons.Sum(), "Sum", "\\sum"),
      new LatexCommand(Icons.latexOperatorsIcons.Integral(), "Integral", "\\int"),
      new LatexCommand(Icons.latexOperatorsIcons.OIntegral(), "O Integral", "\\oint"),
      new LatexCommand(Icons.latexOperatorsIcons.Product(), "Product", "\\prod"),
      new LatexCommand(Icons.latexOperatorsIcons.CoProduct(), "Co-product", "\\coprod")
    });
  }

}
