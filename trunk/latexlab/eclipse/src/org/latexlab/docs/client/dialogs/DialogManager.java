package org.latexlab.docs.client.dialogs;

public class DialogManager {
	
  private Dialog activeDialog;

  public DialogManager() {
    
  }
  
  public void showDialog(Dialog dialog) {
    activeDialog = dialog;
    activeDialog.center();
  }
  
  public void hideDialog() {
    if (activeDialog != null) {
      activeDialog.hide();
      activeDialog = null;
    }
  }
  
}
