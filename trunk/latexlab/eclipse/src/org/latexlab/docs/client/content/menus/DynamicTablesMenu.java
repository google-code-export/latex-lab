package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Contains a Tables menu with on-demand loading.
 */
public class DynamicTablesMenu extends DynamicMenuBar {

  protected static DynamicTablesMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   */
  public static DynamicTablesMenu get() {
    if (instance == null) {
      instance = new DynamicTablesMenu();
    }
    return instance;
  }
  
  /**
   * Constructs a tables menu.
   */
  protected DynamicTablesMenu() {
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
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Table of Contents", new SystemPasteCommand("\\tableofcontents")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Table of Figures", new SystemPasteCommand("\\listoffigures")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Table of Tables", new SystemPasteCommand("\\listoftables")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Bibliography", new SystemPasteCommand("\\begin{thebibliography}{label}\n  \n\\end{thebibliography}")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Glossary", new SystemPasteCommand("\\makeglossary")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Index", new SystemPasteCommand("\\makeindex")),
		  });
		}
    });
  }

}
