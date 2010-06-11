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
 * Contains a Document Title menu with on-demand loading.
 */
public class DynamicDocumentTitleMenu extends DynamicMenuBar {

  protected static DynamicDocumentTitleMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   * 
   * @param commandSource the command source.
   */
  public static DynamicDocumentTitleMenu get(HasCommandHandlers commandSource) {
    if (instance == null) {
      instance = new DynamicDocumentTitleMenu(commandSource);
    }
    return instance;
  }
  
  /**
   * Constructs a document title menu.
   * 
   * @param commandSource the command source
   */
  protected DynamicDocumentTitleMenu(HasCommandHandlers commandSource) {
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
			DynamicDocumentTitleMenu.this.createItem(Icons.editorIcons.Blank(), "Title Properties", new SystemPasteCommand("\\title{}\n\\author{}\n% Remove command to get current date \n\\date{}")),
			DynamicDocumentTitleMenu.this.createItem(Icons.editorIcons.Blank(), "Title", new SystemPasteCommand("\\maketitle")),
			DynamicDocumentTitleMenu.this.createItem(Icons.editorIcons.Blank(), "Title Page", new SystemPasteCommand("\\begin{titlepage}\n  \n\\end{titlepage}")),
			DynamicDocumentTitleMenu.this.createItem(Icons.editorIcons.Blank(), "Abstract", new SystemPasteCommand("\\begin{abstract}\n  \n\\end{abstract}"))
		  });
		}
    });
  }

}
