package org.latexlab.docs.client.events;

import java.util.HashMap;

import org.latexlab.docs.client.commands.Command;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;

/**
 * Schedules and manages distinct or repeating events.
 */
public class Scheduler implements HasCommandHandlers {

  private HandlerManager manager;
  private HashMap<String, Timer> timers;
  
  /**
   * Constructs a scheduler.
   */
  public Scheduler() {
	manager = new HandlerManager(this);
	timers = new HashMap<String, Timer>();
  }
  
  @Override
  public HandlerRegistration addCommandHandler(CommandHandler handler) {
	return manager.addHandler(CommandEvent.getType(), handler);
  }
  
  /**
   * Cancels a previously started, repeating task.
   * 
   * @param task the name of the task to cancel.
   */
  public void cancelRepeating(String task) {
	if (timers.containsKey(task)) {
	  timers.get(task).cancel();
	  timers.remove(task);
	}
  }
  
  @Override
  public void fireEvent(GwtEvent<?> event) {
	manager.fireEvent(event);
  }

  /**
   * Schedules a distinct task.
   * 
   * @param delayMillis the delay, in milliseconds.
   * @param cmd the task's command.
   */
  public void schedule(int delayMillis, final Command cmd) {
	Timer timer = new Timer() {
	  @Override
	  public void run() {
		CommandEvent.fire(Scheduler.this, cmd);
	  }
	};
	timer.schedule(delayMillis);
  }
  
  /**
   * Schedules a repeating task.
   * 
   * @param task the name of the task.
   * @param periodMillis the repeat interval, in milliseconds.
   * @param cmd the task's command.
   */
  public void scheduleRepeating(String task, int periodMillis, final Command cmd) {
	if (timers.containsKey(task)) {
	  timers.get(task).cancel();
	  timers.remove(task);
	}
	Timer timer = new Timer() {
	  @Override
	  public void run() {
		CommandEvent.fire(Scheduler.this, cmd);
	  }
	};
	timers.put(task, timer);
	timer.scheduleRepeating(periodMillis);
  }
  
}
