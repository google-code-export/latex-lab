package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemToggleLatexToolbarCommand;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.content.latex.SetAboveAndBelow;
import org.latexlab.docs.client.content.latex.SetAccents;
import org.latexlab.docs.client.content.latex.SetArrows;
import org.latexlab.docs.client.content.latex.SetArrowsWithCaptions;
import org.latexlab.docs.client.content.latex.SetBinaryOperators;
import org.latexlab.docs.client.content.latex.SetBoundaries;
import org.latexlab.docs.client.content.latex.SetComparison;
import org.latexlab.docs.client.content.latex.SetDiverseSymbols;
import org.latexlab.docs.client.content.latex.SetFormatting;
import org.latexlab.docs.client.content.latex.SetGreekLowercase;
import org.latexlab.docs.client.content.latex.SetGreekUppercase;
import org.latexlab.docs.client.content.latex.SetLogical;
import org.latexlab.docs.client.content.latex.SetConstructs;
import org.latexlab.docs.client.content.latex.SetBigOperators;
import org.latexlab.docs.client.content.latex.SetSets;
import org.latexlab.docs.client.content.latex.SetSubscriptAndSuperscript;
import org.latexlab.docs.client.content.latex.SetWhiteSpacesAndDots;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Contains a Toolbars menu with on-demand loading.
 */
public class DynamicToolbarsMenu extends DynamicMenuBar {

  protected static DynamicToolbarsMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   */
  public static DynamicToolbarsMenu get() {
    if (instance == null) {
      instance = new DynamicToolbarsMenu();
    }
    return instance;
  }
  
  /**
   * Constructs a toolbars menu.
   */
  protected DynamicToolbarsMenu() {
    super(true);
  }

  /**
   * Asynchronously loads the MenuBar's sub items.
   * 
   * @param callback the callback carrying the sub items
   */
  @Override
  protected void getSubMenu(final AsyncCallback<ExtendedMenuItem[]> callback) {
    GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
	      callback.onFailure(reason);
		}
		@Override
		public void onSuccess() {
		  callback.onSuccess(new ExtendedMenuItem[] {
			new ExtendedMenuItem(Icons.latexGroupsIcons.AboveAndBelow(), SetAboveAndBelow.TITLE, new SystemToggleLatexToolbarCommand(SetAboveAndBelow.TITLE)),
			new ExtendedMenuItem(Icons.latexGroupsIcons.Accents(), SetAccents.TITLE, new SystemToggleLatexToolbarCommand(SetAccents.TITLE)),
			new ExtendedMenuItem(Icons.latexGroupsIcons.Arrows(), SetArrows.TITLE, new SystemToggleLatexToolbarCommand(SetArrows.TITLE)),
			new ExtendedMenuItem(Icons.latexGroupsIcons.ArrowsWithCaptions()/*Icon327*/, SetArrowsWithCaptions.TITLE, new SystemToggleLatexToolbarCommand(SetArrowsWithCaptions.TITLE)),
			new ExtendedMenuItem(Icons.latexGroupsIcons.BinaryOperators(), SetBinaryOperators.TITLE, new SystemToggleLatexToolbarCommand(SetBinaryOperators.TITLE)),
			new ExtendedMenuItem(Icons.latexGroupsIcons.Boundaries(), SetBoundaries.TITLE, new SystemToggleLatexToolbarCommand(SetBoundaries.TITLE)),
			new ExtendedMenuItem(Icons.latexGroupsIcons.Comparison(), SetComparison.TITLE, new SystemToggleLatexToolbarCommand(SetComparison.TITLE)),
			new ExtendedMenuItem(Icons.latexGroupsIcons.DiverseSymbols(), SetDiverseSymbols.TITLE, new SystemToggleLatexToolbarCommand(SetDiverseSymbols.TITLE)),
			new ExtendedMenuItem(Icons.latexGroupsIcons.Formatting(), SetFormatting.TITLE, new SystemToggleLatexToolbarCommand(SetFormatting.TITLE)),
			new ExtendedMenuItem(Icons.latexGroupsIcons.GreekLowercaseLetters(), SetGreekLowercase.TITLE, new SystemToggleLatexToolbarCommand(SetGreekLowercase.TITLE)),
			new ExtendedMenuItem(Icons.latexGroupsIcons.GreekUppercaseLetters(), SetGreekUppercase.TITLE, new SystemToggleLatexToolbarCommand(SetGreekUppercase.TITLE)),
			new ExtendedMenuItem(Icons.latexGroupsIcons.Logical(), SetLogical.TITLE, new SystemToggleLatexToolbarCommand(SetLogical.TITLE)),
			new ExtendedMenuItem(Icons.latexGroupsIcons.Mathematical(), SetConstructs.TITLE, new SystemToggleLatexToolbarCommand(SetConstructs.TITLE)),
			new ExtendedMenuItem(Icons.latexGroupsIcons.Operators(), SetBigOperators.TITLE, new SystemToggleLatexToolbarCommand(SetBigOperators.TITLE)),
			new ExtendedMenuItem(Icons.latexGroupsIcons.Sets(), SetSets.TITLE, new SystemToggleLatexToolbarCommand(SetSets.TITLE)),
			new ExtendedMenuItem(Icons.latexGroupsIcons.SubscriptAndSuperscript(), SetSubscriptAndSuperscript.TITLE, new SystemToggleLatexToolbarCommand(SetSubscriptAndSuperscript.TITLE)),
			new ExtendedMenuItem(Icons.latexGroupsIcons.WhiteSpacesAndDots(), SetWhiteSpacesAndDots.TITLE, new SystemToggleLatexToolbarCommand(SetWhiteSpacesAndDots.TITLE))
		  });
		}
    });
  }

}
