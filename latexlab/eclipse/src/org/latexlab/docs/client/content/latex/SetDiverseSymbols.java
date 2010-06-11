package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing diverse symbols.
 */
public class SetDiverseSymbols extends LatexCommandSet {

  protected static SetDiverseSymbols instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetDiverseSymbols get() {
	if (instance == null) {
	  instance = new SetDiverseSymbols();
	}
	return instance;
  }

  /**
   * The command set's title.
   */
  public static final String TITLE = "Diverse Symbols";

  protected SetDiverseSymbols() {
	super(TITLE, new LatexCommand[]{
      new LatexCommand(Icons.latexDiverseSymbolsIcons.Partial(), "Partial", "\\partial"),
      new LatexCommand(Icons.latexDiverseSymbolsIcons.Nabla(), "Nabla", "\\nabla"),
      new LatexCommand(Icons.latexDiverseSymbolsIcons.Infinity(), "Infinity", "\\infty"),
      new LatexCommand(Icons.latexDiverseSymbolsIcons.Im(), " ", "\\Im"),
      new LatexCommand(Icons.latexDiverseSymbolsIcons.Re(), " ", "\\Re"),
      new LatexCommand(Icons.latexDiverseSymbolsIcons.Aleph(), " ", "\\aleph"),
      new LatexCommand(Icons.latexDiverseSymbolsIcons.Angle(), "Angle", "\\angle"),
      new LatexCommand(Icons.latexDiverseSymbolsIcons.Bot(), "Bot", "\\bot"),
      new LatexCommand(Icons.latexDiverseSymbolsIcons.Diamond(), "Diamond", "\\diamond"),
      new LatexCommand(Icons.latexDiverseSymbolsIcons.Ell(), "Ell", "\\ell"),
      new LatexCommand(Icons.latexDiverseSymbolsIcons.Wp(), "Wp", "\\wp"),
      new LatexCommand(Icons.latexDiverseSymbolsIcons.Hbar(), "Hbar", "\\hbar"),
      new LatexCommand(Icons.latexDiverseSymbolsIcons.Integral(), "Integral", "\\int"),
      new LatexCommand(Icons.latexDiverseSymbolsIcons.Sum(), "Sum", "\\sum"),
      new LatexCommand(Icons.latexDiverseSymbolsIcons.Product(), "Product", "\\prod"),
      new LatexCommand(Icons.latexDiverseSymbolsIcons.CoProduct(), "Co-Product", "\\coprod")
    });
  }

}
