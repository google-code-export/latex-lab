package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing greek uppercase letters.
 */
public class SetGreekUppercase extends LatexCommandSet {

  protected static SetGreekUppercase instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetGreekUppercase get() {
	if (instance == null) {
	  instance = new SetGreekUppercase();
	}
	return instance;
  }

  /**
   * The command set's title.
   */
  public static final String TITLE = "Greek Uppercase Letters";

  protected SetGreekUppercase() {
	super(TITLE, new LatexCommand[]{
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalAlpha(), "Capital alpha", "A"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalBeta(), "Capital beta", "B"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalChi(), "Capital chi", "X"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalDelta(), "Capital delta", "\\Delta"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalEpsilon(), "Capital epsilon", "E"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalPhi(), "Capital phi", "\\Phi"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalGamma(), "Capital gamma", "\\Gamma"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalEta(), "Capital eta", "H"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalIota(), "Capital iota", "I"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalKappa(), "Capital kappa", "K"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalLambda(), "Capital lambda", "\\Lambda"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalMu(), "Capital mu", "M"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalNu(), "Capital nu", "N"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalO(), "Capital o", "O"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalPi(), "Capital pi", "\\Pi"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalTheta(), "Capital theta", "\\Theta"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalRho(), "Capital rho", "P"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalSigma(), "Capital sigma", "\\Sigma"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalTau(), "Capital tau", "T"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalUpsilon(), "Capital upsilon", "\\Upsilon"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalOmega(), "Capital omega", "\\Omega"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalXi(), "Capital xi", "\\Xi"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalPsi(), "Capital psi", "\\Psi"),
      new LatexCommand(Icons.latexGreekUppercaseIcons.CapitalZeta(), "Capital zeta", "Z")
    });
  }

}
