package org.latexlab.docs.client.resources.icons.latex;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

//TODO: eliminate the deprecated ImageBundle parent reference.
/**
* An image bundle storing LaTeX icons.
*/
@SuppressWarnings("deprecation")
public interface LatexAccentsIconsImageBundle extends ImageBundle {

  public AbstractImagePrototype HatAccent();
  public AbstractImagePrototype CheckAccent();
  public AbstractImagePrototype BreveAccent();
  public AbstractImagePrototype AcuteAccent();
  public AbstractImagePrototype GraveAccent();
  public AbstractImagePrototype TildeAccent();
  public AbstractImagePrototype BarAccent();
  public AbstractImagePrototype VectorAccent();
  public AbstractImagePrototype DotAccent();
  public AbstractImagePrototype DoubleDotAccent();
  
}
