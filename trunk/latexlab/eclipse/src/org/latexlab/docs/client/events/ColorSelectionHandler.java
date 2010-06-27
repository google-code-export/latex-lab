package org.latexlab.docs.client.events;

/**
 * Defines a color selection event handler.
 */
public interface ColorSelectionHandler {

  /**
   * Invoked when a color is selected.
   * 
   * @param event the color selection event
   */
  void onSelection(ColorSelectionEvent event);
	
}