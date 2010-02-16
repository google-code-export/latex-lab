package org.latexlab.docs.client.dialogs;

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
  
  public static CompilerSettingsDialog getInstance(CommandHandler handler) {
    if (instance == null) {
      instance = new CompilerSettingsDialog();
      instance.addCommandHandler(handler);
    }
    return instance;
  }

  private VerticalPanel content;
  private FlexTable settingsPanel;
  private RadioButton useDefault, useCustom;
  private TextBox clsiServiceUrl, clsiServiceToken, clsiAsyncPath, compilerName;
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
    usage.add(useDefault);
    usage.add(useCustom);
    content.add(usage);
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
    	CommandEvent.fire(CompilerSettingsDialog.this, cmd);
        hide();
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
  
}
