package org.latexlab.docs.client.events;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * Defines an interface indicating that the implementing object contains command handlers.
 */
public interface HasCommandHandlers extends HasHandlers {
	
  /**
   * Registers a command event handler.
   * 
   * @param handler the command event handler to add
  */
  HandlerRegistration addCommandHandler(CommandHandler handler);
  
}
