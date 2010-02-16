package org.latexlab.docs.client.parts;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;

import org.latexlab.docs.client.controls.CodeMirror;

/**
 * A specialized, non-reusable widget containing the text area control.
 */
public class EditorPart extends Composite {

  private TextArea editor;
  private CodeMirror mirror;
  
  /**
   * Constructs a new Editor part.
   */
  public EditorPart() {
    editor = new TextArea();
    editor.setWidth("100%");
    editor.setHeight("99%");
    editor.setStylePrimaryName("gdbe-Text-Editor");
    editor.getElement().setId("_codemirror_");
    initWidget(editor);
  }
  
  public void init() {
    JSONObject pars = new JSONObject();
    pars.put("parserfile", new JSONString("parselatex.js"));
    pars.put("path", new JSONString("/codemirror/js/"));
    pars.put("stylesheet", new JSONString("/codemirror/css/latexcolors.css"));
    mirror = CodeMirror.fromTextArea(editor.getElement(), pars.getJavaScriptObject());
  }
  
  /**
   * Retrieves the text contents in the text area.
   * 
   * @return the current text contents
   */
  public String getText() {
    return mirror.getCode();
  }
  
  /**
   * Sets the text contents.
   * 
   * @param text the text contents to load
   */
  public void setText(String text) {
	if (mirror == null) {
      editor.setText(text);
	} else {
	  mirror.setCode(text);
	}
  }
  
  /**
   * Selects all the text in the text area.
   */
  public void selectAll() {
    editor.selectAll();
  }
  
  public void undo() {
    mirror.undo();
  }
  
  public void redo() {
    mirror.redo();
  }
  
  public void replaceSelection(String text) {
    mirror.replaceSelection(text);
  }
  
}
