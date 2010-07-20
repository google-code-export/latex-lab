package org.latexlab.docs.client.widgets;

import org.latexlab.docs.client.content.icons.Icons;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

/**
 * A base dialog box implementation with glass panel support and close button.
 */
public abstract class Dialog extends DialogBox {

  /**
   * Defines the number of visible modal windows.
   */
  public static int VISIBLE_MODEL_DIALOGS = 0;
	
  protected PushButton closeButton;
  protected final int DIALOG_ZINDEX = 6;
  protected boolean isRaised;
  protected FlexTable mainPanel;
  protected Label titleLabel;
  
  /**
   * Constructs a base dialog window.
   * 
   * @param title the dialog window's title
   * @param modal whether the dialog window is modal
   */
  protected Dialog(String title, boolean modal) {
    setModal(modal);
    this.getElement().getStyle().setZIndex(DIALOG_ZINDEX);
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
    mainPanel.getCellFormatter().setStylePrimaryName(1, 0, "SubHeader");
    mainPanel.getCellFormatter().setVisible(1, 0, false);
    mainPanel.getCellFormatter().setStylePrimaryName(2, 0, "Content");
    mainPanel.getFlexCellFormatter().setColSpan(1, 0, 2);
    mainPanel.getFlexCellFormatter().setColSpan(2, 0, 2);
    mainPanel.setWidget(0, 0, titleLabel);
    mainPanel.setWidget(0, 1, closeButton);
    setWidget(mainPanel);
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
   * Shows and centers the dialog window on the page.
   */
  @Override
  public void center() {
	super.center();
	show();
  }

  /**
   * Retrieves the dialog's content widget.
   */
  public void getContentWidget() {
    mainPanel.getWidget(2, 0);
  }
  
  /**
   * Retrieves the dialog's title.
   */
  public String getTitle() {
    return titleLabel.getText();
  }
  
  /**
   * Retrieves the dialog's top widget.
   */
  public void getTopWidget() {
    mainPanel.getWidget(1, 0);
  }
  
  /**
   * Retrieves the z-index for this dialog window.
   * 
   * @return the z-index for this dialog window.
   */
  private int getZIndex() {
	try {
	  String z = this.getElement().getStyle().getZIndex();
	  if (z != null && !z.equals("")) {
	    return Integer.parseInt(z);
	  }
	} catch (Exception x) { }
	return DIALOG_ZINDEX;
  }
  
  /**
   * Hides the dialog window. If a glass panel is visible then it is hidden,
   * unless another modal window is also visible.
   */
  @Override
  public void hide() {
    if (this.isShowing() && this.isModal()) {
      if (isRaised) {
  	    isRaised = false;
  	    int z = getZIndex();
        this.getElement().getStyle().setZIndex(z - 2);
        if (VISIBLE_MODEL_DIALOGS > 0) {
          GlassPanel.setGlassPanelVisibility(true, z - 3);
        }
      } else {
        GlassPanel.setGlassPanelVisibility(false, getZIndex()-1);
      }
    }
    super.hide();
  }
  
  /**
   * Sets the dialog's content widget.
   * 
   * @param w the widget to set as the dialog's contents
   */
  public void setContentWidget(Widget w) {
    mainPanel.setWidget(2, 0, w);
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
   * Sets the dialog's top widget.
   * 
   * @param w the widget to set as the dialog's top widget
   */
  public void setTopWidget(Widget w) {
	mainPanel.getCellFormatter().setVisible(1, 0, true);
    mainPanel.setWidget(1, 0, w);
  }
  
  /**
   * Makes the dialog window visible. If the window is set to modal then
   * a glass panel is displayed.
   */
  @Override
  public void show() {
    if (!this.isShowing() && this.isModal()) {
      VISIBLE_MODEL_DIALOGS++;
      if (GlassPanel.getGlassPanelVisibility()) {
      	isRaised = true;
      	int z = getZIndex();
        this.getElement().getStyle().setZIndex(z + 2);
      	GlassPanel.setGlassPanelVisibility(true, z + 1);
      } else {
      	GlassPanel.setGlassPanelVisibility(true, getZIndex()-1);
      }
    }
    super.show();
  }
  
}
