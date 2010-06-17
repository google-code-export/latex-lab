package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Contains an Enumeration menu with on-demand loading.
 */
public class DynamicEnumerationMenu extends DynamicMenuBar {

  protected static DynamicEnumerationMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   * 
   * @param commandSource the command source.
   */
  public static DynamicEnumerationMenu get(HasCommandHandlers commandSource) {
    if (instance == null) {
      instance = new DynamicEnumerationMenu(commandSource);
    }
    return instance;
  }
  
  /**
   * Constructs an enumeration menu.
   * 
   * @param commandSource the command source
   */
  protected DynamicEnumerationMenu(HasCommandHandlers commandSource) {
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
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Itemization", new SystemPasteCommand("\\begin{itemize}\n  \\item \n\\end{itemize}")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Enumeration", new SystemPasteCommand("\\begin{enumerate}\n  \\item \n\\end{enumerate}")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Description", new SystemPasteCommand("\\begin{description}\n  \\item[] \n\\end{description}")),
			null,
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Enumeration Entry", new SystemPasteCommand("\\item ")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Description Entry", new SystemPasteCommand("\\item[] ")),
		  });
		}
    });
  }

}
