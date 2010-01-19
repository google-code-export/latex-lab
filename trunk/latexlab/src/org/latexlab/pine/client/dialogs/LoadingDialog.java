package org.latexlab.pine.client.dialogs;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.latexlab.pine.client.dialogs.BaseDialog;

/**
 * A dialog window displaying a loading message.
 */
public class LoadingDialog extends BaseDialog {

  protected Label message;
  
  /**
   * Constructs a Loading dialog window.
   */
  public LoadingDialog() {
    super("Loading", true);
    closeButton.setVisible(false);
    message = new Label();
    VerticalPanel content = new VerticalPanel();
    content.setWidth("500px");
    content.add(message);
    setContentWidget(content);
  }
  
  /**
   * Retrieves the dialog window's message.
   * 
   * @return the dialog window's message
   */
  public String getMessage() {
    return message.getText();
  }
  
  /**
   * Sets the dialog window's message.
   * 
   * @param msg
   */
  public void setMessage(String msg) {
    message.setText(msg);
  }
  
  /**
   * Centers and displays the dialog window and sets the dialog window's message.
   * 
   * @param msg
   */
  public void center(String msg) {
    super.center();
    setMessage(msg);
  }
}
