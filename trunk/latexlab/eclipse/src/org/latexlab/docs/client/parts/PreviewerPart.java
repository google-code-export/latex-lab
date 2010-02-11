package org.latexlab.docs.client.parts;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;

public class PreviewerPart extends Composite {

  private Frame frame;
	
  public PreviewerPart() {
    frame = new Frame();
    frame.setSize("100%", "100%");
	frame.getElement().setPropertyInt("frameBorder", 0);
	frame.getElement().setPropertyInt("marginwidth", 0);
	frame.getElement().setPropertyInt("marginheight", 0);
	initWidget(frame);
  }
	
  public void setUrl(String url) {
	frame.setUrl(url);
  }
  
}
