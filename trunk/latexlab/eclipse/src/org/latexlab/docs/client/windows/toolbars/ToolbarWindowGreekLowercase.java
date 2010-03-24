package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.windows.ToolbarWindow;
import org.latexlab.docs.client.windows.WindowManager;

public class ToolbarWindowGreekLowercase extends ToolbarWindow {

  public final static String TITLE = "Greek Lowercase Letters";

  protected static ToolbarWindowGreekLowercase instance;
  
  public static ToolbarWindowGreekLowercase get(CommandHandler handler,
	    WindowManager manager) {
    if (instance == null) {
      instance = new ToolbarWindowGreekLowercase();
      instance.addCommandHandler(handler);
	  instance.registeredDragController = manager.getWindowController().getPickupDragController();
	  instance.hide();
	  manager.getWindowController().makeResizable(instance);
	  manager.getBoundaryPanel().add(instance, 500, 120);
    }
    return instance;
  }

  protected ToolbarWindowGreekLowercase() {
	super(TITLE);
    buildToolBar();
  }

  private void buildToolBar() {
    addButton(Icons.latexGreekLowercaseIcons.SmallAlpha(), "Small alpha", false, new SystemPasteCommand("\\alpha"));
    addButton(Icons.latexGreekLowercaseIcons.SmallBeta(), "Small beta", false, new SystemPasteCommand("\\beta"));
    addButton(Icons.latexGreekLowercaseIcons.SmallChi(), "Small chi", false, new SystemPasteCommand("\\chi"));
    addButton(Icons.latexGreekLowercaseIcons.SmallDelta(), "Small delta", false, new SystemPasteCommand("\\delta"));
    addButton(Icons.latexGreekLowercaseIcons.SmallEpsilon(), "Small epsilon", false, new SystemPasteCommand("\\epsilon"));
    addButton(Icons.latexGreekLowercaseIcons.SmallPhi(), "Small phi", false, new SystemPasteCommand("\\phi"));
    addButton(Icons.latexGreekLowercaseIcons.SmallVariiertesPhi(), "Small variiertes phi", false, new SystemPasteCommand("\\varphi"));
    addButton(Icons.latexGreekLowercaseIcons.SmallGamma(), "Small gamma", false, new SystemPasteCommand("\\gamma"));
    addButton(Icons.latexGreekLowercaseIcons.SmallEta(), "Small eta", false, new SystemPasteCommand("\\eta"));
    addButton(Icons.latexGreekLowercaseIcons.SmallIota(), "Small iota", false, new SystemPasteCommand("\\iota"));
    addButton(Icons.latexGreekLowercaseIcons.SmallKappa(), "Small kappa", false, new SystemPasteCommand("\\kappa"));
    addButton(Icons.latexGreekLowercaseIcons.SmallLambda(), "Small lambda", false, new SystemPasteCommand("\\lambda"));
    addButton(Icons.latexGreekLowercaseIcons.SmallMu(), "Small mu", false, new SystemPasteCommand("\\mu"));
    addButton(Icons.latexGreekLowercaseIcons.SmallNu(), "Small nu", false, new SystemPasteCommand("\\nu"));
    addButton(Icons.latexGreekLowercaseIcons.SmallOmega(), "Small o", false, new SystemPasteCommand("\\o"));
    addButton(Icons.latexGreekLowercaseIcons.SmallPi(), "Small pi", false, new SystemPasteCommand("\\pi"));
    addButton(Icons.latexGreekLowercaseIcons.SmallVariiertesPi(), "Small variiertes pi", false, new SystemPasteCommand("\\varpi"));
    addButton(Icons.latexGreekLowercaseIcons.SmallTheta(), "Small theta", false, new SystemPasteCommand("\\theta"));
    addButton(Icons.latexGreekLowercaseIcons.SmallVariiertesTheta(), "Small variiertes theta", false, new SystemPasteCommand("\\vartheta"));
    addButton(Icons.latexGreekLowercaseIcons.SmallRho(), "Small rho", false, new SystemPasteCommand("\\rho"));
    addButton(Icons.latexGreekLowercaseIcons.SmallSigma(), "Small sigma", false, new SystemPasteCommand("\\sigma"));
    addButton(Icons.latexGreekLowercaseIcons.SmallVariiertesSigma(), "Small variiertes sigma", false, new SystemPasteCommand("\\varsigma"));
    addButton(Icons.latexGreekLowercaseIcons.SmallTau(), "Small tau", false, new SystemPasteCommand("\\tau"));
    addButton(Icons.latexGreekLowercaseIcons.SmallUpsilon(), "Small upsilon", false, new SystemPasteCommand("\\upsilon"));
    addButton(Icons.latexGreekLowercaseIcons.SmallOmega(), "Small omega", false, new SystemPasteCommand("\\omega"));
    addButton(Icons.latexGreekLowercaseIcons.SmallXi(), "Small xi", false, new SystemPasteCommand("\\xi"));
    addButton(Icons.latexGreekLowercaseIcons.SmallPsi(), "Small psi", false, new SystemPasteCommand("\\psi"));
    addButton(Icons.latexGreekLowercaseIcons.SmallZeta(), "Small zeta", false, new SystemPasteCommand("\\zeta"));
    resize();
  }
}
