package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing font shape commands.
 */
public class SetFontShapes extends LatexCommandSet {

  protected static SetFontShapes instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetFontShapes get() {
	if (instance == null) {
	  instance = new SetFontShapes();
	}
	return instance;
  }
	
  /**
   * The command set's title.
   */
  public static final String TITLE = "Font Shapes";
	
  protected SetFontShapes() {
	super(TITLE, new LatexCommand[]{
	  new LatexCommand(Icons.latexFormattingIcons.VerticalForm(), "Vertical", "\\upshape"),
	  new LatexCommand(Icons.latexFormattingIcons.ItalicForm(), "Italic", "\\itshape"),
	  new LatexCommand(Icons.latexFormattingIcons.SlantedForm(), "Slanted", "\\slshape"),
	  new LatexCommand(Icons.latexFormattingIcons.SmallCaps(), "Small Caps", "\\scshape")
	});
  }

}
