package org.latexlab.docs.client.content.dialogs;

import org.latexlab.docs.client.commands.SystemApplyPreferencesCommand;
import org.latexlab.docs.client.events.CommandEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * A dialog window containing a form for specifying preferences.
 */
public class DynamicPreferencesDialog extends DynamicFormDialog {

  /**
   * Contains form fields for specifying preferences.
   */
  protected class PreferencesFormContents extends FormContents {

	private TextBox autoSaveInterval;
	private CheckBox useAutoSave;
	  
    /**
     * Builds the Preferences form.
     */
	@Override
	protected void buildForm() {
	  content.setWidth("500px");
      ValueChangeHandler<Boolean> changeHandler = new ValueChangeHandler<Boolean>() {
  	      @Override
  	      public void onValueChange(ValueChangeEvent<Boolean> event) {
  		    content.getRowFormatter().setVisible(2, event.getValue());
  	      }
      };
      addHeader("Automatic Saving");
      useAutoSave = new CheckBox("Automatically save current document on a recurring basis.");
      useAutoSave.setValue(true);
      useAutoSave.addValueChangeHandler(changeHandler);
      addField(useAutoSave);
      autoSaveInterval = new TextBox();
      autoSaveInterval.setWidth("60px");
      addField(autoSaveInterval, "Save Interval (seconds)");
      submit.addClickHandler(new ClickHandler() {
          public void onClick(ClickEvent event) {
  		  if (useAutoSave.getValue()) {
  		  String interval = autoSaveInterval.getText();
  		  if (interval == null || interval.equals("")) {
  			Window.alert("Save interval is a required field.");
  			return;
  		  } else {
  		    if (interval.matches("\\d*")) {
  		      int nInterval = Integer.valueOf(interval);
  		      if (nInterval >= 5) {
  		        CommandEvent.fire(new SystemApplyPreferencesCommand(true,
  		                nInterval * 1000));
  		      } else {
  			    Window.alert("The save interval can't be less than 5 seconds.");
  			    return;
  		      }
  		    } else {
  			  Window.alert("The specified save interval is not a valid number.");
  			  return;
  		    }
  		  }
  		} else {
  		  CommandEvent.fire(new SystemApplyPreferencesCommand(false, 10000));
  		}
          hide();
        }
      });
      cancel.addClickHandler(new ClickHandler(){
          public void onClick(ClickEvent event) {
            hide();
          }
      });
	}
  
    /**
     * Resets the form fields to their default values.
     */
    @Override
    public void resetForm() {
	  resetForm(true, 10);
    }
  
    /**
     * Resets the form fields to their default values.
     * 
     * @param autoSave the value to populated the auto save field with.
     * @param saveInterval the value to populated the save interval field with.
     */
    public void resetForm(boolean autoSave, int saveInterval) {
	  useAutoSave.setValue(autoSave);
	  autoSaveInterval.setValue(String.valueOf(saveInterval));
	  content.getRowFormatter().setVisible(2, autoSave);
    }
	
  }

  protected static DynamicPreferencesDialog instance;
  
  /**
   * Retrieves the single instance of this class, using asynchronous instantiation.
   * 
   * @param autoSave the value to populated the auto save field with.
   * @param saveInterval the value to populated the save interval field with.
   */
  public static DynamicPreferencesDialog get(final boolean autoSave,
	    final int saveInterval) {
    if (instance == null) {
      instance = new DynamicPreferencesDialog(autoSave, saveInterval);
    }
    return instance;
  }
  
  private boolean autoSave;
  private int saveInterval;
  
  /**
   * Constructs a dialog window containing a form for specifying preferences.
   */
  public DynamicPreferencesDialog(boolean autoSave, int saveInterval) {
    super("Editor Settings", true, "500px", null);
    this.autoSave = autoSave;
    this.saveInterval = saveInterval;
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
	      PreferencesFormContents form = new PreferencesFormContents();
	      form.resetForm(autoSave, saveInterval);
		  callback.onSuccess(form);
		}
    });
  }

}
