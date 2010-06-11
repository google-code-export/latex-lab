package org.latexlab.docs.client.commands;

/**
 * A command for applying preferences.
 */
public class SystemApplyPreferencesCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 169;
  
  protected int autoSaveIntervalMillisecs;
  protected boolean useAutoSave;
  
  /**
   * Constructs a command for applying preferences.
   * 
   * @param useAutoSave whether to automatically save the document.
   * @param autoSaveIntervalMillisecs the auto-save interval, in milliseconds.
   */
  public SystemApplyPreferencesCommand(boolean useAutoSave,
	    int autoSaveIntervalMillisecs) {
	super("Apply preferences.");
    this.useAutoSave = useAutoSave;
    this.autoSaveIntervalMillisecs = autoSaveIntervalMillisecs;
  }

  /**
   * Retrieves the auto-save interval, in milliseconds.
   * 
   * @return the auto-save interval, in milliseconds.
   */
  public int getAutoSaveIntervalMillisecs() {
    return autoSaveIntervalMillisecs;
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
   * Whether to automatically save the document.
   * 
   * @return whether to automatically save the document.
   */
  public boolean isUseAutoSave() {
    return useAutoSave;
  }

  /**
   * Sets the auto-save interval, in milliseconds.
   * 
   * @param autoSaveIntervalMillisecs the auto-save interval, in milliseconds.
   */
  public void setAutoSaveIntervalMillisecs(int autoSaveIntervalMillisecs) {
    this.autoSaveIntervalMillisecs = autoSaveIntervalMillisecs;
  }

  /**
   * Specifies whether to automatically save the document.
   * 
   * @param useAutoSave whether to automatically save the document.
   */
  public void setUseAutoSave(boolean useAutoSave) {
    this.useAutoSave = useAutoSave;
  }

}
