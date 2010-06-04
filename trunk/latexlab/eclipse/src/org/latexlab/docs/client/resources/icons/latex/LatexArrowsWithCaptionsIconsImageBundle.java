package org.latexlab.docs.client.resources.icons.latex;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

//TODO: eliminate the deprecated ImageBundle parent reference.
/**
* An image bundle storing LaTeX icons.
*/
@SuppressWarnings("deprecation")
public interface LatexArrowsWithCaptionsIconsImageBundle extends ImageBundle {

  public AbstractImagePrototype RightArrowTopCaption();
  public AbstractImagePrototype LeftArrowTopCaption();
  public AbstractImagePrototype LeftRightArrowTopCaption();
  public AbstractImagePrototype RightArrowBottomCaption();
  public AbstractImagePrototype LeftArrowBottomCaption();
  public AbstractImagePrototype LeftRightArrowBottomCaption();
  
}
