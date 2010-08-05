package org.latexlab.docs.client.content.dialogs;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.CommandEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextBox;

/**
 * A dialog window containing a form for inserting a hyperlink.
 */
public class DynamicInsertHyperlinkDialog extends DynamicFormDialog {

  /**
   * Contains form fields for specifying hyperlink fields.
   */
  protected class InsertHyperlinkFormContents extends FormContents {

    protected TextBox text, url;
  
    /**
     * Builds the Insert Hyperlink form.
     */
	@Override
	protected void buildForm() {
	  content.setWidth("500px");
	  text = new TextBox();
	  text.setWidth("250px");
	  url = new TextBox();
	  url.setWidth("250px");
      addField(text, "Text:");
      addField(url, "URL:");
      submit.addClickHandler(new ClickHandler() {
          public void onClick(ClickEvent event) {
            if (url.getText().equals("")) {
              Window.alert("URL is a required field.");
              return;
            }
            String latex = "";
            if (text.getText().equals("")) {
              latex = "\\url{" + url.getText() + "}";
            } else {
              latex = "\\url{" + url.getText() + "}{" + text.getText() + "}";
            }
  		    CommandEvent.fire(new SystemPasteCommand(latex, new String[] { "\\usepackage{hyperref}" }));
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
      onShow = new Runnable() {
		@Override
		public void run() {
		  resetForm(defaultText);
		}
      };
	}

    /**
     * Resets the form fields to their default values.
     */
	@Override
	public void resetForm() {
	  resetForm("");
	}

    /**
     * Resets the form fields to their default values.
     * 
     * @param defaultText the default link text
     */
	public void resetForm(String defaultText) {
	  if (defaultText == null) {
		text.setText("");
	  } else {
	    text.setText(defaultText);
	  }
	  url.setText("");
	}
	
  }

  protected static DynamicInsertHyperlinkDialog instance;
  
  /**
   * Retrieves the single instance of this class.
   */
  public static DynamicInsertHyperlinkDialog get(String defaultText) {
    if (instance == null) {
      instance = new DynamicInsertHyperlinkDialog();
    }
    instance.defaultText = defaultText;
    return instance;
  }
  
  protected String defaultText;
  protected Runnable onShow;
  
  /**
   * Constructs a dialog window containing a form for specifying preferences.
   */
  public DynamicInsertHyperlinkDialog() {
    super("Insert Hyperlink", true, "500px", null);
  }
  
  @Override
  public void show() {
	super.show();
	if (onShow != null) {
	  onShow.run();
	}
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
		  InsertHyperlinkFormContents form = new InsertHyperlinkFormContents();
		  callback.onSuccess(form);
		}
	});
  }
  
}
