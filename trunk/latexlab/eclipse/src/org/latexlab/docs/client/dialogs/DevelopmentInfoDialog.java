package org.latexlab.docs.client.dialogs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;

import org.latexlab.docs.client.events.AsyncInstantiationCallback;
import org.latexlab.docs.client.events.CommandHandler;

/**
 * A dialog window displaying development information.
 */
public class DevelopmentInfoDialog extends ActionDialog {

  protected static DevelopmentInfoDialog instance;

  /**
   * Retrieves the single instance of this class.
   * 
   * @param handler the command handler.
   * @return the single instance of this class.
   */
  public static void get(final CommandHandler handler,
	    final AsyncInstantiationCallback<DevelopmentInfoDialog> cb) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  cb.onFailure(reason);
		}
		@Override
		public void onSuccess() {
	      if (instance == null) {
	        instance = new DevelopmentInfoDialog();
	        instance.addCommandHandler(handler);
	      }
		  cb.onSuccess(instance);
		}
	});
  }

  /**
   * Constructs a dialog window displaying development information.
   */
  public DevelopmentInfoDialog() {
    this.update("Development Environment", "Welcome to the development version of LaTeX Lab.<br /><br />" +
  		"This is where we deploy and test new features that are not yet ready for production use.<br /><br />" +
  		"<a href=\"https://wave.google.com/wave/waveref/googlewave.com/w+tziZnYNzA\" target=\"_blank\">LaTeX Lab Development Wave</a><br /><br />" +
  		"<b>Currently Deployed</b>:<ul><li>Editor Preferences Dialog (Edit -> Preferences)</li></ul>", new ActionDialogOption[0]);
  }
  
}
