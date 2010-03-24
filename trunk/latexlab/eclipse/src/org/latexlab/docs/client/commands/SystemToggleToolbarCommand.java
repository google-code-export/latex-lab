package org.latexlab.docs.client.commands;

public class SystemToggleToolbarCommand extends Command {

  public final static int serialUid = 84;
  
  private String name;
  
  public SystemToggleToolbarCommand(String name) {
	super("Toggle toolbar.");
	this.name = name;
  }

  public String getName() {
	return name;
  }
  
  public void setName(String name) {
	this.name = name;
  }

  @Override
  public int getCommandId() {
    return serialUid;
  }

}
