package org.latexlab.docs.editor.advanced.client.parts;

import java.util.ArrayList;

import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.event.dom.client.HasLoadHandlers;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;

import org.latexlab.docs.client.events.IntRunnable;
import org.latexlab.docs.client.widgets.CodeMirror;
import org.latexlab.docs.client.widgets.CodeMirrorOptions;

/**
 * A specialized, non-reusable widget containing the text area control.
 */
public class EditorPart extends Composite
    implements HasChangeHandlers, HasLoadHandlers, HasFocusHandlers, HasClickHandlers {

  private ArrayList<ChangeHandler> changeHandlers;
  private ArrayList<LoadHandler> loadHandlers;
  private ArrayList<FocusHandler> focusHandlers;
  private ArrayList<ClickHandler> clickHandlers;
  private TextArea editor;
  private CodeMirror mirror;
  private boolean colorSyntax, wrapText, showLineNumbers;
  
  /**
   * Constructs a new Editor part.
   */
  public EditorPart() {
	changeHandlers = new ArrayList<ChangeHandler>();
	loadHandlers = new ArrayList<LoadHandler>();
	focusHandlers = new ArrayList<FocusHandler>();
	clickHandlers = new ArrayList<ClickHandler>();
    editor = new TextArea();
    editor.setWidth("100%");
    editor.setHeight("99%");
    editor.getElement().setId("_codemirror_");
    initWidget(editor);
  }
  
  public void init(boolean colorSyntax, boolean wrapText, boolean showLineNumbers,
	  IntRunnable controlCallback) {
	if (!CodeMirror.isProbablySupported()) {
	  return;
	}
	this.colorSyntax = colorSyntax;
	this.wrapText = wrapText;
	this.showLineNumbers = showLineNumbers;
	CodeMirrorOptions opts = CodeMirrorOptions.newInstance();
	JsArrayString parser = (JsArrayString) JsArrayString.createArray();
	opts.setParserFile(parser);
	opts.setPath("/codemirror/js/");
	if (colorSyntax) {
	  opts.setStylesheet("/codemirror/css/latexcolors.css");
	  parser.push("parselatex.js");
	} else {
	  parser.push("parsedummy.js");
	}
	opts.setWidth("100%");
	opts.setHeight("100%");
	opts.setLineNumbers(showLineNumbers);
	opts.setTextWrapping(wrapText);
	opts.setLineNumberDelay(0);
	opts.setControlCallback(controlCallback);
	opts.setClickCallback(new Runnable() {
		@Override
		public void run() {
		  for (ClickHandler handler : clickHandlers) {
		    handler.onClick(null);
		  }
		}
	});
	opts.setFocusCallback(new Runnable() {
		@Override
		public void run() {
		  for (FocusHandler handler : focusHandlers) {
		    handler.onFocus(null);
		  }
		}
	});
	opts.setChangeCallback(new Runnable() {
		@Override
		public void run() {
		  for (ChangeHandler handler : changeHandlers) {
		    handler.onChange(null);
		  }
		}
	});
	opts.setInitCallback(new Runnable() {
		@Override
		public void run() {
		  for (LoadHandler handler : loadHandlers) {
		    handler.onLoad(null);
		  }
		}
	});
    mirror = CodeMirror.fromTextArea(editor.getElement(), opts);
  }
  
  /**
   * Retrieves the text contents in the text area.
   * 
   * @return the current text contents
   */
  public String getText() {
    return mirror.getCode();
  }
  
  /**
   * Sets the text contents.
   * 
   * @param text the text contents to load
   */
  public void setText(String text) {
	if (mirror == null) {
      editor.setText(text);
	} else {
	  mirror.setCode(text);
	}
  }
  
  public void undo() {
	if (mirror != null) {
      mirror.undo();
	}
  }
  
  public void redo() {
    if (mirror != null) {
      mirror.redo();
    }
  }
  
  public void replaceSelection(String text) {
    if (mirror != null) {
      mirror.replaceSelection(text);
    }
  }
  
  public boolean getShowLineNumbers() {
	return this.showLineNumbers;
  }
  
  public void setShowLineNumbers(boolean showLineNumbers) {
	if (mirror != null) {
      mirror.setLineNumbers(showLineNumbers);
      this.showLineNumbers = showLineNumbers;
	}
  }
  
  public boolean getWrapText() {
	return this.wrapText;
  }
  
  public void setWrapText(boolean wrapText) {
	if (mirror != null) {
      mirror.setTextWrapping(wrapText);
      this.wrapText = wrapText;
	}
  }
  
  public boolean getColorSyntax() {
	return this.colorSyntax;
  }
  
  public void setColorSyntax(boolean colorSyntax) {
	if (mirror != null) {
	  if (colorSyntax) {
		mirror.setParser("LatexParser");
	  } else {
		mirror.setParser("NoParser");
	  }
	  mirror.setCode(mirror.getCode());
	  this.colorSyntax = colorSyntax;
	}
  }
  

  @Override
  public HandlerRegistration addChangeHandler(final ChangeHandler handler) {
    changeHandlers.add(handler);
	return new HandlerRegistration() {
		@Override
		public void removeHandler() {
		  changeHandlers.remove(handler);
		}
	};
  }

  @Override
  public HandlerRegistration addLoadHandler(final LoadHandler handler) {
    loadHandlers.add(handler);
	return new HandlerRegistration() {
		@Override
		public void removeHandler() {
		  loadHandlers.remove(handler);
		}
	};
  }

  @Override
  public HandlerRegistration addFocusHandler(final FocusHandler handler) {
    focusHandlers.add(handler);
	return new HandlerRegistration() {
		@Override
		public void removeHandler() {
		  focusHandlers.remove(handler);
		}
	};
  }

  @Override
  public HandlerRegistration addClickHandler(final ClickHandler handler) {
    clickHandlers.add(handler);
	return new HandlerRegistration() {
		@Override
		public void removeHandler() {
		  clickHandlers.remove(handler);
		}
	};
  }
  
}
