package org.latexlab.docs.client.dialogs;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.resources.icons.EditorIcons;

/**
 * A dialog window displaying details of the application.
 */
public class AboutDialog extends Dialog {

  protected static AboutDialog instance;
  
  public static AboutDialog getInstance(CommandHandler handler) {
    if (instance == null) {
      instance = new AboutDialog();
      instance.addCommandHandler(handler);
    }
    return instance;
  }

  /**
   * Constructs an About dialog window.
   */
  public AboutDialog() {
    super("About", true);
    addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        hide();
      }
    });
    VerticalPanel content = new VerticalPanel();
    content.setStylePrimaryName("gdbe-About");
    Image logo = EditorIcons.icons.LogoLarge().createImage();
    content.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
    content.add(logo);
    content.add(new Label("Google-Docs Base Editor 1.0 Beta"));
    HorizontalPanel links = new HorizontalPanel();
    links.setSpacing(6);
    Anchor projLink = new Anchor("Project Site");
    projLink.setHref("http://code.google.com/p/gdbe/");
    projLink.setTarget("_blank");
    Anchor wikiLink = new Anchor("Project Wiki");
    wikiLink.setHref("http://code.google.com/p/gdbe/w/list");
    wikiLink.setTarget("_blank");
    Anchor issuesLink = new Anchor("Issue Tracker");
    issuesLink.setHref("http://code.google.com/p/gdbe/issues/list");
    issuesLink.setTarget("_blank");
    Anchor downloadsLink = new Anchor("Downloads");
    downloadsLink.setHref("http://code.google.com/p/gdbe/downloads/list");
    downloadsLink.setTarget("_blank");
    links.add(projLink);
    links.add(wikiLink);
    links.add(issuesLink);
    links.add(downloadsLink);
    content.add(links);
    setContentWidget(content);
  }
}
