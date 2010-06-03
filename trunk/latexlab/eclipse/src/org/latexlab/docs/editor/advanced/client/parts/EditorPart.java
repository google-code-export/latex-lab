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
import com.google.gwt.user.client.ui.VerticalPanel;

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
  private boolean colorSyntax, wrapText, showLineNumbers, useSpellChecker;
  
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
    VerticalPanel panel = new VerticalPanel();
    panel.add(editor);
    panel.setWidth("100%");
    panel.setHeight("100%");
    initWidget(panel);
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
  public HandlerRegistration addClickHandler(final ClickHandler handler) {
    clickHandlers.add(handler);
	return new HandlerRegistration() {
		@Override
		public void removeHandler() {
		  clickHandlers.remove(handler);
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
  public HandlerRegistration addLoadHandler(final LoadHandler handler) {
    loadHandlers.add(handler);
	return new HandlerRegistration() {
		@Override
		public void removeHandler() {
		  loadHandlers.remove(handler);
		}
	};
  }
  
  /**
   * Retrieves whether to use syntax coloring.
   * 
   * @return whether to use syntax coloring
   */
  public boolean getColorSyntax() {
	return this.colorSyntax;
  }
  
  /**
   * Retrieves the selected text.
   * 
   * @return the selected text
   */
  public String getSelectedText() {
	return mirror.getSelection();
  }
  
  /**
   * Retrieves whether to display line numbers.
   * 
   * @return whether to display line numbers
   */
  public boolean getShowLineNumbers() {
	return this.showLineNumbers;
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
   * Retrieves whether to use the spellchecker.
   * 
   * @return whether to use the spellchecker
   */
  public boolean getUseSpellChecker() {
	return this.useSpellChecker;
  }
  
  /**
   * Retrieves whether to wrap text.
   * 
   * @return whether to wrap text
   */
  public boolean getWrapText() {
	return this.wrapText;
  }
  
  /**
   * Initializes the text editor with the specified settings.
   * 
   * @param colorSyntax whether to use syntax coloring
   * @param wrapText whether to wrap text
   * @param showLineNumbers whether to display line numbers
   * @param useSpellChecker whether to check spelling
   * @param controlCallback the control key handler
   */
  public void init(boolean colorSyntax, boolean wrapText, boolean showLineNumbers,
	    boolean useSpellChecker, IntRunnable controlCallback) {
	if (!CodeMirror.isProbablySupported()) {
	  return;
	}
	this.colorSyntax = colorSyntax;
	this.wrapText = wrapText;
	this.showLineNumbers = showLineNumbers;
	this.useSpellChecker = useSpellChecker;
	CodeMirrorOptions opts = CodeMirrorOptions.newInstance();
	JsArrayString parser = (JsArrayString) JsArrayString.createArray();
	opts.setParserFile(parser);
	opts.setPath("/codemirror/js/");
	if (colorSyntax) {
	  opts.setStylesheet("/codemirror/css/latexcolors.css");
	  parser.push("parselatex.js");
	} else {
      opts.setStylesheet("/codemirror/css/nocolors.css");
	  parser.push("parsedummy.js");
	}
	opts.setWidth("100%");
	opts.setHeight("100%");
	opts.setLineNumbers(showLineNumbers);
	opts.setTextWrapping(wrapText);
	opts.setDisableSpellcheck(!useSpellChecker);
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
		  if (EditorPart.this.useSpellChecker) {
		    mirror.setSpellcheck(false);
			mirror.setSpellcheck(EditorPart.this.useSpellChecker);
		  }
		}
	});
    mirror = CodeMirror.fromTextArea(editor.getElement(), opts);
  }
  
  /**
   * Performs a redo on the previous text changes.
   */
  public void redo() {
    if (mirror != null) {
      mirror.redo();
    }
  }
  
  /**
   * Replaces the current text selection with the specified text.
   * 
   * @param text the replacement text
   */
  public void replaceSelection(String text) {
    if (mirror != null) {
      mirror.replaceSelection(text);
    }
  }
  
  /**
   * Sets whether to use syntax coloring.
   * 
   * @param colorSyntax whether to use syntax coloring
   */
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
  
  /**
   * Sets whether to display line numbers.
   * 
   * @param showLineNumbers whether to display line numbers
   */
  public void setShowLineNumbers(boolean showLineNumbers) {
	if (mirror != null) {
      mirror.setLineNumbers(showLineNumbers);
      this.showLineNumbers = showLineNumbers;
	}
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

  /**
   * Sets whether to use the spellchecker.
   * 
   * @param useSpellChecker whether to use the spellchecker
   */
  public void setUseSpellChecker(boolean useSpellChecker) {
	if (mirror != null) {
      mirror.setSpellcheck(useSpellChecker);
      this.useSpellChecker = useSpellChecker;
	}
  }

  /**
   * Sets whether to wrap text.
   * 
   * @param wrapText whether to wrap text
   */
  public void setWrapText(boolean wrapText) {
	if (mirror != null) {
      mirror.setTextWrapping(wrapText);
      this.wrapText = wrapText;
	}
  }

  /**
   * Performs an undo on the previous text changes.
   */
  public void undo() {
	if (mirror != null) {
      mirror.undo();
	}
  }
  
}