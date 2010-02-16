package org.latexlab.docs.client;

import java.util.ArrayList;

import org.latexlab.docs.client.data.FileSystemEntry;

public class DocsEditorSettings {
  
  private boolean reuseToolbarWindows = true;
  private String clsiServiceUrl, clsiServiceToken, clsiServiceId, clsiAsyncPath, compilerName;
  private ArrayList<FileSystemEntry> resources;
	
  public DocsEditorSettings() {
    resources = new ArrayList<FileSystemEntry>();
  }

  public boolean isReuseToolbarWindows() {
    return reuseToolbarWindows;
  }

  public void setReuseToolbarWindows(boolean reuseToolbarWindows) {
    this.reuseToolbarWindows = reuseToolbarWindows;
  }

  public ArrayList<FileSystemEntry> getResources() {
    return resources;
  }

  public void setResources(ArrayList<FileSystemEntry> resources) {
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
