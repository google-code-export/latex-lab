package org.latexlab.docs.client.commands;

/**
 * A command for toggling a given toolbar.
 */
public class SystemToggleToolbarCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 84;
  
  protected String name;
  
  /**
   * Constructs a command for toggling a given toolbar.
   * 
   * @param name the name of the toolbar to toggle.
   */
  public SystemToggleToolbarCommand(String name) {
	super("Toggle toolbar.");
	this.name = name;
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
   * Retrieves the name of the toolbar to toggle.
   * 
   * @return the name of the toolbar to toggle.
   */
  public String getName() {
	return name;
  }

  /**
   * Sets the name of the toolbar to toggle.
   * 
   * @param name the name of the toolbar to toggle.
   */
  public void setName(String name) {
	this.name = name;
  }

}
