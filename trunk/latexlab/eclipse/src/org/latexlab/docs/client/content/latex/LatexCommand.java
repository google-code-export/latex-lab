package org.latexlab.docs.client.content.latex;

import com.google.gwt.user.client.ui.AbstractImagePrototype;

/**
 * Defines a LaTeX command.
 */
public class LatexCommand {

  private AbstractImagePrototype icon;
  private String title, text;
  
  /**
   * Constructs a LaTeX command.
   * 
   * @param icon the command icon
   * @param title the command's title
   * @param text the command's text
   */
  public LatexCommand(AbstractImagePrototype icon, String title, String text) {
	this.icon = icon;
	this.title = title;
	this.text = text;
  }

  /**
   * Retrieves the command's icon.
   * 
   * @return the command's icon
   */
  public AbstractImagePrototype getIcon() {
    return icon;
  }

  /**
   * Sets the command's icon.
   * 
   * @param icon the command's icon
   */
  public void setIcon(AbstractImagePrototype icon) {
    this.icon = icon;
  }

  /**
   * Retrieves the command's title.
   * 
   * @return the command's title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the command's title.
   * 
   * @param title the command's title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Retrieves the command's text.
   * 
   * @return the command's text
   */
  public String getText() {
    return text;
  }

  /**
   * Sets the command's text.
   * 
   * @param text the command's text
   */
  public void setText(String text) {
    this.text = text;
  }
  
}
