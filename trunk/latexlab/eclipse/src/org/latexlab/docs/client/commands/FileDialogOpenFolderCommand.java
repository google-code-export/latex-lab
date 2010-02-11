package org.latexlab.docs.client.commands;

public class FileDialogOpenFolderCommand extends Command{

  public final static int serialUid = 35;

  public FileDialogOpenFolderCommand() {
    super("Open document.");
  }
  
  @Override
  public int getCommandId() {
    return serialUid;
  }
  
}
