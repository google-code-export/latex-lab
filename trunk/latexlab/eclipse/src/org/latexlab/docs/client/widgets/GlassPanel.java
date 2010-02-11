package org.latexlab.docs.client.widgets;

import com.google.gwt.user.client.ui.RootPanel;

public class GlassPanel {

  protected static
    com.google.gwt.widgetideas.client.GlassPanel glassPanel;
  
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
