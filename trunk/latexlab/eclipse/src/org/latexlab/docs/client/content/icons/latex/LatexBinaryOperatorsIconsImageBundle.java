package org.latexlab.docs.client.content.icons.latex;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

//TODO: eliminate the deprecated ImageBundle parent reference.
/**
* An image bundle storing LaTeX icons.
*/
@SuppressWarnings("deprecation")
public interface LatexBinaryOperatorsIconsImageBundle extends ImageBundle {

  public AbstractImagePrototype PlusMinus();
  public AbstractImagePrototype MinusPlus();
  public AbstractImagePrototype Multiply();
  public AbstractImagePrototype Divide();
  public AbstractImagePrototype Asterisk();
  public AbstractImagePrototype CenterDot();
  public AbstractImagePrototype Circle();
  public AbstractImagePrototype Bullet();
  public AbstractImagePrototype RoundMultiply();
  public AbstractImagePrototype RoundPlus();
  public AbstractImagePrototype RoundMinus();
  public AbstractImagePrototype RoundDot();
  public AbstractImagePrototype RoundSlash();
  
}
