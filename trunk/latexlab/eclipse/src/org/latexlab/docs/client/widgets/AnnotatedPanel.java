package org.latexlab.docs.client.widgets;

import java.util.ArrayList;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AnnotatedPanel extends Composite implements HasSelectionHandlers<AnnotatedPanel.Annotation> {

  protected HandlerManager handlerManager;
  protected Widget annotatedWidget;
  protected VerticalPanel annotationsBar;
  protected FlexTable content;
	
  public AnnotatedPanel() {
	handlerManager = new HandlerManager(this);
	annotationsBar = new VerticalPanel();
	content = new FlexTable();
	content.setCellPadding(2);
	content.setCellSpacing(0);
	content.insertRow(0);
	content.insertCell(0, 0);
	content.insertCell(0, 1);
	content.getFlexCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
	content.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
	content.getFlexCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
	content.setWidget(0, 0, annotationsBar);
	initWidget(content);
  }
  
  public void clearAnnotations() {
	this.annotationsBar.clear();
  }

  public Widget getAnnotatedWidget() {
    return content.getWidget(0, 1);
  }

  public void setAnnotatedWidget(Widget annotatedWidget) {
    content.setWidget(0, 1, annotatedWidget);
  }
  
  public void setAnnotations(ArrayList<Annotation> annotations) {
	this.annotationsBar.clear();
	int lastYPosition = 0;
	for (final Annotation annotation : annotations) {
	  final int yPosition = annotation.getyPosition() - lastYPosition;
	  lastYPosition = annotation.getyPosition();
	    Anchor hintLink = new Anchor(annotation.getLabel());
	    hintLink.setTitle(annotation.getTitle());
	    hintLink.getElement().getStyle().setFontSize(10, Unit.PX);
        hintLink.getElement().getStyle().setDisplay(Display.BLOCK);
        hintLink.getElement().getStyle().setMarginTop(yPosition - 14, Unit.PX);
	    hintLink.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
			  event.preventDefault();
			  event.stopPropagation();
			  SelectionEvent.fire(AnnotatedPanel.this, annotation);
			}
	    });
	    annotationsBar.add(hintLink);
	}
  }
  
  public boolean getAnnotationsVisibility() {
	return annotationsBar.isVisible();
  }
  
  public void setAnnotationsVisibility(boolean visibility) {
	annotationsBar.setVisible(visibility);
  }

  @Override
  public HandlerRegistration addSelectionHandler(
  		SelectionHandler<Annotation> handler) {
  	return handlerManager.addHandler(SelectionEvent.getType(), handler);
  }

  @Override
  public void fireEvent(GwtEvent<?> event) {
	handlerManager.fireEvent(event);
  }
  
  public static class Annotation {
	 
	protected String label, title, value;
	protected int yPosition;
	  
	public Annotation(int yPosition, String label, String title, String value) {
	  this.yPosition = yPosition;
	  this.label = label;
	  this.title = title;
	  this.value = value;
	}

	public String getLabel() {
	  return label;
	}

	public void setLabel(String label) {
	  this.label = label;
	}

	public String getValue() {
	  return value;
	}

	public void setValue(String value) {
	  this.value = value;
	}

	public int getyPosition() {
	  return yPosition;
	}

	public void setyPosition(int yPosition) {
	  this.yPosition = yPosition;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

  }
}
