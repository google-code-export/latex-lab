package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing font-alignment commands.
 */
public class SetAlignment extends LatexCommandSet {

  protected static SetAlignment instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetAlignment get() {
	if (instance == null) {
	  instance = new SetAlignment();
	}
	return instance;
  }
	
  /**
   * The command set's title.
   */
  public static final String TITLE = "Alignment";
	
  protected SetAlignment() {
	super(TITLE, new LatexCommand[]{
	  new LatexCommand(Icons.latexFormattingIcons.AlignLeft(), "Left", "\\begin{flushleft} <text here> \\end{flushleft}"),
	  new LatexCommand(Icons.latexFormattingIcons.AlignCenter(), "Centered", "\\begin{center} <text here> \\end{center}"),
	  new LatexCommand(Icons.latexFormattingIcons.AlignRight(), "Right", "\\begin{flushright} <text here> \\end{flushright}")
	});
  }

}
