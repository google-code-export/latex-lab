package org.latexlab.clsi.client;

import com.google.gwt.core.client.JavaScriptObject;

public class ClsiServiceCompileResponse extends JavaScriptObject {

  protected ClsiServiceCompileResponse() { }
  
  public final native String getId() /*-{
  	return this.id;
  }-*/;

  public final native String getInstance() /*-{
  	return this.instance;
  }-*/;
  
  public final native double getTime() /*-{
  	return this.time;
  }-*/;
  
  public final native ClsiOutputError[] getOutputErrors() /*-{
    return @org.latexlab.clsi.client.ArrayHelper::toArray(Lcom/google/gwt/core/client/JsArray;)(this.outputErrors);
  }-*/;
  
  public final native ClsiOutputFile[] getOutputFiles() /*-{
    return @org.latexlab.clsi.client.ArrayHelper::toArray(Lcom/google/gwt/core/client/JsArray;)(this.outputFiles);
  }-*/;
  
  public final native ClsiOutputLog[] getOutputLogs() /*-{
    return @org.latexlab.clsi.client.ArrayHelper::toArray(Lcom/google/gwt/core/client/JsArray;)(this.outputLogs);
  }-*/;
  
  public final native String getStatus() /*-{
  	return this.status;
  }-*/;
  
}
