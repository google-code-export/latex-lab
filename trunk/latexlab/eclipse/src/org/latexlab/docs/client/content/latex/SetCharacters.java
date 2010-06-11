package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing font characters commands.
 */
public class SetCharacters extends LatexCommandSet {

  protected static SetCharacters instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetCharacters get() {
	if (instance == null) {
	  instance = new SetCharacters();
	}
	return instance;
  }
	
  /**
   * The command set's title.
   */
  public static final String TITLE = "Characters";
	
  protected SetCharacters() {
	super(TITLE, new LatexCommand[]{
	  new LatexCommand(Icons.latexFormattingIcons.NormalSize(), "Normal", "\\textnormal{ <text here> }"),
	  new LatexCommand(Icons.latexFormattingIcons.Emphasize(), "Emphasized", "\\emph{ <text here> }"),
	  new LatexCommand(Icons.latexFormattingIcons.Bold(), "Bold", "\\textbf{ <text here> }"),
	  new LatexCommand(Icons.latexFormattingIcons.Italic(), "Italic", "\\textit{ <text here> }"),
	  new LatexCommand(Icons.latexFormattingIcons.Slanted(), "Slanted", "\\textsl{ <text here> }"),
	  new LatexCommand(Icons.latexFormattingIcons.Typewriter(), "Typewriter", "\\texttt{ <text here> }"),
	  new LatexCommand(Icons.latexFormattingIcons.SmallCaps(), "Small Caps", "\\textsc{ <text here> }"),
	});
  }

}
