package org.latexlab.pine.client.events;

import java.util.EventListener;

/**
 * Defines the command event listener interface.
 */
public interface CommandEventListener extends EventListener {
  
  /**
   * Fired whenever a command event is received.
   * @param e
   */
  void onCommand(CommandEvent e);
}
