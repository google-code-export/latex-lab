package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing set symbols.
 */
public class SetSets extends LatexCommandSet {

  protected static SetSets instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetSets get() {
	if (instance == null) {
	  instance = new SetSets();
	}
	return instance;
  }

  /**
   * The command set's title.
   */
  public static final String TITLE = "Sets";

  protected SetSets() {
	super(TITLE, new LatexCommand[]{
      new LatexCommand(Icons.latexSetsIcons.ElementOf(), "Element of", "\\in"),
      new LatexCommand(Icons.latexSetsIcons.NotElementOf(), "Not element of", "\\notin"),
      new LatexCommand(Icons.latexSetsIcons.Union(), "Union", "\\cup"),
      new LatexCommand(Icons.latexSetsIcons.Intersection(), "Intersection", "\\cap"),
      new LatexCommand(Icons.latexSetsIcons.UnionLarge(), "Union Large", "\\bigcup"),
      new LatexCommand(Icons.latexSetsIcons.IntersectionLarge(), "Intersection Large", "\\bigcap"),
      new LatexCommand(Icons.latexSetsIcons.ContainedIn(), "Contained in", "\\subset"),
      new LatexCommand(Icons.latexSetsIcons.Contains(), "Contains", "\\supset"),
      new LatexCommand(Icons.latexSetsIcons.ContainedInOrEqualTo(), "Contained in or equal to", "\\subseteq"),
      new LatexCommand(Icons.latexSetsIcons.ContainsOrEqualTo(), "Contains or equal to", "\\supseteq")
    });
  }

}
