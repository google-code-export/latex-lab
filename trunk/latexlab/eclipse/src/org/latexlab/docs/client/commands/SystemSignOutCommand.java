package org.latexlab.docs.client.commands;

public class SystemSignOutCommand extends Command {

  public final static int serialUid = 24;
  
  private String returnUrl;
	  
  public SystemSignOutCommand(String returnUrl) {
	super("Sign out.");
	this.returnUrl = returnUrl;
  }

  public String getReturnUrl() {
    return returnUrl;
  }

  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl;
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }
  
}
