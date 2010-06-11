package org.latexlab.docs.client.commands;

/**
 * A command for opening a new browser window.
 */
public class SystemOpenPageCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 105;
  
  protected String name, url;
  protected boolean useCurrentWindow = false;
  
  /**
   * Constructs a command for opening a new browser window.
   * @param name the window name.
   * @param url the page url.
   * @param useCurrentWindow whether to use the current window.
   */
  public SystemOpenPageCommand(String name, String url, boolean useCurrentWindow) {
	super("Open a webpage.");
	this.name = name;
	this.url = url;
	this.useCurrentWindow = useCurrentWindow;
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
   * Retrieves the window name.
   * 
   * @return the window name.
   */
  public String getName() {
    return name;
  }

  /**
   * Retrieves the page url.
   * 
   * @return the page url.
   */
  public String getUrl() {
    return url;
  }

  /**
   * Whether to use the current window.
   * 
   * @return whether to use the current window.
   */
  public boolean isUseCurrentWindow() {
    return useCurrentWindow;
  }

  /**
   * Sets the window name.
   * 
   * @param name the window name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the page url.
   * 
   * @param url the page url.
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * Specifies whether to use the current window.
   * 
   * @param useCurrentWindow whether to use the current window.
   */
  public void setUseCurrentWindow(boolean useCurrentWindow) {
    this.useCurrentWindow = useCurrentWindow;
  }

}
