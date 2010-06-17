package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.events.HasCommandHandlers;
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
   * 
   * @param commandSource the command source.
   */
  public static DynamicFormatMenu get(HasCommandHandlers commandSource) {
    if (instance == null) {
      instance = new DynamicFormatMenu(commandSource);
    }
    return instance;
  }
  
  /**
   * Constructs a format menu.
   * 
   * @param commandSource the command source
   */
  protected DynamicFormatMenu(HasCommandHandlers commandSource) {
    super(true, commandSource);
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
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Font Family", DynamicLatexFontFamilyMenu.get(commandSource)),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Font Series", DynamicLatexFontSeriesMenu.get(commandSource)),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Font Shapes", DynamicLatexFontShapesMenu.get(commandSource)),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Font Sizes", DynamicLatexFontSizesMenu.get(commandSource)),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Characters", DynamicLatexCharactersMenu.get(commandSource)),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Alignment", DynamicLatexAlignmentMenu.get(commandSource)),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Sloppy Paragraph", new SystemPasteCommand("\\begin{sloppypar} <text here> \\end{sloppypar}")),
		  });
		}
    });
  }

}
