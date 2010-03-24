package org.latexlab.docs.client.resources.icons.latex;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

/**
 * An image bundle storing GDBE icons and images.
 */
@SuppressWarnings("deprecation")
public interface LatexComparisonIconsImageBundle extends ImageBundle {

  public AbstractImagePrototype LesserThanOrEqual();
  public AbstractImagePrototype GreaterThanOrEqual();
  public AbstractImagePrototype Preceding();
  public AbstractImagePrototype Succeding();
  public AbstractImagePrototype TriangleLeft();
  public AbstractImagePrototype TriangleRight();
  public AbstractImagePrototype NotEqual();
  public AbstractImagePrototype Equivalent();
  public AbstractImagePrototype Approximately();
  public AbstractImagePrototype Congruent();
  public AbstractImagePrototype PropTo();
  
}
