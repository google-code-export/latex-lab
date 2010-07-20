package org.latexlab.docs.client.content.dialogs;

import org.latexlab.docs.client.commands.Command;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.widgets.Dialog;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * A generic dialog window displaying a message and an action button.
 */
public class StaticActionDialog extends Dialog {

  /**
   * Defines an action option.
   */
  public static class ActionDialogOption {
	  
	private Command action;
	private String label;
	  
	/**
	 * Constructs an action option.
	 * 
	 * @param label the label describing the option.
	 * @param action the command associated with the action.
	 */
	public ActionDialogOption(String label, Command action) {
	  this.label = label;
	  this.action = action;
	}

	/**
	 * Retrieves the command associated with the action.
	 * @return the command associated with the action.
	 */
	public Command getAction() {
	  return action;
	}

	/**
	 * Retrieves the label describing the option.
	 * 
	 * @return the label describing the option.
	 */
	public String getLabel() {
	  return label;
	}

	/**
	 * Sets the command associated with the action.
	 * 
	 * @param action the command associated with the action.
	 */
	public void setAction(Command action) {
	  this.action = action;
	}

	/**
	 * Sets the label describing the option.
	 * 
	 * @param label the label describing the option.
	 */
	public void setLabel(String label) {
	  this.label = label;
	}
	
  }
  
  protected static StaticActionDialog instance;
  
  /**
   * Retrieves the single instance of this class.
   * 
   * @return the single instance of this class.
   */
  public static StaticActionDialog get() {
    if (instance == null) {
      instance = new StaticActionDialog();
    }
    return instance;
  }
  
  protected HorizontalPanel buttonPanel;
  protected Button cancelButton;
  
  protected HTML message;
  
  /**
   * Constructs an Action dialog window.
   */
  protected StaticActionDialog() {
    super("", true);
    ClickHandler cancelHandler = new ClickHandler(){
      public void onClick(ClickEvent event) {
        hide();
      }
    };
    closeButton.addClickHandler(cancelHandler);
    cancelButton = new Button("Cancel", cancelHandler);
    buttonPanel = new HorizontalPanel();
    buttonPanel.setSpacing(10);
    message = new HTML();
    VerticalPanel content = new VerticalPanel();
    content.setWidth("500px");
    content.add(message);
    content.add(buttonPanel);
    setContentWidget(content);
  }

  /**
   * Updates the dialog window with the specified  contents and action options.
   * 
   * @param title the dialog's title
   * @param message the dialog's html contents
   * @param options the action options
   */
  public void update(String title, String message, ActionDialogOption[] options) {
	this.titleLabel.setText(title);
	this.message.setHTML(message);
	this.buttonPanel.clear();
	if (options.length > 0) {
	  for (ActionDialogOption opt : options) {
	    final Command cmd = opt.getAction();
        Button actionButton = new Button(opt.getLabel(), new ClickHandler(){
          public void onClick(ClickEvent event) {
            hide();
            if (cmd != null) {
              CommandEvent.fire(cmd);
            }
          }
        });
        buttonPanel.add(actionButton);
	  }
	  cancelButton.setText("Cancel");
	} else {
	  cancelButton.setText("OK");
	}
	buttonPanel.add(cancelButton);
  }
}
