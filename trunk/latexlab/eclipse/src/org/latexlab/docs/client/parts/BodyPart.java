package org.latexlab.docs.client.parts;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.VerticalSplitPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A specialized, non-reusable widget containing the body frame.
 */
public class BodyPart extends Composite {

  private HorizontalSplitPanel hContent;
  private VerticalSplitPanel vContent;
  
  /**
   * Constructs a new Body part.
   */
  public BodyPart() {
    vContent = new VerticalSplitPanel();
    vContent.setWidth("100%");
    vContent.setHeight("100%");
    vContent.setSplitPosition("100%");
    hContent = new HorizontalSplitPanel();
    hContent.setWidth("100%");
    hContent.setHeight("100%");
    hContent.setSplitPosition("100%");
    vContent.setTopWidget(hContent);
    initWidget(vContent);
  }
  
  /**
   * Sets the bottom widget.
   * 
   * @param w the widget to use on the bottom panel
   */
  public void setBottomWidget(Widget w){
    vContent.setBottomWidget(w);
  }
  
  /**
   * Sets the horizontal split position.
   * 
   * @param pos the horizontal split position
   */
  public void setHorizontalSplitPosition(String pos) {
    hContent.setSplitPosition(pos);
  }
  
  /**
   * Sets the top-left widget.
   * 
   * @param w the widget to use on the top-left panel
   */
  public void setTopLeftWidget(Widget w){
    hContent.setLeftWidget(w);
  }
  
  /**
   * Sets the top-right widget.
   * 
   * @param w the widget to use on the top-right panel
   */
  public void setTopRightWidget(Widget w){
    hContent.setRightWidget(w);
  }
  
  /**
   * Sets the vertical split position.
   * 
   * @param pos the vertical split position
   */
  public void setVerticalSplitPosition(String pos) {
    vContent.setSplitPosition(pos);
  }

}
