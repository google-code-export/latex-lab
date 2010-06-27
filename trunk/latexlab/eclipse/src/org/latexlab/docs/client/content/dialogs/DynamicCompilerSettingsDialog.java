package org.latexlab.docs.client.content.dialogs;

import org.latexlab.docs.client.commands.Command;
import org.latexlab.docs.client.commands.SystemApplyCompilerSettingsCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * A dialog window containing a form for specifying compiler settings.
 */
public class DynamicCompilerSettingsDialog extends DynamicFormDialog {

  /**
   * Contains form fields for specifying compiler settings.
   */
  protected class CompilerSettingsFormContents extends FormContents {

	private TextBox clsiServiceUrl, clsiServiceToken, clsiAsyncPath, compilerName;
	private RadioButton useDefault, useMikTex, useTexLive, useCustom;
	private VerticalPanel warning;
	
	/**
	 * Builds the compiler settings form.
	 */
	@Override
	protected void buildForm() {
      content.setWidth("650px");
	  ValueChangeHandler<Boolean> changeHandler = new ValueChangeHandler<Boolean>() {
		@Override
		public void onValueChange(ValueChangeEvent<Boolean> event) {
	      setView(!useCustom.getValue());
		}
	  };
	  VerticalPanel usage = new VerticalPanel();
	  useDefault = new RadioButton("usage", "Use the default LaTeX Lab compiler.");
	  useDefault.setValue(true);
	  useDefault.addValueChangeHandler(changeHandler);
	  useCustom = new RadioButton("usage");
	  useCustom.setHTML("Use a third party <a href=\"http://code.google.com/p/common-latex-service-interface/\" target=\"_blank\">CLSI</a> provider.");
	  useCustom.addValueChangeHandler(changeHandler);
	  useMikTex = new RadioButton("usage");
	  useMikTex.setEnabled(false);
	  String devUrl = Window.Location.getHref().replace("/docs.", "/dev.");
	  useMikTex.setHTML("Use a local <a href=\"http://miktex.org/\" target=\"_blank\">MikTeX</a> installation. <a href=\"" + devUrl + "\">Available in development version</a>");
	  useMikTex.addValueChangeHandler(changeHandler);
	  useTexLive = new RadioButton("usage");
	  useTexLive.setEnabled(false);
	  useTexLive.setHTML("Use a local <a href=\"http://www.tug.org/texlive/\" target=\"_blank\">TeX Live</a> installation.");
	  useTexLive.addValueChangeHandler(changeHandler);
	  usage.add(useDefault);
	  usage.add(useMikTex);
	  usage.add(useTexLive);
	  usage.add(useCustom);
	  addField(usage);
	  warning = new VerticalPanel();
	  warning.setStylePrimaryName("lab-Warning");
	  warning.add(new HTML("The default LaTeX Lab compiler is a shared environment which may " +
	      "temporarily cache document contents for performance. For sensitive documents " +
	      "you are encouraged to use LaTeX Lab with a local MikTeX/TeX Live installation or a private CLSI environment. " +
	      "<br /><br />For more information visit the <a href=\"http://code.google.com/p/latex-lab\" target=\"_blank\">Privacy</a> " +
	      "and <a href=\"http://code.google.com/p/latex-lab\" target=\"_blank\">Terms and Conditions</a> pages." +
	      "<br /><br />By using the default LaTeX Lab compiler you agree to the " +
	      "<a href=\"http://code.google.com/p/latex-lab\" target=\"_blank\">Terms and Conditions</a>."));
	  addField(warning);
	  clsiServiceUrl = new TextBox();
	  clsiServiceUrl.setWidth("400px");
	  addField(clsiServiceUrl, "Service URL:");
	  clsiServiceToken = new TextBox();
	  clsiServiceToken.setWidth("200px");
	  addField(clsiServiceToken, "Service token:");
	  clsiAsyncPath = new TextBox();
	  clsiAsyncPath.setWidth("400px");
	  addField(clsiAsyncPath, "Service output path:");
	  compilerName = new TextBox();
	  compilerName.setWidth("200px");
	  compilerName.setValue("latex");
	  addField(compilerName, "Compiler name:");
	  submit.addClickHandler(new ClickHandler() {
	    public void onClick(ClickEvent event) {
	      SystemApplyCompilerSettingsCommand cmd;
	      if (useDefault.getValue()) {
	    	cmd = new SystemApplyCompilerSettingsCommand();
	      } else {
	    	if (useCustom.getValue() &&
	    		(clsiServiceUrl.getValue().equals("") ||
	    	    clsiServiceToken.getValue().equals("") ||
	    	    clsiAsyncPath.getValue().equals("") ||
	    	    compilerName.getValue().equals(""))) {
	    	  Window.alert("All fields are required.");
	    	  return;
	    	} else {
	    	  SystemApplyCompilerSettingsCommand.Compiler compiler =
	    		  SystemApplyCompilerSettingsCommand.Compiler.REMOTE_DEFAULT_COMPILER;
	    	  if (useMikTex.getValue()) {
	    		compiler =
	    		    SystemApplyCompilerSettingsCommand.Compiler.LOCAL_MIKTEX_COMPILER;
	    	  } else if (useTexLive.getValue()) {
    		    compiler =
	    		    SystemApplyCompilerSettingsCommand.Compiler.LOCAL_TEXLIVE_COMPILER;
	    	  } else if (useCustom.getValue()) {
    		    compiler =
	    		    SystemApplyCompilerSettingsCommand.Compiler.REMOTE_CUSTOM_COMPILER;
	    	  }
	    	  cmd = new SystemApplyCompilerSettingsCommand(compiler,
	    	      clsiServiceUrl.getValue(),
	    	      clsiServiceToken.getValue(),
	    	      clsiAsyncPath.getValue(),
	    	      compilerName.getValue());
	    	}
	      }
    	  cmd.setContinueCommand(continueCommand);
	      DynamicCompilerSettingsDialog.this.hide();
	      CommandEvent.fire(DynamicCompilerSettingsDialog.this, cmd);
	    }
	  });
	  cancel.addClickHandler(new ClickHandler(){
	    public void onClick(ClickEvent event) {
	      DynamicCompilerSettingsDialog.this.hide();
	    }
	  });
	  setView(true);
	}

