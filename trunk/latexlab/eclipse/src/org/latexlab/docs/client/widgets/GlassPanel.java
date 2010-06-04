package org.latexlab.docs.client.widgets;

import com.google.gwt.user.client.ui.RootPanel;

/**
 * A shared glass panel widget.
 */
public class GlassPanel {

  protected static
    com.google.gwt.widgetideas.client.GlassPanel glassPanel;
  
  /**
   * Retrieves the glass panel's visibility.
   * 
   * @return the glass panel's visibility
   */
  public static boolean getGlassPanelVisibility() {
	if (glassPanel == null) {
	  return false;
	}
	return glassPanel.isVisible();
  }
  
  /**
   * Sets the shared glass panel's visibility.
   * 
   * @param visible whether the glass panel should be visible
   */
  public static void setGlassPanelVisibility(boolean visible, int zIndex) {
    if (glassPanel == null) {
      glassPanel =
        new com.google.gwt.widgetideas.client.GlassPanel(false);
      RootPanel.get().add(glassPanel, 0, 0);
    }
    if (visible && zIndex > 0) {
    	glassPanel.getElement().getStyle().setZIndex(zIndex);
    }
    glassPanel.setVisible(visible);
  }
  
}
