package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing logic symbols.
 */
public class SetLogical extends LatexCommandSet {

  protected static SetLogical instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetLogical get() {
	if (instance == null) {
	  instance = new SetLogical();
	}
	return instance;
  }

  /**
   * The command set's title.
   */
  public static final String TITLE = "Logical";

  protected SetLogical() {
	super(TITLE, new LatexCommand[]{
      new LatexCommand(Icons.latexLogicalIcons.NotIn(), "Not In", "\\ni"),
      new LatexCommand(Icons.latexLogicalIcons.Exists(), "Exists", "\\exists"),
      new LatexCommand(Icons.latexLogicalIcons.ForAll(), "For all", "\\forall"),
      new LatexCommand(Icons.latexLogicalIcons.Negation(), "Negation", "\\neg"),
      new LatexCommand(Icons.latexLogicalIcons.And(), "And", "\\wedge"),
      new LatexCommand(Icons.latexLogicalIcons.Or(), "Or", "\\vee")
    });
  }

}
