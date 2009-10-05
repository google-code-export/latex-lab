package org.latexlab.pine.editor.client.parts;

import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;

import org.latexlab.pine.client.events.CommandEventSource;

/**
 * A specialized, non-reusable widget containing the text area control.
 */
public class EditorPart extends CommandEventSource {

  private ScrollPanel content;
  private TextArea editor;
  
  /**
   * Constructs a new Editor part.
   */
  public EditorPart() {
    editor = new TextArea();
    editor.setWidth("100%");
    editor.setHeight("99%");
    content = new ScrollPanel();
    content.setStylePrimaryName("gdbe-Text-Editor");
    content.setWidth("100%");
    content.setHeight("100%");
    content.add(editor);
    initWidget(content);
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
  
  /**
   * Selects all the text in the text area.
   */
  public void selectAll() {
    editor.selectAll();
  }
  
}
