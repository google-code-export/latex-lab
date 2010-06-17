package org.latexlab.docs.client.content.dialogs;

import org.latexlab.docs.client.widgets.DynamicDialog;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * The base implementation of a form based dialog.
 */
public abstract class DynamicFormDialog extends DynamicDialog {

  /**
   * A base implementation of a form contents widget.
   */
  protected abstract static class FormContents extends Composite {
  
	protected HorizontalPanel buttonPanel;
    protected FlexTable content;
    protected Button submit, cancel;
	 
    /**
     * Constructs a new form contents.
     */
	public FormContents() {
      content = new FlexTable();
      content.setCellPadding(2);
      content.setWidth("500px");
      content.setStylePrimaryName("lab-Form");
      submit = new Button("OK");
      cancel = new Button("Cancel");
      buttonPanel = new HorizontalPanel();
      buttonPanel.setSpacing(10);
      buttonPanel.add(submit);
      buttonPanel.add(cancel);
	  content.insertRow(0);
	  content.insertCell(0, 0);
	  content.getFlexCellFormatter().setColSpan(0, 0, 2);
	  content.setWidget(0, 0, buttonPanel);
	  initWidget(content);
	  buildForm();
	}
	
    /**
     * Adds a form field.
     * 
     * @param field the form field's widget.
     */
    protected void addField(Widget field) {
	  addField(field, null);
    }
  
    /**
     * Adds a labeled form field.
     * 
     * @param field the form field's widget.
     * @param name the form field's label.
     */
    protected void addField(Widget field, String name) {
	  int i = content.getRowCount() - 1;
	  if (name == null) {
	    content.insertRow(i);
	    content.insertCell(i, 0);
	    content.getFlexCellFormatter().setColSpan(i, 0, 2);
	    content.setWidget(i, 0, field);
	  } else {
	    content.insertRow(i);
	    content.insertCell(i, 0);
	    content.insertCell(i, 1);
	    content.getCellFormatter().setWidth(i, 0, "140px");
	    content.setWidget(i, 0, new Label(name));
	    content.setWidget(i, 1, field);
	  }
    }

    /**
     * Adds a header.
     * 
     * @param name the header's label.
     */
    protected void addHeader(String name) {
	  int i = content.getRowCount() - 1;
	  content.insertRow(i);
	  content.insertCell(i, 0);
	  content.getFlexCellFormatter().setColSpan(i, 0, 2);
	  Label label = new Label(name);
	  label.setStylePrimaryName("lab-Form-Header");
	  content.setWidget(i, 0, label);
    }
    
    /**
     * Builds the form.
     */
    protected abstract void buildForm();
  
    /**
     * Resets the form fields to their default values.
     */
    public abstract void resetForm();
	
  }

  /**
   * Constructs a New Form dialog.
   * 
   * @param title the dialog window's title
   * @param modal whether the dialog window is modal
   * @param targetWidth the initial width of the dialog frame, or null to inherit
   * @param targetHeight the initial height of the dialog frame, or null to inherit
   */
  protected DynamicFormDialog(String title, boolean modal,
		String targetWidth, String targetHeight) {
    super(title, modal, targetWidth, targetHeight);
    addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        hide();
      }
    });
  }

  /**
   * Retrieves the dialog's contents.
   * 
   * @return the dialog's contents
   */
  @Override
  protected void getContents(final AsyncCallback<Widget> callback) {
	getFormContents(new AsyncCallback<FormContents>() {
		@Override
		public void onFailure(Throwable caught) {
		  callback.onFailure(caught);
		}
		@Override
		public void onSuccess(FormContents result) {
		  callback.onSuccess(result);
		}
	});
  }
  
  /**
   * Retrieves the dialog's form contents.
   * 
   * @return the dialog's form contents
   */
  protected abstract void getFormContents(AsyncCallback<FormContents> callback);

}
