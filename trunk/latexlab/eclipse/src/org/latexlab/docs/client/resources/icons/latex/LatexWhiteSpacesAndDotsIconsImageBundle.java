package org.latexlab.docs.client.resources.icons.latex;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

//TODO: eliminate the deprecated ImageBundle parent reference.
/**
* An image bundle storing LaTeX icons.
*/
@SuppressWarnings("deprecation")
public interface LatexWhiteSpacesAndDotsIconsImageBundle extends ImageBundle {

  public AbstractImagePrototype SmallestSpace();
  public AbstractImagePrototype VerySmallSpace();
  public AbstractImagePrototype SmallSpace();
  public AbstractImagePrototype LittleSmallerSpace();
  public AbstractImagePrototype NormalSpace();
  public AbstractImagePrototype LowerDots();
  public AbstractImagePrototype CenterDots();
  public AbstractImagePrototype VerticalDots();
  public AbstractImagePrototype DiagonalDots();
  
}
