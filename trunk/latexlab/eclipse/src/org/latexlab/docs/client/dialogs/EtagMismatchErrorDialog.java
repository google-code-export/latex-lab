package org.latexlab.docs.client.dialogs;

import org.latexlab.docs.client.commands.Command;
import org.latexlab.docs.client.commands.CurrentDocumentRefreshCommand;
import org.latexlab.docs.client.commands.CurrentDocumentReloadCommand;
import org.latexlab.docs.client.commands.CurrentDocumentRevisionHistoryCommand;
import org.latexlab.docs.client.events.AsyncInstantiationCallback;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;

/**
 * A dialog window displaying an ETag error message.
 */
public class EtagMismatchErrorDialog extends ErrorDialog {

  protected static EtagMismatchErrorDialog instance;

  /**
   * Retrieves the single instance of this class, using asynchronous instantiation.
   * 
   * @param handler the command handler.
   * @param cb the asynchronous instantiation callback.
   */
  public static void get(final CommandHandler handler,
	    final AsyncInstantiationCallback<EtagMismatchErrorDialog> cb) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  cb.onFailure(reason);
		}
		@Override
		public void onSuccess() {
	      if (instance == null) {
	        instance = new EtagMismatchErrorDialog();
	        instance.addCommandHandler(handler);
	      }
		  cb.onSuccess(instance);
		}
	});
  }
  
  /**
   * Causes the code for this class to be loaded.
   */
  public static void prefetch() {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) { }
		@Override
		public void onSuccess() {
		  new EtagMismatchErrorDialog();
		}
	});
  }
  
  protected Button reload;
  
  /**
   * Constructs a dialog window displaying an ETag error message.
   */
  protected EtagMismatchErrorDialog() {
	titleLabel.setText("Version Mismatch Warning");
	retry.setText("Overwrite Server Version");
    this.reload = new Button("Reload Document", new ClickHandler(){
      public void onClick(ClickEvent event) {
        if (Window.confirm("The document will be reloaded, any unsaved changes will be lost.")) {
          hide();
          CommandEvent.fire(EtagMismatchErrorDialog.this, new CurrentDocumentReloadCommand());
        }
      }
    });
    buttonPanel.insert(this.reload, 1);
  }

  /**
   * Updates the dialog window with specified information.
   * 
   * @param error the error exception.
   * @param errorCommand the command that triggered the error.
   * @param cancelCommand the command to trigger on cancel.
   */
  @Override
  public void update(Throwable error, Command errorCommand, Command cancelCommand) {
    this.error = error;
    this.errorCommand = new CurrentDocumentRefreshCommand(false, errorCommand);
    this.cancelCommand = cancelCommand;
    this.messagePanel.clear();
    this.messagePanel.add(new HTML("A different and recent version of the current document exists in the Google Docs servers.<br />" +
    		"You may choose to continue and apply the pending changes to the version at the server or reload the document.<br />" +
    		"Reloading the document will cause any unsaved changes to be lost."));
    Anchor viewRevisions = new Anchor("View and compare all versions of the current document", "#");
    viewRevisions.addClickHandler(new ClickHandler(){
		@Override
		public void onClick(ClickEvent event) {
		  event.preventDefault();
		  event.stopPropagation();
		  CommandEvent.fire(EtagMismatchErrorDialog.this, new CurrentDocumentRevisionHistoryCommand());
		}
    });
    this.messagePanel.add(viewRevisions);
    ScrollPanel detailPanel = new ScrollPanel();
    detailPanel.setWidth("480px");
    detailPanel.setHeight("60px");
    detailPanel.add(new Label("Error details:\n\n" + error.getMessage()));
    this.messagePanel.add(detailPanel);
  }
  
}
