package org.latexlab.docs.client.resources.icons.latex;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

/**
 * An image bundle storing GDBE icons and images.
 */
@SuppressWarnings("deprecation")
public interface LatexLogicalIconsImageBundle extends ImageBundle {

  public AbstractImagePrototype NotIn();
  public AbstractImagePrototype Exists();
  public AbstractImagePrototype ForAll();
  public AbstractImagePrototype Negation();
  public AbstractImagePrototype And();
  public AbstractImagePrototype Or();
  
}
