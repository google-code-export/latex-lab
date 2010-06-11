package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing subscript and superscript symbols.
 */
public class SetSubscriptAndSuperscript extends LatexCommandSet {

  protected static SetSubscriptAndSuperscript instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetSubscriptAndSuperscript get() {
	if (instance == null) {
	  instance = new SetSubscriptAndSuperscript();
	}
	return instance;
  }

  /**
   * The command set's title.
   */
  public static final String TITLE = "Subscript & Superscript";

  protected SetSubscriptAndSuperscript() {
	super(TITLE, new LatexCommand[]{
      new LatexCommand(Icons.latexSubscriptAndSuperscriptIcons.RightSuperscript(), "Right superscript", "^{}"),
      new LatexCommand(Icons.latexSubscriptAndSuperscriptIcons.RightSubscript(), "Right subscript", "_{}"),
      new LatexCommand(Icons.latexSubscriptAndSuperscriptIcons.RightSubAndSuperScripts(), "Right sub & super scripts", "^{}_{}"),
      new LatexCommand(Icons.latexSubscriptAndSuperscriptIcons.LeftSuperscript(), "Left superscript", "^{}"),
      new LatexCommand(Icons.latexSubscriptAndSuperscriptIcons.LeftSubscript(), "Left subscript", "_{}"),
      new LatexCommand(Icons.latexSubscriptAndSuperscriptIcons.LeftSubAndSuperScripts(), "Left sub & super scripts", "^{}_{}"),
      new LatexCommand(Icons.latexSubscriptAndSuperscriptIcons.TopScript(), "Topscript", "^{}"),
      new LatexCommand(Icons.latexSubscriptAndSuperscriptIcons.LowScript(), "Lowscript", "_{}"),
      new LatexCommand(Icons.latexSubscriptAndSuperscriptIcons.TopAndLowScripts(), "Top & low scripts", "^{}_{}")
    });
  }

}
