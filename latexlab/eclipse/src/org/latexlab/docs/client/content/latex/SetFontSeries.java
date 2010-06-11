package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing font-series commands.
 */
public class SetFontSeries extends LatexCommandSet {

  protected static SetFontSeries instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetFontSeries get() {
	if (instance == null) {
	  instance = new SetFontSeries();
	}
	return instance;
  }
	
  /**
   * The command set's title.
   */
  public static final String TITLE = "Font Series";
	
  protected SetFontSeries() {
	super(TITLE, new LatexCommand[]{
	  new LatexCommand(Icons.latexFormattingIcons.MediumSeries(), "Medium", "\\mdseries"),
	  new LatexCommand(Icons.latexFormattingIcons.BoldSeries(), "Bold", "\\bfseries"),
	});
  }

}
