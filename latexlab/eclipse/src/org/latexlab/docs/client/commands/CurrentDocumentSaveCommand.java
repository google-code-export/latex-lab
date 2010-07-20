package org.latexlab.docs.client.commands;

/**
 * A command for saving the current document.
 */
public class CurrentDocumentSaveCommand extends Command {
	
  /**
   * The command's unique id.
   */
  public final static int serialUid = 7;
  
  protected boolean isScheduled = false;
  
  /**
   * Constructs a command for saving the current document.
   */
  public CurrentDocumentSaveCommand(boolean isScheduled) {
	super("Save current document.");
	this.isScheduled = isScheduled;
  }

  /**
   * Retrieves whether the current save command is schedule based.
   * 
   * @return whether the current save command is schedule based.
   */
  public boolean isScheduled() {
    return isScheduled;
  }

  /**
   * Specifies whether the current save command is schedule based.
   * 
   * @param isScheduled whether the current save command is schedule based.
   */
  public void setScheduled(boolean isScheduled) {
    this.isScheduled = isScheduled;
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

}
