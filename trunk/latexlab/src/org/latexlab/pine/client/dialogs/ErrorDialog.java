package org.latexlab.pine.client.dialogs;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.latexlab.pine.client.dialogs.BaseDialog;
import org.latexlab.pine.client.events.CommandEvent;

/**
 * A dialog window displaying a loading message.
 */
public class ErrorDialog extends BaseDialog {

  protected VerticalPanel messagePanel;
  protected CommandEvent errorCommand, cancelCommand;
  protected Throwable error;
  protected Button retry, cancel;
  
  /**
   * Constructs an Error dialog window.
   */
  public ErrorDialog() {
    super("Error", true);
    ClickHandler cancelHandler = new ClickHandler(){
      public void onClick(ClickEvent event) {
        hide();
        if (cancelCommand != null) {
          fireOnCommand(cancelCommand);
        }
      }
    };
    closeButton.addClickHandler(cancelHandler);
    messagePanel = new VerticalPanel();
    messagePanel.setSpacing(10);
    this.retry = new Button("Retry", new ClickHandler(){
      public void onClick(ClickEvent event) {
        hide();
        fireOnCommand(errorCommand);
      }
    });
    this.cancel = new Button("Cancel", cancelHandler);
    VerticalPanel content = new VerticalPanel();
    HorizontalPanel buttons = new HorizontalPanel();
    buttons.setSpacing(10);
    buttons.add(this.retry);
    buttons.add(this.cancel);
    content.setWidth("500px");
    content.add(messagePanel);
    content.add(buttons);
    setContentWidget(content);
  }
  
  /**
   * Retrieves the Command event that triggered the error.
   * 
   * @return the error command event
   */
  public CommandEvent getErrorCommand() {
    return errorCommand;
  }
  
  /**
   * Sets the command event that triggered the error.
   * 
   * @param event the error command event
   */
  public void setErrorCommand(CommandEvent event) {
    this.errorCommand = event;
  }
  
  /**
   * Retrieves the Command event to trigger on cancel
   * 
   * @return the error command event
   */
  public CommandEvent getCancelCommand() {
    return cancelCommand;
  }
  
  /**
   * Sets the Command event to trigger on cancel.
   * 
   * @param event the cancel command event
   */
  public void setCancelCommand(CommandEvent event) {
    this.cancelCommand = event;
  }
  
  /**
   * Displays an error message.
   * 
   * @param error the caught exception
   * @param errorCommand the command that triggered the error
   * @param cancelCommand the command to trigger on cancel
   */
  public void showError(Throwable error, CommandEvent errorCommand, CommandEvent cancelCommand) {
    this.error = error;
    this.errorCommand = errorCommand;
    this.cancelCommand = cancelCommand;
    this.messagePanel.clear();
    this.messagePanel.add(new Label("An error occurred while performing the following command:"));
    this.messagePanel.add(new Label(errorCommand.getCommand().getDescription()));
    ScrollPanel detailPanel = new ScrollPanel();
    detailPanel.setWidth("480px");
    detailPanel.setHeight("60px");
    detailPanel.add(new Label("Error details:\n\n" + error.getMessage()));
    this.messagePanel.add(detailPanel);
    this.center();
  }
}
