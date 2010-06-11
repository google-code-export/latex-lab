package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.CurrentDocumentExportCommand;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.MenuItem;

/**
 * Contains an Export menu with on-demand loading.
 */
public class DynamicExportMenu extends DynamicMenuBar {

  protected static DynamicExportMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   * 
   * @param commandSource the command source.
   */
  public static DynamicExportMenu get(HasCommandHandlers commandSource) {
    if (instance == null) {
      instance = new DynamicExportMenu(commandSource);
    }
    return instance;
  }
  
  /**
   * Constructs an export menu.
   * 
   * @param commandSource the command source
   */
  protected DynamicExportMenu(HasCommandHandlers commandSource) {
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
			DynamicExportMenu.this.createItem(Icons.editorIcons.Blank(), "Portable Document Format (PDF)", new CurrentDocumentExportCommand("pdf")),
			DynamicExportMenu.this.createItem(Icons.editorIcons.Blank(), "PostScript Document (PS)", new CurrentDocumentExportCommand("ps")),
			DynamicExportMenu.this.createItem(Icons.editorIcons.Blank(), "Device Independent Format (DVI)", new CurrentDocumentExportCommand("dvi"))
		  });
		}
    });
  }

}
