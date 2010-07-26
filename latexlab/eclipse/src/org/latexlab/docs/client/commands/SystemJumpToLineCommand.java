package org.latexlab.docs.client.commands;

/**
 * A command for jumping to a source code line.
 */
public class SystemJumpToLineCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 205;
  
  protected int lineNumber;
  
  /**
   * Constructs a command for jumping to a source code line.
   * 
   * @param linNumber the line number to jump to.
   */
  public SystemJumpToLineCommand(int lineNumber) {
	super("Jump to source code line.");
	this.lineNumber = lineNumber;
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
   * Retrieves the line number to jump to.
   * 
   * @return the line number to jump to.
   */
  public int getLineNumber() {
    return lineNumber;
  }

  /**
   * Sets the line number to jump to.
   * 
   * @param lineNumber the line number to jump to.
   */
  public void setLineNumber(int lineNumber) {
    this.lineNumber = lineNumber;
  }

}
