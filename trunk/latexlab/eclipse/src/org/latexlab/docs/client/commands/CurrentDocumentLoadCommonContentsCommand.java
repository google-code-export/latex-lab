package org.latexlab.docs.client.commands;

/**
 * Constructs a command for loading preset document contents.
 */
public class CurrentDocumentLoadCommonContentsCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 170;
  
  protected String contentName;
  
  /**
   * Constructs a command for loading preset contents.
   * 
   * @param contentName the name of the content to load.
   */
  public CurrentDocumentLoadCommonContentsCommand(String contentName) {
	super("Load common document contents.");
	this.contentName = contentName;
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
   * Retrieves the name of the content to load.
   * 
   * @return the name of the content to load.
   */
  public String getContentName() {
    return contentName;
  }

  /**
   * Sets the name of the content to load.
   * 
   * @param contentName the name of the content to load.
   */
  public void setContentName(String contentName) {
    this.contentName = contentName;
  }

}
