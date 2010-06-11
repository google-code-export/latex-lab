package org.latexlab.docs.client.commands;

/**
 * The accepted Command event types.
 */
public abstract class Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 0;
	  
  protected int attemptCount;
  protected String description;
  
  /**
   * Constructs a new command.
   * 
   * @param description the command description.
   */
  public Command(String description) {
    this.description = description;
    this.attemptCount = 0;
  }
  
  /** 
   * Retrieves the number of times this command has been attempted.
   * 
   * @return the number of attempts
   */
  public int getAttemptCount() {
    return attemptCount;
  }

  /**
   * Retrieves the command's unique id.
   * 
   * @return
   */
  public abstract int getCommandId();

  /**
   * Retrieves the Command description.
   * 
   * @return the command description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Increments the number of times this command has been attempted.
   */
  public void newAttempt() {
    this.attemptCount++;
  }
}
