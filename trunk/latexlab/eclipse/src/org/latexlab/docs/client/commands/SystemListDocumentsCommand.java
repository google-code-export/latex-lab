package org.latexlab.docs.client.commands;

/**
 * A command for listing documents in the File dialog.
 */
public class SystemListDocumentsCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 34;
  
  protected boolean useCache = true;
  
  /**
   * Constructs a command for listing documents in the File dialog.
   * 
   * @param useCache whether to use a document cache, when available.
   */
  public SystemListDocumentsCommand(boolean useCache) {
    super("List documents.");
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
  
}
