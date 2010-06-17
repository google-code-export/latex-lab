package org.latexlab.docs.client.events;

/**
 * Defines a file selection event handler.
 */
public interface FileSelectionHandler {

  /**
   * Invoked when a file is selected.
   * 
   * @param event the file selection event
   */
  void onSelection(FileSelectionEvent event);
	
}