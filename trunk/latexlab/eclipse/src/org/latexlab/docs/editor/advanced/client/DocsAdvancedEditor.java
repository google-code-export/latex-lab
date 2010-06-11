package org.latexlab.docs.editor.advanced.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;

import org.latexlab.docs.client.content.dialogs.StaticLoadingDialog;
import org.latexlab.docs.client.events.AsyncInstantiationCallback;

/**
 * The LaTeX Lab document editor module.
 */
public class DocsAdvancedEditor implements EntryPoint {

  /**
   * Load and initialize the controller.
   */
  public void onModuleLoad() {
	StaticLoadingDialog.get().center("Loading components...");
	DocsAdvancedEditorController.get(
	    new AsyncInstantiationCallback<DocsAdvancedEditorController>() {
			@Override
			public void onFailure(Throwable caught) {
			  Window.alert("There was an error loading a required component.");
			}
			@Override
			public void onSuccess(DocsAdvancedEditorController result) {
		      StaticLoadingDialog.get().hide();
			  result.initialize();
			}
	    }
	);
  }
  
}