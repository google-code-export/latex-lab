package org.latexlab.docs.client.dialogs;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.VerticalPanel;


/**
 * A dialog window displaying details of the application.
 */
public class CompilerOptionsDialog extends Dialog {

  /**
   * Constructs an About dialog window.
   */
  public CompilerOptionsDialog() {
    super("About", true);
    addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        hide();
      }
    });
    VerticalPanel content = new VerticalPanel();
    setContentWidget(content);
  }
}
