package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.AsyncInstantiationCallback;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.windows.ToolbarWindow;
import org.latexlab.docs.client.windows.WindowManager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;

/**
 * A toolbar containing LaTeX commands.
 */
public class ToolbarWindowGreekUppercase extends ToolbarWindow {

  /**
   * The toolbar window's title.
   */
  public final static String TITLE = "Greek Uppercase Letters";

  protected static ToolbarWindowGreekUppercase instance;

  /**
   * Retrieves the single instance of this class, using asynchronous instantiation.
   * 
   * @param handler the command handler
   * @param manager the window manager
   * @param cb the asynchronous instantiation callback
   */
  public static void get(final CommandHandler handler, final WindowManager manager,
	    final AsyncInstantiationCallback<ToolbarWindowGreekUppercase> cb) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  cb.onFailure(reason);
		}
		@Override
		public void onSuccess() {
	      if (instance == null) {
	        instance = new ToolbarWindowGreekUppercase();
	        instance.addCommandHandler(handler);
		    instance.registeredDragController = manager.getWindowController().getPickupDragController();
		    instance.hide();
		    manager.getWindowController().makeResizable(instance);
		    manager.getBoundaryPanel().add(instance, 500, 120);
	      }
	      cb.onSuccess(instance);
		}
	});
  }
  
  /**
   * Causes the code for this class to be loaded.
   */
  public static void prefetch() {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) { }
		@Override
		public void onSuccess() {
		  new ToolbarWindowGreekLowercase();
		}
	});
  }

  protected ToolbarWindowGreekUppercase() {
	super(TITLE);
    buildToolBar();
  }

  /**
   * Builds the toolbar contents.
   */
  private void buildToolBar() {
    addButton(Icons.latexGreekUppercaseIcons.CapitalAlpha(), "Capital alpha", false, new SystemPasteCommand("A"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalBeta(), "Capital beta", false, new SystemPasteCommand("B"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalChi(), "Capital chi", false, new SystemPasteCommand("X"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalDelta(), "Capital delta", false, new SystemPasteCommand("\\Delta"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalEpsilon(), "Capital epsilon", false, new SystemPasteCommand("E"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalPhi(), "Capital phi", false, new SystemPasteCommand("\\Phi"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalGamma(), "Capital gamma", false, new SystemPasteCommand("\\Gamma"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalEta(), "Capital eta", false, new SystemPasteCommand("H"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalIota(), "Capital iota", false, new SystemPasteCommand("I"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalKappa(), "Capital kappa", false, new SystemPasteCommand("K"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalLambda(), "Capital lambda", false, new SystemPasteCommand("\\Lambda"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalMu(), "Capital mu", false, new SystemPasteCommand("M"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalNu(), "Capital nu", false, new SystemPasteCommand("N"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalO(), "Capital o", false, new SystemPasteCommand("O"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalPi(), "Capital pi", false, new SystemPasteCommand("\\Pi"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalTheta(), "Capital theta", false, new SystemPasteCommand("\\Theta"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalRho(), "Capital rho", false, new SystemPasteCommand("P"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalSigma(), "Capital sigma", false, new SystemPasteCommand("\\Sigma"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalTau(), "Capital tau", false, new SystemPasteCommand("T"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalUpsilon(), "Capital upsilon", false, new SystemPasteCommand("\\Upsilon"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalOmega(), "Capital omega", false, new SystemPasteCommand("\\Omega"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalXi(), "Capital xi", false, new SystemPasteCommand("\\Xi"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalPsi(), "Capital psi", false, new SystemPasteCommand("\\Psi"));
    addButton(Icons.latexGreekUppercaseIcons.CapitalZeta(), "Capital zeta", false, new SystemPasteCommand("Z"));
    resize();
  }
}
