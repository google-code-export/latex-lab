package org.latexlab.pine.client.events;

import java.util.EventObject;

/**
 * A command event.
 */
public class CommandEvent extends EventObject {

  /**
   * The accepted Command event types.
   */
  public enum Command {
    FILE_DIALOG_LIST_ALL("List all documents."),
    FILE_DIALOG_LIST_STARRED("List starred documents."),
    FILE_DIALOG_STAR_DOCUMENT("Star document."),
    FILE_DIALOG_UNSTAR_DOCUMENT("Unstar document."),
    FILE_DIALOG_OPEN_DOCUMENT("Open document."),
    
    GENERIC_CLOSE_CURRENT_DOCUMENT("Close current document."),
    GENERIC_DELETE_CURRENT_DOCUMENT("Delete current document."),
    GENERIC_SAVE_CURRENT_DOCUMENT("Save current document."),
    GENERIC_SAVE_AND_CLOSE_CURRENT_DOCUMENT("Close current document."),
    GENERIC_SAVE_CURRENT_DOCUMENT_AS_NEW_COPY("Save current document as new copy."),
    GENERIC_RENAME_CURRENT_DOCUMENT("Rename current document."),
    GENERIC_LOAD_CURRENT_DOCUMENT_CONTENTS("Load current document."),
    
    GENERIC_START_NEW_DOCUMENT("Start new document."),
    GENERIC_LOAD_NEW_DOCUMENT("Load new document."),
    GENERIC_OPEN_EXISTING_DOCUMENT("Open existing document."),
    GENERIC_LOAD_EXISTING_DOCUMENT("Load existing document."),
    
    GENERIC_ABOUT("About GDBE."),
    GENERIC_COPY("Copy selected text."),
    GENERIC_CUT("Cut selected text."),
    GENERIC_FULL_SCREEN_VIEW("Display editor in full screen view."),
    GENERIC_PASTE("Paste text."),
    GENERIC_PRINT("Print current document."),
    GENERIC_REDO("Redo change."),
    GENERIC_SELECT_ALL("Select all text."),
    GENERIC_SIGN_OUT("Sign out."),
    GENERIC_UNDO("Undo change."),
    GENERIC_VIEW_AS_WEB_PAGE("View current document as web page,"),
    GENERIC_VIEW_REVISION_HISTORY("View revision history."),
    NONE("Do nothing!");
    
    private final String description;
    
    Command(String description) {
      this.description = description;
    }
    
    /**
     * Retrieves the Command description.
     * @return the command description
     */
    public String getDescription() {
      return this.description;
    }
    
  }
  
  private static final long serialVersionUID = 8510259004785518738L;
  
  private Command command;
  private String[] parameters;
  private int attemptCount = 0;
  
  /**
   * Constructs a command event.
   * 
   * @param source the event source
   * @param command the command type
   */
  public CommandEvent(Object source, Command command) {
    super(source);
    this.command = command;
  }
  
  /**
   * Constructs a command event.
   * 
   * @param source the event source
   * @param command the command type
   * @param parameters the command parameters
   */
  public CommandEvent(Object source, Command command, String[] parameters) {
    super(source);
    this.command = command;
    this.parameters = parameters;
  }

  /**
   * Retrieves the command type.
   * 
   * @return the command type
   */
  public Command getCommand() {
    return command;
  }

  /**
   * Retrieves the command parameters.
   * 
   * @return the command parameters.
   */
  public String[] getParameters() {
    return parameters;
  }

  /** 
   * Retrieves the number of times this command has been attempted.
   * 
   * @return the number of attempts
   */
  public int getAttemptCount() {
    return attemptCount;
  }

  /**
   * Increments the number of times this command has been attempted.
   */
  
  public void newAttempt() {
    this.attemptCount++;
  }

}
