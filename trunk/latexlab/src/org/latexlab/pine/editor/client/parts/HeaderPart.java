package org.latexlab.pine.editor.client.parts;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Anchor;
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

import org.latexlab.pine.client.events.CommandEvent;
import org.latexlab.pine.client.events.CommandEventSource;
import org.latexlab.pine.client.resources.icons.EditorIconsImageBundle;

import java.util.Date;

/**
 * A specialized, non-reusable widget containing the logo, title, links and button menu.
 */
public class HeaderPart extends CommandEventSource implements ClickHandler {
  
  private VerticalPanel content;
  private FlexTable lower, upper;
  private Label author;
  private HorizontalPanel links, status;
  private Anchor title, docsLink, helpLink, signoutLink;
  private HTML info;
  
  /**
   * Constructs a new Header part.
   */
  public HeaderPart() {
    content = new VerticalPanel();
    content.setWidth("100%");
    status = new HorizontalPanel();
    status.setWidth("100%");
    status.setStylePrimaryName("gdbe-Header-Status");
    status.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
    upper = new FlexTable();
    upper.setWidth("100%");
    upper.insertRow(0);
    upper.insertCell(0, 0);
    upper.insertCell(0, 1);
    upper.getFlexCellFormatter().setHorizontalAlignment(0, 1, HorizontalPanel.ALIGN_RIGHT);
    lower = new FlexTable();
    lower.setWidth("100%");
    lower.insertRow(0);
    lower.insertCell(0, 0);
    lower.insertCell(0, 1);
    lower.getFlexCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    content.add(status);
    content.add(buildUpper());
    content.add(buildLower());
    initWidget(content);
  }
  
  /**
   * Builds the upper part of the editor.
   * The upper region contains the Google Docs links, status and logo.
   * 
   * @return a table containing the upper controls
   */
  private FlexTable buildUpper() {
    EditorIconsImageBundle EditorIcons = (EditorIconsImageBundle)GWT.create(EditorIconsImageBundle.class);
    /* Upper Header Panel */
    Image logo = EditorIcons.Logo().createImage();
    logo.setWidth("91px");
    logo.setHeight("31px");
    logo.setStylePrimaryName("gdbe-Logo");
    links = new HorizontalPanel();
    links.setStylePrimaryName("gdbe-Header-Links");
    author = new Label();
    docsLink = new Anchor("Docs Home", "http://docs.google.com/", "_blank");
    helpLink = new Anchor("Help", "", "_blank");
    signoutLink = new Anchor("Sign Out");
    signoutLink.addClickHandler(new ClickHandler(){
      public void onClick(ClickEvent event) {
        fireOnCommand(new CommandEvent(signoutLink, CommandEvent.Command.GENERIC_SIGN_OUT));
      }
    });
    links.add(author);
    links.add(docsLink);
    links.add(helpLink);
    links.add(signoutLink);
    upper.setWidget(0, 0, logo);
    upper.setWidget(0, 1, links);
    return upper;
  }
  
  /**
   * Builds the lower part of the editor.
   * The lower region contains the title, info and button menu controls.
   * 
   * @return a table containing the lower controls
   */
  private FlexTable buildLower() {    
    EditorIconsImageBundle EditorIcons = (EditorIconsImageBundle)GWT.create(EditorIconsImageBundle.class);
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

    HorizontalPanel actionsPanel = new HorizontalPanel();
    actionsPanel.setHeight("30px");
    actionsPanel.setStylePrimaryName("gdbe-Actions-Panel");
    MenuBar menu = new MenuBar(false);
    MenuBar shareMenu = new MenuBar(true);
    addMenuItem(shareMenu, EditorIcons.Blank(), "View as web page (Preview)...", CommandEvent.Command.GENERIC_VIEW_AS_WEB_PAGE);
    MenuItem item = menu.addItem("Share", true, shareMenu);
    item.setStylePrimaryName("gdbe-HighlightedMenuItem");
    menu.addSeparator();
    addMenuItem(menu, null, "Save", CommandEvent.Command.GENERIC_SAVE_CURRENT_DOCUMENT);
    menu.addSeparator();
    addMenuItem(menu, null, "Save & Close", CommandEvent.Command.GENERIC_SAVE_AND_CLOSE_CURRENT_DOCUMENT);
    actionsPanel.add(menu);
    
    lower.setWidget(0, 0, titlePanel);
    lower.setWidget(0, 1, actionsPanel);
    return lower;
  }
  
  /**
   * Adds a menu item to a menu bar.
   * 
   * @param menuBar the menu bar to which to add the item
   * @param icon the menu item's icon, if any
   * @param title the menu item's title
   * @param command the menu items associated command type
   */
  private void addMenuItem(final MenuBar menuBar, AbstractImagePrototype icon, String title, final CommandEvent.Command command) {
    MenuItem mi;
    if (icon == null) {
      mi = menuBar.addItem(title, true, (Command)null);
    } else {
      mi = menuBar.addItem(icon.getHTML() + " " + title, true, (Command)null);
    }
    mi.setCommand(new Command() {
      public void execute() {
        fireOnCommand(new CommandEvent(menuBar, command));
      }
    });
  }
  
  /**
   * Handles a click event. Listens to click events on the title region and prompts
   * for a rename by firing a rename command event.
   */
  public void onClick(ClickEvent event) {
    if (event.getSource() == title) {
      this.fireOnCommand(new CommandEvent(this, CommandEvent.Command.GENERIC_RENAME_CURRENT_DOCUMENT));
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
}
