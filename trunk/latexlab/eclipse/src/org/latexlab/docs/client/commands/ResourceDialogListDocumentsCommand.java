package org.latexlab.docs.client.commands;

public class ResourceDialogListDocumentsCommand extends Command {

  public final static int serialUid = 85;

  private boolean useCache = true;
  
  public ResourceDialogListDocumentsCommand(boolean useCache) {
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
