package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.MenuItem;

/**
 * Contains a Math menu with on-demand loading.
 */
public class DynamicMathMenu extends DynamicMenuBar {

  protected static DynamicMathMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   * 
   * @param commandSource the command source.
   */
  public static DynamicMathMenu get(HasCommandHandlers commandSource) {
    if (instance == null) {
      instance = new DynamicMathMenu(commandSource);
    }
    return instance;
  }
  
  /**
   * Constructs a math menu.
   * 
   * @param commandSource the command source
   */
  protected DynamicMathMenu(HasCommandHandlers commandSource) {
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
			DynamicMathMenu.this.createItem(Icons.editorIcons.Blank(), "Greek Letters", DynamicLatexGreekLowercaseLettersMenu.get(commandSource)),
			DynamicMathMenu.this.createItem(Icons.editorIcons.Blank(), "Greek Uppercase Letters", DynamicLatexGreekUppercaseLettersMenu.get(commandSource)),
			null,
			DynamicMathMenu.this.createItem(Icons.editorIcons.Blank(), "Constructs", DynamicLatexConstructsMenu.get(commandSource)),
			DynamicMathMenu.this.createItem(Icons.editorIcons.Blank(), "Big Operators", DynamicLatexBigOperatorsMenu.get(commandSource)),
			DynamicMathMenu.this.createItem(Icons.editorIcons.Blank(), "Binary Operators", DynamicLatexBinaryOperatorsMenu.get(commandSource)),
			DynamicMathMenu.this.createItem(Icons.editorIcons.Blank(), "Comparison", DynamicLatexComparisonMenu.get(commandSource)),
			DynamicMathMenu.this.createItem(Icons.editorIcons.Blank(), "Logical", DynamicLatexLogicalMenu.get(commandSource)),
			DynamicMathMenu.this.createItem(Icons.editorIcons.Blank(), "Sets", DynamicLatexSetsMenu.get(commandSource)),
			null,
			DynamicMathMenu.this.createItem(Icons.editorIcons.Blank(), "Boundaries", DynamicLatexBoundariesMenu.get(commandSource)),
			DynamicMathMenu.this.createItem(Icons.editorIcons.Blank(), "Above and Below", DynamicLatexAboveAndBelowMenu.get(commandSource)),
			DynamicMathMenu.this.createItem(Icons.editorIcons.Blank(), "Subscript and Superscript", DynamicLatexSubscriptAndSuperscriptMenu.get(commandSource)),
			DynamicMathMenu.this.createItem(Icons.editorIcons.Blank(), "Accents", DynamicLatexAccentsMenu.get(commandSource)),
			null,
			DynamicMathMenu.this.createItem(Icons.editorIcons.Blank(), "Arrows", DynamicLatexArrowsMenu.get(commandSource)),
			DynamicMathMenu.this.createItem(Icons.editorIcons.Blank(), "Arrows With Captions", DynamicLatexArrowsWithCaptionsMenu.get(commandSource)),
			DynamicMathMenu.this.createItem(Icons.editorIcons.Blank(), "Whitespaces and Dots", DynamicLatexWhiteSpacesAndDotsMenu.get(commandSource)),
			null,
			DynamicMathMenu.this.createItem(Icons.editorIcons.Blank(), "Diverse Symbols", DynamicLatexDiverseSymbolsMenu.get(commandSource)),
			null,
			DynamicMathMenu.this.createItem(Icons.editorIcons.Blank(), "Matrix", new SystemPasteCommand("\\begin{array}[pos]{spalten}\n  \n\\end{array}")),
		  });
		}
    });
  }

}
