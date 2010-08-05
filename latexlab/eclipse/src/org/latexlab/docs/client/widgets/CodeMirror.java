package org.latexlab.docs.client.widgets;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

/**
 * An overlay type exposing the Code Mirror API.
 */
public class CodeMirror extends JavaScriptObject {

  /**
   * Creates a new CodeMirror instance, from an existing text area.
   * 
   * @param textarea the text area to build upon
   * @param pars the CodeMirror options
   * @return a CodeMirror instance
   */
  public native static CodeMirror fromTextArea(Element textarea, CodeMirrorOptions pars) /*-{
  	return $wnd.CodeMirror.fromTextArea(textarea, pars);
  }-*/;
  
  /**
   * Initializes this CodeMirror instance.
   */
  public native static void init() /*-{
  	return $wnd.CodeMirror.init();
  }-*/;
  
  /**
   * Determines whether CodeMirror is supported.
   * @return whether CodeMirror is supported
   */
  public native static boolean isProbablySupported() /*-{
  	return $wnd.CodeMirror.isProbablySupported();
  }-*/;
  
  /**
   * Creates a new CodeMirror instance.
   * 
   * @param parent the parent element
   * @param pars the CodeMirror options
   * @return a CodeMirror instance
   */
  public native static CodeMirror newInstance(Element parent, CodeMirrorOptions pars) /*-{
    return new $wnd.CodeMirror(parent, pars);
  }-*/;
  
  protected CodeMirror() { }
  
  /**
   * Clears the undo history.
   */
  public final native void clearHistory() /*-{
    this.clearHistory();
  }-*/;
  
  /**
   * Sets the cursor to the beginning of a given line.
   * 
   * @param lineNumber the line to jump to
   */
  public final native void jumpToLine(int lineNumber) /*-{
    this.jumpToLine(lineNumber);
  }-*/;
  
  /**
   * Retrieves the text editor contents.
   * 
   * @return the text editor contents
   */
  public final native String getCode() /*-{
    return this.getCode();
  }-*/;
  
  /**
   * Retrieves the cursor position.
   * 
   * @param start option
   * @return the native JavaScript cursor position object
   */
  public final native JavaScriptObject getCursorPosition(boolean start) /*-{
    return this.getCursorPosition(start);
  }-*/;
  
  /**
   * Retrieves the undo history size.
   * 
   * @return the undo history size
   */
  public final native JavaScriptObject getHistorySize() /*-{
    return this.historySize();
  }-*/;
  
  /**
   * Retrieves the search cursor.
   * 
   * @param search the search string
   * @param atCursor at cursor option
   * @return the search cursor
   */
  public final native JavaScriptObject getSearchCursor(String search, boolean atCursor) /*-{
    return this.getSearchCursor(search, atCursor);
  }-*/;
  
  /**
   * Retrieves the current text selection.
   * 
   * @return the current text selection
   */
  public final native String getSelection() /*-{
    return this.selection();
  }-*/;
  
  /**
   * Capture keys.
   * 
   * @param callback the native JavaScript callback function
   * @param filter the filter
   */
  public final native void grabKeys(JavaScriptObject callback, JavaScriptObject filter) /*-{
    this.grabKeys(callback, filter);
  }-*/;
  
  /**
   * Causes this editor to redo the most recent set of changes.
   */
  public final native void redo() /*-{
    this.redo();
  }-*/;
  
  /**
   * Causes this editor instance to apply indentation.
   */
  public final native void reindent() /*-{
    this.reindent();
  }-*/;
  
  /**
   * Causes this editor instance to apply indentation to the current text selection.
   */
  public final native void reindentSelection() /*-{
    this.reindentSelection();
  }-*/;
  
  /**
   * Removes the editor from the parent container.
   */
  public final native void remove() /*-{
  	this.frame.parentNode.removeChild(this.frame);
  }-*/;
  
  /**
   * Replaces the current text selection.
   * 
   * @param text the replacement text
   */
  public final native void replaceSelection(String text) /*-{
    this.replaceSelection(text);
  }-*/;
  
  /**
   * Sets the text editor contents.
   * 
   * @param code the text editor contents
   */
  public final native void setCode(String code) /*-{
    this.setCode(code);
  }-*/;
  
  /**
   * Sets the show line numbers setting.
   * 
   * @param on whether to display line numbers
   */
  public final native void setLineNumbers(boolean on) /*-{
    this.setLineNumbers(on);
  }-*/;
  
  /**
   * Set the parser name.
   * 
   * @param name the parser name
   */
  public final native void setParser(String name, JavaScriptObject parserConfig) /*-{
    this.setParser(name, parserConfig);
  }-*/;
  
  /**
   * Sets the spellcheck setting.
   * 
   * @param on whether to use the spellcheck
   */
  public final native void setSpellcheck(boolean on) /*-{
    this.setSpellcheck(on);
  }-*/;
  
  /**
   * Sets the text wrapping setting.
   * 
   * @param on whether to wrap text
   */
  public final native void setTextWrapping(boolean on) /*-{
    this.setTextWrapping(on);
  }-*/;
  
  /**
   * Causes this editor to undo the most recent set of changes.
   */
  public final native void undo() /*-{
    this.undo();
  }-*/;
  
  /**
   * Uncapture keys.
   */
  public final native void ungrabKeys() /*-{
    this.ungrabKeys();
  }-*/;
  
}
