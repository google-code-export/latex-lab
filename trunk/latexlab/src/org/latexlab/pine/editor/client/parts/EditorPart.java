package org.latexlab.pine.editor.client.parts;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;

import org.latexlab.pine.client.controls.CodeMirror;
import org.latexlab.pine.client.events.CommandEventSource;

/**
 * A specialized, non-reusable widget containing the text area control.
 */
public class EditorPart extends CommandEventSource {

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
    mirror.setCode(text);
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
