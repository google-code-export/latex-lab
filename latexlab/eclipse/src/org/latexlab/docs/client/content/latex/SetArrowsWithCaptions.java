package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing arrows with captions.
 */
public class SetArrowsWithCaptions extends LatexCommandSet {

  protected static SetArrowsWithCaptions instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetArrowsWithCaptions get() {
	if (instance == null) {
	  instance = new SetArrowsWithCaptions();
	}
	return instance;
  }

  /**
   * The command set's title.
   */
  public static final String TITLE = "Arrows With Captions";

  protected SetArrowsWithCaptions() {
	super(TITLE, new LatexCommand[]{
      new LatexCommand(Icons.latexArrowsWithCaptionsIcons.RightArrowTopCaption(), "Right arrow, top caption", "\\stackrel{}{\\rightarrow}"),
      new LatexCommand(Icons.latexArrowsWithCaptionsIcons.LeftArrowTopCaption(), "Left arrow, top caption", "\\stackrel{}{\\leftarrow}"),
      new LatexCommand(Icons.latexArrowsWithCaptionsIcons.LeftRightArrowTopCaption(), "Left/Right arrow, top caption", "\\stackrel{}{\\leftrightarrow}"),
      new LatexCommand(Icons.latexArrowsWithCaptionsIcons.RightArrowBottomCaption(), "Right arrow, bottom caption", "\\stackrel{\\rightarrow}{}"),
      new LatexCommand(Icons.latexArrowsWithCaptionsIcons.LeftArrowBottomCaption(), "Left arrow, bottom caption", "\\stackrel{\\leftarrow}{}"),
      new LatexCommand(Icons.latexArrowsWithCaptionsIcons.LeftRightArrowBottomCaption(), "Left/Right arrow, bottom caption", "\\stackrel{\\leftrightarrow}{}")
	});
  }

}
