package org.latexlab.docs.client.events;

import com.google.gwt.event.logical.shared.SelectionEvent;

/**
 * Defines a color selection event.
 */
public class ColorSelectionEvent extends SelectionEvent<String> {

  /**
   * Constructs a new color selection event.
   * 
   * @param selectedColor the selected color
   */
  public ColorSelectionEvent(String selectedColor) {
	super(selectedColor);
  }
	
}
