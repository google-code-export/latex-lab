package org.latexlab.docs.client.widgets;

import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Image;

/**
 * An image widget with zoom in/out support.
 */
public class ScalableImage extends Image {
	
  private int imgWidth = 0, imgHeight = 0;
  private double scale = 1;

  /**
   * Constructs a ScalableImage.
   * 
   * @param url the image url
   * @param scale the scale
   */
  public ScalableImage(String url, double scale) {
	super(url);
	this.scale = scale;
	this.getElement().getStyle().setOpacity(0);
	this.addLoadHandler(new LoadHandler() {
		@Override
		public void onLoad(LoadEvent event) {
		  imgWidth = ScalableImage.this.getOffsetWidth();
		  imgHeight = ScalableImage.this.getOffsetHeight();
		  ScalableImage.this.getElement().getStyle().setOpacity(1);
		  setScale(ScalableImage.this.scale);
		}
	});
  }
	
  /**
   * Sets the scale to the specified value.
   * 
   * @param scale the scale
   */
  public void setScale(double scale) {
	if (imgWidth == 0) {
	  if (this.getOffsetWidth() > 0) {
		imgWidth = this.getOffsetWidth();
	  } else {
		return;
	  }
	}
	if (imgHeight == 0) {
	  if (this.getOffsetHeight() > 0) {
		  imgHeight = this.getOffsetHeight();
	  } else {
		return;
	  }
	}
	this.scale = scale;
    double width = scale * imgWidth;
    double height = scale * imgHeight;
    this.setPixelSize((int)width, (int)height);
  }
  
}
