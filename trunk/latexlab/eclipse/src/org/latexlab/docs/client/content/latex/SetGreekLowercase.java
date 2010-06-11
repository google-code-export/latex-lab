package org.latexlab.docs.client.content.latex;

import org.latexlab.docs.client.content.icons.Icons;

/**
 * A LaTeX command set containing greek lowercase letters.
 */
public class SetGreekLowercase extends LatexCommandSet {

  protected static SetGreekLowercase instance;
  
  /**
   * Retrieves the shared instance of this command set.
   * 
   * @return the shared instance of this command set
   */
  public static SetGreekLowercase get() {
	if (instance == null) {
	  instance = new SetGreekLowercase();
	}
	return instance;
  }

  /**
   * The command set's title.
   */
  public static final String TITLE = "Greek Lowercase Letters";

  protected SetGreekLowercase() {
	super(TITLE, new LatexCommand[]{
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallAlpha(), "Small alpha", "\\alpha"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallBeta(), "Small beta", "\\beta"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallChi(), "Small chi", "\\chi"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallDelta(), "Small delta", "\\delta"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallEpsilon(), "Small epsilon", "\\epsilon"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallPhi(), "Small phi", "\\phi"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallVariiertesPhi(), "Small variiertes phi", "\\varphi"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallGamma(), "Small gamma", "\\gamma"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallEta(), "Small eta", "\\eta"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallIota(), "Small iota", "\\iota"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallKappa(), "Small kappa", "\\kappa"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallLambda(), "Small lambda", "\\lambda"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallMu(), "Small mu", "\\mu"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallNu(), "Small nu", "\\nu"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallOmega(), "Small o", "\\o"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallPi(), "Small pi", "\\pi"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallVariiertesPi(), "Small variiertes pi", "\\varpi"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallTheta(), "Small theta", "\\theta"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallVariiertesTheta(), "Small variiertes theta", "\\vartheta"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallRho(), "Small rho", "\\rho"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallSigma(), "Small sigma", "\\sigma"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallVariiertesSigma(), "Small variiertes sigma", "\\varsigma"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallTau(), "Small tau", "\\tau"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallUpsilon(), "Small upsilon", "\\upsilon"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallOmega(), "Small omega", "\\omega"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallXi(), "Small xi", "\\xi"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallPsi(), "Small psi", "\\psi"),
      new LatexCommand(Icons.latexGreekLowercaseIcons.SmallZeta(), "Small zeta", "\\zeta")
    });
  }

}
