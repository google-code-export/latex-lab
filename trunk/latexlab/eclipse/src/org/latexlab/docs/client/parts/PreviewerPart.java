package org.latexlab.docs.client.parts;

import org.latexlab.docs.client.commands.CurrentDocumentCompileCommand;
import org.latexlab.docs.client.commands.CurrentDocumentExportCommand;
import org.latexlab.docs.client.commands.SystemSetPerspectiveCommand;
import org.latexlab.docs.client.commands.SystemViewPageCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.widgets.ScalableImage;

import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * A specialized, non-reusable widget containing the preview panel.
 */
public class PreviewerPart extends Composite implements HasCommandHandlers {

  /**
   * The allows zoom percentages.
   */
  private static final double[] SCALES = new double[]
      { 0.15, 0.25, 0.50, 0.75, 1, 1.25, 1.5, 1.75, 2, 2.25, 2.5, 2.75, 3 };
  
  private VerticalPanel content;
  private ScalableImage currentImage;
  private int currentPage = 0, currentScale = 4, lastTopScroll = 0, lastLeftScroll;
  private HandlerManager manager;
  private String[] pages = new String[0];

  /**
   * Constructs a previewer part.
   */
  public PreviewerPart() {
    manager = new HandlerManager(this);
	content = new VerticalPanel();
    content.setSize("100%", "100%");
    content.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    content.setStylePrimaryName("lab-Previewer");
	content.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	FlowPanel panel = new FlowPanel();
	panel.setWidth("100%");
	Label msg = new Label("There's nothing to preview yet, click the compile button to get started.");
	msg.setStylePrimaryName("lab-Error");
	Anchor showLogs = new Anchor("Compile Current Document", "#");
	showLogs.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		  event.preventDefault();
		  event.stopPropagation();
          CommandEvent.fire(PreviewerPart.this, new CurrentDocumentCompileCommand());
		}
	});
	panel.add(msg);
	panel.add(showLogs);
	content.add(panel);
	initWidget(content);
  }
  
  @Override
  public HandlerRegistration addCommandHandler(CommandHandler handler) {
	return manager.addHandler(CommandEvent.getType(), handler);
  }
	
  /**
   * Adds a message to the preview panel.
   */
  private void addMessage() {
	HorizontalPanel pan = new HorizontalPanel();
	pan.setStylePrimaryName("caption");
	HTML msg = new HTML("Preview may not match the intended output exactly. " +
			"View the rendered document by &nbsp;");
	Anchor link = new Anchor("exporting to PDF", "#");
	link.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		  event.preventDefault();
		  event.stopPropagation();
	      CommandEvent.fire(PreviewerPart.this, new CurrentDocumentExportCommand(CurrentDocumentExportCommand.PDF));
		}
	});
	pan.add(msg);
	pan.add(link);
	content.add(pan);
  }
  
  /**
   * Clears the preview contents.
   */
  public void clear() {
	if (currentImage != null) {
	  lastTopScroll = content.getElement().getParentElement().getScrollTop();
	  lastLeftScroll = content.getElement().getParentElement().getScrollLeft();
	}
    content.clear();
  }
  
  @Override
  public void fireEvent(GwtEvent<?> event) {
	manager.fireEvent(event);
  }
  
  /**
   * Displays an error message.
   * 
   * @param msg the error message
   */
  public void setError(String msg) {
    currentImage = null;
    currentPage = 0;
	content.clear();
	content.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	FlowPanel panel = new FlowPanel();
	panel.setWidth("100%");
	Label err = new Label(msg);
	err.setStylePrimaryName("lab-Error");
	Anchor showLogs = new Anchor("View Compiler Output", "#");
	showLogs.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		  event.preventDefault();
		  event.stopPropagation();
          CommandEvent.fire(PreviewerPart.this, new SystemSetPerspectiveCommand(SystemSetPerspectiveCommand.VIEW_OUTPUT));
		}
	});
	panel.add(err);
	panel.add(showLogs);
	content.add(panel);
  }
  
  /**
   * Sets the preview page image urls.
   * 
   * @param pages
   */
  public void setPages(String[] pages) {
	this.pages = pages;
	currentImage = null;
	showPage(currentPage);
  }

  /**
   * Displays a given page.
   * 
   * @param page the index of the page to display
   */
  public void showPage(int page){
	content.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
	if (page == currentPage && currentImage != null) {
	  currentImage.setScale(SCALES[currentScale]);
	} else {
	  if (page >= 0 && page < pages.length) {
	    content.clear();
	    addMessage();
	    currentImage = new ScalableImage(pages[page],
	      SCALES[currentScale]);
	    if (page == currentPage && (lastTopScroll > 0 || lastLeftScroll > 0)) {
	      currentImage.addLoadHandler(new LoadHandler() {
			@Override
			public void onLoad(LoadEvent event) {
		      content.getElement().getParentElement().setScrollTop(lastTopScroll);
		      content.getElement().getParentElement().setScrollLeft(lastLeftScroll);
			}
	      });
	    }
        currentPage = page;
	    content.add(currentImage);
	  }
	}
  }
  
  /**
   * Displays thumbnail of all the preview pages available.
   */
  public void showPageIndex() {
	content.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
	currentImage = null;
	content.clear();
	addMessage();
	FlowPanel panel = new FlowPanel();
	for (int i=0; i<pages.length; i++) {
	  final int pageNumber = i;
      VerticalPanel box = new VerticalPanel();
      box.setPixelSize(200, 320);
      box.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	  box.setStylePrimaryName("lab-Previewer-Page");
	  box.getElement().getStyle().setOverflow(Overflow.HIDDEN);
	  Image img = new Image();
	  img.setUrl(pages[i].replace("?", "_tn?"));
	  img.setStylePrimaryName("lab-Previewer-Page-Image");
	  Anchor caption = new Anchor("Page " + (i + 1));
	  caption.setHref("#");
	  caption.setStylePrimaryName("lab-Previewer-Page-Caption");
	  caption.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		  event.preventDefault();
		  event.stopPropagation();
		  CommandEvent.fire(PreviewerPart.this, new SystemViewPageCommand(pageNumber));
		}
	  });
	  box.add(caption);
	  box.add(img);
	  panel.add(box);
	}
	content.add(panel);
  }
  
  /**
   * Zooms into the current page.
   */
  public void zoomIn() {
	if (pages.length == 0) return;
	if (currentScale < SCALES.length - 1) {
	  currentScale++;
	}
	showPage(currentPage);
  }
  
  /**
   * Zooms out of the current page.
   */
  public void zoomOut() {
	if (pages.length == 0) return;
    if (currentScale > 0) {
	  currentScale--;
	}
	showPage(currentPage);
  }
  
}
