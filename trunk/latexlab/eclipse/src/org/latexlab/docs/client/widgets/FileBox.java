package org.latexlab.docs.client.widgets;

import org.latexlab.docs.client.content.dialogs.DynamicFileSelectionDialog;
import org.latexlab.docs.client.events.FileSelectionEvent;
import org.latexlab.docs.client.events.FileSelectionHandler;
import org.latexlab.docs.client.gdocs.DocumentServiceEntry;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;

/**
 * A file input widget.
 */
public class FileBox extends Composite {
	
  protected Anchor link;
  protected DocumentServiceEntry value;

  /**
   * Constructs a new FileBox instance.
   */
  public FileBox() {
	link = new Anchor("No File Selected");
	link.setHref("#");
	link.setTitle("Click to select file.");
	link.setStylePrimaryName("lab-FileBox-Empty");
	link.addClickHandler(new ClickHandler() {
	    @Override
		public void onClick(ClickEvent event) {
	      event.preventDefault();
	      event.stopPropagation();
		  DynamicFileSelectionDialog.get(
			  new FileSelectionHandler() {
				  @Override
				  public void onSelection(FileSelectionEvent event) {
					setValue(event.getSelectedItem());
				  }
			  }
		  ).center();
		}
    });
    initWidget(link);
  }

  /**
   * Retrieves the selected value.
   * 
   * @return the selected value
   */
  public DocumentServiceEntry getValue() {
    return value;
  }

  /**
   * Sets the selected value.
   * 
   * @param value the selected value
   */
  public void setValue(DocumentServiceEntry value) {
    this.value = value;
    if (value == null) {
      link.setText("No File Selected");
      link.setStylePrimaryName("lab-FileBox-Empty");
    } else {
      link.setText(value.getTitle());
      link.setStylePrimaryName("lab-FileBox");
    }
  }

}

