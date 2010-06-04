package org.latexlab.docs.client.events;

import org.latexlab.docs.client.commands.Command;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * A command event.
 */
public class CommandEvent extends GwtEvent<CommandHandler> {

  /**
   * The event type.
   */
  private static Type<CommandHandler> TYPE;

  /**
   * Fires a command event on all registered handlers in the handler source.
   * 
   * @param <S> The handler source
   * @param source the source of the handlers
   * @param command the command
   */
  public static <S extends HasCommandHandlers & HasHandlers> void fire(S source,
      Command command) {
    if (TYPE != null) {
      CommandEvent event = new CommandEvent(command);
      source.fireEvent(event);
    }
  }
  
  /**
   * Flows a source-less command event to a specific handler.
   * @param handler the command handler
   * @param command the command
   */
  public static void flow(CommandHandler handler,
      Command command) {
    if (TYPE != null) {
      CommandEvent event = new CommandEvent(command);
      handler.onCommand(event);
    }
  }

  /**
   * Ensures the existence of the handler hook and then returns it.
   * 
   * @return returns a handler hook
   */
  public static Type<CommandHandler> getType() {
    if (TYPE == null) {
      TYPE = new Type<CommandHandler>();
    }
    return TYPE;
  }

  private Command command;

  /**
   * Construct a new {@link CommandEvent}.
   * 
   * @param width the new width
   * @param height the new height
   */
  protected CommandEvent(Command command) {
    this.command = command;
  }

  @Override
  protected void dispatch(CommandHandler handler) {
    handler.onCommand(this);
  }
  @Override
  public final Type<CommandHandler> getAssociatedType() {
    return TYPE;
  }

  /**
   * Retrieves the command type.
   * 
   * @return the command type
   */
  public Command getCommand() {
    return command;
  }

  @Override
  public String toDebugString() {
    assertLive();
    return super.toDebugString() + " command = " + command;
  }
}
