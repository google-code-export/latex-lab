package org.latexlab.docs.client.widgets;

import org.latexlab.docs.client.events.IntRunnable;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

public class CodeMirrorOptions extends JavaScriptObject {

  public static final String TAB_MODE_SPACES = "spaces";
  public static final String TAB_MODE_DEFAULT = "default";
  public static final String TAB_MODE_SHIFT = "shift";
	
  public static CodeMirrorOptions newInstance() {
    return JavaScriptObject.createObject().cast();
  }
  
  protected CodeMirrorOptions() {}
  
  public final native void setInitCallback(Runnable initCallback) /*-{
  	this.initCallback = function() {
  		initCallback.@java.lang.Runnable::run()();
  	};
  }-*/;
  
  public final native void setFocusCallback(Runnable focusCallback) /*-{
  	this.focusCallback = function() {
  		focusCallback.@java.lang.Runnable::run()();
  	};
  }-*/;
  
  public final native void setClickCallback(Runnable clickCallback) /*-{
  	this.clickCallback = function() {
  		clickCallback.@java.lang.Runnable::run()();
  	};
  }-*/;
  
  public final native void setChangeCallback(Runnable onChange) /*-{
  	this.onChange = function() {
  		onChange.@java.lang.Runnable::run()();
  	};
  }-*/;
  
  public final native void setControlCallback(IntRunnable controlFunction) /*-{
  	this.controlFunction = function(k) {
  		controlFunction.@org.latexlab.docs.client.events.IntRunnable::run(I)(k);
  	};
  }-*/;
  
  public final native void setStylesheet(String stylesheet) /*-{
  	this.stylesheet = stylesheet;
  }-*/;
  
  public final native void setPath(String path) /*-{
  	this.path = path;
  }-*/;
  
  public final native void setParserFile(JsArrayString parserFile) /*-{
  	this.parserfile = parserFile;
  }-*/;
  
  public final native void setBaseFiles(JsArrayString baseFiles) /*-{
  	this.basefiles = baseFiles;
  }-*/;
  
  public final native void setIFrameClass(String iframeClass) /*-{
  	this.iframeClass = iframeClass;
  }-*/;
  
  public final native void setPassDelay(int passDelay) /*-{
  	this.passDelay = passDelay;
  }-*/;
  
  public final native void setPassTime(int passTime) /*-{
  	this.passTime = passTime;
  }-*/;

  public final native void setLineNumberDelay(int lineNumberDelay) /*-{
  	this.lineNumberDelay = lineNumberDelay;
  }-*/;
  
  public final native void setLineNumberTime(int lineNumberTime) /*-{
  	this.lineNumberTime = lineNumberTime;
  }-*/;
  
  public final native void setContinuousScanning(boolean continuousScanning) /*-{
  	this.continuousScanning = continuousScanning;
  }-*/;
  
  public final native void setSaveFunction(Runnable saveFunction) /*-{
  	this.saveFunction = function() {
  		saveFunction.@java.lang.Runnable::run()();
  	};
  }-*/;

  public final native void setUndoDepth(int undoDepth) /*-{
  	this.undoDepth = undoDepth;
  }-*/;
  
  public final native void setUndoDelay(int undoDelay) /*-{
  	this.undoDelay = undoDelay;
  }-*/;
  
  public final native void setDisableSpellcheck(boolean disableSpellcheck) /*-{
  	this.disableSpellcheck = disableSpellcheck;
  }-*/;
  
  public final native void setTextWrapping(boolean textWrapping) /*-{
  	this.textWrapping = textWrapping;
  }-*/;
  
  public final native void setReadOnly(boolean readOnly) /*-{
  	this.readOnly = readOnly;
  }-*/;
  
  public final native void setWidth(String width) /*-{
  	this.width = width;
  }-*/;
  
  public final native void setHeight(String height) /*-{
  	this.height = height;
  }-*/;
  
  public final native void setAutoMatchParens(boolean autoMatchParens) /*-{
  	this.autoMatchParens = autoMatchParens;
  }-*/;
  
  public final native void setParserConfig(JavaScriptObject parserConfig) /*-{
  	this.parserConfig = parserConfig;
  }-*/;
  
  public final native void setTabMode(String tabMode) /*-{
  	this.tabMode = tabMode;
  }-*/;
  
  public final native void setReindentOnLoad(boolean reindentOnLoad) /*-{
  	this.reindentOnLoad = reindentOnLoad;
  }-*/;
  
  public final native void setActiveTokens(JavaScriptObject activeTokens) /*-{
  	this.activeTokens = activeTokens;
  }-*/;
  
  public final native void setCursorActivity(Runnable cursorActivity) /*-{
  	this.cursorActivity = function() {
  		cursorActivity.@java.lang.Runnable::run()();
  	};
  }-*/;
  
  public final native void setLineNumbers(boolean lineNumbers) /*-{
  	this.lineNumbers = lineNumbers;
  }-*/;
  
  public final native void setIndentUnit(int indentUnit) /*-{
  	this.indentUnit = indentUnit;
  }-*/;
  
}
