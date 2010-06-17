package org.latexlab.docs.editor.advanced.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.latexlab.docs.client.commands.CurrentDocumentChangedCommand;
import org.latexlab.docs.client.commands.CurrentDocumentSaveCommand;
import org.latexlab.docs.client.commands.NewDocumentStartCommand;
import org.latexlab.docs.client.commands.SystemSetPerspectiveCommand;
import org.latexlab.docs.client.commands.SystemShowDialogCommand;
import org.latexlab.docs.client.content.dialogs.DynamicFileListDialog;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.content.latex.SetAboveAndBelow;
import org.latexlab.docs.client.content.latex.SetAccents;
import org.latexlab.docs.client.content.latex.SetArrows;
import org.latexlab.docs.client.content.latex.SetArrowsWithCaptions;
import org.latexlab.docs.client.content.latex.SetBinaryOperators;
import org.latexlab.docs.client.content.latex.SetBoundaries;
import org.latexlab.docs.client.content.latex.SetComparison;
import org.latexlab.docs.client.content.latex.SetDiverseSymbols;
import org.latexlab.docs.client.content.latex.SetFormatting;
import org.latexlab.docs.client.content.latex.SetGreekLowercase;
import org.latexlab.docs.client.content.latex.SetGreekUppercase;
import org.latexlab.docs.client.content.latex.SetLogical;
import org.latexlab.docs.client.content.latex.SetConstructs;
import org.latexlab.docs.client.content.latex.SetBigOperators;
import org.latexlab.docs.client.content.latex.SetSets;
import org.latexlab.docs.client.content.latex.SetSubscriptAndSuperscript;
import org.latexlab.docs.client.content.latex.SetWhiteSpacesAndDots;
import org.latexlab.docs.client.content.windows.ToolbarWindowAboveAndBelow;
import org.latexlab.docs.client.content.windows.ToolbarWindowAccents;
import org.latexlab.docs.client.content.windows.ToolbarWindowArrows;
import org.latexlab.docs.client.content.windows.ToolbarWindowArrowsWithCaptions;
import org.latexlab.docs.client.content.windows.ToolbarWindowBinaryOperators;
import org.latexlab.docs.client.content.windows.ToolbarWindowBoundaries;
import org.latexlab.docs.client.content.windows.ToolbarWindowComparison;
import org.latexlab.docs.client.content.windows.ToolbarWindowDiverseSymbols;
import org.latexlab.docs.client.content.windows.ToolbarWindowFormatting;
import org.latexlab.docs.client.content.windows.ToolbarWindowGreekLowercase;
import org.latexlab.docs.client.content.windows.ToolbarWindowGreekUppercase;
import org.latexlab.docs.client.content.windows.ToolbarWindowLogical;
import org.latexlab.docs.client.content.windows.ToolbarWindowMath;
import org.latexlab.docs.client.content.windows.ToolbarWindowOperators;
import org.latexlab.docs.client.content.windows.ToolbarWindowSets;
import org.latexlab.docs.client.content.windows.ToolbarWindowSubscriptAndSuperscript;
import org.latexlab.docs.client.content.windows.ToolbarWindowWhiteSpacesAndDots;
import org.latexlab.docs.client.events.AsyncInstantiationCallback;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.events.IntRunnable;
import org.latexlab.docs.client.parts.BodyPart;
import org.latexlab.docs.client.parts.FooterPart;
import org.latexlab.docs.client.parts.HeaderPart;
import org.latexlab.docs.client.parts.OutputPart;
import org.latexlab.docs.client.parts.PreviewerPart;
import org.latexlab.docs.client.parts.HeaderPart.SaveState;
import org.latexlab.docs.client.widgets.ToolbarWindow;
import org.latexlab.docs.client.widgets.WindowManager;
import org.latexlab.docs.editor.advanced.client.parts.EditorPart;
import org.latexlab.docs.editor.advanced.client.parts.MenuPart;
import org.latexlab.docs.editor.advanced.client.parts.ToolbarPart;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DocsAdvancedEditorView {

  protected static DocsAdvancedEditorView instance;
  
  public static void get(final CommandHandler handler, final String userEmail,
	    final AsyncInstantiationCallback<DocsAdvancedEditorView> cb) {
	GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  cb.onFailure(reason);
		}
		@Override
		public void onSuccess() {
	      if (instance == null) {
	        instance = new DocsAdvancedEditorView(handler, userEmail);
	      }
		  cb.onSuccess(instance);
		}
	});
  }
	
  public enum LockFunction {
	SAVE,
	COMPILE
  }
	  
  private CommandHandler controller;
  private AbsolutePanel root;
  private WindowManager windowManager;
  private BodyPart body;
  private EditorPart editor;
  private PreviewerPart previewer;
  private OutputPart output;
  private FlexTable contentPane;
  private HeaderPart header;
  private FooterPart footer;
  private MenuPart menu;
  private ToolbarPart tools;
  private HashMap<String, ToolbarWindow> toolbars;
  private IntRunnable controlKeyHandler;
  
  protected DocsAdvancedEditorView(CommandHandler c, String userEmail) {
	controller = c;
	toolbars = new HashMap<String, ToolbarWindow>();
    contentPane = new FlexTable();
    contentPane.setWidth("100%");
    contentPane.setHeight("100%");
    contentPane.setCellSpacing(0);
    contentPane.setCellPadding(0);
    contentPane.setBorderWidth(0);
    contentPane.insertRow(0);
    contentPane.insertCell(0, 0);
    contentPane.getFlexCellFormatter().setHeight(0, 0, "120px");
    contentPane.insertRow(1);
    contentPane.insertCell(1, 0);
    contentPane.insertRow(2);
    contentPane.insertCell(2, 0);
    contentPane.getFlexCellFormatter().setHeight(2, 0, "20px");
    header = new HeaderPart();
    header.setAuthor(userEmail);
    header.addCommandHandler(controller);
    footer = new FooterPart();
    footer.addCommandHandler(controller);
    menu = new MenuPart();
    menu.addCommandHandler(controller);
    tools = new ToolbarPart();
    tools.addCommandHandler(controller);
    editor = new EditorPart();
    editor.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
	      try {
		    menu.close();
	      } catch (Exception x) { }
		}
    });
    editor.addChangeHandler(new ChangeHandler() {
		@Override
		public void onChange(ChangeEvent event) {
	      CommandEvent.flow(controller, new CurrentDocumentChangedCommand());
		}
    });
    previewer = new PreviewerPart();
    previewer.addCommandHandler(controller);
    VerticalPanel headerPanel = new VerticalPanel();
    headerPanel.setWidth("100%");
    headerPanel.add(header);
    headerPanel.add(menu);
    headerPanel.add(tools);
    output = new OutputPart();
    output.setOutput("");
    body = new BodyPart();
    body.setWidth("100%");
    body.setHeight("100%");
    body.setTopLeftWidget(this.editor);
    body.setTopRightWidget(this.previewer);
    body.setBottomWidget(this.output);
    contentPane.setWidget(0, 0, headerPanel);
    contentPane.setWidget(1, 0, body);
    contentPane.setWidget(2, 0, footer);
    root = new AbsolutePanel();
    root.setSize("100%", "100%");
    root.add(contentPane);
    FocusPanel wrap = new FocusPanel(root);
    controlKeyHandler = new IntRunnable() {
		@Override
		public void run(int i) {
	      Event e = Event.getCurrentEvent();
	      switch(i) {
	      case 83: //CTRL+S
	    	if (e != null) e.preventDefault();
	    	CommandEvent.flow(controller, new CurrentDocumentSaveCommand());
			break;
		  case 79: //CTRL+O
	    	if (e != null) e.preventDefault();
	    	CommandEvent.flow(controller, new SystemShowDialogCommand(DynamicFileListDialog.class));
			break;
		  case 78: //CTRL+N
	    	if (e != null) e.preventDefault();
	    	CommandEvent.flow(controller, new NewDocumentStartCommand());
			break;
		  }
		}
    };
    wrap.addKeyDownHandler(new KeyDownHandler() {
		@Override
		public void onKeyDown(KeyDownEvent event) {
		  if (event.isControlKeyDown()) {
			controlKeyHandler.run(event.getNativeKeyCode());
		  } else {
			int key = event.getNativeKeyCode();
			switch (key) {
			case 8: //BACKSPACE
				event.stopPropagation();
				event.preventDefault();
				break;
			}
		  }
		}
    });
    RootPanel.get().add(wrap);
    windowManager = new WindowManager(root);
    PickupDragController dragController = new PickupDragController(root, true);
    dragController.setBehaviorConstrainedToBoundaryPanel(true);
    dragController.setBehaviorMultipleSelection(false);
    dragController.setBehaviorDragStartSensitivity(1);
  }
  
  public BodyPart getBody() {
    return body;
  }

  public EditorPart getEditor() {
    return editor;
  }

  public PreviewerPart getPreviewer() {
    return previewer;
  }

  public OutputPart getOutput() {
    return output;
  }

  public HeaderPart getHeader() {
    return header;
  }

  public FooterPart getFooter() {
    return footer;
  }

  public MenuPart getMenu() {
    return menu;
  }

  public ToolbarPart getTools() {
    return tools;
  }
  
  public int getToolbarLeft(ToolbarWindow tb) {
	return windowManager.getBoundaryPanel().getWidgetLeft(tb);
  }
  
  public int getToolbarTop(ToolbarWindow tb) {
	return windowManager.getBoundaryPanel().getWidgetTop(tb);
  }
  
  public void setToolbarPosition(ToolbarWindow tb, int left, int top) {
    windowManager.getBoundaryPanel().setWidgetPosition(tb, left, top);
  }
  
  public void loadEditor(boolean colorSyntax,
	    boolean wrapText, boolean showLineNumbers, boolean checkSpelling,
	    LoadHandler handler) {
    editor.addLoadHandler(handler);
    editor.init(colorSyntax, wrapText, showLineNumbers,
        checkSpelling, controlKeyHandler);
  }
  
  public void setPerspective(int view) {
  	switch(view) {
  	case SystemSetPerspectiveCommand.VIEW_SOURCE:
  	  body.setHorizontalSplitPosition("100%");
  	  body.setVerticalSplitPosition("100%");
      break;
  	case SystemSetPerspectiveCommand.VIEW_PREVIEW:
      body.setHorizontalSplitPosition("0%");
      body.setVerticalSplitPosition("100%");
      break;
  	case SystemSetPerspectiveCommand.VIEW_SPLIT:
      body.setHorizontalSplitPosition("50%");
      body.setVerticalSplitPosition("100%");
      break;
  	case SystemSetPerspectiveCommand.VIEW_OUTPUT:
      body.setHorizontalSplitPosition("100%");
      body.setVerticalSplitPosition("0%");
      break;
  	}
  }
  
  public void setFunctionLock(LockFunction func, boolean locked) {
	switch (func) {
	case SAVE:
	  header.setSaveState(locked ? SaveState.SAVING : SaveState.SAVED);
	  tools.setButtonEnabled(1, !locked);
	  menu.setMenuItemEnabled(new String[] { "File", "Save" }, !locked);
	  break;
	case COMPILE:
	  tools.setButtonEnabled(5, !locked);
	  menu.setMenuItemEnabled(new String[] { "Compiler", "Compile..." }, !locked);
	  break;
	}
  }
  
  public void toggleFullScreen() {
	boolean isFullScreen = !header.isVisible();
	String[] path = new String[] { "View", "Full-screen mode" };
    if (isFullScreen) {
      menu.setMenuItemIcon(path, Icons.editorIcons.Blank());
      header.setVisible(true);
      contentPane.getFlexCellFormatter().setHeight(0, 0, "120px");
	    body.setVerticalSplitPosition("100%");
    } else {
      menu.setMenuItemIcon(path, Icons.editorIcons.CheckBlack());
      header.setVisible(false);
      contentPane.getFlexCellFormatter().setHeight(0, 0, "40px");
	    body.setVerticalSplitPosition("100%");
    }
  }

  public void toggleToolbar(final String name, final boolean reuseWindows) {
	targetToolbar(name, new AsyncCallback<ToolbarWindow>() {
		@Override
		public void onFailure(Throwable caught) {
		}
		@Override
		public void onSuccess(ToolbarWindow target) {
		  toolbars.put(name, target);
	      boolean toVisible = !target.isVisible();
		  if (reuseWindows) {
			Iterator<Entry<String, ToolbarWindow>> it =
				toolbars.entrySet().iterator();
			while(it.hasNext()) {
			  Entry<String, ToolbarWindow> entry = it.next();
			  ToolbarWindow tb = entry.getValue();
		      if (tb.isVisible()) {
		  	    int left = getToolbarLeft(tb);
		  	    int top = getToolbarTop(tb);
		        tb.hide();
		        setToolbarPosition(target, left, top);
		        tools.setButtonState(entry.getKey(), false);
		        menu.setMenuItemHighlighted(new String[] { "View", "Toolbars", entry.getKey() }, false);
		      }
			}
		  } else {
		    target.hide();
		    tools.setButtonState(name, false);
	        menu.setMenuItemHighlighted(new String[] { "View", "Toolbars", name }, false);
		  }
		  if (toVisible) {
		    target.show();
		    tools.setButtonState(name, true);
	        menu.setMenuItemHighlighted(new String[] { "View", "Toolbars", name }, true);
		  }
		}
	});
  }
  
  private void targetToolbar(String name, final AsyncCallback<ToolbarWindow> cb) {
	if (name.equalsIgnoreCase(SetAboveAndBelow.TITLE)) {
	  cb.onSuccess(ToolbarWindowAboveAndBelow.get(controller, windowManager));
	} else if (name.equalsIgnoreCase(SetAccents.TITLE)) {
	  cb.onSuccess(ToolbarWindowAccents.get(controller, windowManager));
	} else if (name.equalsIgnoreCase(SetArrows.TITLE)) {
	  cb.onSuccess(ToolbarWindowArrows.get(controller, windowManager));
	} else if (name.equalsIgnoreCase(SetArrowsWithCaptions.TITLE)) {
	  cb.onSuccess(ToolbarWindowArrowsWithCaptions.get(controller, windowManager));
	} else if (name.equalsIgnoreCase(SetBinaryOperators.TITLE)) {
	  cb.onSuccess(ToolbarWindowBinaryOperators.get(controller, windowManager));
	} else if (name.equalsIgnoreCase(SetBoundaries.TITLE)) {
	  cb.onSuccess(ToolbarWindowBoundaries.get(controller, windowManager));
	} else if (name.equalsIgnoreCase(SetComparison.TITLE)) {
	  cb.onSuccess(ToolbarWindowComparison.get(controller, windowManager));
	} else if (name.equalsIgnoreCase(SetDiverseSymbols.TITLE)) {
	  cb.onSuccess(ToolbarWindowDiverseSymbols.get(controller, windowManager));
	} else if (name.equalsIgnoreCase(SetFormatting.TITLE)) {
	  cb.onSuccess(ToolbarWindowFormatting.get(controller, windowManager));
	} else if (name.equalsIgnoreCase(SetGreekLowercase.TITLE)) {
	  cb.onSuccess(ToolbarWindowGreekLowercase.get(controller, windowManager));
	} else if (name.equalsIgnoreCase(SetGreekUppercase.TITLE)) {
	  cb.onSuccess(ToolbarWindowGreekUppercase.get(controller, windowManager));
	} else if (name.equalsIgnoreCase(SetLogical.TITLE)) {
	  cb.onSuccess(ToolbarWindowLogical.get(controller, windowManager));
	} else if (name.equalsIgnoreCase(SetConstructs.TITLE)) {
	  cb.onSuccess(ToolbarWindowMath.get(controller, windowManager));
	} else if (name.equalsIgnoreCase(SetBigOperators.TITLE)) {
	  cb.onSuccess(ToolbarWindowOperators.get(controller, windowManager));
	} else if (name.equalsIgnoreCase(SetSets.TITLE)) {
	  cb.onSuccess(ToolbarWindowSets.get(controller, windowManager));
	} else if (name.equalsIgnoreCase(SetSubscriptAndSuperscript.TITLE)) {
	  cb.onSuccess(ToolbarWindowSubscriptAndSuperscript.get(controller, windowManager));
	} else if (name.equalsIgnoreCase(SetWhiteSpacesAndDots.TITLE)) {
	  cb.onSuccess(ToolbarWindowWhiteSpacesAndDots.get(controller, windowManager));
	}
  }
  
}
