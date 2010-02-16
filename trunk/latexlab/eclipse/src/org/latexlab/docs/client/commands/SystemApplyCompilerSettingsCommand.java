package org.latexlab.docs.client.commands;

public class SystemApplyCompilerSettingsCommand extends Command {

  public final static int serialUid = 93;
  
  private boolean useDefault;
  private String clsiServiceUrl, clsiServiceToken, clsiAsyncPath, compilerName;
  
  public SystemApplyCompilerSettingsCommand() {
	super("Apply LaTeX compiler settings.");
    this.useDefault = true;
  }
  
  public SystemApplyCompilerSettingsCommand(boolean useDefault,
	  String clsiServiceUrl, String clsiServiceToken,
	  String clsiAsyncPath, String compilerName) {
	super("Apply LaTeX compiler settings.");
	this.useDefault = useDefault;
	this.clsiServiceUrl = clsiServiceUrl;
	this.clsiServiceToken = clsiServiceToken;
	this.clsiAsyncPath = clsiAsyncPath;
	this.compilerName = compilerName;
  }
  
  public boolean isUseDefault() {
    return useDefault;
  }

  public void setUseDefault(boolean useDefault) {
    this.useDefault = useDefault;
  }

  public String getClsiServiceUrl() {
    return clsiServiceUrl;
  }

  public void setClsiServiceUrl(String clsiServiceUrl) {
    this.clsiServiceUrl = clsiServiceUrl;
  }

  public String getClsiServiceToken() {
    return clsiServiceToken;
  }

  public void setClsiServiceToken(String clsiServiceToken) {
    this.clsiServiceToken = clsiServiceToken;
  }

  public String getClsiAsyncPath() {
    return clsiAsyncPath;
  }

  public void setClsiAsyncPath(String clsiAsyncPath) {
    this.clsiAsyncPath = clsiAsyncPath;
  }

  public String getCompilerName() {
    return compilerName;
  }

  public void setCompilerName(String compilerName) {
    this.compilerName = compilerName;
  }

  @Override
  public int getCommandId() {
    return serialUid;
  }

}
