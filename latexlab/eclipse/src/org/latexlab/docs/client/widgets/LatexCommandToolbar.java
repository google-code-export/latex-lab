package org.latexlab.docs.client.widgets;

import org.latexlab.docs.client.commands.SystemToggleLatexToolbarCommand;
import org.latexlab.docs.client.events.CommandEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * A toolbar containing LaTeX commands.
 */
public abstract class LatexCommandToolbar extends ToolbarWindow {

  protected static LatexCommandToolbar instance;
  
  protected LatexCommandToolbar(final String title) {
	super(title, "200px", null);
    addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          CommandEvent.fire(new SystemToggleLatexToolbarCommand(title));
	    }
	});
  }

}
