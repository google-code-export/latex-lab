package org.latexlab.docs.client.commands;

/**
 * A command for setting the perspective.
 */
public class SystemSetPerspectiveCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 106;
  
  /**
   * View the compiler output.
   */
  public final static int VIEW_OUTPUT = 8;
  /**
   * View the compiled document preview.
   */
  public final static int VIEW_PREVIEW = 2;
  /**
   * View the source.
   */
  public final static int VIEW_SOURCE = 1;
  /**
   * View the source a preview.
   */
  public final static int VIEW_SPLIT = 4;
  /**
   * View a mixed perspective.
   */
  public final static int VIEW_MIXED = 5;
  
  protected int view;
  
  /**
   * Constructs a command for setting the perspective.
   * 
   * @param view the perspective to set.
   */
  public SystemSetPerspectiveCommand(int view) {
	super("Open perspective.");
	this.view = view;
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
   * Retrieves the perspective to set.
   * 
   * @return the perspective to set.
   */
  public int getView() {
    return view;
  }

  /**
   * Sets the perspective to set.
   * 
   * @param view the perspective to set.
   */
  public void setView(int view) {
    this.view = view;
  }

}
