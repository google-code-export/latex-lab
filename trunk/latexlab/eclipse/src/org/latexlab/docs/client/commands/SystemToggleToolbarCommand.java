package org.latexlab.docs.client.commands;

public class SystemToggleToolbarCommand extends Command {

  public final static int serialUid = 84;
  
  private int index;
  
  public SystemToggleToolbarCommand(int index) {
	super("Toggle toolbar.");
	this.index = index;
  }

  public int getIndex() {
	return index;
  }
  
  public void setIndex(int index) {
	this.index = index;
  }

  @Override
  public int getCommandId() {
    return serialUid;
  }

}
