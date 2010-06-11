package org.latexlab.docs.client.content.icons.latex;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

//TODO: eliminate the deprecated ImageBundle parent reference.
/**
* An image bundle storing LaTeX icons.
*/
@SuppressWarnings("deprecation")
public interface LatexOperatorsIconsImageBundle extends ImageBundle {

  public AbstractImagePrototype Sum();
  public AbstractImagePrototype Integral();
  public AbstractImagePrototype OIntegral();
  public AbstractImagePrototype Product();
  public AbstractImagePrototype CoProduct();
  
}
