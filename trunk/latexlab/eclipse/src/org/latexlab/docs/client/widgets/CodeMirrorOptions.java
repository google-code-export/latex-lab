package org.latexlab.docs.client.widgets;

import org.latexlab.docs.client.events.IntRunnable;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

/**
 * An overlay type exposing the CodeMirror options.
 */
public class CodeMirrorOptions extends JavaScriptObject {

  /**
   * Defines the default tab mode option.
   */
  public static final String TAB_MODE_DEFAULT = "default";
  /**
   * Defines the shift tab mode option.
   */
  public static final String TAB_MODE_SHIFT = "shift";
  /**
   * Defines the spaces tab mode option.
   */
  public static final String TAB_MODE_SPACES = "spaces";
	
  /**
   * Creates a new CodeMirror options instance.
   * 
   * @return a new CodeMirror options instance.
   */
  public static CodeMirrorOptions newInstance() {
    return JavaScriptObject.createObject().cast();
  }
  
  protected CodeMirrorOptions() {}
  
  /**
   * Sets whether to auto-match parentheses.
   * 
   * @param autoMatchParens whether to auto-match parentheses.
   */
  public final native void setAutoMatchParens(boolean autoMatchParens) /*-{
  	this.autoMatchParens = autoMatchParens;
  }-*/;
  
  /**
   * Sets the base files to use with CodeMirror.
   * 
   * @param baseFiles the base files
   */
  public final native void setBaseFiles(JsArrayString baseFiles) /*-{
  	this.basefiles = baseFiles;
  }-*/;
  
  /**
   * Sets the callback to invoke when contents change.
   * 
   * @param changeCallback the change callback
   */
  public final native void setChangeCallback(Runnable changeCallback) /*-{
  	this.onChange = function() {
  		changeCallback.@java.lang.Runnable::run()();
  	};
  }-*/;
  
  /**
   * Sets the callback to invoke when a click event occurs.
   * 
   * @param clickCallback the click callback
   */
  public final native void setClickCallback(Runnable clickCallback) /*-{
  	this.clickCallback = function() {
  		clickCallback.@java.lang.Runnable::run()();
  	};
  }-*/;
  
  /**
   * Sets whether to use continuous scanning.
   * 
   * @param continuousScanning whether to use continuous scanning
   */
  public final native void setContinuousScanning(boolean continuousScanning) /*-{
  	this.continuousScanning = continuousScanning;
  }-*/;
  
  /**
   * Sets the callback to invoke when a key is pressed along with the control key.
   * 
   * @param controlCallback the focus callback
   */
  public final native void setControlCallback(IntRunnable controlCallback) /*-{
  	this.controlFunction = function(k) {
  		controlCallback.@org.latexlab.docs.client.events.IntRunnable::run(I)(k);
  	};
  }-*/;
  
  /**
   * Sets the callback to invoke when the text cursor changes position.
   * 
   * @param cursorActivityCallback the cursor activity callback
   */
  public final native void setCursorActivity(Runnable cursorActivityCallback) /*-{
  	this.cursorActivity = function() {
  		cursorActivityCallback.@java.lang.Runnable::run()();
  	};
  }-*/;
  
  /**
   * Sets whether to disable the spellchecker.
   * 
   * @param disableSpellcheck whether to disable the spellchecker.
   */
  public final native void setDisableSpellcheck(boolean disableSpellcheck) /*-{
  	this.disableSpellcheck = disableSpellcheck;
  }-*/;
  
  /**
   * Sets the callback to invoke when the editor gains focus.
   * 
   * @param focusCallback the focus callback
   */
  public final native void setFocusCallback(Runnable focusCallback) /*-{
  	this.focusCallback = function() {
  		focusCallback.@java.lang.Runnable::run()();
  	};
  }-*/;
  
  /**
   * Sets the editor's height.
   * 
   * @param height the editor's height
   */
  public final native void setHeight(String height) /*-{
  	this.height = height;
  }-*/;
  
  /**
   * Sets the CodeMirror IFrame's css class.
   * 
   * @param iframeClass the css class
   */
  public final native void setIFrameClass(String iframeClass) /*-{
  	this.iframeClass = iframeClass;
  }-*/;

  /**
   * Sets the indentation unit, in characters.
   * 
   * @param indentUnit the indentation unit, in characters
   */
  public final native void setIndentUnit(int indentUnit) /*-{
  	this.indentUnit = indentUnit;
  }-*/;
  
