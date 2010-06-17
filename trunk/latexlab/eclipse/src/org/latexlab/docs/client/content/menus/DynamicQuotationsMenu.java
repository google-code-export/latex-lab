package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Contains a Quotations menu with on-demand loading.
 */
public class DynamicQuotationsMenu extends DynamicMenuBar {

  protected static DynamicQuotationsMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   * 
   * @param commandSource the command source.
   */
  public static DynamicQuotationsMenu get(HasCommandHandlers commandSource) {
    if (instance == null) {
      instance = new DynamicQuotationsMenu(commandSource);
    }
    return instance;
  }
  
  /**
   * Constructs a quotations menu.
   * 
   * @param commandSource the command source
   */
  protected DynamicQuotationsMenu(HasCommandHandlers commandSource) {
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
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Short Quotation", new SystemPasteCommand("\\begin{quote}\n  \n\\end{quote}")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Quotation", new SystemPasteCommand("\\begin{quotation}\n  \n\\end{quotation}")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Verse", new SystemPasteCommand("\\begin{verse}\n  \n\\end{verse}")),
		  });
		}
    });
  }

}
