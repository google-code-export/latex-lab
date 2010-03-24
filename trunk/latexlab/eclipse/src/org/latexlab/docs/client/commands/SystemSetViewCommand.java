package org.latexlab.docs.client.commands;

public class SystemSetViewCommand extends Command {

  public final static int serialUid = 106;
  public final static int VIEW_SOURCE = 1;
  public final static int VIEW_PREVIEW = 2;
  public final static int VIEW_SPLIT = 4;
  public final static int VIEW_OUTPUT = 8;
  
  private int view;
  
  public SystemSetViewCommand(int view) {
	super("Open perspective.");
	this.view = view;
  }

  public int getView() {
    return view;
  }

  public void setView(int view) {
    this.view = view;
  }

  @Override
  public int getCommandId() {
    return serialUid;
  }

}
