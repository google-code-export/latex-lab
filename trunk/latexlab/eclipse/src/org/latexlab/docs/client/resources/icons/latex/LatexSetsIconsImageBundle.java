package org.latexlab.docs.client.resources.icons.latex;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

//TODO: eliminate the deprecated ImageBundle parent reference.
/**
* An image bundle storing LaTeX icons.
*/
@SuppressWarnings("deprecation")
public interface LatexSetsIconsImageBundle extends ImageBundle {

  public AbstractImagePrototype ElementOf();
  public AbstractImagePrototype NotElementOf();
  public AbstractImagePrototype Union();
  public AbstractImagePrototype Intersection();
  public AbstractImagePrototype UnionLarge();
  public AbstractImagePrototype IntersectionLarge();
  public AbstractImagePrototype ContainedIn();
  public AbstractImagePrototype Contains();
  public AbstractImagePrototype ContainedInOrEqualTo();
  public AbstractImagePrototype ContainsOrEqualTo();
  
}
