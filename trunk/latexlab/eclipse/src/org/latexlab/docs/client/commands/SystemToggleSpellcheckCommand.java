package org.latexlab.docs.client.commands;

/**
 * A command for toggling the spellchecker.
 */
public class SystemToggleSpellcheckCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 111;
  
  /**
   * Constructs a command for toggling the spellchecker.
   */
  public SystemToggleSpellcheckCommand() {
	super("Update \"Use spellchecker\" setting.");
  }

  /**
   * Retrieves the unique Command Id.
   * 
   * @return the unique Command Id
   */
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
