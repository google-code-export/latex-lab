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

/**
 * Describes a feed of a user's documents.
 */
public class DocumentFeed extends com.google.gwt.gdata.client.Feed<DocumentFeed, DocumentEntry> {

  /**
   * Constructs a document feed.
   * @return A DocumentFeed object.
   */
  @SuppressWarnings("unchecked")
  public static native DocumentFeed newInstance() /*-{
    return new $wnd.google.gdata.Feed();
  }-*/;

  protected DocumentFeed() { }

  /**
   * Returns the JavaScript constructor for this class.
   * @return The JavaScript constructor.
   */
  public static final native JavaScriptObject getConstructor() /*-{
    return $wnd.google.gdata.Feed;
  }-*/;
  
  /**
   * Returns the link that provides the URI of next page in a paged feed.
   * 
   * @return Link that provides the URI of next page in a paged feed.
   */
  public final native DocumentLink getNextLink() /*-{
    return this.getNextLink();
  }-*/;

  /**
   * Returns the link that provides the URI of previous page in a paged feed.
   * 
   * @return Link that provides the URI of previous page in a paged feed.
   */
  public final native DocumentLink getPreviousLink() /*-{
    return this.getPreviousLink();
  }-*/;

  /**
   * Returns the current representation of the feed by requesting it from the
   * associated service using the feeds self link.
   * 
   * @param callback Callback defining success and failure handlers for this
   * command.
   */
  public final void getSelf(DocumentFeedCallback callback) {
    this.getSelf(callback);
  }

  /**
   * Inserts a new document entry into the feed.
   * 
   * @param entry Entry to insert.
   * @param callback Callback defining success and failure handlers for this
   * command.
   */
  public final void insertDocumentEntry(DocumentEntry entry,
      DocumentEntryCallback callback) {
    this.insertEntry(entry, callback);
  }

}