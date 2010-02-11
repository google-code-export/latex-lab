package com.google.gwt.gdata.ext.client.documents;

import com.google.gwt.gdata.client.atom.Text;

/**
 * Describes document contents.
 */
public class DocumentContents extends Text {

  /**
   * Constructs a document contents object.
   * @return A DocumentContents object.
   */
  public static native DocumentContents newInstance() /*-{
    return new $wnd.google.gdata.atom.Text();
  }-*/;
  
  protected DocumentContents() { }
  
}
