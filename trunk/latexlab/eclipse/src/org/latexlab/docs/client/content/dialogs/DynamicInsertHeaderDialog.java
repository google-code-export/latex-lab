package org.latexlab.docs.client.content.dialogs;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.CommandEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * A dialog window containing a form for inserting a LaTeX header.
 */
public class DynamicInsertHeaderDialog extends DynamicFormDialog {

  /**
   * Contains form fields for specifying LaTeX header settings .
   */
  protected class InsertHeaderFormContents extends FormContents {

    protected ListBox level;
    protected TextBox title, label;
  
    /**
     * Builds the Insert LaTeX Header form.
     */
	@Override
	protected void buildForm() {
	  content.setWidth("500px");
      level = new ListBox();
	  level.addItem("Part");
	  level.addItem("Chapter");
	  level.addItem("Section");
	  level.addItem("Sub-Section");
	  level.addItem("Sub-Sub-Section");
	  level.addItem("Paragraph");
	  level.addItem("Sub-Paragraph");
	  level.setSelectedIndex(2);
	  title = new TextBox();
	  title.setWidth("250px");
	  label = new TextBox();
	  label.setWidth("250px");
      addField(level, "Level:");
      addField(title, "Title:");
      addField(label, "Label:");
      submit.addClickHandler(new ClickHandler() {
          public void onClick(ClickEvent event) {
            String latex = "";
            switch(level.getSelectedIndex()) {
            case 0:
          	  latex = "\\section{" + title.getText() + "}";
        	  break;
            case 1:
        	  latex = "\\chapter{" + title.getText() + "}";
        	  break;
            case 2:
          	  latex = "\\section{" + title.getText() + "}";
        	  break;
            case 3:
        	  latex = "\\subsection{" + title.getText() + "}";
        	  break;
            case 4:
        	  latex = "\\subsubsection{" + title.getText() + "}";
        	  break;
            case 5:
        	  latex = "\\paragraph{" + title.getText() + "}";
        	  break;
            case 6:
        	  latex = "\\subparagraph{" + title.getText() + "}";
        	  break;
            }
            if (!label.getText().equals("")) {
        	  latex += "\n\\label{" + label.getText() + "}";
            }
  		    CommandEvent.fire(new SystemPasteCommand(latex));
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
	  level.setSelectedIndex(2);
	  title.setText("");
	  label.setText("");
	}
	
  }

  protected static DynamicInsertHeaderDialog instance;
  
  /**
   * Retrieves the single instance of this class.
   */
  public static DynamicInsertHeaderDialog get() {
    if (instance == null) {
      instance = new DynamicInsertHeaderDialog();
    }
    return instance;
  }
  
  /**
   * Constructs a dialog window containing a form for specifying preferences.
   */
  public DynamicInsertHeaderDialog() {
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
		  callback.onSuccess(new InsertHeaderFormContents());
		}
	});
  }
  
}
