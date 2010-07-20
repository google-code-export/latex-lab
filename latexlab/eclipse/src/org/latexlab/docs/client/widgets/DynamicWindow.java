package org.latexlab.docs.client.widgets;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A base implementation of window with on-demand content loading.
 */
public abstract class DynamicWindow extends Window {

  protected boolean hasContents = false;
	
  /**
   * Constructs a dynamic window.
   * 
   * @param title the window's title
   * @param allowDropping whether the window supports dropping
   * @param targetWidth the initial width of the window frame, or null to inherit
   * @param targetHeight the initial height of the dialog window, or null to inherit
   */
  protected DynamicWindow(String title, boolean allowDropping,
		String targetWidth, String targetHeight) {
    super(title, new HorizontalPanel(), allowDropping);
    HorizontalPanel contents = (HorizontalPanel) getContentWidget();
    if (targetWidth != null) {
      contents.setWidth(targetWidth);
    }
    if (targetHeight != null) {
      contents.setHeight(targetHeight);
    }
    contents.setStylePrimaryName("lab-Loading");
    contents.add(new HTML("&nbsp;"));
  }
  
  /**
   * Retrieves the window's contents asynchronously.
   * 
   * @param callback the callback carrying the content widget
   */
  protected abstract void getContents(AsyncCallback<Widget> callback);
  
  /**
   * Whether the window's contents have loaded.
   * 
   * @return whether the window's contents have loaded
   */
  public boolean isHasContents() {
    return hasContents;
  }

  /**
   * Makes the window visible.
   */
  @Override
  public void show() {
	super.show();
	if (!hasContents) {
	  hasContents = true;
	  getContents(new AsyncCallback<Widget>() {
		  @Override
		  public void onFailure(Throwable caught) {
	        hasContents = false;
		    com.google.gwt.user.client.Window.alert("A required component is unavailable or a new version has been deployed. You'll need to refresh your browser.");
		  }
		  @Override
		  public void onSuccess(Widget result) {
	        hasContents = true;
		    setContentWidget(result);
		  }
	  });
	}
  }

}
