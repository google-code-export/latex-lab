package org.latexlab.docs.client.dialogs;

import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.resources.icons.Icons;

/**
 * A dialog window displaying details of the application.
 */
public class AboutDialog extends Dialog {

  protected static AboutDialog instance;
  
  public static AboutDialog get(CommandHandler handler) {
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
    content.setStylePrimaryName("lab-About");
    Image logo = Icons.editorIcons.LogoLarge().createImage();
    content.setHorizontalAlignment(VerticalPanel.ALIGN_LEFT);
    content.add(logo);
    content.setSize("650px", "100px");
    HTML l1 = new HTML("&copy; 2010 LatexLab.org. Licensed under the Apache License, Version 2.0.<br /><br />" +
    		"LaTeX Lab is a web based LaTeX editor for Google Docs made possible by the following systems and open source projects:");
    l1.getElement().getStyle().setPaddingTop(5, Unit.PX);
    Label l2 = new Label("LaTeX Lab is under development.");
    l2.getElement().getStyle().setPaddingTop(10, Unit.PX);
    l2.getElement().getStyle().setColor("red");
    l2.getElement().getStyle().setFontWeight(FontWeight.BOLD);
    String linksHtml = "<br />" +
    		"<b><a href=\"http://code.google.com/webtoolkit/\">Google Web Toolkit (GWT)</a></b>, " +
    		"<a href=\"http://code.google.com/appengine/\">Google App Engine</a>, " +
    		"<a href=\"http://code.google.com/p/common-latex-service-interface/\">Common LaTeX Service Interface (CLSI)</a>, " +
    		"<a href=\"http://marijn.haverbeke.nl/codemirror/\">Code Mirror In-Browser Editor</a>, " +
    		"<a href=\"http://code.google.com/p/gwt-dnd/\">Gwt-Dnd Project</a>, " +
    		"<a href=\"http://code.google.com/p/gwt-gdata/\">Gwt-GData Project</a>, " +
    		"<a href=\"http://code.google.com/p/gdbe/\">Google-Docs Base Editor (GDBE)</a>, " +
    		"<br /><a href=\"http://code.google.com/p/latex-lab\">Visit the LaTeX Lab Project site</a>";
    HTML links = new HTML(linksHtml);
    content.add(l1);
    content.add(links);
    content.add(l2);
    setContentWidget(content);
  }
}
