package org.latexlab.docs.client.dialogs;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * The shared implementation of form based dialogs.
 */
public abstract class FormDialog extends Dialog {

  protected static FormDialog instance;

  protected HorizontalPanel buttonPanel;
  protected FlexTable content;
  protected Button submit, cancel;
  
  /**
   * Constructs a New Form dialog.
   * 
   * @param title the dialog window's title
   * @param modal whether the dialog window is modal
   */
  protected FormDialog(String title, boolean modal) {
    super(title, modal);
    addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        hide();
      }
    });
    submit = new Button("OK");
    cancel = new Button("Cancel");
    buttonPanel = new HorizontalPanel();
    buttonPanel.setSpacing(10);
    buttonPanel.add(submit);
    buttonPanel.add(cancel);
    content = new FlexTable();
    content.setCellPadding(2);
    content.setWidth("500px");
    content.setStylePrimaryName("lab-Form");
    setContentWidget(content);
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
	int i = content.getRowCount();
	if (name == null) {
	  content.insertRow(i);
	  content.insertCell(i, 0);
	  content.getFlexCellFormatter().setColSpan(i, 0, 2);
	  content.setWidget(i, 0, field);
	} else {
	  content.insertRow(i);
	  content.insertCell(i, 0);
	  content.insertCell(i, 1);
	  content.getCellFormatter().setWidth(i, 0, "200px");
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
	int i = content.getRowCount();
	content.insertRow(i);
	content.insertCell(i, 0);
	content.getFlexCellFormatter().setColSpan(i, 0, 2);
	Label label = new Label(name);
	label.setStylePrimaryName("lab-Form-Header");
	content.setWidget(i, 0, label);
  }
  
  /**
   * Builds the basic form.
   */
  protected void buildForm() {
    addField(buttonPanel);
  }
  
  /**
   * Hides the form dialog.
   */
  @Override
  public void hide() {
    super.hide();
  }
  
  /**
   * Resets the form fields to their default values.
   */
  public abstract void resetForm();
  
}
