package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Contains a Format menu with on-demand loading.
 */
public class DynamicFormatMenu extends DynamicMenuBar {

  protected static DynamicFormatMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   */
  public static DynamicFormatMenu get() {
    if (instance == null) {
      instance = new DynamicFormatMenu();
    }
    return instance;
  }
  
  /**
   * Constructs a format menu.
   */
  protected DynamicFormatMenu() {
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
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Normal Font", new SystemPasteCommand("\\normalfont")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Font Family", DynamicLatexFontFamilyMenu.get()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Font Series", DynamicLatexFontSeriesMenu.get()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Font Shapes", DynamicLatexFontShapesMenu.get()),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Font Sizes", DynamicLatexFontSizesMenu.get()),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Characters", DynamicLatexCharactersMenu.get()),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Alignment", DynamicLatexAlignmentMenu.get()),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Sloppy Paragraph", new SystemPasteCommand("\\begin{sloppypar} <text here> \\end{sloppypar}")),
		  });
		}
    });
  }

}
