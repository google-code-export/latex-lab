package org.latexlab.docs.client.content.dialogs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.widgets.DynamicDialog;

/**
 * A dialog window displaying development information.
 */
public class DynamicDevelopmentInfoDialog extends DynamicDialog {

  protected static DynamicDevelopmentInfoDialog instance;

  /**
   * Retrieves the single instance of this class.
   * 
   * @return the single instance of this class.
   */
  public static DynamicDevelopmentInfoDialog get(final CommandHandler handler) {
    if (instance == null) {
      instance = new DynamicDevelopmentInfoDialog();
      instance.addCommandHandler(handler);
    }
    return instance;
  }

  /**
   * Constructs a dialog window displaying development information.
   */
  public DynamicDevelopmentInfoDialog() {
	super("Development Environment", true, "500px", null);
    addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        hide();
      }
    });
  }
  
  /**
   * Contains development information.
   */
  protected class DevelopmentInfoDialogContents extends Composite {
	
	/**
	 * Constructs a Development Info dialog contents.
	 */
	public DevelopmentInfoDialogContents() {
	  VerticalPanel panel = new VerticalPanel();
	  panel.setWidth("500px");
	  panel.add(new HTML("Welcome to the development version of LaTeX Lab.<br /><br />" +
  		"This is where we deploy and test new features that are not yet ready for production use.<br /><br />" +
  		"<a href=\"https://wave.google.com/wave/waveref/googlewave.com/w+tziZnYNzA\" target=\"_blank\">LaTeX Lab Development Wave</a><br /><br />" +
  		"<b>Currently Deployed</b>:<ul><li>Editor Preferences Dialog (Edit -> Preferences)</li>" +
  		"<li>Code Splitting (on-demand loading of components)</li><li>Insert, Math and Format menus</li></ul>"));
	  Button ok = new Button("OK", new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		  hide();
		}
	  });
	  panel.add(ok);
	  initWidget(panel);
	}
	  
  }

  /**
   * Retrieves the dialog's contents asynchronously.
   * 
   * @param callback the callback carrying the content widget
   */
  @Override
  protected void getContents(final AsyncCallback<Widget> callback) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  callback.onFailure(reason);
		}
		@Override
		public void onSuccess() {
		  callback.onSuccess(new DevelopmentInfoDialogContents());
		}
	});
  }
  
}
