package org.latexlab.docs.client.content.dialogs;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;

/**
 * A dialog window containing a form for inserting a LaTeX table.
 */
public class DynamicInsertTableDialog extends DynamicFormDialog {

  /**
   * Contains form fields for specifying a LaTeX table.
   */
  protected class InsertTableFormContents extends FormContents {
	  
	protected RadioButton alignTop, alignCenter, alignBottom;
	protected TextBox caption, label;
	protected CheckBox centerHorizontal, insertAsFloat;
	protected CheckBox herePosition, topPosition, bottomPosition, floatPosition;
	protected RadioButton oneColumn, twoColumns;
	  
    /**
     * Builds the Insert LaTeX Header form.
     */
	@Override
	protected void buildForm() {
	  content.setWidth("500px");
	  alignTop = new RadioButton("alignment", "Align at top line");
	  alignCenter = new RadioButton("alignment", "Align at center of table");
	  alignCenter.setValue(true);
	  alignBottom = new RadioButton("alignment", "Align at bottom line");
	  HorizontalPanel alignment = new HorizontalPanel();
	  alignment.add(alignTop);
	  alignment.add(alignCenter);
	  alignment.add(alignBottom);
	  centerHorizontal = new CheckBox("Center horizontal");
      centerHorizontal.setValue(true);
	  insertAsFloat = new CheckBox("Insert as float");
      insertAsFloat.setValue(true);
      insertAsFloat.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
		  @Override
		  public void onValueChange(ValueChangeEvent<Boolean> event) {
		    boolean value = event.getValue();
		    for (int i=7; i<=10; i++) {
		      content.getRowFormatter().setVisible(i, value);
		    }
		    center();
		  }
      });
	  caption = new TextBox();
	  caption.setWidth("250px");
	  label = new TextBox();
	  label.setWidth("250px");
	  herePosition = new CheckBox("Here");
	  topPosition = new CheckBox("Top of the page");
      topPosition.setValue(true);
	  bottomPosition = new CheckBox("Bottom of the page");
      bottomPosition.setValue(true);
	  floatPosition = new CheckBox("Page of floats");
      floatPosition.setValue(true);
      FlexTable position = new FlexTable();
      position.setWidth("100%");
      position.insertRow(0);
      position.insertCell(0, 0);
      position.insertCell(0, 1);
      position.insertRow(1);
      position.insertCell(1, 0);
      position.insertCell(1, 1);
      position.setWidget(0, 0, herePosition);
      position.setWidget(0, 1, topPosition);
      position.setWidget(1, 0, bottomPosition);
      position.setWidget(1, 1, floatPosition);
	  oneColumn = new RadioButton("expansion", "One column");
      oneColumn.setValue(true);
	  twoColumns = new RadioButton("expansion", "Two columns");
	  HorizontalPanel expansion = new HorizontalPanel();
	  expansion.add(oneColumn);
	  expansion.add(twoColumns);
	  addHeader("Caption");
      addField(caption, "Caption:");
      addField(label, "Label:");
	  addHeader("Alignment");
      addField(alignment);
      addField(centerHorizontal);
      addField(insertAsFloat);
      addHeader("Position");
      addField(position);
	  addHeader("Expansion");
      addField(expansion);
      submit.addClickHandler(new ClickHandler() {
          public void onClick(ClickEvent event) {
            String align = "", center = "", cap = "", lab = "", pos = "", two = "";
            if (alignTop.getValue()) {
        	  align = "[t]";
            } else if (alignTop.getValue()) {
        	  align = "[b]";
            }
            if (centerHorizontal.getValue()) {
        	  center = "\\centering\n";
            }
            if (insertAsFloat.getValue()) {
              if (!caption.getValue().equals("")) {
        	    cap = "\n\\caption{" + caption.getValue() + "}";
              }
              if (!label.getValue().equals("")) {
        	    lab = "\n\\label{" + label.getValue() + "}";
              }
              if (herePosition.getValue() ||
                  !topPosition.getValue() ||
                  !bottomPosition.getValue() ||
                  !floatPosition.getValue()) {
                if (herePosition.getValue()) {
        	      pos += "h";
                }
                if (topPosition.getValue()) {
          	      pos += "t";
                }
                if (bottomPosition.getValue()) {
          	      pos += "b";
                }
                if (floatPosition.getValue()) {
          	      pos += "f";
                }
                pos = "[" + pos + "]";
              }
              if (twoColumns.getValue()) {
        	    two = "*";
              }
            }
            String latex = "\\begin{table" + two + "}" + pos + "\n" + center + "\\begin{tabular}\n" + align + "\n  \n\\end{tabular}" + cap + lab + "\n\\end{table" + two + "}";
  		    CommandEvent.fire(DynamicInsertTableDialog.this, 
  			      new SystemPasteCommand(latex));
            hide();
            resetForm();
          }
      });
      cancel.addClickHandler(new ClickHandler(){
          public void onClick(ClickEvent event) {
            hide();
            resetForm();
          }
      });
	}

    /**
     * Resets the form fields to their default values.
     */
	@Override
	public void resetForm() {
	  alignTop.setValue(false);
	  alignCenter.setValue(true);
      alignBottom.setValue(false);
      oneColumn.setValue(true);
      twoColumns.setValue(false);
      centerHorizontal.setValue(true);
      insertAsFloat.setValue(true);
      herePosition.setValue(false);
      topPosition.setValue(true);
      bottomPosition.setValue(true);
      floatPosition.setValue(true);
      caption.setValue("");
      label.setValue("");
	}
	  
  }

  protected static DynamicInsertTableDialog instance;
  
  /**
   * Retrieves the single instance of this class.
   * 
   * @param handler the command handler.
   */
  public static DynamicInsertTableDialog get(final CommandHandler handler) {
    if (instance == null) {
      instance = new DynamicInsertTableDialog();
      instance.addCommandHandler(handler);
    }
    return instance;
  }

  /**
   * Constructs a dialog window containing a form for specifying preferences.
   */
  public DynamicInsertTableDialog() {
    super("Insert Header", true, "500px", null);
  }

  /**
   * Retrieves the dialog's contents asynchronously.
   * 
   * @param callback the callback carrying the content widget
   */
  @Override
  protected void getFormContents(final AsyncCallback<FormContents> callback) {
    GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
	      callback.onFailure(reason);
		}
		@Override
		public void onSuccess() {
		  callback.onSuccess(new InsertTableFormContents());
		}
    });
  }
  
}
