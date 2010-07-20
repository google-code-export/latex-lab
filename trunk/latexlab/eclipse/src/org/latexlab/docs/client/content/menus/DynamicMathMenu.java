package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Contains a Math menu with on-demand loading.
 */
public class DynamicMathMenu extends DynamicMenuBar {

  protected static DynamicMathMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   */
  public static DynamicMathMenu get() {
    if (instance == null) {
      instance = new DynamicMathMenu();
    }
    return instance;
  }
  
  /**
   * Constructs a math menu.
   */
  protected DynamicMathMenu() {
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
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Greek Letters", DynamicLatexGreekLowercaseLettersMenu.get()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Greek Uppercase Letters", DynamicLatexGreekUppercaseLettersMenu.get()),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Constructs", DynamicLatexConstructsMenu.get()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Big Operators", DynamicLatexBigOperatorsMenu.get()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Binary Operators", DynamicLatexBinaryOperatorsMenu.get()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Comparison", DynamicLatexComparisonMenu.get()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Logical", DynamicLatexLogicalMenu.get()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Sets", DynamicLatexSetsMenu.get()),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Boundaries", DynamicLatexBoundariesMenu.get()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Above and Below", DynamicLatexAboveAndBelowMenu.get()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Subscript and Superscript", DynamicLatexSubscriptAndSuperscriptMenu.get()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Accents", DynamicLatexAccentsMenu.get()),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Arrows", DynamicLatexArrowsMenu.get()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Arrows With Captions", DynamicLatexArrowsWithCaptionsMenu.get()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Whitespaces and Dots", DynamicLatexWhiteSpacesAndDotsMenu.get()),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Diverse Symbols", DynamicLatexDiverseSymbolsMenu.get()),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Matrix", new SystemPasteCommand("\\begin{array}[pos]{spalten}\n  \n\\end{array}")),
		  });
		}
    });
  }

}
