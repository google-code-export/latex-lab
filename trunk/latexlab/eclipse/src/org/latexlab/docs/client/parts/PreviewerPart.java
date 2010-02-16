package org.latexlab.docs.client.parts;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PreviewerPart extends Composite {

  private VerticalPanel content;
	
  public PreviewerPart() {
	content = new VerticalPanel();
    content.setSize("100%", "100%");
    content.setStylePrimaryName("lab-Previewer");
	initWidget(content);
  }
  
  public void clear() {
    content.clear();
  }
	
  public void setUrls(String[] urls) {
	content.clear();
	for (String url : urls) {
	  Image img = new Image();
	  img.setUrl(url);
	  content.add(img);
	}
  }
  
}
