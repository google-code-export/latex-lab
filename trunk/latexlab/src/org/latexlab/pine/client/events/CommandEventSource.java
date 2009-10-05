package org.latexlab.pine.client.events;

import com.google.gwt.user.client.ui.Composite;

import java.util.ArrayList;

/**
 * A base implementation of a Command event source.
 */
public class CommandEventSource extends Composite {
  
  protected ArrayList<CommandEventListener> listeners = new ArrayList<CommandEventListener>();
  
  /**
   * Registers a command event listener.
   * 
   * @param listener the command event listener to add
   */
  public void addCommandEventListener(CommandEventListener listener) {
    listeners.add(listener);
  }
  
  /**
   * Removes a command event listener.
   * @param listener the command event listener to remove
   */
  public void removeCommandEventListener(CommandEventListener listener) {
    listeners.remove(listener);
  }
  
  /**
   * Fires a command event.
   * 
   * @param e the command event to fire
   */
  protected void fireOnCommand(CommandEvent e) {
    e.newAttempt();
    for (int i=0; i<listeners.size(); i++) {
      listeners.get(i).onCommand(e);
    }
  }
}
