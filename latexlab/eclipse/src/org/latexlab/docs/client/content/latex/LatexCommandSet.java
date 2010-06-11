package org.latexlab.docs.client.content.latex;

/**
 * Defines a set of LaTeX commands.
 */
public abstract class LatexCommandSet {

  private String title;
  private LatexCommand[] commands;
  
  /**
   * Constructs a new LaTeX command set.
   * 
   * @param title the command set's title
   * @param commands the set's LaTeX commands
   */
  protected LatexCommandSet(String title, LatexCommand[] commands) {
	this.title = title;
	this.commands = commands;
  }

  /**
   * Retrieves the command set's title.
   * 
   * @return the command set's title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the command set's title.
   * 
   * @param title the command set's title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Retrieves the set's LaTeX commands.
   * 
   * @return the set's LaTeX commands
   */
  public LatexCommand[] getCommands() {
    return commands;
  }

  /**
   * Sets the set's LaTeX commands
   * 
   * @param commands the set's LaTeX commands
   */
  public void setCommands(LatexCommand[] commands) {
    this.commands = commands;
  }
  
}
