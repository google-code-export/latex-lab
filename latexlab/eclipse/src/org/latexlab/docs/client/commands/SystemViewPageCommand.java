package org.latexlab.docs.client.commands;

/**
 * A command for displaying a given preview page.
 */
public class SystemViewPageCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 120;
  
  protected int pageNumber = 0;
  
  /**
   * Constructs a command for viewing a given preview page.
   * 
   * @param page the index of the page to display.
   */
  public SystemViewPageCommand(int page) {
	super("View page.");
	this.pageNumber = page;
  }
  
  /**
   * Retrieves the index of the page to display.
   * 
   * @return the index of the page to display.
   */
  public int getPageNumber() {
	return pageNumber;
  }

  /**
   * Sets the index of the page to display.
   * 
   * @param pageNumber the index of the page to display.
   */
  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
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

}
