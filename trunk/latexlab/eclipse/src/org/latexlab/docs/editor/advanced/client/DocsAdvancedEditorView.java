package org.latexlab.docs.editor.advanced.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.latexlab.docs.client.commands.CurrentDocumentChangedCommand;
import org.latexlab.docs.client.commands.CurrentDocumentSaveCommand;
import org.latexlab.docs.client.commands.NewDocumentStartCommand;
import org.latexlab.docs.client.commands.SystemSetPerspectiveCommand;
import org.latexlab.docs.client.commands.SystemShowDialogCommand;
import org.latexlab.docs.client.dialogs.FileListDialog;
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
import org.latexlab.docs.client.resources.icons.Icons;
import org.latexlab.docs.client.windows.ToolbarWindow;
import org.latexlab.docs.client.windows.WindowManager;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowAboveAndBelow;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowAccents;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowArrows;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowArrowsWithCaptions;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowBinaryOperators;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowBoundaries;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowComparison;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowDiverseSymbols;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowFormatting;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowGreekLowercase;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowGreekUppercase;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowLogical;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowMath;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowOperators;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowSets;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowSubscriptAndSuperscript;
import org.latexlab.docs.client.windows.toolbars.ToolbarWindowWhiteSpacesAndDots;
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
import com.google.gwt.user.client.Window;
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
	    	CommandEvent.flow(controller, new SystemShowDialogCommand(FileListDialog.class));
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
	  menu.setMenuItemEnabled("Save", !locked);
	  break;
	case COMPILE:
	  tools.setButtonEnabled(5, !locked);
	  menu.setMenuItemEnabled("Compile...", !locked);
	  break;
	}
  }
  
  public void toggleFullScreen() {
	boolean isFullScreen = !header.isVisible();
    if (isFullScreen) {
      menu.setMenuItemIcon("Full-screen mode", Icons.editorIcons.Blank());
      header.setVisible(true);
      contentPane.getFlexCellFormatter().setHeight(0, 0, "120px");
	    body.setVerticalSplitPosition("100%");
    } else {
      menu.setMenuItemIcon("Full-screen mode", Icons.editorIcons.CheckBlack());
      header.setVisible(false);
      contentPane.getFlexCellFormatter().setHeight(0, 0, "40px");
	    body.setVerticalSplitPosition("100%");
    }
  }

  public void toggleToolbar(final String name, final boolean reuseWindows) {
	targetToolbar(name, new AsyncCallback<ToolbarWindow>() {
		@Override
		public void onFailure(Throwable caught) {
		  Window.alert("A required component failed to load.");
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
		      }
			}
		  } else {
		    target.hide();
		    tools.setButtonState(name, false);
		  }
		  if (toVisible) {
		    target.show();
		    tools.setButtonState(name, true);
		  }
		}
	});
  }
  
  private void targetToolbar(String name, final AsyncCallback<ToolbarWindow> cb) {
	if (name.equalsIgnoreCase(ToolbarWindowAboveAndBelow.TITLE)) {
	  ToolbarWindowAboveAndBelow.get(controller, windowManager, new AsyncInstantiationCallback<ToolbarWindowAboveAndBelow>() {
		@Override public void onFailure(Throwable caught) { cb.onFailure(caught); }
		@Override public void onSuccess(ToolbarWindowAboveAndBelow result) { cb.onSuccess(result); }
	  });
	} else if (name.equalsIgnoreCase(ToolbarWindowAccents.TITLE)) {
	  ToolbarWindowAccents.get(controller, windowManager, new AsyncInstantiationCallback<ToolbarWindowAccents>() {
		@Override public void onFailure(Throwable caught) { cb.onFailure(caught); }
		@Override public void onSuccess(ToolbarWindowAccents result) { cb.onSuccess(result); }
	  });
	} else if (name.equalsIgnoreCase(ToolbarWindowArrows.TITLE)) {
	  ToolbarWindowArrows.get(controller, windowManager, new AsyncInstantiationCallback<ToolbarWindowArrows>() {
		@Override public void onFailure(Throwable caught) { cb.onFailure(caught); }
		@Override public void onSuccess(ToolbarWindowArrows result) { cb.onSuccess(result); }
	  });
	} else if (name.equalsIgnoreCase(ToolbarWindowArrowsWithCaptions.TITLE)) {
	  ToolbarWindowArrowsWithCaptions.get(controller, windowManager, new AsyncInstantiationCallback<ToolbarWindowArrowsWithCaptions>() {
		@Override public void onFailure(Throwable caught) { cb.onFailure(caught); }
		@Override public void onSuccess(ToolbarWindowArrowsWithCaptions result) { cb.onSuccess(result); }
	  });
	} else if (name.equalsIgnoreCase(ToolbarWindowBinaryOperators.TITLE)) {
	  ToolbarWindowBinaryOperators.get(controller, windowManager, new AsyncInstantiationCallback<ToolbarWindowBinaryOperators>() {
		@Override public void onFailure(Throwable caught) { cb.onFailure(caught); }
		@Override public void onSuccess(ToolbarWindowBinaryOperators result) { cb.onSuccess(result); }
	  });
	} else if (name.equalsIgnoreCase(ToolbarWindowBoundaries.TITLE)) {
	  ToolbarWindowBoundaries.get(controller, windowManager, new AsyncInstantiationCallback<ToolbarWindowBoundaries>() {
		@Override public void onFailure(Throwable caught) { cb.onFailure(caught); }
		@Override public void onSuccess(ToolbarWindowBoundaries result) { cb.onSuccess(result); }
	  });
	} else if (name.equalsIgnoreCase(ToolbarWindowComparison.TITLE)) {
	  ToolbarWindowComparison.get(controller, windowManager, new AsyncInstantiationCallback<ToolbarWindowComparison>() {
		@Override public void onFailure(Throwable caught) { cb.onFailure(caught); }
		@Override public void onSuccess(ToolbarWindowComparison result) { cb.onSuccess(result); }
	  });
	} else if (name.equalsIgnoreCase(ToolbarWindowDiverseSymbols.TITLE)) {
	  ToolbarWindowDiverseSymbols.get(controller, windowManager, new AsyncInstantiationCallback<ToolbarWindowDiverseSymbols>() {
		@Override public void onFailure(Throwable caught) { cb.onFailure(caught); }
		@Override public void onSuccess(ToolbarWindowDiverseSymbols result) { cb.onSuccess(result); }
	  });
	} else if (name.equalsIgnoreCase(ToolbarWindowFormatting.TITLE)) {
	  ToolbarWindowFormatting.get(controller, windowManager, new AsyncInstantiationCallback<ToolbarWindowFormatting>() {
		@Override public void onFailure(Throwable caught) { cb.onFailure(caught); }
		@Override public void onSuccess(ToolbarWindowFormatting result) { cb.onSuccess(result); }
	  });
	} else if (name.equalsIgnoreCase(ToolbarWindowGreekLowercase.TITLE)) {
	  ToolbarWindowGreekLowercase.get(controller, windowManager, new AsyncInstantiationCallback<ToolbarWindowGreekLowercase>() {
		@Override public void onFailure(Throwable caught) { cb.onFailure(caught); }
		@Override public void onSuccess(ToolbarWindowGreekLowercase result) { cb.onSuccess(result); }
	  });
	} else if (name.equalsIgnoreCase(ToolbarWindowGreekUppercase.TITLE)) {
	  ToolbarWindowGreekUppercase.get(controller, windowManager, new AsyncInstantiationCallback<ToolbarWindowGreekUppercase>() {
		@Override public void onFailure(Throwable caught) { cb.onFailure(caught); }
		@Override public void onSuccess(ToolbarWindowGreekUppercase result) { cb.onSuccess(result); }
	  });
	} else if (name.equalsIgnoreCase(ToolbarWindowLogical.TITLE)) {
	  ToolbarWindowLogical.get(controller, windowManager, new AsyncInstantiationCallback<ToolbarWindowLogical>() {
		@Override public void onFailure(Throwable caught) { cb.onFailure(caught); }
		@Override public void onSuccess(ToolbarWindowLogical result) { cb.onSuccess(result); }
	  });
	} else if (name.equalsIgnoreCase(ToolbarWindowMath.TITLE)) {
	  ToolbarWindowMath.get(controller, windowManager, new AsyncInstantiationCallback<ToolbarWindowMath>() {
		@Override public void onFailure(Throwable caught) { cb.onFailure(caught); }
		@Override public void onSuccess(ToolbarWindowMath result) { cb.onSuccess(result); }
	  });
	} else if (name.equalsIgnoreCase(ToolbarWindowOperators.TITLE)) {
	  ToolbarWindowOperators.get(controller, windowManager, new AsyncInstantiationCallback<ToolbarWindowOperators>() {
		@Override public void onFailure(Throwable caught) { cb.onFailure(caught); }
		@Override public void onSuccess(ToolbarWindowOperators result) { cb.onSuccess(result); }
	  });
	} else if (name.equalsIgnoreCase(ToolbarWindowSets.TITLE)) {
	  ToolbarWindowSets.get(controller, windowManager, new AsyncInstantiationCallback<ToolbarWindowSets>() {
		@Override public void onFailure(Throwable caught) { cb.onFailure(caught); }
		@Override public void onSuccess(ToolbarWindowSets result) { cb.onSuccess(result); }
	  });
	} else if (name.equalsIgnoreCase(ToolbarWindowSubscriptAndSuperscript.TITLE)) {
	  ToolbarWindowSubscriptAndSuperscript.get(controller, windowManager, new AsyncInstantiationCallback<ToolbarWindowSubscriptAndSuperscript>() {
		@Override public void onFailure(Throwable caught) { cb.onFailure(caught); }
		@Override public void onSuccess(ToolbarWindowSubscriptAndSuperscript result) { cb.onSuccess(result); }
	  });
	} else if (name.equalsIgnoreCase(ToolbarWindowWhiteSpacesAndDots.TITLE)) {
	  ToolbarWindowWhiteSpacesAndDots.get(controller, windowManager, new AsyncInstantiationCallback<ToolbarWindowWhiteSpacesAndDots>() {
		@Override public void onFailure(Throwable caught) { cb.onFailure(caught); }
		@Override public void onSuccess(ToolbarWindowWhiteSpacesAndDots result) { cb.onSuccess(result); }
	  });
	}
  }
  
}
