package org.latexlab.docs.client.commands;

public class SystemPasteCommand extends Command {

  public final static int serialUid = 83;
  
  private String text;
  
  public SystemPasteCommand(String text) {
	super("Paste text.");
	this.text = text;
  }

  public String getText() {
	return text;
  }
  
  public void setText(String text) {
	this.text = text;
  }

  @Override
  public int getCommandId() {
    return serialUid;
  }

}
