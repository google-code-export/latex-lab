package org.latexlab.docs.client.commands;

import org.latexlab.docs.client.gdocs.DocumentServiceEntry;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * A command for listing documents in the File dialog.
 */
public class SystemListDocumentsCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 34;
  
  protected AsyncCallback<DocumentServiceEntry[]> callback;
  protected boolean useCache = true;
  
  /**
   * Constructs a command for listing documents in the File dialog.
   * 
   * @param useCache whether to use a document cache, when available.
   * @param callback the callback to invoke when documents are available
   */
  public SystemListDocumentsCommand(boolean useCache,
	  AsyncCallback<DocumentServiceEntry[]> callback) {
    super("List documents.");
    this.callback = callback;
    this.useCache = useCache;
  }

  /**
   * Retrieves the command's unique id.
   * 
   * @return the command's unique id.
   */
  @Override
  public int getCommandId() {
    return serialUid;
  }

  /**
   * Whether to use a document cache, when available.
   * 
   * @return whether to use a document cache, when available.
   */
  public boolean isUseCache() {
    return useCache;
  }

  /**
   * Specifies whether to use a document cache, when available.
   * 
   * @param useCache whether to use a document cache, when available.
   */
  public void setUseCache(boolean useCache) {
    this.useCache = useCache;
  }
  
  /**
   * Retrieves the callback to invoke when documents are available.
   * 
   * @return the callback to invoke when documents are available
   */
  public AsyncCallback<DocumentServiceEntry[]> getCallback() {
   return callback;
  }

  /**
   * Sets the callback to invoke when documents are available.
   * 
   * @param callback the callback to invoke when documents are available
   */
  public void setCallback(AsyncCallback<DocumentServiceEntry[]> callback) {
    this.callback = callback;
  }
  
}
