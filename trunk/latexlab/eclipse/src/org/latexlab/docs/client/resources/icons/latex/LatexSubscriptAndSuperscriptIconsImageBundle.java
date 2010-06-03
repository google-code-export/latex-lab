package org.latexlab.docs.client.resources.icons.latex;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

//TODO: eliminate the deprecated ImageBundle parent reference.
/**
* An image bundle storing LaTeX icons.
*/
@SuppressWarnings("deprecation")
public interface LatexSubscriptAndSuperscriptIconsImageBundle extends ImageBundle {

  public AbstractImagePrototype RightSuperscript();
  public AbstractImagePrototype RightSubscript();
  public AbstractImagePrototype RightSubAndSuperScripts();
  public AbstractImagePrototype LeftSuperscript();
  public AbstractImagePrototype LeftSubscript();
  public AbstractImagePrototype LeftSubAndSuperScripts();
  public AbstractImagePrototype TopScript();
  public AbstractImagePrototype LowScript();
  public AbstractImagePrototype TopAndLowScripts();
  
}
