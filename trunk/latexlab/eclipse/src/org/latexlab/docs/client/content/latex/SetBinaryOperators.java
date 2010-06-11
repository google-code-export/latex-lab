package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing arrows with binary operators.
 */
public class SetBinaryOperators extends LatexCommandSet {

  protected static SetBinaryOperators instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetBinaryOperators get() {
	if (instance == null) {
	  instance = new SetBinaryOperators();
	}
	return instance;
  }

  /**
   * The command set's title.
   */
  public static final String TITLE = "Binary Operators";

  protected SetBinaryOperators() {
	super(TITLE, new LatexCommand[]{
      new LatexCommand(Icons.latexBinaryOperatorsIcons.PlusMinus(), "Plus/Minus", "\\pm"),
      new LatexCommand(Icons.latexBinaryOperatorsIcons.MinusPlus(), "Minus/Plus", "\\mp"),
      new LatexCommand(Icons.latexBinaryOperatorsIcons.Multiply(), "Multiply", "\\times"),
      new LatexCommand(Icons.latexBinaryOperatorsIcons.Divide(), "Divide", "\\div"),
      new LatexCommand(Icons.latexBinaryOperatorsIcons.Asterisk(), "Asterisk", "\\ast"),
      new LatexCommand(Icons.latexBinaryOperatorsIcons.CenterDot(), "Center Dot", "\\cdot"),
      new LatexCommand(Icons.latexBinaryOperatorsIcons.Circle(), "Circle", "\\circ"),
      new LatexCommand(Icons.latexBinaryOperatorsIcons.Bullet(), "Bullet", "\\bullet"),
      new LatexCommand(Icons.latexBinaryOperatorsIcons.RoundMultiply(), "Round multiply", "\\otimes"),
      new LatexCommand(Icons.latexBinaryOperatorsIcons.RoundPlus(), "Round plus", "\\oplus"),
      new LatexCommand(Icons.latexBinaryOperatorsIcons.RoundMinus(), "Round minus", "\\ominus"),
      new LatexCommand(Icons.latexBinaryOperatorsIcons.RoundDot(), "Round dot", "\\odot"),
      new LatexCommand(Icons.latexBinaryOperatorsIcons.RoundSlash(), "Round slash", "\\oslash")
    });
  }

}
