package org.latexlab.docs.client.commands;

public class SystemSpecifyCompilerSettingsCommand extends Command {

  public final static int serialUid = 92;
  
  public SystemSpecifyCompilerSettingsCommand() {
	super("Specify the LaTeX compiler settings.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }

}
