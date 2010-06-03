package org.latexlab.docs.client.commands;

/**
 * A command for refreshing the current document's metadata.
 */
public class CurrentDocumentRefreshCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 113;
  
  protected Command continueCommand;
  protected boolean executeInBackground = true;
  
  /**
   * Constructs a command for refreshing the current document's metadata.
   * 
   * @param executeInBackground whether to execute the refresh in the background.
   * @param continueCommand the command to execute after document refresh.
   */
  public CurrentDocumentRefreshCommand(boolean executeInBackground,
	    Command continueCommand) {
	super("Refresh current document.");
	this.executeInBackground = executeInBackground;
	this.continueCommand = continueCommand;
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
   * Retrieves the command to execute after document refresh.
   * 
   * @return the command to execute after document refresh.
   */
  public Command getContinueCommand() {
    return continueCommand;
  }

  /**
   * Whether to execute the refresh in the background.
   * 
   * @return whether to execute the refresh in the background.
   */
  public boolean isExecuteInBackground() {
    return executeInBackground;
  }

  /**
   * Sets the command to execute after document refresh.
   * 
   * @param continueCommand the command to execute after document refresh.
   */
  public void setContinueCommand(Command continueCommand) {
    this.continueCommand = continueCommand;
  }

  /**
   * Specifies whether to execute the refresh in the background.
   * 
   * @param executeInBackground whether to execute the refresh in the background.
   */
  public void setExecuteInBackground(boolean executeInBackground) {
    this.executeInBackground = executeInBackground;
  }
 
}
