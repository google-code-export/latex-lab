package org.latexlab.docs.client.dialogs;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.latexlab.docs.client.events.CommandHandler;

/**
 * A dialog window displaying details of the application.
 */
public class FeedbackDialog extends Dialog {

  protected static FeedbackDialog instance;
  
  public static FeedbackDialog getInstance(CommandHandler handler) {
    if (instance == null) {
      instance = new FeedbackDialog();
      instance.addCommandHandler(handler);
    }
    return instance;
  }

  /**
   * Constructs an About dialog window.
   */
  public FeedbackDialog() {
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
