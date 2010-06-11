package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing comparison symbols.
 */
public class SetComparison extends LatexCommandSet {

  protected static SetComparison instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetComparison get() {
	if (instance == null) {
	  instance = new SetComparison();
	}
	return instance;
  }

  /**
   * The command set's title.
   */
  public static final String TITLE = "Comparison";

  protected SetComparison() {
	super(TITLE, new LatexCommand[]{
      new LatexCommand(Icons.latexComparisonIcons.LesserThanOrEqual(), "Lesser than or equal", "\\leq"),
      new LatexCommand(Icons.latexComparisonIcons.GreaterThanOrEqual(), "Greater than or equal", "\\geq"),
      new LatexCommand(Icons.latexComparisonIcons.Preceding(), "Preceding", "\\prec"),
      new LatexCommand(Icons.latexComparisonIcons.Succeding(), "Succeding", "\\succ"),
      new LatexCommand(Icons.latexComparisonIcons.TriangleLeft(), "Triangle left", "\\triangleleft"),
      new LatexCommand(Icons.latexComparisonIcons.TriangleRight(), "Triangle right", "\\triangleright"),
      new LatexCommand(Icons.latexComparisonIcons.NotEqual(), "Not equal", "\\neq"),
      new LatexCommand(Icons.latexComparisonIcons.Equivalent(), "Equivalent", "\\equiv"),
      new LatexCommand(Icons.latexComparisonIcons.Approximately(), "Approximately", "\\approx"),
      new LatexCommand(Icons.latexComparisonIcons.Congruent(), "Congruent", "\\cong"),
      new LatexCommand(Icons.latexComparisonIcons.PropTo(), "Prop to", "\\propto")
    });
  }

}
