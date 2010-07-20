package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Contains a Document Title menu with on-demand loading.
 */
public class DynamicDocumentTitleMenu extends DynamicMenuBar {

  protected static DynamicDocumentTitleMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   */
  public static DynamicDocumentTitleMenu get() {
    if (instance == null) {
      instance = new DynamicDocumentTitleMenu();
    }
    return instance;
  }
  
  /**
   * Constructs a document title menu.
   */
  protected DynamicDocumentTitleMenu() {
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
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Title Properties", new SystemPasteCommand("\\title{}\n\\author{}\n% Remove command to get current date \n\\date{}")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Title", new SystemPasteCommand("\\maketitle")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Title Page", new SystemPasteCommand("\\begin{titlepage}\n  \n\\end{titlepage}")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Abstract", new SystemPasteCommand("\\begin{abstract}\n  \n\\end{abstract}"))
		  });
		}
    });
  }

}
