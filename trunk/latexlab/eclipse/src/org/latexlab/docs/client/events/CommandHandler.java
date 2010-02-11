package org.latexlab.docs.client.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * Defines the command event listener interface.
 */
public interface CommandHandler extends EventHandler {
  
  /**
   * Fired whenever a command event is received.
   * @param e The command event.
   */
  void onCommand(CommandEvent event);
}
