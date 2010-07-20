package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.CurrentDocumentExportCommand;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Contains an Export menu with on-demand loading.
 */
public class DynamicExportMenu extends DynamicMenuBar {

  protected static DynamicExportMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   */
  public static DynamicExportMenu get() {
    if (instance == null) {
      instance = new DynamicExportMenu();
    }
    return instance;
  }
  
  /**
   * Constructs an export menu.
   */
  protected DynamicExportMenu() {
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
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Portable Document Format (PDF)", new CurrentDocumentExportCommand("pdf")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "PostScript Document (PS)", new CurrentDocumentExportCommand("ps")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Device Independent Format (DVI)", new CurrentDocumentExportCommand("dvi"))
		  });
		}
    });
  }

}
