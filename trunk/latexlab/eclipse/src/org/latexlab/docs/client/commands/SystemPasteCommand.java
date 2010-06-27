package org.latexlab.docs.client.commands;

/**
 * A command for pasting text into the text editor.
 */
public class SystemPasteCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 83;
  
  protected String[] preamble;
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
   * Constructs a command for pasting text into the text editor.
   * 
   * @param text the text to paste.
   * @param preamble the preamble lines which the paste text depends on.
   */
  public SystemPasteCommand(String text, String[] preamble) {
	super("Paste text.");
	this.text = text;
	this.preamble = preamble;
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
   * Retrieves the preamble lines for the paste text.
   * 
   * @return the preamble lines for the paste text.
   */
  public String[] getPreamble() {
    return preamble;
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
   * Specifies the preamble lines for the paste text.
   * 
   * @param preamble the preamble lines for the paste text.
   */
  public void setPreamble(String[] preamble) {
    this.preamble = preamble;
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
