package org.latexlab.docs.client.commands;

/**
 * A command for applying compiler settings.
 */
public class SystemApplyCompilerSettingsCommand extends Command {

  /**
   * The command's unique id.
   */
  public final static int serialUid = 93;
  
  protected String clsiServiceUrl, clsiServiceToken, clsiAsyncPath, compilerName;
  protected boolean useDefault;
  
  /**
   * Constructs a command for applying compiler settings.
   */
  public SystemApplyCompilerSettingsCommand() {
	super("Apply LaTeX compiler settings.");
    this.useDefault = true;
  }
  
  /**
   * Constructs a command for applying compiler settings.
   * 
   * @param useDefault whether to use the default compiler.
   * @param clsiServiceUrl the CLSI service url.
   * @param clsiServiceToken the CLSI service token.
   * @param clsiAsyncPath the CLSI service's async output path.
   * @param compilerName the CLSI service's compiler name.
   */
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
  
  /**
   * Retrieves the CLSI service's async output path.
   * 
   * @return the CLSI service's async output path.
   */
  public String getClsiAsyncPath() {
    return clsiAsyncPath;
  }

  /**
   * Retrieves the CLSI service token.
   * 
   * @return the CLSI service token.
   */
  public String getClsiServiceToken() {
    return clsiServiceToken;
  }

  /**
   * Retrieves the CLSI service url.
   * 
   * @return the CLSI service url.
   */
  public String getClsiServiceUrl() {
    return clsiServiceUrl;
  }

  /**
   * Retrieves the command's unique id.
   * 
   * @return the command's unique id.
   */
  @Override
  public int getCommandId() {
    return serialUid;
  }

  /**
   * Retrieves the CLSI service's compiler name.
   * 
   * @return the CLSI service's compiler name.
   */
  public String getCompilerName() {
    return compilerName;
  }

  /**
   * Whether to use the default compiler.
   * 
   * @return whether to use the default compiler.
   */
  public boolean isUseDefault() {
    return useDefault;
  }

  /**
   * Sets the CLSI service's async output path.
   * 
   * @param clsiAsyncPath the CLSI service's async output path.
   */
  public void setClsiAsyncPath(String clsiAsyncPath) {
    this.clsiAsyncPath = clsiAsyncPath;
  }

  /**
   * Sets the CLSI service token.
   * 
   * @param clsiServiceToken the CLSI service token.
   */
  public void setClsiServiceToken(String clsiServiceToken) {
    this.clsiServiceToken = clsiServiceToken;
  }

  /**
   * Sets the CLSI service url.
   * 
   * @param clsiServiceUrl the CLSI service url.
   */
  public void setClsiServiceUrl(String clsiServiceUrl) {
    this.clsiServiceUrl = clsiServiceUrl;
  }

  /**
   * Sets the CLSI service's compiler name.
   * 
   * @param compilerName the CLSI service's compiler name.
   */
  public void setCompilerName(String compilerName) {
    this.compilerName = compilerName;
  }

  /**
   * Specifies whether to use the default compiler.
   * 
   * @param useDefault whether to use the default compiler.
   */
  public void setUseDefault(boolean useDefault) {
    this.useDefault = useDefault;
  }

}
