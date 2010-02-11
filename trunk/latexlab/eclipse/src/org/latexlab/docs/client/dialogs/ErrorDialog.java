package org.latexlab.docs.client.dialogs;

import org.latexlab.docs.client.commands.Command;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * A dialog window displaying a loading message.
 */
public class ErrorDialog extends Dialog {

  protected static ErrorDialog instance;
  
  public static ErrorDialog getInstance(CommandHandler handler) {
    if (instance == null) {
      instance = new ErrorDialog();
      instance.addCommandHandler(handler);
    }
    return instance;
  }

  protected VerticalPanel messagePanel;
  protected Command errorCommand, cancelCommand;
  protected Throwable error;
  protected Button retry, cancel;
  
  /**
   * Constructs an Error dialog window.
   */
  protected ErrorDialog() {
    super("Error", true);
    ClickHandler cancelHandler = new ClickHandler(){
      public void onClick(ClickEvent event) {
        hide();
        if (cancelCommand != null) {
          CommandEvent.fire(ErrorDialog.this, cancelCommand);
        }
      }
    };
    closeButton.addClickHandler(cancelHandler);
    messagePanel = new VerticalPanel();
    messagePanel.setSpacing(10);
    this.retry = new Button("Retry", new ClickHandler(){
      public void onClick(ClickEvent event) {
        hide();
        CommandEvent.fire(ErrorDialog.this, errorCommand);
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
  public Command getErrorCommand() {
    return errorCommand;
  }
  
  /**
   * Sets the command event that triggered the error.
   * 
   * @param event the error command event
   */
  public void setErrorCommand(Command event) {
    this.errorCommand = event;
  }
  
  /**
   * Retrieves the Command event to trigger on cancel
   * 
   * @return the error command event
   */
  public Command getCancelCommand() {
    return cancelCommand;
  }
  
  /**
   * Sets the Command event to trigger on cancel.
   * 
   * @param event the cancel command event
   */
  public void setCancelCommand(Command event) {
    this.cancelCommand = event;
  }
  
  /**
   * Displays an error message.
   * 
   * @param error the caught exception
   * @param errorCommand the command that triggered the error
   * @param cancelCommand the command to trigger on cancel
   */
  public void showError(Throwable error, Command errorCommand, Command cancelCommand) {
	this.update(error, errorCommand, cancelCommand);
    this.center();
  }
  
  public void update(Throwable error, Command errorCommand, Command cancelCommand) {
    this.error = error;
    this.errorCommand = errorCommand;
    this.cancelCommand = cancelCommand;
    this.messagePanel.clear();
    this.messagePanel.add(new Label("An error occurred while performing the following command:"));
    this.messagePanel.add(new Label(errorCommand.getDescription()));
    ScrollPanel detailPanel = new ScrollPanel();
    detailPanel.setWidth("480px");
    detailPanel.setHeight("60px");
    detailPanel.add(new Label("Error details:\n\n" + error.getMessage()));
    this.messagePanel.add(detailPanel);
  }
}
