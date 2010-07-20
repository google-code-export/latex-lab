package org.latexlab.docs.client.widgets;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A base implementation of dialog with on-demand content loading.
 */
public abstract class DynamicDialog extends Dialog {

  protected boolean hasContents = false;
	
  /**
   * Constructs a dynamic dialog.
   * 
   * @param title the dialog's title
   * @param modal whether the dialog is modal
   * @param targetWidth the initial width of the dialog frame, or null to inherit
   * @param targetHeight the initial height of the dialog frame, or null to inherit
   */
  protected DynamicDialog(String title, boolean modal,
		String targetWidth, String targetHeight) {
    super(title, modal);
    HorizontalPanel contents = new HorizontalPanel();
    if (targetWidth != null) {
      contents.setWidth(targetWidth);
    }
    if (targetHeight != null) {
      contents.setHeight(targetHeight);
    }
    HTML loading = new HTML("&nbsp;");
    loading.setStylePrimaryName("lab-Loading");
    contents.add(loading);
    setContentWidget(contents);
  }
  
  /**
   * Retrieves the dialog's contents asynchronously.
   * 
   * @param callback the callback carrying the content widget
   */
  protected abstract void getContents(AsyncCallback<Widget> callback);
  
  /**
   * Whether the dialog's contents have loaded.
   * 
   * @return whether the dialog's contents have loaded
   */
  public boolean isHasContents() {
    return hasContents;
  }

  /**
   * Makes the dialog window visible.
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
		    Window.alert("A required component is unavailable or a new version has been deployed. You'll need to refresh your browser.");
		  }
		  @Override
		  public void onSuccess(Widget result) {
	        hasContents = true;
		    setContentWidget(result);
		    DynamicDialog.this.center();
		  }
	  });
	}
  }

}
