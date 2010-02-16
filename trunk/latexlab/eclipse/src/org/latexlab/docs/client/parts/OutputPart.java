package org.latexlab.docs.client.parts;

import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A specialized, non-reusable widget containing the output panel.
 */
public class OutputPart extends Composite {

  private VerticalPanel content;
  private Widget output;
  
  /**
   * Constructs a new Body part.
   */
  public OutputPart() {
    content = new VerticalPanel();
    content.setSize("100%", "100%");
    initWidget(content);
  }
  
  /**
   * Appends to the output contents.
   * 
   * @param text the text to append
   */
  public void appendOutput(String text) {
	if (this.output == null || !(this.output instanceof Label)) {
	  this.clear();
	  this.output = new Label();
	  this.content.add(this.output);
	}
    Label label = (Label) output;
    label.setText(label.getText() + text);
  }
  
  /**
   * Clears the current output text.
   */
  public void clear() {
	if (this.output != null) {
	  this.output.removeFromParent();
	  this.output = null;
	}
  }
  
  /**
   * Sets the output text contents.
   * 
   * @param text the output contents
   */
  public void setOutput(String text) {
	if (this.output == null || !(this.output instanceof Label)) {
	  this.clear();
	  Label label = new Label();
	  label.setSize("100%", "100%");
	  label.setStylePrimaryName("latexlab-Output");
	  this.output = label;
	  this.content.add(this.output);
	}
	Label label = (Label) output;
    label.setText(text);
  }
  
  /**
   * Sets the output to the contents of a given url.
   * 
   * @param url the content url
   */
  public void setUrl(String url) {
    if (this.output == null || !(this.output instanceof Frame)) {
	  this.clear();
      Frame frame = new Frame();
      frame.setSize("100%", "1500px");
      IFrameElement.as(frame.getElement()).setFrameBorder(0);
      IFrameElement.as(frame.getElement()).setMarginHeight(0);
      IFrameElement.as(frame.getElement()).setMarginWidth(0);
      IFrameElement.as(frame.getElement()).setScrolling("no");
      this.output = frame;
      this.content.add(this.output);
      this.content.getElement().getParentElement().getStyle().setProperty("overflowX", "hidden");
    }
    Frame frame = (Frame) this.output;
    frame.setUrl(url);
  }
  
}