	/**
	 * Resets the form.
	 */
	@Override
	public void resetForm() {
      //TODO: implement, if appropriate.
	}

	/**
	 * Updates the form view.
	 * 
	 * @param isDefault whether to display the default view
	 */
	private void setView(boolean isDefault) {
      content.getRowFormatter().setVisible(1, isDefault);
	  for (int i=2; i<6; i++) {
		content.getRowFormatter().setVisible(i, !isDefault);
      }
	  warning.setVisible(useDefault.getValue());
	  DynamicCompilerSettingsDialog.this.center();
	}
	
  }

  protected static DynamicCompilerSettingsDialog instance;

  /**
   * Retrieves the single instance of this class.
   * 
   * @param handler the command handler.
   * @param continueCommand the command to execute once settings are applied.
   */
  public static DynamicCompilerSettingsDialog get(final CommandHandler handler, final Command continueCommand) {
    if (instance == null) {
      instance = new DynamicCompilerSettingsDialog();
      instance.addCommandHandler(handler);
    }
    instance.setContinueCommand(continueCommand);
    return instance;
  }
  
  private Command continueCommand;
  
  /**
   * Constructs a dialog window containing a form for specifying compiler settings.
   */
  public DynamicCompilerSettingsDialog() {
    super("Compiler Settings", true, "650px", null);
    addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          hide();
        }
    });
  }

  /**
   * Retrieves the command to execute once settings are applied.
   * 
   * @return the command to execute once settings are applied.
   */
  public Command getContinueCommand() {
    return continueCommand;
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
		  callback.onSuccess(new CompilerSettingsFormContents());
		}
  	});
  }
  
  /**
   * Sets the command to execute once settings are applied.
   * 
   * @param continueCommand the command to execute once settings are applied.
   */
  public void setContinueCommand(Command continueCommand) {
    this.continueCommand = continueCommand;
  }
  
}
