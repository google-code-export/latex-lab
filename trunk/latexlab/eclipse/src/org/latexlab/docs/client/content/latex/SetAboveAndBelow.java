package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing above-and-below symbols.
 */
public class SetAboveAndBelow extends LatexCommandSet {

  protected static SetAboveAndBelow instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetAboveAndBelow get() {
	if (instance == null) {
	  instance = new SetAboveAndBelow();
	}
	return instance;
  }
	
  /**
   * The command set's title.
   */
  public static final String TITLE = "Above & Below";
	
  protected SetAboveAndBelow() {
	super(TITLE, new LatexCommand[]{
	  new LatexCommand(Icons.latexAboveAndBelowIcons.Overline(), "Overline", "\\overline{}"),
	  new LatexCommand(Icons.latexAboveAndBelowIcons.Underline(), "Underline", "\\underline{}"),
	  new LatexCommand(Icons.latexAboveAndBelowIcons.WideHat(), "Wide hat", "\\widehat{}"),
	  new LatexCommand(Icons.latexAboveAndBelowIcons.WideTilde(), "Wide tilde", "\\widetilde{}"),
	  new LatexCommand(Icons.latexAboveAndBelowIcons.Stack(), "Stack", "\\stackrel{}{}"),
	  new LatexCommand(Icons.latexAboveAndBelowIcons.OverBrace(), "Over brace", "\\overbrace{}^{}"),
	  new LatexCommand(Icons.latexAboveAndBelowIcons.UnderBrace(), "Under brace", "\\underbrace{}_{}")
	});
  }

}
