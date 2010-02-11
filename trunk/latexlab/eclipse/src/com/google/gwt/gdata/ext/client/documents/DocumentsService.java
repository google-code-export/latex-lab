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

import com.google.gwt.accounts.client.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.gdata.client.GDataRequestParameters;
import com.google.gwt.gdata.client.GoogleService;
import com.google.gwt.gdata.client.impl.CallErrorException;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Google Documents service.
 */
public class DocumentsService extends GoogleService {

  private static final DocumentsServiceProxyAsync serviceProxy =
    GWT.create(DocumentsServiceProxy.class);
  private static final String DOCS_SCOPE = "https://docs.google.com/feeds/";
  
  /**
   * Constructs a DocumentsService object.
   * 
   * @param applicationName Name of application (used for tracking).
   * @return A DocumentsService object.
   */
  public static native DocumentsService newInstance(
      String applicationName) /*-{
    return new $wnd.google.gdata.client.GoogleService(applicationName);
  }-*/;

  protected DocumentsService() { }

  /**
   * Deletes a document entry.
   * 
   * @param uri URI of entry.
   * @param callback Callback defining success and failure handlers for this
   * command.
   */
  public final void deleteDocumentEntry(String uri,
      DocumentEntryCallback callback,
      GDataRequestParameters parameters) {
    this.deleteEntry(uri, callback, parameters);
  }

  /**
   * Retrieves a document entry.
   * 
   * @param uri URI of entry.
   * @param callback Callback defining success and failure handlers for this
   * command.
   */
  public final void getDocumentEntry(String uri,
      DocumentEntryCallback callback) {
    this.getEntry(uri, callback, DocumentEntry.getConstructor(), true, null);
  }
  
  /**
   * Retrieves a document entry.
   * 
   * @param uri URI of entry.
   * @param callback Callback defining success and failure handlers for this
   * command.
   * @param parameters The request parameters.
   */
  public final void getDocumentEntry(String uri,
      DocumentEntryCallback callback, GDataRequestParameters parameters) {
    this.getEntry(uri, callback, DocumentEntry.getConstructor(), true,
        parameters);
  }

  /**
   * Retrieves the feed of documents.
   * 
   * @param uri URI of feed or query.
   * @param callback Callback defining success and failure handlers for this
   * command.
   */
  public final void getDocumentFeed(String uri,
      DocumentFeedCallback callback) {
    this.getFeed(uri, callback, DocumentFeed.getConstructor(), true, null);
  }
  
  /**
   * Retrieves the feed of documents.
   * 
   * @param uri URI of feed or query.
   * @param callback Callback defining success and failure handlers for this
   * command.
   * @param parameters The request parameters.
   */
  public final void getDocumentFeed(String uri,
      DocumentFeedCallback callback, GDataRequestParameters parameters) {
    this.getFeed(uri, callback, DocumentFeed.getConstructor(), true, parameters);
  }


  /**
   * Inserts a new document entry.
   * 
   * @param uri URI of feed.
   * @param entry Entry to insert.
   * @param callback Callback defining success and failure handlers for this
   * command.
   */
  public final void insertDocumentEntry(String uri, DocumentEntry entry,
      DocumentEntryCallback callback) {
    this.insertEntry(uri, entry, callback, DocumentEntry.getConstructor());
  }
  
  /**
   * Updates a document entry.
   * 
   * @param uri URI of entry.
   * @param entry Entry to update.
   * @param callback Callback defining success and failure handlers for this
   * command.
   */
  public final void updateDocumentEntry(String uri, DocumentEntry entry,
      DocumentEntryCallback callback) {
    this.updateEntry(uri, entry, callback,
        DocumentEntry.getConstructor(), null);
  }
  
  /**
   * Updates a document entry.
   * 
   * @param uri URI of entry.
   * @param entry Entry to update.
   * @param callback Callback defining success and failure handlers for this
   * command.
   * @param parameters The request parameters.
   */
  public final void updateDocumentEntry(String uri, DocumentEntry entry,
      DocumentEntryCallback callback, GDataRequestParameters parameters) {
    this.updateEntry(uri, entry, callback, DocumentEntry.getConstructor(),
        parameters);
  }

  /**
   * Retrieves a document's contents.
   * 
   * @param documentId The document id.
   * @param callback Callback defining success and failure handlers for this
   * command.
   */
  public final void getDocumentContents(String documentId,
      final DocumentContentsCallback callback) {
    getDocumentContents(documentId, callback, null); 
  }

  /**
   * Retrieves a document's contents.
   * 
   * @param documentId The document id.
   * @param callback Callback defining success and failure handlers for this
   * command.
   * @param parameters The request parameters.
   */
  public final void getDocumentContents(String documentId,
      final DocumentContentsCallback callback,
      GDataRequestParameters parameters) {
    String token = User.checkLogin(DOCS_SCOPE);
    serviceProxy.getDocumentContents("gwt-gdata-ext", token, documentId,
        new AsyncCallback<String>() {
          public void onFailure(Throwable caught) {
            callback.onFailure(new CallErrorException(caught.getMessage()));
          }
          public void onSuccess(String result) {
            DocumentContents contents = DocumentContents.newInstance();
            contents.setText(result);
            contents.setType("text");
            callback.onSuccess(contents);
          }
    });
  }

  /**
   * Sets the document's contents.
   * 
   * @param documentId The document id.
   * @param contents The document contents.
   * @param callback Callback defining success and failure handlers for this
   * command.
   */
  public final void setDocumentContents(String documentId,
      final String contents, final DocumentContentsCallback callback) {
    setDocumentContents(documentId, contents, callback, null);
  }
  
  /**
   * Sets the document's contents.
   * 
   * @param documentId The document id.
   * @param contents The document contents.
   * @param callback Callback defining success and failure handlers for this
   * command.
   * @param parameters The request parameters.
   */
  public final void setDocumentContents(String documentId,
      final String contents, final DocumentContentsCallback callback,
      GDataRequestParameters parameters) {
    String token = User.checkLogin(DOCS_SCOPE);
    String etag = null;
    if (parameters != null) {
      etag = parameters.getEtag();
    }
    serviceProxy.setDocumentContents("gwt-gdata-ext", token, documentId, etag, contents,
        new AsyncCallback<Boolean>() {
          public void onFailure(Throwable caught) {
            callback.onFailure(new CallErrorException(caught.getMessage()));
          }
          public void onSuccess(Boolean result) {
            DocumentContents dc = DocumentContents.newInstance();
            dc.setText(contents);
            dc.setType("text");
            callback.onSuccess(dc);
          }
    });
  }
  
}