  /**
   * Sets the callback to invoke when the editor completes initialization.
   * 
   * @param focusCallback the initialization callback
   */
  public final native void setInitCallback(Runnable initCallback) /*-{
  	this.initCallback = function() {
  		initCallback.@java.lang.Runnable::run()();
  	};
  }-*/;
  
  /**
   * Sets the delay length, in milliseconds, for updating the line numbers.
   * 
   * @param lineNumberDelay the line number delay.
   */
  public final native void setLineNumberDelay(int lineNumberDelay) /*-{
  	this.lineNumberDelay = lineNumberDelay;
  }-*/;
  
  /**
   * Sets whether to display line numbers.
   * 
   * @param lineNumbers whether to display line numbers.
   */
  public final native void setLineNumbers(boolean lineNumbers) /*-{
  	this.lineNumbers = lineNumbers;
  }-*/;

  /**
   * Sets the line number time.
   * 
   * @param lineNumberTime the line number time
   */
  public final native void setLineNumberTime(int lineNumberTime) /*-{
  	this.lineNumberTime = lineNumberTime;
  }-*/;
  
  /**
   * Sets the parser configuration options object.
   * 
   * @param parserConfig the parser configuration options object
   */
  public final native void setParserConfig(JavaScriptObject parserConfig) /*-{
  	this.parserConfig = parserConfig;
  }-*/;
  
  /**
   * Sets the parser file names.
   * 
   * @param parserFile the parser file names
   */
  public final native void setParserFile(JsArrayString parserFile) /*-{
  	this.parserfile = parserFile;
  }-*/;
  
  /**
   * Sets the delay between passes.
   * 
   * @param passDelay the delay between passes
   */
  public final native void setPassDelay(int passDelay) /*-{
  	this.passDelay = passDelay;
  }-*/;
  
  /**
   * Sets the pass time.
   * 
   * @param passTime the pass time
   */
  public final native void setPassTime(int passTime) /*-{
  	this.passTime = passTime;
  }-*/;
  
  /**
   * Sets the root path.
   * 
   * @param path the root path
   */
  public final native void setPath(String path) /*-{
  	this.path = path;
  }-*/;
  
  /**
   * Sets whether to use read-only mode.
   * 
   * @param readOnly
   */
  public final native void setReadOnly(boolean readOnly) /*-{
  	this.readOnly = readOnly;
  }-*/;
  
  /**
   * Sets whether to reindent on-load.
   * 
   * @param reindentOnLoad whether to reindent on-load
   */
  public final native void setReindentOnLoad(boolean reindentOnLoad) /*-{
  	this.reindentOnLoad = reindentOnLoad;
  }-*/;
  
  /**
   * Sets the save callback.
   * 
   * @param saveFunction the save callback
   */
  public final native void setSaveFunction(Runnable saveFunction) /*-{
  	this.saveFunction = function() {
  		saveFunction.@java.lang.Runnable::run()();
  	};
  }-*/;
  
  /**
   * Sets the CodeMirror stylesheet.
   * 
   * @param stylesheet the CodeMirror stylesheet
   */
  public final native void setStylesheet(String stylesheet) /*-{
  	this.stylesheet = stylesheet;
  }-*/;
  
  /**
   * Sets the tab mode.
   * 
   * @param tabMode the tab mode
   */
  public final native void setTabMode(String tabMode) /*-{
  	this.tabMode = tabMode;
  }-*/;
  
  /**
   * Sets whether to use text wrapping.
   * 
   * @param textWrapping whether to use text wrapping.
   */
  public final native void setTextWrapping(boolean textWrapping) /*-{
  	this.textWrapping = textWrapping;
  }-*/;
  
  /**
   * Sets the undo delay.
   * 
   * @param undoDelay the undo delay
   */
  public final native void setUndoDelay(int undoDelay) /*-{
  	this.undoDelay = undoDelay;
  }-*/;
  
  /**
   * Sets the undo depth
   * 
   * @param undoDepth the undo depth
   */
  public final native void setUndoDepth(int undoDepth) /*-{
  	this.undoDepth = undoDepth;
  }-*/;
  
  /**
   * Sets the editor's width.
   * 
   * @param width the editor's width
   */
  public final native void setWidth(String width) /*-{
  	this.width = width;
  }-*/;
  
}
