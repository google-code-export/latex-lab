package org.latexlab.docs.client.parts;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;

/**
 * A specialized, non-reusable widget containing the output panel.
 */
public class OutputPart extends Composite {

  private ScrollPanel content;
  private Label output;
  
  /**
   * Constructs a new Body part.
   */
  public OutputPart() {
    output = new Label();
    content = new ScrollPanel();
    content.setWidth("100%");
    content.setHeight("100%");
    content.add(output);
    content.setStylePrimaryName("latexlab-Output");
    initWidget(content);
  }
  
  /**
   * Appends to the output contents.
   * 
   * @param text the text to append
   */
  public void appendOutput(String text) {
    this.output.setText(this.output.getText() + text);
  }
  
  /**
   * Clears the current output text.
   */
  public void clearOutput() {
    this.output.setText("");
  }
  
  /**
   * Retrieves the output text contents.
   * 
   * @return text the output contents
   */
  public String getOutput() {
    return this.output.getText();
  }
  
  /**
   * Sets the output text contents.
   * 
   * @param text the output contents
   */
  public void setOutput(String text) {
    this.output.setText(text);
  }
  
}
