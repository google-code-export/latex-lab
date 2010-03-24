package org.latexlab.docs.client.parts;

import org.latexlab.docs.client.commands.SystemSetViewCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.events.HasCommandHandlers;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PushButton;

public class FooterPart extends Composite implements HasCommandHandlers {

  private HandlerManager manager;
  private HorizontalPanel content;
  
  public FooterPart() {
	manager = new HandlerManager(this);
	content = new HorizontalPanel();
    content.setWidth("100%");
    content.setStylePrimaryName("lab-Footer");
    PushButton viewSource = new PushButton();
    viewSource.setText("Source");
    viewSource.setTitle("View Source");
    viewSource.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
          CommandEvent.fire(FooterPart.this, new SystemSetViewCommand(SystemSetViewCommand.VIEW_SOURCE));
		}
    });
    PushButton viewPreview = new PushButton();
    viewPreview.setText("Preview");
    viewPreview.setTitle("Preview");
    viewPreview.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
          CommandEvent.fire(FooterPart.this, new SystemSetViewCommand(SystemSetViewCommand.VIEW_PREVIEW));
		}
    });
    PushButton viewSplit = new PushButton();
    viewSplit.setText("Split");
    viewSplit.setTitle("View Source and Preview");
    viewSplit.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
          CommandEvent.fire(FooterPart.this, new SystemSetViewCommand(SystemSetViewCommand.VIEW_SPLIT));
		}
    });
    PushButton viewOutput = new PushButton();
    viewOutput.setText("Output");
    viewOutput.setTitle("View Output");
    viewOutput.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
          CommandEvent.fire(FooterPart.this, new SystemSetViewCommand(SystemSetViewCommand.VIEW_OUTPUT));
		}
    });
    HorizontalPanel buttons = new HorizontalPanel();
    buttons.add(viewSource);
    buttons.add(viewPreview);
    buttons.add(viewSplit);
    buttons.add(viewOutput);
    content.add(buttons);
    initWidget(content);
  }

  @Override
  public HandlerRegistration addCommandHandler(CommandHandler handler) {
	return manager.addHandler(CommandEvent.getType(), handler);
  }
  
  @Override
  public void fireEvent(GwtEvent<?> event) {
	manager.fireEvent(event);
  }
  
}
