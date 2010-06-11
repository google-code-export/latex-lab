package org.latexlab.docs.client.parts;

import org.latexlab.docs.client.commands.SystemSetPerspectiveCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.widgets.PageScroller;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PushButton;

/**
 * A specialized, non-reusable widget containing the footer panel.
 */
public class FooterPart extends Composite implements HasCommandHandlers {

  private FlexTable content;
  private HorizontalPanel left, right;
  private HandlerManager manager;
  private PageScroller viewer;
  
  /**
   * Constructs a FooterPart.
   */
  public FooterPart() {
	manager = new HandlerManager(this);
	left = new HorizontalPanel();
	left.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	right = new HorizontalPanel();
	right.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	content = new FlexTable();
	content.setCellPadding(0);
	content.setCellSpacing(0);
	content.insertRow(0);
	content.addCell(0);
	content.addCell(0);
	content.setWidget(0, 0, left);
	content.setWidget(0, 1, right);
	content.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
	content.getFlexCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    content.setWidth("100%");
    content.setStylePrimaryName("lab-Footer");
    PushButton viewSource = new PushButton();
    viewSource.setText("Source");
    viewSource.setTitle("View Source");
    viewSource.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
          CommandEvent.fire(FooterPart.this, new SystemSetPerspectiveCommand(SystemSetPerspectiveCommand.VIEW_SOURCE));
		}
    });
    PushButton viewPreview = new PushButton();
    viewPreview.setText("Preview");
    viewPreview.setTitle("Preview");
    viewPreview.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
          CommandEvent.fire(FooterPart.this, new SystemSetPerspectiveCommand(SystemSetPerspectiveCommand.VIEW_PREVIEW));
		}
    });
    PushButton viewSplit = new PushButton();
    viewSplit.setText("Split");
    viewSplit.setTitle("View Source and Preview");
    viewSplit.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
          CommandEvent.fire(FooterPart.this, new SystemSetPerspectiveCommand(SystemSetPerspectiveCommand.VIEW_SPLIT));
		}
    });
    PushButton viewOutput = new PushButton();
    viewOutput.setText("Output");
    viewOutput.setTitle("View Output");
    viewOutput.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
          CommandEvent.fire(FooterPart.this, new SystemSetPerspectiveCommand(SystemSetPerspectiveCommand.VIEW_OUTPUT));
		}
    });
    HorizontalPanel buttons = new HorizontalPanel();
    buttons.add(viewSource);
    buttons.add(viewPreview);
    buttons.add(viewSplit);
    buttons.add(viewOutput);
    viewer = new PageScroller();
    left.add(buttons);
    right.add(viewer);
    initWidget(content);
  }
  
  @Override
  public HandlerRegistration addCommandHandler(CommandHandler handler) {
	viewer.addCommandHandler(handler);
	return manager.addHandler(CommandEvent.getType(), handler);
  }

  @Override
  public void fireEvent(GwtEvent<?> event) {
	manager.fireEvent(event);
  }
  
  /**
   * Retrieves the page scroller.
   * 
   * @return the page scroller
   */
  public PageScroller getPageViewer() {
	return viewer;
  }
  
}
