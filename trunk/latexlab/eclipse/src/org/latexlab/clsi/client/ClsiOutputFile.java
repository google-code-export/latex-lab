package org.latexlab.clsi.client;

import com.google.gwt.core.client.JavaScriptObject;

public class ClsiOutputFile extends JavaScriptObject{

  protected ClsiOutputFile() { }
  
  public final native String getMimeType() /*-{
  	return this.mimeType;
  }-*/;
  
  public final native String getType() /*-{
  	return this.type;
  }-*/;
  
  public final native String getUrl() /*-{
  	return this.url;
  }-*/;
  
}

