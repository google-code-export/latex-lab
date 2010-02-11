/*
 * Copyright 2009 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.gwt.gdata.ext.client.documents;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.gdata.client.atom.Id;

/**
 * Describes a document entry in the feed of a user's documents.
 */
public class DocumentEntry extends com.google.gwt.gdata.client.Entry<DocumentEntry> {

  /**
   * Constructs a document entry.
   * @return A DocumentEntry object.
   */
  public static native DocumentEntry newInstance() /*-{
    return new $wnd.google.gdata.Entry();
  }-*/;

  protected DocumentEntry() { }

  /**
   * Deletes the entry from the feed.
   * 
   * @param callback Callback defining success and failure handlers for this
   * command.
   */
  public final void deleteEntry(DocumentEntryCallback callback) {
    this.deleteEntry(callback);
  }

  /**
   * Returns the JavaScript constructor for this class.
   * @return The JavaScript constructor.
   */
  public static final native JavaScriptObject getConstructor() /*-{
    return $wnd.google.gdata.Entry;
  }-*/;
  
  public final Id getResourceId() {
    String id = this.getId().getValue();
    if (id.lastIndexOf("%3A") >= 0) {
      id = id.substring(id.lastIndexOf("%3A") + 3);
    }
    Id rid = Id.newInstance();
    rid.setValue(id);
    return rid;
  }
  
  /**
   * Returns the link that provides the URI of the full feed (without any query
   * parameters).
   * 
   * @return Link that provides the URI of the full feed (without any query
   * parameters).
   */
  public final native DocumentLink getFeedLink() /*-{
    return this.getFeedLink();
  }-*/;

  /**
   * Returns the link that provides the URI of an alternate format of the
   * entry's or feed's contents.
   * 
   * @return Link that provides the URI of an alternate format of the entry's
   * or feed's contents.
   */
  public final native DocumentLink getHtmlLink() /*-{
    return this.getHtmlLink();
  }-*/;

  /**
   * Returns the current representation of the entry by requesting it from the
   * associated service using the entry's self link.
   * 
   * @param callback Callback defining success and failure handlers for this
   * command.
   * @return current representation of the entry.
   */
  public final DocumentEntry getSelf(DocumentEntryCallback callback) {
    return this.getSelf(callback);
  }

  /**
   * Updated the entry in the feed by sending the representation of this entry.
   * 
   * @param callback Callback defining success and failure handlers for this
   * command.
   */
  public final void updateEntry(DocumentEntryCallback callback) {
    this.updateEntry(callback);
  }
  
}
