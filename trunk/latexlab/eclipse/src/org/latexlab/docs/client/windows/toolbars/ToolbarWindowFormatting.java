package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.events.AsyncInstantiationCallback;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.windows.ToolbarWindow;
import org.latexlab.docs.client.windows.WindowManager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;

/**
 * A toolbar containing LaTeX commands.
 */
public class ToolbarWindowFormatting extends ToolbarWindow {

  /**
   * The toolbar window's title.
   */
  public final static String TITLE = "Formatting";
  
  protected static ToolbarWindowFormatting instance;

  /**
   * Retrieves the single instance of this class, using asynchronous instantiation.
   * 
   * @param handler the command handler
   * @param manager the window manager
   * @param cb the asynchronous instantiation callback
   */
  public static void get(final CommandHandler handler, final WindowManager manager,
	    final AsyncInstantiationCallback<ToolbarWindowFormatting> cb) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  cb.onFailure(reason);
		}
		@Override
		public void onSuccess() {
	      if (instance == null) {
	        instance = new ToolbarWindowFormatting();
	        instance.addCommandHandler(handler);
		    instance.registeredDragController = manager.getWindowController().getPickupDragController();
		    instance.hide();
		    manager.getWindowController().makeResizable(instance);
		    manager.getBoundaryPanel().add(instance, 500, 120);
	      }
	      cb.onSuccess(instance);
		}
	});
  }
  
  /**
   * Causes the code for this class to be loaded.
   */
  public static void prefetch() {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) { }
		@Override
		public void onSuccess() {
		  new ToolbarWindowFormatting();
		}
	});
  }

  protected ToolbarWindowFormatting() {
	super(TITLE);
	buttonsPerRow = 2;
    buildToolBar();
  }

  /**
   * Builds the toolbar contents.
   */
  private void buildToolBar() {
    addButton(Icons.latexFormattingIcons.Emphasize(), "Emphasize", false, new SystemPasteCommand("\\emph{ <text here> }"));
    addButton(Icons.latexFormattingIcons.Bold(), "Bold", false, new SystemPasteCommand("\\textbf{ <text here> }"));
    addButton(Icons.latexFormattingIcons.Italic(), "Italic", false, new SystemPasteCommand("\\textit{ <text here> }"));
    addButton(Icons.latexFormattingIcons.Slanted(), "Slanted", false, new SystemPasteCommand("\\textsl{ <text here> }"));
    addButton(Icons.latexFormattingIcons.Typewriter(), "Typewriter", false, new SystemPasteCommand("\\texttt{ <text here> }"));
    addButton(Icons.latexFormattingIcons.SmallCaps(), "Small caps", false, new SystemPasteCommand("\\textsc{ <text here> }"));
    addButton(Icons.latexFormattingIcons.Standard(), "Standard", false, new SystemPasteCommand("\\normalfont"));
    addButton(Icons.latexFormattingIcons.MediumSeries(), "Medium series", false, new SystemPasteCommand("\\mdseries"));
    addButton(Icons.latexFormattingIcons.Bold(), "Bold series", false, new SystemPasteCommand("\\bfseries"));
    addButton(Icons.latexFormattingIcons.VerticalForm(), "Vertical form", false, new SystemPasteCommand("\\upshape"));
    addButton(Icons.latexFormattingIcons.ItalicForm(), "Italic form", false, new SystemPasteCommand("\\itshape"));
    addButton(Icons.latexFormattingIcons.SlantedForm(), "Slanted form", false, new SystemPasteCommand("\\slshape"));
    addButton(Icons.latexFormattingIcons.SmallCaps(), "Small caps", false, new SystemPasteCommand("\\scshape"));
    addButton(Icons.latexFormattingIcons.RomanFamily(), "Roman family", false, new SystemPasteCommand("\\rmfamily"));
    addButton(Icons.latexFormattingIcons.SansSerifFamily(), "Sans-Serif family", false, new SystemPasteCommand("\\sffamily"));
    addButton(Icons.latexFormattingIcons.TypewriterFamily(), "Typewriter family", false, new SystemPasteCommand("\\ttfamily"));
    addButton(Icons.latexFormattingIcons.TinySize(), "Tiny size", false, new SystemPasteCommand("\\tiny"));
    addButton(Icons.latexFormattingIcons.ScriptSize(), "Script size", false, new SystemPasteCommand("\\scriptsize"));
    addButton(Icons.latexFormattingIcons.FootnoteSize(), "Footnote size", false, new SystemPasteCommand("\\footnotesize"));
    addButton(Icons.latexFormattingIcons.SmallSize(), "Small size", false, new SystemPasteCommand("\\small"));
    addButton(Icons.latexFormattingIcons.NormalSize(), "Normal size", false, new SystemPasteCommand("\\normalsize"));
    addButton(Icons.latexFormattingIcons.LargeSize(), "Large size", false, new SystemPasteCommand("\\large"));
    addButton(Icons.latexFormattingIcons.LargerSize(), "Larger size", false, new SystemPasteCommand("\\Large"));
    addButton(Icons.latexFormattingIcons.EvenLargerSize(), "Even larger size", false, new SystemPasteCommand("\\LARGE"));
    addButton(Icons.latexFormattingIcons.HugerSize(), "Huge size", false, new SystemPasteCommand("\\huge"));
    addButton(Icons.latexFormattingIcons.HugerSize(), "Huger size", false, new SystemPasteCommand("\\Huge"));
    addButton(Icons.latexFormattingIcons.AlignLeft(), "Align left", false, new SystemPasteCommand("\\begin{flushleft}\\end{flushleft}"));
    addButton(Icons.latexFormattingIcons.AlignCenter(), "Align center", false, new SystemPasteCommand("\\begin{center}\\end{center}"));
    addButton(Icons.latexFormattingIcons.AlignRight(), "Align right", false, new SystemPasteCommand("\\begin{flushright}\\end{flushright}"));
    resize();
  }
}
