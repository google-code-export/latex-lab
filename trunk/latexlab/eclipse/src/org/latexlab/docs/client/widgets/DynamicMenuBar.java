package org.latexlab.docs.client.widgets;

import org.latexlab.docs.client.events.HasCommandHandlers;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.MenuItem;

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
  public DynamicMenuBar(boolean vertical, HasCommandHandlers commandSource) {
    super(vertical, commandSource);
    this.addItem("<span class=\"lab-Menu-Loading\">Loading...<span>", true, (Command) null);
  }

  /**
   * Extends the on load event to trigger on-demand loading
   * of child items.
   */
  @Override
  public void onLoad() {
	if (!hasContents) {
      hasContents = true;
      getSubMenu(new AsyncCallback<MenuItem[]>() {
		@Override
		public void onFailure(Throwable caught) {
	      hasContents = false;
		  Window.alert("A required component failed to load.");
		}
		@Override
		public void onSuccess(MenuItem[] result) {
	      hasContents = true;
	      DynamicMenuBar.this.clearItems();
	      for (MenuItem item : result) {
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
  protected abstract void getSubMenu(AsyncCallback<MenuItem[]> callback);
  
}
