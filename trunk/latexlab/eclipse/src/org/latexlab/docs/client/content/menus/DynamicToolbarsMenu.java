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
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.MenuItem;

/**
 * Contains a Toolbars menu with on-demand loading.
 */
public class DynamicToolbarsMenu extends DynamicMenuBar {

  protected static DynamicToolbarsMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   * 
   * @param commandSource the command source.
   */
  public static DynamicToolbarsMenu get(HasCommandHandlers commandSource) {
    if (instance == null) {
      instance = new DynamicToolbarsMenu(commandSource);
    }
    return instance;
  }
  
  /**
   * Constructs a toolbars menu.
   * 
   * @param commandSource the command source
   */
  protected DynamicToolbarsMenu(HasCommandHandlers commandSource) {
    super(true, commandSource);
  }

  /**
   * Asynchronously loads the MenuBar's sub items.
   * 
   * @param callback the callback carrying the sub items
   */
  @Override
  protected void getSubMenu(final AsyncCallback<MenuItem[]> callback) {
    GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
	      callback.onFailure(reason);
		}
		@Override
		public void onSuccess() {
		  callback.onSuccess(new MenuItem[] {
			DynamicToolbarsMenu.this.createItem(Icons.latexGroupsIcons.AboveAndBelow(), SetAboveAndBelow.TITLE, new SystemToggleLatexToolbarCommand(SetAboveAndBelow.TITLE)),
			DynamicToolbarsMenu.this.createItem(Icons.latexGroupsIcons.Accents(), SetAccents.TITLE, new SystemToggleLatexToolbarCommand(SetAccents.TITLE)),
			DynamicToolbarsMenu.this.createItem(Icons.latexGroupsIcons.Arrows(), SetArrows.TITLE, new SystemToggleLatexToolbarCommand(SetArrows.TITLE)),
			DynamicToolbarsMenu.this.createItem(Icons.latexGroupsIcons.ArrowsWithCaptions()/*Icon327*/, SetArrowsWithCaptions.TITLE, new SystemToggleLatexToolbarCommand(SetArrowsWithCaptions.TITLE)),
			DynamicToolbarsMenu.this.createItem(Icons.latexGroupsIcons.BinaryOperators(), SetBinaryOperators.TITLE, new SystemToggleLatexToolbarCommand(SetBinaryOperators.TITLE)),
			DynamicToolbarsMenu.this.createItem(Icons.latexGroupsIcons.Boundaries(), SetBoundaries.TITLE, new SystemToggleLatexToolbarCommand(SetBoundaries.TITLE)),
			DynamicToolbarsMenu.this.createItem(Icons.latexGroupsIcons.Comparison(), SetComparison.TITLE, new SystemToggleLatexToolbarCommand(SetComparison.TITLE)),
			DynamicToolbarsMenu.this.createItem(Icons.latexGroupsIcons.DiverseSymbols(), SetDiverseSymbols.TITLE, new SystemToggleLatexToolbarCommand(SetDiverseSymbols.TITLE)),
			DynamicToolbarsMenu.this.createItem(Icons.latexGroupsIcons.Formatting(), SetFormatting.TITLE, new SystemToggleLatexToolbarCommand(SetFormatting.TITLE)),
			DynamicToolbarsMenu.this.createItem(Icons.latexGroupsIcons.GreekLowercaseLetters(), SetGreekLowercase.TITLE, new SystemToggleLatexToolbarCommand(SetGreekLowercase.TITLE)),
			DynamicToolbarsMenu.this.createItem(Icons.latexGroupsIcons.GreekUppercaseLetters(), SetGreekUppercase.TITLE, new SystemToggleLatexToolbarCommand(SetGreekUppercase.TITLE)),
			DynamicToolbarsMenu.this.createItem(Icons.latexGroupsIcons.Logical(), SetLogical.TITLE, new SystemToggleLatexToolbarCommand(SetLogical.TITLE)),
			DynamicToolbarsMenu.this.createItem(Icons.latexGroupsIcons.Mathematical(), SetConstructs.TITLE, new SystemToggleLatexToolbarCommand(SetConstructs.TITLE)),
			DynamicToolbarsMenu.this.createItem(Icons.latexGroupsIcons.Operators(), SetBigOperators.TITLE, new SystemToggleLatexToolbarCommand(SetBigOperators.TITLE)),
			DynamicToolbarsMenu.this.createItem(Icons.latexGroupsIcons.Sets(), SetSets.TITLE, new SystemToggleLatexToolbarCommand(SetSets.TITLE)),
			DynamicToolbarsMenu.this.createItem(Icons.latexGroupsIcons.SubscriptAndSuperscript(), SetSubscriptAndSuperscript.TITLE, new SystemToggleLatexToolbarCommand(SetSubscriptAndSuperscript.TITLE)),
			DynamicToolbarsMenu.this.createItem(Icons.latexGroupsIcons.WhiteSpacesAndDots(), SetWhiteSpacesAndDots.TITLE, new SystemToggleLatexToolbarCommand(SetWhiteSpacesAndDots.TITLE))
		  });
		}
    });
  }

}
