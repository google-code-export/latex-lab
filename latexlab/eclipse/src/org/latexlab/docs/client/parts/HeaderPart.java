package org.latexlab.docs.client.parts;

import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.latexlab.docs.client.commands.Command;
import org.latexlab.docs.client.commands.CurrentDocumentRenameCommand;
import org.latexlab.docs.client.commands.CurrentDocumentSaveAndCloseCommand;
import org.latexlab.docs.client.commands.CurrentDocumentSaveCommand;
import org.latexlab.docs.client.commands.SystemSignOutCommand;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.events.CommandEvent;

import java.util.Date;

/**
 * A specialized, non-reusable widget containing the logo, title, links and button menu.
 */
public class HeaderPart extends Composite implements ClickHandler {

  /**
   * Defines the allows save states.
   */
  public enum SaveState {
	SAVED,
	SAVING
  }
  
  private Label author;
  private VerticalPanel content;
  private HTML info;
  private HorizontalPanel leftLinks, rightLinks, status;
  private FlexTable main;
  private MenuItem saveMenuItem, saveAndCloseMenuItem;
  private Timer timer;
  private Anchor title, signoutLink;
  
  /**
   * Constructs a new Header part.
   */
  public HeaderPart() {
    content = new VerticalPanel();
    content.setWidth("100%");
    status = new HorizontalPanel();
    status.setWidth("100%");
    status.setStylePrimaryName("lab-Header-Status");
    status.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
    main = new FlexTable();
    main.setWidth("100%");
    main.setCellSpacing(0);
    main.insertRow(0);
    main.insertCell(0, 0);
    main.getFlexCellFormatter().setHeight(0, 0, "12");
    main.getFlexCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
    main.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
    main.setWidth("100%");
    main.insertRow(1);
    main.insertCell(1, 0);
    main.getFlexCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_MIDDLE);
    main.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_LEFT);
    content.add(status);
    content.add(main);
    buildUpper();
    buildLower();
    initWidget(content);
  }
  
  /**
   * Adds a menu item to a menu bar.
   * 
   * @param menuBar the menu bar to which to add the item
   * @param icon the menu item's icon, if any
   * @param title the menu item's title
   * @param command the menu items associated command type
   */
  private MenuItem addMenuItem(final MenuBar menuBar, AbstractImagePrototype icon, String title, final Command command) {
    MenuItem mi;
    if (icon == null) {
      mi = menuBar.addItem(title, true, (com.google.gwt.user.client.Command)null);
    } else {
      mi = menuBar.addItem(icon.getHTML() + " " + title, true, (com.google.gwt.user.client.Command)null);
    }
    mi.setCommand(new com.google.gwt.user.client.Command() {
      public void execute() {
      	CommandEvent.fire(command);
      }
    });
    return mi;
  }
  
  /**
   * Builds the lower part of the editor.
   * The lower region contains the title, info and button menu controls.
   * 
   * @return a table containing the lower controls
   */
  private void buildLower() {
	FlexTable table = new FlexTable();
	table.setWidth("100%");
	table.setCellSpacing(0);
	table.setCellPadding(0);
	table.insertRow(0);
	table.insertCell(0, 0);
	table.insertCell(0, 1);
	table.getFlexCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
    table.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
	table.getFlexCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_MIDDLE);
    table.getFlexCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    Image logo = Icons.editorIcons.Logo().createImage();
    logo.setStylePrimaryName("lab-Header-Logo");
    HorizontalPanel titlePanel = new HorizontalPanel();
    titlePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    titlePanel.getElement().getStyle().setOverflow(Overflow.HIDDEN);
    title = new Anchor("", "");
    title.setStylePrimaryName("lab-Header-Title");
    title.setTitle("Click to rename this document");
    title.addClickHandler(this);
    info = new HTML();
    info.setStylePrimaryName("lab-Header-Info");
    info.setHTML("not saved");
    titlePanel.add(logo);
    titlePanel.add(title);
    titlePanel.add(info);
    HorizontalPanel actionsPanel = new HorizontalPanel();
    actionsPanel.setHeight("30px");
    actionsPanel.setStylePrimaryName("lab-Header-Actions");
    MenuBar menu = new MenuBar(false);
    saveMenuItem = addMenuItem(menu, null, "Save Now", new CurrentDocumentSaveCommand(false));
    menu.addSeparator();
    saveAndCloseMenuItem = addMenuItem(menu, null, "Save & Close", new CurrentDocumentSaveAndCloseCommand());
    saveAndCloseMenuItem.setStylePrimaryName("lab-HighlightedMenuItem");
    actionsPanel.add(menu);
    table.setWidget(0, 0, titlePanel);
    table.setWidget(0, 1, actionsPanel);
    main.setWidget(1, 0, table);
  }
  
  /**
   * Builds the upper part of the editor.
   * The upper region contains the Google Docs links, status and logo.
   * 
   * @return a table containing the upper controls
   */
  private void buildUpper() {
	FlexTable table = new FlexTable();
	table.setWidth("100%");
	table.setCellSpacing(0);
	table.setCellPadding(0);
	table.insertRow(0);
	table.insertCell(0, 0);
	table.insertCell(0, 1);
	table.getFlexCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
    table.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
	table.getFlexCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
    table.getFlexCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    table.getFlexCellFormatter().setStylePrimaryName(0, 0, "lab-Header-Links");
    table.getFlexCellFormatter().setStylePrimaryName(0, 1, "lab-Header-Links");
    /* Upper Header Panel */
    leftLinks = new HorizontalPanel();
    Anchor projectLink = new Anchor("Project", "http://code.google.com/p/latex-lab", "_blank");
    Anchor wikiLink = new Anchor("Wiki", "http://code.google.com/p/latex-lab/w/list", "_blank");
    Anchor issuesLink = new Anchor("Issues", "http://code.google.com/p/latex-lab/issues/list", "_blank");
    Anchor devLink = new Anchor("Development Version", "http://dev.latexlab.org/docs", "_blank");
    Anchor texLink = new Anchor("TeXnicCenter", "http://www.texniccenter.org/", "_blank");
    leftLinks.add(projectLink);
    leftLinks.add(wikiLink);
    leftLinks.add(issuesLink);
    leftLinks.add(devLink);
    leftLinks.add(texLink);
    rightLinks = new HorizontalPanel();
    author = new Label();
    Anchor docsLink = new Anchor("Docs Home", "http://docs.google.com/", "_blank");
    Anchor helpLink = new Anchor("Help", "http://code.google.com/p/latex-lab/wiki/UsingLaTeXLab", "_blank");
    Anchor acLink = new Anchor("Access Control", "https://www.google.com/accounts/IssuedAuthSubTokens", "_blank");
    signoutLink = new Anchor("Sign Out");
    signoutLink.addClickHandler(new ClickHandler(){
      public void onClick(ClickEvent event) {
        CommandEvent.fire(new SystemSignOutCommand());
      }
    });
    rightLinks.add(author);
    rightLinks.add(docsLink);
    rightLinks.add(acLink);
    rightLinks.add(helpLink);
    rightLinks.add(signoutLink);
    table.setWidget(0, 0, leftLinks);
    table.setWidget(0, 1, rightLinks);
    main.setWidget(0, 0, table);
  }

  /**
   * Handles a click event. Listens to click events on the title region and prompts
   * for a rename by firing a rename command event.
   */
  public void onClick(ClickEvent event) {
    if (event.getSource() == title) {
      CommandEvent.fire(new CurrentDocumentRenameCommand());
      event.preventDefault();
    }
  }

  /**
   * Sets the displayed author.
   * 
   * @param author the author which to display
   */
  public void setAuthor(String author) {
    this.author.setText(author);
  }
  
  /**
   * Sets the displayed document info.
   * 
   * @param documentId the document id which to display
   * @param edited the date of last edit which to display
   * @param editor the editor which to display
   */
  @SuppressWarnings("deprecation")
  public void setInfo(final String documentId, final Date edited, final String editor) {
	long timeDiff = new Date().getTime() - edited.getTime();
	String dateLabel;
	if (timer != null) {
	  timer.cancel();
	}
	timer = new Timer() {
		@Override
		public void run() {
		  setInfo(documentId, edited, editor);
		}
	};
	if (timeDiff < 60000) {
	  dateLabel = "seconds ago";
	  timer.schedule(30000);
	} else if (timeDiff < 3600000){
	  int mins = (int) (timeDiff / 60000);
	  dateLabel = mins + " minute" + (mins == 1 ? "" : "s") + " ago";
	  timer.schedule(30000);
	} else {
	  dateLabel = edited.toLocaleString();
	  int gmt = dateLabel.indexOf(" GMT");
	  if (gmt > 0) {
		dateLabel = dateLabel.substring(0, gmt);
	  }
	}
    info.setHTML(" saved <a href=\"http://docs.google.com/Revs?id=" + documentId + "&tab=revlist\" target=\"_blank\">" +
        dateLabel + "</a> by " + editor);
  }
  
  /**
   * Updates the save state. If the save state corresponds to an ongoing save,
   * then the corresponding interface elements are disabled.
   * 
   * @param state the new save state
   */
  public void setSaveState(SaveState state) {
	switch (state) {
	case SAVED:
	  saveMenuItem.setText("Save Now");
	  saveMenuItem.removeStyleDependentName("Disabled");
	  saveAndCloseMenuItem.removeStyleDependentName("Disabled");
	  break;
	case SAVING:
	  saveMenuItem.setText("Saving...");
	  saveMenuItem.addStyleDependentName("Disabled");
	  saveAndCloseMenuItem.addStyleDependentName("Disabled");
	  break;
	}
  }

  /**
   * Sets the contents of the status region.
   * 
   * @param status the status which to display
   */
  public void setStatus(String status) {
    this.status.clear();
    if (status != null & !status.equals("")) {
      Label lbl = new Label(status);
      lbl.setStylePrimaryName("lab-Header-StatusLabel");
      this.status.add(lbl);
    }
  }
  
  /**
   * Sets the displayed title. If the title exceeds thirty characters it's truncated
   * and appended with ellipsis.
   * 
   * @param title the title which to display
   */
  public void setTitle(String title) {
    if (title.length() > 30) {
      title = title.substring(0, 30) + "...";
    }
    this.title.setText(title);
  }
}
