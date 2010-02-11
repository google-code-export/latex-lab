package org.latexlab.docs.client.controls;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

public class CodeMirror extends JavaScriptObject {

  protected CodeMirror() { }
  
  public native static CodeMirror newInstance(Element parent, JavaScriptObject pars) /*-{
    return new $wnd.CodeMirror(parent, pars);
  }-*/;
  
  public native static CodeMirror fromTextArea(Element textarea, JavaScriptObject pars) /*-{
    return $wnd.CodeMirror.fromTextArea(textarea, pars);
  }-*/;
  
  public final native String getCode() /*-{
    return this.getCode();
  }-*/;
  
  public final native void setCode(String code) /*-{
    this.setCode(code);
  }-*/;
  
  public final native String getSelection() /*-{
    return this.selection();
  }-*/;
  
  public final native void replaceSelection(String text) /*-{
    this.replaceSelection(text);
  }-*/;
  
  public final native void reindent() /*-{
    this.reindent();
  }-*/;
  
  public final native void reindentSelection() /*-{
    this.reindentSelection();
  }-*/;
  
  public final native JavaScriptObject getSearchCursor(String search, boolean atCursor) /*-{
    return this.getSearchCursor(search, atCursor);
  }-*/;
  
  public final native void undo() /*-{
    this.undo();
  }-*/;
  
  public final native void redo() /*-{
    this.redo();
  }-*/;
  
  public final native JavaScriptObject getHistorySize() /*-{
    return this.historySize();
  }-*/;
  
  public final native void clearHistory() /*-{
    this.clearHistory();
  }-*/;
  
  public final native void grabKeys(JavaScriptObject callback, JavaScriptObject filter) /*-{
    this.grabKeys(callback, filter);
  }-*/;
  
  public final native void ungrabKeys() /*-{
    this.ungrabKeys();
  }-*/;
  
  public final native void setParser(String name) /*-{
    this.setParser(name);
  }-*/;
  
  public final native JavaScriptObject getCursorPosition(boolean start) /*-{
    return this.getCursorPosition(start);
  }-*/;
  
  public final native JavaScriptObject getFirstLine() /*-{
    return this.firstLine();
  }-*/;
  
  public final native JavaScriptObject getLastLine() /*-{
    return this.lastLine();
  }-*/;

  public final native JavaScriptObject getNextLine(JavaScriptObject handle) /*-{
    return this.nextLine(handle);
  }-*/;

  public final native JavaScriptObject getPrevLine(JavaScriptObject handle) /*-{
    return this.prevLine(handle);
  }-*/;

  public final native JavaScriptObject getNthLine(JavaScriptObject handle) /*-{
    return this.nthLine(handle);
  }-*/;

  public final native String getLineContent(JavaScriptObject handle) /*-{
    return this.lineContent(handle);
  }-*/;

  public final native void setLineContent(JavaScriptObject handle) /*-{
    this.setLineContent(handle);
  }-*/;

  public final native int getLineNumber(JavaScriptObject handle) /*-{
    return this.lineNumber(handle);
  }-*/;

  public final native void selectLines(JavaScriptObject startHandle, int startOffset, JavaScriptObject endHandle, int endOffset) /*-{
    this.selectLines(startHandle, startOffset, endHandle, endOffset);
  }-*/;
  
  public final native int insertIntoLine(JavaScriptObject handle, int position, String text) /*-{
    return this.insertIntoLine(handle, position, text);
  }-*/;
  
}
