package org.latexlab.docs.client.content.dialogs;

import java.util.ArrayList;

import org.latexlab.docs.client.commands.SystemAddResourcesCommand;
import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.gdocs.DocumentServiceEntry;
import org.latexlab.docs.client.widgets.FileBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;

/**
 * A dialog window containing a form for inserting an image.
 */
public class DynamicInsertImageDialog extends DynamicFormDialog {

  /**
   * Contains form fields for specifying image settings .
   */
  protected class InsertHeaderFormContents extends FormContents {

    protected FileBox image;
	protected TextBox caption, label;
	protected CheckBox centerHorizontal, insertAsFloat;
	protected CheckBox herePosition, topPosition, bottomPosition, floatPosition;
	protected RadioButton oneColumn, twoColumns;
  
    /**
     * Builds the Insert Image form.
     */
	@Override
	protected void buildForm() {
	  content.setWidth("500px");
	  image = new FileBox();
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
      addField(image, "Image:");
	  addHeader("Caption");
      addField(caption, "Caption:");
      addField(label, "Label:");
      addField(centerHorizontal);
      addField(insertAsFloat);
      addHeader("Position");
      addField(position);
	  addHeader("Expansion");
      addField(expansion);
      submit.addClickHandler(new ClickHandler() {
          public void onClick(ClickEvent event) {
        	if (image.getValue() == null) {
        	  Window.alert("Image is a required field.");
        	  return;
        	}
            String center = "", cap = "", lab = "", pos = "", two = "";
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
            ArrayList<DocumentServiceEntry> addResources = new ArrayList<DocumentServiceEntry>();
            addResources.add(image.getValue());
            String latex = "\\begin{figure" + two + "}" + pos + "\n" + center + "\\includegraphics{" + image.getValue().getIdentifier() + "}" + cap + lab + "\n\\end{figure" + two + "}";
  		    CommandEvent.fire(DynamicInsertImageDialog.this, 
  			      new SystemPasteCommand(latex, new String[] {"\\usepackage{graphicx}"}));
  		    CommandEvent.fire(DynamicInsertImageDialog.this,
  		    	  new SystemAddResourcesCommand(addResources));
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
	  image.setValue(null);
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

  protected static DynamicInsertImageDialog instance;
  
  /**
   * Retrieves the single instance of this class.
   * 
   * @param handler the command handler.
   */
  public static DynamicInsertImageDialog get(final CommandHandler handler) {
    if (instance == null) {
      instance = new DynamicInsertImageDialog();
      instance.addCommandHandler(handler);
      DynamicFileSelectionDialog.get(handler, null);
    }
    return instance;
  }
  
  /**
   * Constructs a dialog window containing a form for specifying preferences.
   */
  public DynamicInsertImageDialog() {
    super("Insert Image", true, "500px", null);
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
		  callback.onSuccess(new InsertHeaderFormContents());
		}
	});
  }
  
}
