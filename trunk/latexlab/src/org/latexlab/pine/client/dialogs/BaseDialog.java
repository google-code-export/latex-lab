package org.latexlab.pine.client.dialogs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widgetideas.client.GlassPanel;

import org.latexlab.pine.client.events.CommandEvent;
import org.latexlab.pine.client.events.CommandEventListener;
import org.latexlab.pine.client.resources.icons.EditorIconsImageBundle;

import java.util.ArrayList;

/**
 * A base dialog box implementation with glass panel support and close button.
 */
public class BaseDialog extends DialogBox{
  
  protected ArrayList<CommandEventListener> listeners = new ArrayList<CommandEventListener>();
  protected FlexTable mainPanel;
  protected Label titleLabel;
  protected PushButton closeButton;
  protected static GlassPanel glassPanel;
  
  /**
   * Constructs a base dialog window.
   * 
   * @param title the dialog window's title
   * @param modal whether the dialog window is modal
   */
  protected BaseDialog(String title, boolean modal) {
    setModal(modal);
    setGlassPanelVisbility(false);
    EditorIconsImageBundle editorIcons = (EditorIconsImageBundle)GWT.create(EditorIconsImageBundle.class);
    titleLabel = new Label();
    titleLabel.setText(title);
    closeButton = new PushButton(editorIcons.CloseBlue().createImage());
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
    mainPanel.getCellFormatter().setStylePrimaryName(0, 0, "Title");
    mainPanel.getCellFormatter().setStylePrimaryName(1, 0, "Tabs");
    mainPanel.getCellFormatter().setStylePrimaryName(2, 0, "Content");
    mainPanel.getFlexCellFormatter().setColSpan(1, 0, 2);
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
    super.show();
    if (this.isModal()) setGlassPanelVisbility(true);
  }
  
  /**
   * Hides the dialog window.
   */
  @Override
  public void hide() {
    super.hide();
    setGlassPanelVisbility(false);
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
  
  /**
   * Registers a command event listener.
   * 
   * @param listener the command event listener to add
   */
  public void addCommandEventListener(CommandEventListener listener) {
    listeners.add(listener);
  }
  
  /**
   * Removes a command event listener.
   * 
   * @param listener the command event listener to remove
   */
  public void removeCommandEventListener(CommandEventListener listener) {
    listeners.remove(listener);
  }
  
  /**
   * Fires a command event.
   * 
   * @param e the command event to fire
   */
  protected void fireOnCommand(CommandEvent e) {
    for (int i=0; i<listeners.size(); i++) {
      listeners.get(i).onCommand(e);
    }
  }
  
  /**
   * Sets the shared glass panel's visibility.
   * 
   * @param visible whether the glass panel should be visible
   */
  protected static void setGlassPanelVisbility(boolean visible) {
    if (glassPanel == null) {
      glassPanel = new GlassPanel(false);
      RootPanel.get().add(glassPanel, 0, 0);
    }
    glassPanel.setVisible(visible);
  }
  
}
