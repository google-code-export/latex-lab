package org.latexlab.docs.client.content.dialogs;

import org.latexlab.docs.client.events.ColorSelectionEvent;
import org.latexlab.docs.client.events.ColorSelectionHandler;
import org.latexlab.docs.client.widgets.ColorPicker;
import org.latexlab.docs.client.widgets.DynamicDialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A dialog window for selecting a color.
 */
public class DynamicColorSelectionDialog extends DynamicDialog {

  /**
   * Contains Color Selection Dialog contents.
   */
  protected class ColorSelectionDialogContents extends Composite {

	protected ColorPicker picker;
	
    /**
     * Constructs a new Color Selection Dialog contents.
     */
	protected ColorSelectionDialogContents() {
      addClickHandler(new ClickHandler() {
          public void onClick(ClickEvent event) {
            hide();
          }
      });
      HorizontalPanel content = new HorizontalPanel();
      picker = new ColorPicker();
      picker.addClickHandler(new ClickHandler() {
  		@Override
  		public void onClick(ClickEvent event) {
  		  hide();
  		  if (selectionHandler != null) {
  			ColorSelectionEvent e = new ColorSelectionEvent(picker.getSelectedColor());
  			selectionHandler.onSelection(e);
  		  }
  		}
      });
      content.add(picker);
      initWidget(content);
	}
	
  }

  protected static DynamicColorSelectionDialog instance;

  /**
   * Retrieves the single instance of this class.
   * 
   * @param selectionHandler the selection handler.
   */
  public static DynamicColorSelectionDialog get(
	  ColorSelectionHandler selectionHandler) {
    if (instance == null) {
      instance = new DynamicColorSelectionDialog();
    }
    instance.selectionHandler = selectionHandler;
    return instance;
  }
  
  protected ColorSelectionHandler selectionHandler;
  
  /**
   * Constructs a dialog window for selecting a color.
   */
  public DynamicColorSelectionDialog() {
    super("Select Color", true, "400px", null);
    mainPanel.getCellFormatter().setStyleName(2, 0, "");
  }

  /**
   * Retrieves the dialog's contents asynchronously.
   * 
   * @param callback the callback carrying the content widget
   */
  @Override
  protected void getContents(final AsyncCallback<Widget> callback) {
    GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		}
		@Override
		public void onSuccess() {
	      callback.onSuccess(new ColorSelectionDialogContents());
	      center();
		}
    });
  }
  
}
