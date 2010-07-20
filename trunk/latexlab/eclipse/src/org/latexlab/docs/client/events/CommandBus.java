package org.latexlab.docs.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;

public class CommandBus implements HasCommandHandlers {
	
  protected static CommandBus instance;
  
  public static CommandBus get() {
	if (instance == null) {
	  instance = new CommandBus();
	}
	return instance;
  }

  protected HandlerManager manager;
  
  protected CommandBus() {
    manager = new HandlerManager(this);
  }

  /**
   * Adds a command handler.
   * 
   * @param handler the command handler
   */
  @Override
  public HandlerRegistration addCommandHandler(CommandHandler handler) {
	return manager.addHandler(CommandEvent.getType(), handler);
  }

  @Override
  public void fireEvent(GwtEvent<?> event) {
	manager.fireEvent(event);
  }
  
}
