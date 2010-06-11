package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing boundary symbols.
 */
public class SetBoundaries extends LatexCommandSet {

  protected static SetBoundaries instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetBoundaries get() {
	if (instance == null) {
	  instance = new SetBoundaries();
	}
	return instance;
  }

  /**
   * The command set's title.
   */
  public static final String TITLE = "Boundaries";

  protected SetBoundaries() {
	super(TITLE, new LatexCommand[]{
      new LatexCommand(Icons.latexBoundariesIcons.InvisibleLeftBoundary(), "Invisible left boundary", "\\left."),
      new LatexCommand(Icons.latexBoundariesIcons.InvisibleRightBoundary(), "Invisible right boundary", "\\right."),
      new LatexCommand(Icons.latexBoundariesIcons.LeftParentheses(), "Left parentheses", "\\left("),
      new LatexCommand(Icons.latexBoundariesIcons.RightParantheses(), "Right parantheses", "\\right)"),
      new LatexCommand(Icons.latexBoundariesIcons.LeftSquareBracket(), "Left square bracket", "\\left["),
      new LatexCommand(Icons.latexBoundariesIcons.RightSquareBracket(), "Right square bracket", "\\right]"),
      new LatexCommand(Icons.latexBoundariesIcons.LeftCurlyBracket(), "Left curly bracket", "\\left\\{"),
      new LatexCommand(Icons.latexBoundariesIcons.RightCurlyBracket(), "Right curly bracket", "\\right\\}"),
      new LatexCommand(Icons.latexBoundariesIcons.LeftFloor(), "Left floor", "\\left\\lfloor"),
      new LatexCommand(Icons.latexBoundariesIcons.RightFloor(), "Right floor", "\\right\\rfloor"),
      new LatexCommand(Icons.latexBoundariesIcons.LeftCeil(), "Left ceil", "\\left\\lceil"),
      new LatexCommand(Icons.latexBoundariesIcons.RightCeil(), "Right ceil", "\\right\\rceil"),
      new LatexCommand(Icons.latexBoundariesIcons.LeftAngleBracket(), "Left angle bracket", "\\left\\langle"),
      new LatexCommand(Icons.latexBoundariesIcons.RightAngleBracket(), "Right angle bracket", "\\right\\rangle"),
      new LatexCommand(Icons.latexBoundariesIcons.LeftModulus(), "Left modulus", "\\left|"),
      new LatexCommand(Icons.latexBoundariesIcons.RightModulus(), "Right modulus", "\\right|"),
      new LatexCommand(Icons.latexBoundariesIcons.LeftDouble(), "Left double", "\\left\\|"),
      new LatexCommand(Icons.latexBoundariesIcons.RightDouble(), "Right double", "\\right\\|"),
      new LatexCommand(Icons.latexBoundariesIcons.OverBrace(), "Over brace", "\\overbrace{}^{}"),
      new LatexCommand(Icons.latexBoundariesIcons.UnderBrace(), "Under brace", "\\underbrace{}_{}")
    });
  }

}
