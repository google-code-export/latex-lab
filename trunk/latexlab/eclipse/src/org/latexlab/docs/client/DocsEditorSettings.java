package org.latexlab.docs.client;

import java.util.ArrayList;

import org.latexlab.docs.client.gdocs.DocumentServiceEntry;

import com.google.gwt.user.client.Window;

/**
 * Stores configuration settings.
 */
public class DocsEditorSettings {
  
  private int autoSaveInterval = 10000;
  private String clsiServiceUrl, clsiServiceToken, clsiServiceId, clsiAsyncPath, compilerName;
  private DocumentServiceEntry primaryResource;
  private ArrayList<DocumentServiceEntry> resources;
  private boolean reuseToolbarWindows = true, colorSyntax = true, showLineNumbers = true,
      wrapText = true, checkSpelling = false, hasCompilerSettings = false,
      enforceEtags = false, isDevelopment = false, useAutoSave = true;

  /**
   * Constructs a new settings object.
   */
  public DocsEditorSettings() {
    resources = new ArrayList<DocumentServiceEntry>();
    isDevelopment = Window.Location.getHref().startsWith("http://dev.");
  }

  /**
   * Retrieves the auto-save interval, in milliseconds.
   * 
   * @return the auto-save interval, in milliseconds.
   */
  public int getAutoSaveInterval() {
    return autoSaveInterval;
  }

  /**
   * Retrieves the CLSI service async output path.
   * 
   * @return the CLSI service async output path.
   */
  public String getClsiAsyncPath() {
    return clsiAsyncPath;
  }

