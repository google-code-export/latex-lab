package org.latexlab.docs.client.dialogs;

import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.widgets.GlassPanel;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

/**
 * A base dialog box implementation with glass panel support and close button.
 */
public abstract class Dialog extends DialogBox implements HasCommandHandlers {

  protected final int DIALOG_ZINDEX = 5;
  protected HandlerManager manager;
  protected FlexTable mainPanel;
  protected Label titleLabel;
  protected PushButton closeButton;
  
  /**
   * Constructs a base dialog window.
   * 
   * @param title the dialog window's title
   * @param modal whether the dialog window is modal
   */
  protected Dialog(String title, boolean modal) {
	manager = new HandlerManager(this);
    setModal(modal);
    this.getElement().getStyle().setZIndex(5);
    GlassPanel.setGlassPanelVisibility(false, DIALOG_ZINDEX - 1);
    titleLabel = new Label();
    titleLabel.setText(title);
    closeButton = new PushButton(Icons.editorIcons.CloseBlue().createImage());
    closeButton.setStylePrimaryName("CloseButton");
    mainPanel = new FlexTable();
    mainPanel.setCellSpacing(0);
    mainPanel.setCellPadding(0);
    mainPanel.setWidth("100%");
    mainPanel.insertRow(0);
    mainPanel.insertCell(0, 0);
    mainPanel.insertCell(0, 1);
    mainPanel.insertRow(1);
    mainPanel.insertCell(1, 0);
    mainPanel.insertRow(2);
    mainPanel.insertCell(2, 0);
    mainPanel.getRowFormatter().setStylePrimaryName(0, "Header");
    mainPanel.getCellFormatter().setHorizontalAlignment(0, 1, HorizontalPanel.ALIGN_RIGHT);
    mainPanel.getCellFormatter().setWidth(0, 1, "16px");
    mainPanel.getCellFormatter().setStylePrimaryName(0, 0, "Title");
    mainPanel.getCellFormatter().setStylePrimaryName(1, 0, "Tabs");
    mainPanel.getCellFormatter().setStylePrimaryName(2, 0, "Content");
    mainPanel.getFlexCellFormatter().setColSpan(1, 0, 2);
    mainPanel.getFlexCellFormatter().setStylePrimaryName(1, 0, "SubHeader");
    mainPanel.getFlexCellFormatter().setColSpan(2, 0, 2);
    mainPanel.setWidget(0, 0, titleLabel);
    mainPanel.setWidget(0, 1, closeButton);
    setWidget(mainPanel);
  }
  
  /**
   * Makes the dialog window visible. If window is set to modal then
   * a glass panel is displayed.
   */
  @Override
  public void show() {
    if (this.isModal()) {
      GlassPanel.setGlassPanelVisibility(true, DIALOG_ZINDEX-1);
    }
    super.show();
  }
  
  /**
   * Hides the dialog window.
   */
  @Override
  public void hide() {
    if (this.isShowing() && this.isModal()) {
      GlassPanel.setGlassPanelVisibility(false, DIALOG_ZINDEX-1);
    }
    super.hide();
  }
  
  /**
   * Retrieves the dialog contents widget.
   */
  public void getContentWidget() {
    mainPanel.getWidget(2, 0);
  }
  
  /**
   * Sets the dialog contents widget.
   * 
   * @param w the widget to set as the dialog's contents
   */
  public void setContentWidget(Widget w) {
    mainPanel.setWidget(2, 0, w);
  }
  
  /**
   * Retrieves the dialog's top widget.
   */
  public void getTopWidget() {
    mainPanel.getWidget(1, 0);
  }
  
  /**
   * Sets the dialog's top widget.
   * 
   * @param w the widget to set as the dialog's top widget
   */
  public void setTopWidget(Widget w) {
    mainPanel.setWidget(1, 0, w);
  }
  
  /**
   * Retrieves the dialog's title.
   */
  public String getTitle() {
    return titleLabel.getText();
  }
  
  /**
   * Sets the dialog's title.
   * 
   * @param title the dialog's title
   */
  public void setTitle(String title) {
    titleLabel.setText(title);
  }
  
  /**
   * Register a click handler with the dialog's close button.
   * 
   * @param closeIconClickHandler the click handler to add
   */
  public void addClickHandler(ClickHandler closeIconClickHandler) {
    closeButton.addClickHandler(closeIconClickHandler);
  }

  @Override
  public HandlerRegistration addCommandHandler(CommandHandler handler) {
	return manager.addHandler(CommandEvent.getType(), handler);
  }
  
  @Override
  public void fireEvent(GwtEvent<?> event) {
	manager.fireEvent(event);
  }
  
}
