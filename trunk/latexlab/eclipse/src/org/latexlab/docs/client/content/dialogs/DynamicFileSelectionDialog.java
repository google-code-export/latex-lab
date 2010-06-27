package org.latexlab.docs.client.content.dialogs;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import org.latexlab.docs.client.commands.SystemListDocumentsCommand;
import org.latexlab.docs.client.events.CommandEvent;
import org.latexlab.docs.client.events.CommandHandler;
import org.latexlab.docs.client.events.FileSelectionEvent;
import org.latexlab.docs.client.events.FileSelectionHandler;
import org.latexlab.docs.client.gdocs.DocumentServiceEntry;
import org.latexlab.docs.client.widgets.DynamicDialog;
import org.latexlab.docs.client.widgets.ExplorerTree;

/**
 * A dialog window for selecting a file.
 */
public class DynamicFileSelectionDialog extends DynamicDialog {

  /**
   * Contains File Selection Dialog contents.
   */
  protected class FileSelectionDialogContents extends Composite {
	
	private Button ok, cancel;
	private Anchor refresh;
    private VerticalPanel panel;
    private ScrollPanel scroll;
    private ExplorerTree tree;
  
    /**
     * Constructs a new File Selection Dialog contents.
     */
	protected FileSelectionDialogContents() {
      addClickHandler(new ClickHandler() {
          public void onClick(ClickEvent event) {
            hide();
          }
      });
      panel = new VerticalPanel();
      panel.setWidth("400px");
      panel.setVerticalAlignment(VerticalPanel.ALIGN_TOP);
      initWidget(panel);
      scroll = new ScrollPanel();
	  tree = new ExplorerTree(true, true, true, null);
	  tree.setSize("100%", "100%");
	  tree.setExcludedOpen(true);
	  scroll.add(tree);
	  ok = new Button("OK", new ClickHandler(){
	    public void onClick(ClickEvent event) {
	      hide();
	      if (selectionHandler != null) {
	        ArrayList<DocumentServiceEntry> files = tree.getSelectedEntries();
	        if (files.size() > 0) {
	          selectionHandler.onSelection(new FileSelectionEvent(files.get(0)));
	        }
	      }
	    }
	  });
	  cancel = new Button("Cancel", new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        hide();
	      }
	    }
	  );
	  refresh = new Anchor("Refresh", "#");
	  refresh.addClickHandler(new ClickHandler(){
	    public void onClick(ClickEvent event) {
	      event.preventDefault();
	      event.stopPropagation();
	      loadEntries(false);
	    }
	  });
	  HorizontalPanel buttons = new HorizontalPanel();
	  buttons.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	  buttons.setSpacing(10);
	  buttons.add(ok);
	  buttons.add(cancel);
	  buttons.add(refresh);
	  panel.add(scroll);
	  panel.add(buttons);
	  onShowContent = new Runnable() {
		@Override
		public void run() {
		  resize();
		  if (tree.getItemCount() == 0) {
		    loadEntries(true);
		  }
		} 
	  };
	}
	  
	/**
	 * Causes document entries to be loaded.
	 * 
	 * @param useCache whether to use a document cache, when available.
	 */
	private void loadEntries(boolean useCache) {
	  tree.clear();
	  panel.setStylePrimaryName("lab-Loading");
	  CommandEvent.fire(DynamicFileSelectionDialog.this, new SystemListDocumentsCommand(useCache,
	      new AsyncCallback<DocumentServiceEntry[]>() {
			  @Override
		      public void onFailure(Throwable caught) {
			  }
			  @Override
			  public void onSuccess(DocumentServiceEntry[] result) {
			    setEntries(result);
			  }
	      }
	  ));
	}

	/**
	 * Resizes the dialog window to match the browser window size.
	 */
	private void resize() {
	  int targetHeight = Window.getClientHeight() - 180;
	  panel.setPixelSize(400, targetHeight);
	  scroll.setPixelSize(400, targetHeight - 40);
	  this.setWidth("400px");
	}

	/**
	 * Displays a list of document entries.
	 * 
	 * @param entries the document entries.
	 * @param excludeId the ID of the document to be excluded, if any.
	 */
	public void setEntries(DocumentServiceEntry[] entries) {
      panel.setStyleName("");
	  tree.setEntries(entries);
	  resize();
	}
	
  }

  protected static DynamicFileSelectionDialog instance;

  /**
   * Retrieves the single instance of this class.
   * 
   * @param commandHandler the command handler.
   * @param selectionHandler the selection handler.
   */
  public static DynamicFileSelectionDialog get(
	  final CommandHandler commandHandler,
	  FileSelectionHandler selectionHandler) {
    if (instance == null) {
      instance = new DynamicFileSelectionDialog();
      instance.addCommandHandler(commandHandler);
    }
    instance.setSelectionHandler(selectionHandler);
    return instance;
  }
  
  protected Runnable onShowContent;
  protected FileSelectionHandler selectionHandler;
  
  /**
   * Constructs a dialog window for selecting a file.
   */
  public DynamicFileSelectionDialog() {
    super("Select File", true, "400px", null);
  }

  /**
   * Retrieves the dialog's contents asynchronously.
   * 
   * @param callback the callback carrying the content widget
   */
  @Override
  protected void getContents(final AsyncCallback<Widget> callback) {
    GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		}
		@Override
		public void onSuccess() {
	      callback.onSuccess(new FileSelectionDialogContents());
	      center();
		}
    });
  }
  
  /**
   * Sets the file selection handler.
   * 
   * @param selectionHandler the file selection handler
   */
  public void setSelectionHandler(FileSelectionHandler selectionHandler) {
    this.selectionHandler = selectionHandler;
  }

  /**
   * Makes the dialog window visible.
   */
  @Override
  public void show() {
    super.show();
    if (onShowContent != null) {
      onShowContent.run();
    }
  }
  
}
