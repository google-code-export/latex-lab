package org.latexlab.docs.client.commands;

/**
 * A command for pasting text into the text editor.
 */
public class SystemPasteCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 83;
  
  protected String text;
  
  /**
   * Constructs a command for pasting text into the text editor.
   * 
   * @param text the text to paste.
   */
  public SystemPasteCommand(String text) {
	super("Paste text.");
	this.text = text;
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
   * Retrieves the text to paste.
   * 
   * @return the text to paste.
   */
  public String getText() {
	return text;
  }

  /**
   * Specifies the text to paste.
   * 
   * @param text the text to paste.
   */
  public void setText(String text) {
	this.text = text;
  }

}
