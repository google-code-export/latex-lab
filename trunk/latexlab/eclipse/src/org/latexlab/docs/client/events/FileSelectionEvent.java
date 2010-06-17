package org.latexlab.docs.client.events;

import org.latexlab.docs.client.gdocs.DocumentServiceEntry;

import com.google.gwt.event.logical.shared.SelectionEvent;

/**
 * Defines a file selection event.
 */
public class FileSelectionEvent extends SelectionEvent<DocumentServiceEntry> {

  /**
   * Constructs a new file selection event.
   * 
   * @param selectedItem the selected file
   */
  public FileSelectionEvent(DocumentServiceEntry selectedItem) {
	super(selectedItem);
  }
	
}
