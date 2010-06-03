package org.latexlab.docs.client.commands;

/**
 * A command for exporting the current document in a given format.
 */
public class CurrentDocumentExportCommand extends Command {

  /**
   * The DVI export format code.
   */
  public final static String DVI = "DVI";
  
  /**
   * The PDF export format code.
   */
  public final static String PDF = "PDF";
  /**
   * The PostScript export format code.
   */
  public final static String PS = "PS";
  /**
   * The command's unique id.
   */
  public final static int serialUid = 89;
  
  protected String exportFormat;
  
  /**
   * Constructs a command for exporting the current document.
   * 
   * @param exportFormat the export format.
   */
  public CurrentDocumentExportCommand(String exportFormat) {
	super("Export current document.");
	this.exportFormat = exportFormat;
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
   * Retrieves the export format.
   * 
   * @return the export format.
   */
  public String getExportFormat() {
	return exportFormat;
  }

  /**
   * Sets the export format.
   * 
   * @param exportFormat the export format.
   */
  public void setExportFormat(String exportFormat) {
    this.exportFormat = exportFormat;
  }

}
