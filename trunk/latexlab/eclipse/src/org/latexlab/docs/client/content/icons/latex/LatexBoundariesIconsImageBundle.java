package org.latexlab.docs.client.content.icons.latex;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

//TODO: eliminate the deprecated ImageBundle parent reference.
/**
* An image bundle storing LaTeX icons.
*/
@SuppressWarnings("deprecation")
public interface LatexBoundariesIconsImageBundle extends ImageBundle {

  public AbstractImagePrototype InvisibleLeftBoundary();
  public AbstractImagePrototype InvisibleRightBoundary();
  public AbstractImagePrototype LeftParentheses();
  public AbstractImagePrototype RightParantheses();
  public AbstractImagePrototype LeftSquareBracket();
  public AbstractImagePrototype RightSquareBracket();
  public AbstractImagePrototype LeftCurlyBracket();
  public AbstractImagePrototype RightCurlyBracket();
  public AbstractImagePrototype LeftFloor();
  public AbstractImagePrototype RightFloor();
  public AbstractImagePrototype LeftCeil();
  public AbstractImagePrototype RightCeil();
  public AbstractImagePrototype LeftAngleBracket();
  public AbstractImagePrototype RightAngleBracket();
  public AbstractImagePrototype LeftModulus();
  public AbstractImagePrototype RightModulus();
  public AbstractImagePrototype LeftDouble();
  public AbstractImagePrototype RightDouble();
  public AbstractImagePrototype OverBrace();
  public AbstractImagePrototype UnderBrace();
  
}
