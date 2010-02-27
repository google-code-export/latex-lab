package org.latexlab.docs.client;

import java.util.ArrayList;

import org.latexlab.docs.client.gdocs.DocumentServiceEntry;

public class DocsEditorSettings {
  
  private boolean reuseToolbarWindows = true;
  private String clsiServiceUrl, clsiServiceToken, clsiServiceId, clsiAsyncPath, compilerName;
  private ArrayList<DocumentServiceEntry> resources;
	
  public DocsEditorSettings() {
    resources = new ArrayList<DocumentServiceEntry>();
  }

  public boolean isReuseToolbarWindows() {
    return reuseToolbarWindows;
  }

  public void setReuseToolbarWindows(boolean reuseToolbarWindows) {
    this.reuseToolbarWindows = reuseToolbarWindows;
  }

  public ArrayList<DocumentServiceEntry> getResources() {
    return resources;
  }

  public void setResources(ArrayList<DocumentServiceEntry> resources) {
    this.resources = resources;
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

  public String getClsiServiceId() {
    return clsiServiceId;
  }

  public void setClsiServiceId(String clsiServiceId) {
    this.clsiServiceId = clsiServiceId;
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

}
