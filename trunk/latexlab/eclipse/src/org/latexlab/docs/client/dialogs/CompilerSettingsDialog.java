package org.latexlab.docs.client.dialogs;

import org.latexlab.docs.client.commands.Command;
import org.latexlab.docs.client.commands.SystemApplyCompilerSettingsCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


/**
 * A dialog window displaying details of the application.
 */
public class CompilerSettingsDialog extends Dialog {

  protected static CompilerSettingsDialog instance;
  
  public static CompilerSettingsDialog get(CommandHandler handler, Command continueCommand) {
    if (instance == null) {
      instance = new CompilerSettingsDialog();
      instance.addCommandHandler(handler);
    }
    instance.setContinueCommand(continueCommand);
    return instance;
  }

  private VerticalPanel content, warning;
  private FlexTable settingsPanel;
  private RadioButton useDefault, useMikTex, useTexLive, useCustom;
  private TextBox clsiServiceUrl, clsiServiceToken, clsiAsyncPath, compilerName;
  private Command continueCommand;
  private Button ok, cancel;
  
  /**
   * Constructs an About dialog window.
   */
  public CompilerSettingsDialog() {
    super("Compiler Settings", true);
    ClickHandler cancelHandler = new ClickHandler() {
      public void onClick(ClickEvent event) {
        hide();
      }
    };
    ValueChangeHandler<Boolean> changeHandler = new ValueChangeHandler<Boolean>() {
	  @Override
	  public void onValueChange(ValueChangeEvent<Boolean> event) {
		settingsPanel.setVisible(useCustom.getValue());
		warning.setVisible(useDefault.getValue());
		CompilerSettingsDialog.this.center();
	  }
    };
    addClickHandler(cancelHandler);
    content = new VerticalPanel();
    content.setSpacing(10);
    content.setWidth("650px");
    VerticalPanel usage = new VerticalPanel();
    useDefault = new RadioButton("usage", "Use the default LaTeX Lab compiler.");
    useDefault.setValue(true);
    useDefault.addValueChangeHandler(changeHandler);
    useCustom = new RadioButton("usage");
    useCustom.setHTML("Use a third party <a href=\"http://code.google.com/p/common-latex-service-interface/\" target=\"_blank\">CLSI</a> provider.");
    useCustom.addValueChangeHandler(changeHandler);
    useMikTex = new RadioButton("usage");
    useMikTex.setHTML("Use a local <a href=\"http://miktex.org/\" target=\"_blank\">MikTeX</a> installation.");
    useMikTex.addValueChangeHandler(changeHandler);
    useMikTex.setEnabled(false);
    useTexLive = new RadioButton("usage");
    useTexLive.setHTML("Use a local <a href=\"http://www.tug.org/texlive/\" target=\"_blank\">TeX Live</a> installation.");
    useTexLive.addValueChangeHandler(changeHandler);
    useTexLive.setEnabled(false);
    usage.add(useDefault);
    usage.add(useMikTex);
    usage.add(useTexLive);
    usage.add(useCustom);
    content.add(usage);
    warning = new VerticalPanel();
    warning.setStylePrimaryName("lab-Warning");
    warning.add(new HTML("The default LaTeX Lab compiler is a shared environment which may " +
    		"temporarily cache document contents for performance. For sensitive documents " +
    		"you are encouraged to use LaTeX Lab with a local MikTeX/TeX Live installation or a private CLSI environment. " +
    		"<br /><br />For more information visit the <a href=\"http://code.google.com/p/latex-lab\" target=\"_blank\">Privacy</a> " +
    		"and <a href=\"http://code.google.com/p/latex-lab\" target=\"_blank\">Terms and Conditions</a> pages." +
    		"<br /><br />By using the default LaTeX Lab compiler you agree to the " +
    		"<a href=\"http://code.google.com/p/latex-lab\" target=\"_blank\">Terms and Conditions</a>."));
    content.add(warning);
    settingsPanel = new FlexTable();
    settingsPanel.setVisible(false);
    clsiServiceUrl = new TextBox();
    clsiServiceUrl.setWidth("400px");
    addSettingsField(clsiServiceUrl, "Service URL");
    clsiServiceToken = new TextBox();
    clsiServiceToken.setWidth("200px");
    addSettingsField(clsiServiceToken, "Service Token");
    clsiAsyncPath = new TextBox();
    clsiAsyncPath.setWidth("400px");
    addSettingsField(clsiAsyncPath, "Service output path");
    compilerName = new TextBox();
    compilerName.setWidth("200px");
    compilerName.setValue("latex");
    addSettingsField(compilerName, "Compiler Name");
    content.add(settingsPanel);
    ok = new Button("OK", new ClickHandler(){
      public void onClick(ClickEvent event) {
    	SystemApplyCompilerSettingsCommand cmd;
    	if (useDefault.getValue()) {
    	  cmd = new SystemApplyCompilerSettingsCommand();
    	} else {
    	  if (clsiServiceUrl.getValue().equals("") ||
    	      clsiServiceToken.getValue().equals("") ||
    	      clsiAsyncPath.getValue().equals("") ||
    	      compilerName.getValue().equals("")) {
    	    Window.alert("All fields are required.");
    	    return;
    	  } else {
    	    cmd = new SystemApplyCompilerSettingsCommand(false,
    	        clsiServiceUrl.getValue(),
    	        clsiServiceToken.getValue(),
    	        clsiAsyncPath.getValue(),
    	        compilerName.getValue());
    	  }
    	}
        hide();
    	CommandEvent.fire(CompilerSettingsDialog.this, cmd);
    	if (continueCommand != null) {
    	  CommandEvent.fire(CompilerSettingsDialog.this, continueCommand);
    	}
      }
    });
    cancel = new Button("Cancel", cancelHandler);
    HorizontalPanel buttons = new HorizontalPanel();
    buttons.setSpacing(10);
    buttons.add(ok);
    buttons.add(cancel);
    content.add(buttons);
    setContentWidget(content);
  }
  
  private void addSettingsField(Widget field, String name) {
	int i = settingsPanel.getRowCount();
    settingsPanel.insertRow(i);
    settingsPanel.insertCell(i, 0);
    settingsPanel.insertCell(i, 1);
    settingsPanel.setWidget(i, 0, new Label(name + ":"));
    settingsPanel.setWidget(i, 1, field);
  }

  public Command getContinueCommand() {
    return continueCommand;
  }

  public void setContinueCommand(Command continueCommand) {
    this.continueCommand = continueCommand;
  }

}
