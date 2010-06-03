package org.latexlab.docs.editor.advanced.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;

import org.latexlab.docs.client.dialogs.LoadingDialog;
import org.latexlab.docs.client.events.AsyncInstantiationCallback;

/**
 * The LaTeX Lab document editor module.
 */
public class DocsAdvancedEditor implements EntryPoint {

  /**
   * Load and initialize the controller.
   */
  public void onModuleLoad() {
	LoadingDialog.get().center("Loading components...");
	DocsAdvancedEditorController.get(
	    new AsyncInstantiationCallback<DocsAdvancedEditorController>() {
			@Override
			public void onFailure(Throwable caught) {
			  Window.alert("There was an error loading a required component.");
			}
			@Override
			public void onSuccess(DocsAdvancedEditorController result) {
		      LoadingDialog.get().hide();
			  result.initialize();
			}
	    }
	);
  }
  
}