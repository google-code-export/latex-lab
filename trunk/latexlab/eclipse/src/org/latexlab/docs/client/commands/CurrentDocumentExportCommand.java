package org.latexlab.docs.client.commands;

public class CurrentDocumentExportCommand extends Command {

  public final static int serialUid = 89;
  
  private String exportFormat;
  
  public CurrentDocumentExportCommand(String exportFormat) {
	super("Export current document.");
	this.exportFormat = exportFormat;
  }
  
  public String getExportFormat() {
	return exportFormat;
  }

  public void setExportFormat(String exportFormat) {
    this.exportFormat = exportFormat;
  }

  @Override
  public int getCommandId() {
    return serialUid;
  }

}
