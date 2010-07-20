package org.latexlab.docs.client.content.dialogs;

import org.latexlab.docs.client.commands.Command;
import org.latexlab.docs.client.commands.CurrentDocumentRefreshCommand;
import org.latexlab.docs.client.commands.CurrentDocumentReloadCommand;
import org.latexlab.docs.client.commands.CurrentDocumentRevisionHistoryCommand;
import org.latexlab.docs.client.events.CommandEvent;

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
public class StaticEtagMismatchErrorDialog extends StaticErrorDialog {

  protected static StaticEtagMismatchErrorDialog instance;

  /**
   * Retrieves the single instance of this class.
   */
  public static StaticEtagMismatchErrorDialog get() {
    if (instance == null) {
      instance = new StaticEtagMismatchErrorDialog();
    }
    return instance;
  }
  
  protected Button reload;
  
  /**
   * Constructs a dialog window displaying an ETag error message.
   */
  protected StaticEtagMismatchErrorDialog() {
	titleLabel.setText("Version Mismatch Warning");
	retry.setText("Overwrite Server Version");
    this.reload = new Button("Reload Document", new ClickHandler(){
      public void onClick(ClickEvent event) {
        if (Window.confirm("The document will be reloaded, any unsaved changes will be lost.")) {
          hide();
          CommandEvent.fire(new CurrentDocumentReloadCommand());
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
		  CommandEvent.fire(new CurrentDocumentRevisionHistoryCommand());
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
