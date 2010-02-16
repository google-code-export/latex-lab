package org.latexlab.clsi.client;

import com.google.gwt.core.client.JavaScriptObject;

public class ClsiOutputError extends JavaScriptObject {

  protected ClsiOutputError() { }
  
  public final native String getMessage() /*-{
  	return this.message;
  }-*/;
  
  public final native String getType() /*-{
  	return this.type;
  }-*/;
  
}
