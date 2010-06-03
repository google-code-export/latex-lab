package org.latexlab.docs.client.dialogs;

import org.latexlab.docs.client.commands.SystemApplyPreferencesCommand;
import org.latexlab.docs.client.events.AsyncInstantiationCallback;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * A dialog window containing a form for specifying preferences.
 */
public class PreferencesDialog extends FormDialog {

  protected static PreferencesDialog instance;

  /**
   * Retrieves the single instance of this class, using asynchronous instantiation.
   * 
   * @param handler the command handler.
   * @param autoSave the value to populated the auto save field with.
   * @param saveInterval the value to populated the save interval field with.
   * @param cb the asynchronous instantiation callback.
   */
  public static void get(final CommandHandler handler,
		final boolean autoSave, final int saveInterval,
	    final AsyncInstantiationCallback<PreferencesDialog> cb) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  cb.onFailure(reason);
		}
		@Override
		public void onSuccess() {
	      if (instance == null) {
	        instance = new PreferencesDialog();
	        instance.addCommandHandler(handler);
	      }
	      instance.resetForm(autoSave, saveInterval);
		  cb.onSuccess(instance);
		}
	});
  }
  
  /**
   * Causes the code for this class to be loaded.
   */
  public static void prefetch() {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) { }
		@Override
		public void onSuccess() {
		  new PreferencesDialog();
		}
	});
  }
  
  private TextBox autoSaveInterval;
  private CheckBox useAutoSave;
  
  /**
   * Constructs a dialog window containing a form for specifying preferences.
   */
  public PreferencesDialog() {
    super("Editor Settings", true);
    addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          hide();
        }
    });
    buildForm();
  }
  
  /**
   * Builds the Preferences form.
   */
  @Override
  protected void buildForm() {
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
    addField(autoSaveInterval, "Save Interval (in seconds)");
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
		        CommandEvent.fire(PreferencesDialog.this, 
		            new SystemApplyPreferencesCommand(true,
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
		  CommandEvent.fire(PreferencesDialog.this, 
		      new SystemApplyPreferencesCommand(false, 10000));
		}
        hide();
      }
    });
    cancel.addClickHandler(new ClickHandler(){
        public void onClick(ClickEvent event) {
          hide();
        }
    });
    super.buildForm();
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
