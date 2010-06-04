package org.latexlab.docs.client.dialogs;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * A dialog window displaying a loading message.
 */
public class LoadingDialog extends Dialog {

  protected static LoadingDialog instance;

  /**
   * Retrieves the single instance of this class.
   * 
   * @return the single instance of this class.
   */
  public static LoadingDialog get() {
    if (instance == null) {
      instance = new LoadingDialog();
    }
    return instance;
  }
  
  protected Label message;
  
  /**
   * Constructs a Loading dialog window.
   */
  protected LoadingDialog() {
    super("Loading", true);
    closeButton.setVisible(false);
    message = new Label();
    VerticalPanel content = new VerticalPanel();
    content.setWidth("500px");
    content.add(message);
    setContentWidget(content);
  }
  
  /**
   * Centers and displays the dialog window and sets the dialog window's message.
   * 
   * @param msg the dialog window's message.
   */
  public void center(String msg) {
    super.center();
    setMessage(msg);
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
   * @param msg the dialog window's message.
   */
  public void setMessage(String msg) {
    message.setText(msg);
  }
}
