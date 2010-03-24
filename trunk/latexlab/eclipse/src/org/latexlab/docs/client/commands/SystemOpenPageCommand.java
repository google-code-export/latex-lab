package org.latexlab.docs.client.commands;

public class SystemOpenPageCommand extends Command {

  public final static int serialUid = 105;
  
  private String name, url;
  
  public SystemOpenPageCommand(String name, String url) {
	super("Open a webpage.");
	this.name = name;
	this.url = url;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public int getCommandId() {
    return serialUid;
  }

}
