package org.latexlab.docs.client.content.dialogs;

import org.latexlab.docs.client.commands.Command;
import org.latexlab.docs.client.commands.CurrentDocumentRefreshCommand;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;

/**
 * A dialog window displaying an ETag error message.
 */
public class StaticIncompatibilityErrorDialog extends StaticErrorDialog {

  protected static StaticIncompatibilityErrorDialog instance;

  /**
   * Retrieves the single instance of this class.
   */
  public static StaticIncompatibilityErrorDialog get() {
    if (instance == null) {
      instance = new StaticIncompatibilityErrorDialog();
    }
    return instance;
  }
  
  protected Button reload;
  
  /**
   * Constructs a dialog window displaying an ETag error message.
   */
  protected StaticIncompatibilityErrorDialog() {
	titleLabel.setText("Incompatibility Error");
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
    this.messagePanel.add(new HTML("At this time, LaTeX Lab is not compatible with the latest " +
    		"version of the Google Docs editor.<br /><br />" +
    		"To open and edit documents in LaTeX Lab please " +
    		"ensure that the <b>New version of Google documents</b> option is unchecked:<br /><br />" +
    		"<a href=\"http://docs.google.com/settings?hl=en\" title=\"Click to change this setting in Google Docs\" target=\"_blank\"><img src=\"/images/gdocs-version-setting.png\" border=\"0px\" /></a><br /><br />" +
    		"Follow these instructions to change the Google Docs version setting:" +
    		"<ol><li>In Google Docs, on the top right menu, click <i>Settings</i> -> <i>Document Settings</i>.</li>" +
    		"<li>In the settings window, click the <i>Editing</i> tab and uncheck the <b>New version of Google documents</b> option.</li>" +
    		"<li>Click save.</li></ol>" +
    		"<b>Note</b>: documents previously created with the new version of the Google Docs editor " +
    		"will not be editable in LaTeX Lab, even after unchecking the option to use the new version.<br /><br />" +
    		"We apologize for any inconvenience and hope to support the latest Google Docs editor in a future version."));
    this.messagePanel.setWidth("750px");
  }
  
}
