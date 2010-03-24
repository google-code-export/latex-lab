package org.latexlab.docs.editor.simple.client.parts;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;

/**
 * A specialized, non-reusable widget containing the text area control.
 */
public class EditorPart extends Composite {

  private TextArea editor;
  private boolean wrapText = true;
  
  /**
   * Constructs a new Editor part.
   */
  public EditorPart() {
    editor = new TextArea();
    editor.setWidth("100%");
    editor.setHeight("99%");
    editor.setStylePrimaryName("lab-Text-Editor");
    initWidget(editor);
  }
  
  /**
   * Retrieves the text contents in the text area.
   * 
   * @return the current text contents
   */
  public String getText() {
    return editor.getText();
  }
  
  /**
   * Sets the text contents.
   * 
   * @param text the text contents to load
   */
  public void setText(String text) {
	editor.setText(text);
  }
  
  public boolean getWrapText() {
	return wrapText;
  }
  
  public void setWrapText(boolean wrapText) {
	if (this.wrapText != wrapText) {
	  this.wrapText = wrapText;
	  if (wrapText) {
		editor.getElement().setAttribute("wrap", "soft");
	    editor.removeStyleDependentName("NoWrap");
	  } else {
		editor.getElement().setAttribute("wrap", "off");
	    editor.addStyleDependentName("NoWrap");
	  }
	}
  }
  
}
