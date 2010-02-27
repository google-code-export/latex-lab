package org.latexlab.docs.client.commands;

public class FileDialogListDocumentsCommand extends Command {

  public final static int serialUid = 34;
  
  private boolean useCache = true;
  
  public FileDialogListDocumentsCommand(boolean useCache) {
    super("List documents.");
    this.useCache = useCache;
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

  public boolean isUseCache() {
    return useCache;
  }

  public void setUseCache(boolean useCache) {
    this.useCache = useCache;
  }
  
}
