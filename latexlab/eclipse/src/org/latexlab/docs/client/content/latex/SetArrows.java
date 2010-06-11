package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing arrows.
 */
public class SetArrows extends LatexCommandSet {

  protected static SetArrows instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetArrows get() {
	if (instance == null) {
	  instance = new SetArrows();
	}
	return instance;
  }

  /**
   * The command set's title.
   */
  public static final String TITLE = "Arrows";

  protected SetArrows() {
	super(TITLE, new LatexCommand[]{
      new LatexCommand(Icons.latexArrowsIcons.LeftArrow(), "Left arrow", "\\leftarrow"),
      new LatexCommand(Icons.latexArrowsIcons.RightArrow(), "Right arrow", "\\rightarrow"),
      new LatexCommand(Icons.latexArrowsIcons.LeftRightArrow(), "Left/Right arrow", "\\leftrightarrow"),
      new LatexCommand(Icons.latexArrowsIcons.DoubleLeftArrow(), "Double left arrow", "\\Leftarrow"),
      new LatexCommand(Icons.latexArrowsIcons.DoubleRightArrow(), "Double right arrow", "\\Rightarrow"),
      new LatexCommand(Icons.latexArrowsIcons.DoubleLeftRightArrow(), "Double left/right arrow", "\\Leftrightarrow"),
      new LatexCommand(Icons.latexArrowsIcons.UpArrow(), "Up arrow", "\\uparrow"),
      new LatexCommand(Icons.latexArrowsIcons.DownArrow(), "Down arrow", "\\downarrow"),
      new LatexCommand(Icons.latexArrowsIcons.UpDownArrow(), "Up/Down arrow", "\\updownarrow"),
      new LatexCommand(Icons.latexArrowsIcons.DoubleUpArrow(), "Double up arrow", "\\Uparrow"),
      new LatexCommand(Icons.latexArrowsIcons.DoubleDownArrow(), "Double down arrow", "\\Downarrow"),
      new LatexCommand(Icons.latexArrowsIcons.DoubleUpDownArrow(), "Double up/down arrow", "\\Updownarrow"),
      new LatexCommand(Icons.latexArrowsIcons.LeftHarpoonUp(), "Left harpoon up", "\\leftharpoonup"),
      new LatexCommand(Icons.latexArrowsIcons.RightHarpoonUp(), "Right harpoon up", "\\rightharpoonup"),
      new LatexCommand(Icons.latexArrowsIcons.LeftRightHarpoons(), "Left/Right harpoons", "\\rightleftharpoons"),
      new LatexCommand(Icons.latexArrowsIcons.LeftHarpoonDown(), "Left harpoon down", "\\leftharpoondown"),
      new LatexCommand(Icons.latexArrowsIcons.RightHarpoonDown(), "Right harpoon down", "\\rightharpoondown"),
      new LatexCommand(Icons.latexArrowsIcons.MapsTo(), "Maps to", "\\mapsto"),
      new LatexCommand(Icons.latexArrowsIcons.LongMapsTo(), "Long maps to", "\\longmapsto"),
      new LatexCommand(Icons.latexArrowsIcons.HookLeft(), "Hook left", "\\hookleftarrow"),
      new LatexCommand(Icons.latexArrowsIcons.HookRight(), "Hook right", "\\hookrightarrow"),
      new LatexCommand(Icons.latexArrowsIcons.NortheastArrow(), "Northeast arrow", "\\nearrow"),
      new LatexCommand(Icons.latexArrowsIcons.SoutheastArrow(), "Southeast arrow", "\\searrow"),
      new LatexCommand(Icons.latexArrowsIcons.SouthwestArrow(), "Southwest arrow", "\\swarrow"),
      new LatexCommand(Icons.latexArrowsIcons.NorthwestArrow(), "Northwest arrow", "\\nwarrow"),
      new LatexCommand(Icons.latexArrowsIcons.LongLeftArrow(), "Long left arrow", "\\longleftarrow"),
      new LatexCommand(Icons.latexArrowsIcons.LongRightArrow(), "Long right arrow", "\\longrightarrow"),
      new LatexCommand(Icons.latexArrowsIcons.LongLeftRightArrow(), "Long left/right arrow", "\\longleftrightarrow"),
      new LatexCommand(Icons.latexArrowsIcons.LongDoubleLeftArrow(), "Long double left arrow", "\\Longleftarrow"),
      new LatexCommand(Icons.latexArrowsIcons.LongDoubleRightArrow(), "Long double right arrow", "\\Longrightarrow"),
      new LatexCommand(Icons.latexArrowsIcons.LongDoubleLeftRightArrow(), "Long double left/right arrow", "\\Longleftrightarrow")
	});
  }

}
