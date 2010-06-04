package org.latexlab.docs.client.commands;

import org.latexlab.docs.client.dialogs.Dialog;

/**
 * A command for displaying a given dialog.
 */
public class SystemShowDialogCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 168;
  
  @SuppressWarnings("unchecked")
  protected Class dialogType;
  
  /**
   * Constructs a command for displaying a given dialog.
   * @param <T> the dialog type.
   * @param dialogType the dialog class.
   */
  public <T extends Dialog> SystemShowDialogCommand(Class<T> dialogType) {
	super("Open dialog.");
	this.dialogType = dialogType;
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
   * Retrieves the dialog type.
   * @param <T> the dialog type.
   * @return the dialog class.
   */
  @SuppressWarnings("unchecked")
  public <T extends Dialog> Class<T> getDialogType() {
    return dialogType;
  }

  /**
   * Sets the dialog type.
   * @param <T> the dialog type.
   * @param dialogType the dialog class.
   */
  public <T extends Dialog> void setDialogType(Class<T> dialogType) {
    this.dialogType = dialogType;
  }

}
