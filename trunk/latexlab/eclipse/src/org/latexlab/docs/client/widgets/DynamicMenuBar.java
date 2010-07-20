package org.latexlab.docs.client.widgets;

import org.latexlab.docs.client.content.icons.Icons;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Implements a MenuBar with on-demand loading.
 */
public abstract class DynamicMenuBar extends ExtendedMenuBar {

  protected boolean hasContents = false;
	
  /**
   * Constructs a dynamic menu bar.
   * 
   * @param vertical whether to orient the menu bar vertically
   */
  public DynamicMenuBar(boolean vertical) {
    super(vertical);
    this.addItem(Icons.editorIcons.Blank(), "Loading...", (Command) null);
  }

  /**
   * Extends the on load event to trigger on-demand loading
   * of child items.
   */
  @Override
  public void onLoad() {
	if (!hasContents) {
      hasContents = true;
      getSubMenu(new AsyncCallback<ExtendedMenuItem[]>() {
		@Override
		public void onFailure(Throwable caught) {
	      hasContents = false;
		  Window.alert("A required component is unavailable or a new version has been deployed. You'll need to refresh your browser.");
		}
		@Override
		public void onSuccess(ExtendedMenuItem[] result) {
	      hasContents = true;
	      DynamicMenuBar.this.clearItems();
	      for (ExtendedMenuItem item : result) {
	    	if (item == null) {
	    	  DynamicMenuBar.this.addSeparator();
	    	} else {
	    	  DynamicMenuBar.this.addItem(item);
	    	}
	      }
		}
      });
	}
  }
  
  /**
   * Asynchronously loads the MenuBar's sub items.
   * 
   * @param callback the callback carrying the sub items
   */
  protected abstract void getSubMenu(AsyncCallback<ExtendedMenuItem[]> callback);
  
}
