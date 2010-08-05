package org.latexlab.docs.client.parts;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.latexlab.docs.client.commands.CurrentDocumentCompileCommand;
import org.latexlab.docs.client.commands.CurrentDocumentExportCommand;
import org.latexlab.docs.client.commands.SystemJumpToLineCommand;
import org.latexlab.docs.client.commands.SystemSetPerspectiveCommand;
import org.latexlab.docs.client.commands.SystemViewPageCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.widgets.AnnotatedPanel;
import org.latexlab.docs.client.widgets.ScalableImage;
import org.latexlab.docs.client.widgets.AnnotatedPanel.Annotation;

import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
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
public class PreviewerPart extends Composite {

  /**
   * The allows zoom percentages.
   */
  private static final double[] SCALES = new double[]
      { 0.15, 0.25, 0.50, 0.75, 1, 1.25, 1.5, 1.75, 2, 2.25, 2.5, 2.75, 3 };
  private static final int DEFAULT_SCALE = 4;
  
  private VerticalPanel content;
  private ScalableImage currentImage;
  private AnnotatedPanel annPanel;
  private int currentPage = 0, currentScale = DEFAULT_SCALE, lastTopScroll = 0, lastLeftScroll;
  private LinkedHashMap<Integer, ArrayList<Annotation>> sourceHints =
      new LinkedHashMap<Integer, ArrayList<Annotation>>();
  private String[] pages = new String[0];

  /**
   * Constructs a previewer part.
   */
  public PreviewerPart() {
	annPanel = new AnnotatedPanel();
    annPanel.addSelectionHandler(new SelectionHandler<Annotation>() {
		@Override
		public void onSelection(SelectionEvent<Annotation> event) {
		  int line = Integer.valueOf(event.getSelectedItem().getValue());
		  CommandEvent.fire(new SystemJumpToLineCommand(line));
		}
    });
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
          CommandEvent.fire(new CurrentDocumentCompileCommand());
		}
	});
	panel.add(msg);
	panel.add(showLogs);
	content.add(panel);
	initWidget(content);
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
	      CommandEvent.fire(new CurrentDocumentExportCommand(CurrentDocumentExportCommand.PDF));
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
	  lastTopScroll = getScrollTop();
	  lastLeftScroll = getScrollLeft();
	}
    content.clear();
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
          CommandEvent.fire(new SystemSetPerspectiveCommand(SystemSetPerspectiveCommand.VIEW_OUTPUT));
		}
	});
	panel.add(err);
	panel.add(showLogs);
	content.add(panel);
  }
  
  /**
   * Sets the preview page image urls.
   * 
   * @param pages the preview page urls
   * @param sourceHints a map of y-coordinate to source line annotations
   */
  public int setPages(String[] pages, LinkedHashMap<Integer, ArrayList<Annotation>> sourceHints) {
	this.pages = pages;
	this.sourceHints = sourceHints;
	currentImage = null;
	return showPage(currentPage);
  }

  /**
   * Displays a given page.
   * 
   * @param page the index of the page to display
   */
  public int showPage(final int page){
	content.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
	if (page == currentPage && currentImage != null) {
	  currentImage.setScale(SCALES[currentScale]);
	  annPanel.setAnnotationsVisibility(currentScale == DEFAULT_SCALE);
	} else {
	  if (page >= 0 && page < pages.length) {
	    content.clear();
	    annPanel.clearAnnotations();
	    addMessage();
	    currentImage = new ScalableImage(pages[page],
	      SCALES[currentScale]);
	    if (sourceHints.containsKey(page)) {
	      annPanel.setAnnotations(sourceHints.get(page));
	      annPanel.setAnnotationsVisibility(false);
	      currentImage.addLoadHandler(new LoadHandler() {
			@Override
			public void onLoad(LoadEvent event) {
			  annPanel.setAnnotationsVisibility(currentScale == DEFAULT_SCALE);
			}
	      });
	    }
	    if (page == currentPage && (lastTopScroll > 0 || lastLeftScroll > 0)) {
	      currentImage.addLoadHandler(new LoadHandler() {
			@Override
			public void onLoad(LoadEvent event) {
			  setScrollPosition(lastTopScroll, lastLeftScroll);
			}
	      });
	    }
        annPanel.setAnnotatedWidget(currentImage);
	    content.add(annPanel);
        currentPage = page;
	  }
	}
	return currentPage;
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
		  CommandEvent.fire(new SystemViewPageCommand(pageNumber));
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
  
  private void setScrollPosition(int top, int left) {
    content.getElement().getParentElement().setScrollTop(top);
    content.getElement().getParentElement().setScrollLeft(left);
  }
  
  private int getScrollTop() {
	return content.getElement().getParentElement().getScrollTop();
  }
  
  private int getScrollLeft() {
	return content.getElement().getParentElement().getScrollLeft();
  }
  
}