  /**
   * Retrieves the CLSI service user ID.
   * 
   * @return the CLSI service user ID.
   */
  public String getClsiServiceId() {
    return clsiServiceId;
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
   * Retrieves the CLSI service compiler name.
   * 
   * @return the CLSI service compiler name.
   */
  public String getCompilerName() {
    return compilerName;
  }

  /**
   * Retrieves whether CLSI service settings have been set.
   * 
   * @return whether CLSI service settings have been set.
   */
  public boolean getHasCompilerSettings() {
    return hasCompilerSettings;
  }

  /**
   * Retrieves the primary compile resource.
   * 
   * @return the primary compile resource.
   */
  public DocumentServiceEntry getPrimaryResource() {
    return primaryResource;
  }

  /**
   * Retrieves any additional compile resources.
   * 
   * @return any additional compile resources.
   */
  public ArrayList<DocumentServiceEntry> getResources() {
    return resources;
  }

  /**
   * Whether to check spelling in the text editor.
   * 
   * @return whether to check spelling in the text editor.
   */
  public boolean isCheckSpelling() {
    return checkSpelling;
  }

  /**
   * Whether to use syntax coloring on the text editor.
   * 
   * @return whether to use syntax coloring on the text editor.
   */
  public boolean isColorSyntax() {
    return colorSyntax;
  }

  /**
   * Whether the current environment is a development environment.
   * 
   * @return whether the current environment is a development environment.
   */
  public boolean isDevelopment() {
    return isDevelopment;
  }

  /**
   * Whether to enforce etags when updating the current document.
   * 
   * @return whether to enforce etags when updating the current document.
   */
  public boolean isEnforceEtags() {
    return enforceEtags;
  }

  /**
   * Whether to reuse toolbar windows.
   * 
   * @return whether to reuse toolbar windows.
   */
  public boolean isReuseToolbarWindows() {
    return reuseToolbarWindows;
  }

  /**
   * Whether to display line numbers in the text editor.
   * 
   * @return whether to display line numbers in the text editor.
   */
  public boolean isShowLineNumbers() {
    return showLineNumbers;
  }

  /**
   * Whether to automatically save document when changed.
   * 
   * @return whether to automatically save document when changed.
   */
  public boolean isUseAutoSave() {
    return useAutoSave;
  }

  /**
   * Whether to wrap text in the text editor.
   * 
   * @return whether to wrap text in the text editor.
   */
  public boolean isWrapText() {
    return wrapText;
  }

  /**
   * Specifies the auto-save interval, in milliseconds.
   * 
   * @param autoSaveInterval the auto-save interval, in milliseconds.
   */
  public void setAutoSaveInterval(int autoSaveInterval) {
    this.autoSaveInterval = autoSaveInterval;
  }

  /**
   * Specifies whether to check spelling in the text editor.
   * 
   * @param checkSpelling whether to check spelling in the text editor.
   */
  public void setCheckSpelling(boolean checkSpelling) {
    this.checkSpelling = checkSpelling;
  }

  /**
   * Sets the CLSI service async output path.
   * 
   * @param clsiAsyncPath the CLSI service async output path.
   */
  public void setClsiAsyncPath(String clsiAsyncPath) {
    this.clsiAsyncPath = clsiAsyncPath;
  }

  /**
   * Sets the CLSI service user ID.
   * 
   * @param clsiServiceId the CLSI service user ID.
   */
  public void setClsiServiceId(String clsiServiceId) {
    this.clsiServiceId = clsiServiceId;
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
   * Specifies whether to use syntax coloring on the text editor.
   * 
   * @param colorSyntax whether to use syntax coloring on the text editor.
   */
  public void setColorSyntax(boolean colorSyntax) {
    this.colorSyntax = colorSyntax;
  }

  /**
   * Sets the CLSI service compiler name.
   * 
   * @param compilerName the CLSI service compiler name.
   */
  public void setCompilerName(String compilerName) {
    this.compilerName = compilerName;
  }

  /**
   * Specifies whether the current environment is a development environment.
   * 
   * @param isDevelopment whether the current environment is a development environment.
   */
  public void setDevelopment(boolean isDevelopment) {
    this.isDevelopment = isDevelopment;
  }

  /**
   * Specifies whether to enforce etags when updating the current document.
   * 
   * @param enforceEtags whether to enforce etags when updating the current document.
   */
  public void setEnforceEtags(boolean enforceEtags) {
    this.enforceEtags = enforceEtags;
  }

  /**
   * Specifies whether CLSI service settings have ben set.
   * 
   * @param hasCompilerSettings whether CLSI service settings have been set.
   */
  public void setHasCompilerSettings(boolean hasCompilerSettings) {
    this.hasCompilerSettings = hasCompilerSettings;
  }

  /**
   * Sets the primary compile resource.
   * 
   * @param primaryResource the primary compile resource.
   */
  public void setPrimaryResource(DocumentServiceEntry primaryResource) {
    this.primaryResource = primaryResource;
  }

  /**
   * Sets the additional compile resources.
   * 
   * @param resources the additional compile resources.
   */
  public void setResources(ArrayList<DocumentServiceEntry> resources) {
    this.resources = resources;
  }

  /**
   * Specifies whether to reuse toolbar windows.
   * 
   * @param reuseToolbarWindows whether to reuse toolbar windows.
   */
  public void setReuseToolbarWindows(boolean reuseToolbarWindows) {
    this.reuseToolbarWindows = reuseToolbarWindows;
  }

  /**
   * Specifies whether to display line numbers in the text editor.
   * 
   * @param showLineNumbers whether to display line numbers in the text editor.
   */
  public void setShowLineNumbers(boolean showLineNumbers) {
    this.showLineNumbers = showLineNumbers;
  }

  /**
   * Specifies whether to automatically save document when changed.
   * 
   * @param useAutoSave whether to automatically save document when changed.
   */
  public void setUseAutoSave(boolean useAutoSave) {
    this.useAutoSave = useAutoSave;
  }

  /**
   * Specifies whether to wrap text in the text editor.
   * 
   * @param wrapText whether to wrap text in the text editor.
   */
  public void setWrapText(boolean wrapText) {
    this.wrapText = wrapText;
  }
  
}
