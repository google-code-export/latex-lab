package org.latexlab.docs.client.content.icons.latex;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

//TODO: eliminate the deprecated ImageBundle parent reference.
/**
* An image bundle storing LaTeX icons.
*/
@SuppressWarnings("deprecation")
public interface LatexMathIconsImageBundle extends ImageBundle {

  public AbstractImagePrototype Fraction();
  public AbstractImagePrototype Division();
  public AbstractImagePrototype SquareRoot();
  public AbstractImagePrototype NthRoot();
  
}
