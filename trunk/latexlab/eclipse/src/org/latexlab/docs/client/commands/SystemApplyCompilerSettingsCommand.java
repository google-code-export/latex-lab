package org.latexlab.docs.client.commands;

/**
 * A command for applying compiler settings.
 */
public class SystemApplyCompilerSettingsCommand extends Command {

  /**
   * Defines the allowed compiler types.
   */
  public enum Compiler {
	LOCAL_MIKTEX_COMPILER,
	LOCAL_TEXLIVE_COMPILER,
	REMOTE_CUSTOM_COMPILER,
	REMOTE_DEFAULT_COMPILER
  }
	
  /**
   * The command's unique id.
   */
  public final static int serialUid = 93;
  
  protected String clsiServiceUrl, clsiServiceToken, clsiAsyncPath, compilerName;
  protected Compiler compiler;
  protected Command continueCommand;
  
  /**
   * Constructs a command for applying compiler settings.
   */
  public SystemApplyCompilerSettingsCommand() {
	super("Apply LaTeX compiler settings.");
    this.compiler = Compiler.REMOTE_DEFAULT_COMPILER;
  }
  
  /**
   * Constructs a command for applying compiler settings.
   * 
   * @param compiler whether the compiler type to use.
   * @param clsiServiceUrl the CLSI service url.
   * @param clsiServiceToken the CLSI service token.
   * @param clsiAsyncPath the CLSI service's async output path.
   * @param compilerName the CLSI service's compiler name.
   */
  public SystemApplyCompilerSettingsCommand(Compiler compiler,
	  String clsiServiceUrl, String clsiServiceToken,
	  String clsiAsyncPath, String compilerName) {
	super("Apply LaTeX compiler settings.");
	this.compiler = compiler;
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
   * Retrieves the compiler type to use.
   * 
   * @return the compiler type to use.
   */
  public Compiler getCompiler() {
    return compiler;
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
   * Retrieves the command to execute after compiler settings have been applied.
   * 
   * @return the command to execute after compiler settings have been applied.
   */
  public Command getContinueCommand() {
	return continueCommand;
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
   * Sets the compiler type to use.
   * 
   * @param compiler the compiler type to use.
   */
  public void setCompiler(Compiler compiler) {
    this.compiler = compiler;
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
   * Sets the command to execute after compiler settings have been applied.
   * 
   * @param continueCommand the command to execute after compiler settings have been applied.
   */
  public void setContinueCommand(Command continueCommand) {
    this.continueCommand = continueCommand;
  }

}
