package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing accents.
 */
public class SetAccents extends LatexCommandSet {

  protected static SetAccents instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetAccents get() {
	if (instance == null) {
	  instance = new SetAccents();
	}
	return instance;
  }

  /**
   * The command set's title.
   */
  public static final String TITLE = "Accents";

  protected SetAccents() {
	super(TITLE, new LatexCommand[]{
	  new LatexCommand(Icons.latexAccentsIcons.HatAccent(), "Hat accent", "\\hat{}"),
	  new LatexCommand(Icons.latexAccentsIcons.CheckAccent(), "Check accent", "\\check{}"),
	  new LatexCommand(Icons.latexAccentsIcons.BreveAccent(), "Breve accent", "\\breve{}"),
	  new LatexCommand(Icons.latexAccentsIcons.AcuteAccent(), "Acute accent", "\\acute{}"),
	  new LatexCommand(Icons.latexAccentsIcons.GraveAccent(), "Grave accent", "\\grave{}"),
	  new LatexCommand(Icons.latexAccentsIcons.TildeAccent(), "Tilde accent", "\\tilde{}"),
	  new LatexCommand(Icons.latexAccentsIcons.BarAccent(), "Bar accent", "\\bar{}"),
	  new LatexCommand(Icons.latexAccentsIcons.VectorAccent(), "Vector accent", "\\vec{}"),
      new LatexCommand(Icons.latexAccentsIcons.DotAccent(), "Dot accent", "\\dot{}"),
      new LatexCommand(Icons.latexAccentsIcons.DoubleDotAccent(), "Double-dot accent", "\\ddot{}")
	});
  }

}
