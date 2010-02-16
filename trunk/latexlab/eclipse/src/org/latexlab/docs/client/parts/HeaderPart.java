package org.latexlab.docs.client.parts;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
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
import org.latexlab.docs.client.commands.CurrentDocumentViewAsWebPageCommand;
import org.latexlab.docs.client.commands.SystemSignOutCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.resources.icons.EditorIcons;
import org.latexlab.docs.client.resources.icons.EditorIconsImageBundle;

import java.util.Date;

/**
 * A specialized, non-reusable widget containing the logo, title, links and button menu.
 */
public class HeaderPart extends Composite implements HasCommandHandlers, ClickHandler {

  private HandlerManager manager;
  private VerticalPanel content;
  private FlexTable main;
  private Label author;
  private HorizontalPanel links, status;
  private Anchor title, docsLink, helpLink, signoutLink;
  private HTML info;
  
  /**
   * Constructs a new Header part.
   */
  public HeaderPart() {
	manager = new HandlerManager(this);
    content = new VerticalPanel();
    content.setWidth("100%");
    status = new HorizontalPanel();
    status.setWidth("100%");
    status.setStylePrimaryName("gdbe-Header-Status");
    status.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
    main = new FlexTable();
    main.setWidth("100%");
    main.insertRow(0);
    main.insertCell(0, 0);
    main.insertCell(0, 1);
    main.getFlexCellFormatter().setVerticalAlignment(0, 0, HorizontalPanel.ALIGN_BOTTOM);
    main.getFlexCellFormatter().setHorizontalAlignment(0, 1, HorizontalPanel.ALIGN_RIGHT);
    main.setWidth("100%");
    main.insertRow(1);
    main.insertCell(1, 0);
    main.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
    main.getFlexCellFormatter().setRowSpan(0, 0, 2);
    content.add(status);
    content.add(main);
    buildUpper();
    buildLower();
    initWidget(content);
  }
  
  /**
   * Builds the upper part of the editor.
   * The upper region contains the Google Docs links, status and logo.
   * 
   * @return a table containing the upper controls
   */
  private void buildUpper() {
    EditorIconsImageBundle EditorIcons = (EditorIconsImageBundle)GWT.create(EditorIconsImageBundle.class);
    /* Upper Header Panel */
    VerticalPanel brandPanel = new VerticalPanel();
    brandPanel.setHeight("100%");
    brandPanel.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
    brandPanel.setSpacing(2);
    Image logo = EditorIcons.Logo().createImage();
    logo.setStylePrimaryName("gdbe-Logo");
    HorizontalPanel titlePanel = new HorizontalPanel();
    titlePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    title = new Anchor("", "");
    title.setStylePrimaryName("gdbe-Header-Title");
    title.setTitle("Click to rename this document");
    title.addClickHandler(this);
    info = new HTML();
    info.setStylePrimaryName("gdbe-Header-Info");
    titlePanel.add(title);
    titlePanel.add(info);
    links = new HorizontalPanel();
    links.setStylePrimaryName("gdbe-Header-Links");
    author = new Label();
    docsLink = new Anchor("Docs Home", "http://docs.google.com/", "_blank");
    helpLink = new Anchor("Help", "", "_blank");
    signoutLink = new Anchor("Sign Out");
    signoutLink.addClickHandler(new ClickHandler(){
      public void onClick(ClickEvent event) {
        CommandEvent.fire(HeaderPart.this, new SystemSignOutCommand("/splash.html"));
      }
    });
    links.add(author);
    links.add(docsLink);
    links.add(helpLink);
    links.add(signoutLink);
    brandPanel.add(logo);
    brandPanel.add(titlePanel);
    main.setWidget(0, 0, brandPanel);
    main.setWidget(0, 1, links);
  }
  
  /**
   * Builds the lower part of the editor.
   * The lower region contains the title, info and button menu controls.
   * 
   * @return a table containing the lower controls
   */
  private void buildLower() {
    HorizontalPanel actionsPanel = new HorizontalPanel();
    actionsPanel.setHeight("30px");
    actionsPanel.setStylePrimaryName("gdbe-Actions-Panel");
    MenuBar menu = new MenuBar(false);
    
    MenuBar shareMenu = new MenuBar(true);
    addMenuItem(shareMenu, EditorIcons.icons.Blank(), "View as web page (Preview)...", new CurrentDocumentViewAsWebPageCommand());
    MenuItem item = menu.addItem("Share", true, shareMenu);
    item.setStylePrimaryName("gdbe-HighlightedMenuItem");
    menu.addSeparator();
    
    addMenuItem(menu, null, "Save", new CurrentDocumentSaveCommand());
    menu.addSeparator();
    addMenuItem(menu, null, "Save & Close", new CurrentDocumentSaveAndCloseCommand());
    actionsPanel.add(menu);
    main.setWidget(1, 0, actionsPanel);
  }
  
  /**
   * Adds a menu item to a menu bar.
   * 
   * @param menuBar the menu bar to which to add the item
   * @param icon the menu item's icon, if any
   * @param title the menu item's title
   * @param command the menu items associated command type
   */
  private void addMenuItem(final MenuBar menuBar, AbstractImagePrototype icon, String title, final Command command) {
    MenuItem mi;
    if (icon == null) {
      mi = menuBar.addItem(title, true, (com.google.gwt.user.client.Command)null);
    } else {
      mi = menuBar.addItem(icon.getHTML() + " " + title, true, (com.google.gwt.user.client.Command)null);
    }
    mi.setCommand(new com.google.gwt.user.client.Command() {
      public void execute() {
      	CommandEvent.fire(HeaderPart.this, command);
      }
    });
  }
  
  /**
   * Handles a click event. Listens to click events on the title region and prompts
   * for a rename by firing a rename command event.
   */
  public void onClick(ClickEvent event) {
    if (event.getSource() == title) {
      CommandEvent.fire(HeaderPart.this, new CurrentDocumentRenameCommand());
      event.preventDefault();
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

  /**
   * Sets the displayed author.
   * 
   * @param author the author which to display
   */
  public void setAuthor(String author) {
    this.author.setText(author);
  }

  /**
   * Sets the contents of the status region.
   * 
   * @param status the status which to display
   */
  public void setStatus(String status) {
    this.status.clear();
    if (status != null & !status.equals("")) {
      this.status.add(new Label(status));
    }
  }
  
  /**
   * Sets the displayed document info.
   * 
   * @param documentId the document id which to display
   * @param edited the date of last edit which to display
   * @param editor the editor which to display
   */
  public void setInfo(String documentId, Date edited, String editor) {
    info.setHTML(" saved on <a href=\"http://docs.google.com/Revs?id=" + documentId + "&tab=revlist\" target=\"_blank\">" +
        edited + "</a> by " + editor);
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
