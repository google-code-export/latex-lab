package org.latexlab.docs.client.resources.icons.latex;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

/**
 * An image bundle storing GDBE icons and images.
 */
@SuppressWarnings("deprecation")
public interface LatexAboveAndBelowIconsImageBundle extends ImageBundle {
	
  public AbstractImagePrototype Overline();
  public AbstractImagePrototype Underline();
  public AbstractImagePrototype WideHat();
  public AbstractImagePrototype WideTilde();
  public AbstractImagePrototype Stack();
  public AbstractImagePrototype OverBrace();
  public AbstractImagePrototype UnderBrace();
  
}
