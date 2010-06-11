package org.latexlab.docs.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;

import com.allen_sauer.gwt.dnd.client.AbstractDragController;
import com.allen_sauer.gwt.dnd.client.DragEndEvent;
import com.allen_sauer.gwt.dnd.client.DragHandler;
import com.allen_sauer.gwt.dnd.client.DragStartEvent;
import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.allen_sauer.gwt.dnd.client.drop.BoundaryDropController;
import com.allen_sauer.gwt.dnd.client.util.DragClientBundle;
import com.allen_sauer.gwt.dnd.client.util.Location;
import com.allen_sauer.gwt.dnd.client.util.WidgetLocation;

import java.util.HashMap;

import org.latexlab.docs.client.widgets.Window.Direction;

/**
 * Manages window dragging, dropping and resizing.
 */
public class WindowManager {
  
  /**
   * Combines drag, drop and resize controllers.
   */
  public class WindowController {
    /**
     * A window resize controller.
     */
    public final class WindowResizeDragController extends AbstractDragController {

      private static final int MIN_WIDGET_SIZE = 10;
      private HashMap<Widget, Direction> directionMap = new HashMap<Widget, Direction>();
      private Window windowPanel = null;
      
      /**
       * Constructs a window resize controller.
       * 
       * @param boundaryPanel the boundary panel
       */
      public WindowResizeDragController(AbsolutePanel boundaryPanel) {
        super(boundaryPanel);
      }

      @Override
      public void dragEnd() {
        super.dragEnd();
        windowPanel = (Window) context.draggable.getParent().getParent();
        windowPanel.removeStyleName(DragClientBundle.INSTANCE.css().dragging());
        windowPanel.setContentSizeFinal();
      }

      /**
       * Processes a drag move.
       */
      public void dragMove() {
        int direction = ((WindowResizeDragController) context.dragController).getDirection(context.draggable).getBits();
        if ((direction & Direction.NORTH.getBits()) != 0) {
          int delta = context.draggable.getAbsoluteTop() - context.desiredDraggableY;
          if (delta != 0) {
            int contentHeight = windowPanel.getContentHeight();
            int newHeight = Math.max(contentHeight + delta, MIN_WIDGET_SIZE);
            if (newHeight != contentHeight) {
              moveBy(windowPanel, 0, contentHeight - newHeight);
            }
            windowPanel.setContentSize(windowPanel.getContentWidth(), newHeight);
          }
        } else if ((direction & Direction.SOUTH.getBits()) != 0) {
          int delta = context.desiredDraggableY - context.draggable.getAbsoluteTop();
          if (delta != 0) {
            windowPanel.setContentSize(windowPanel.getContentWidth(), windowPanel.getContentHeight()
                + delta);
          }
        }
        if ((direction & Direction.WEST.getBits()) != 0) {
          int delta = context.draggable.getAbsoluteLeft() - context.desiredDraggableX;
          if (delta != 0) {
            int contentWidth = windowPanel.getContentWidth();
            int newWidth = Math.max(contentWidth + delta, MIN_WIDGET_SIZE);
            if (newWidth != contentWidth) {
              moveBy(windowPanel, contentWidth - newWidth, 0);
            }
            windowPanel.setContentSize(newWidth, windowPanel.getContentHeight());
          }
        } else if ((direction & Direction.EAST.getBits()) != 0) {
          int delta = context.desiredDraggableX - context.draggable.getAbsoluteLeft();
          if (delta != 0) {
            windowPanel.setContentSize(windowPanel.getContentWidth() + delta,
                windowPanel.getContentHeight());
          }
        }
      }
      
      @Override
      public void dragStart() {
        super.dragStart();
        windowPanel = (Window) context.draggable.getParent().getParent();
        windowPanel.addStyleName(DragClientBundle.INSTANCE.css().dragging());
      }

      /**
       * Retrieves the direction for a given boundary widget.
       * 
       * @param draggable the widget for which to retrieve the direction
       * @return the boundary widget's direction
       */
      private Direction getDirection(Widget draggable) {
        return directionMap.get(draggable);
      }

      /**
       * Makes a widget draggable.
       * 
       * @param widget the widget to make draggable
       * @param direction the drag direction
       */
      public void makeDraggable(Widget widget, Direction direction) {
        super.makeDraggable(widget);
        directionMap.put(widget, direction);
      }

      /**
       * Moves a window by a given horizontal and vertical distance.
       * 
       * @param win the window to move
       * @param right the horizontal distance
       * @param down the vertical distance
       */
      private void moveBy(Window win, int right, int down) {
        AbsolutePanel parent = (AbsolutePanel) win.getParent();
        Location location = new WidgetLocation(win, parent);
        int finalX = location.getLeft() + right;
        int finalY = location.getTop() + down;
        parent.setWidgetPosition(win, finalX, finalY);
      }

      /**
       * Creates a new boundary drop controller.
       * 
       * @param boundaryPanel the boundary panel
       * @param allowDroppingOnBoundaryPanel whether to allow dropping
       * @return the boundary drop controller
       */
      protected BoundaryDropController newBoundaryDropController(AbsolutePanel boundaryPanel,
          boolean allowDroppingOnBoundaryPanel) {
        if (allowDroppingOnBoundaryPanel) {
          throw new IllegalArgumentException();
        }
        return new BoundaryDropController(boundaryPanel, false);
      }
    }

    private final AbsolutePanel boundaryPanel;

    private PickupDragController pickupDragController;

    private WindowResizeDragController resizeDragController;

    /**
     * Constructs a WindowController.
     * 
     * @param boundaryPanel the boundary panel
     */
    public WindowController(AbsolutePanel boundaryPanel) {
      this.boundaryPanel = boundaryPanel;

      pickupDragController = new PickupDragController(boundaryPanel, true);
      pickupDragController.setBehaviorConstrainedToBoundaryPanel(false);
      pickupDragController.setConstrainWidgetToBoundaryPanel(false);
      pickupDragController.setBehaviorMultipleSelection(false);

      resizeDragController = new WindowResizeDragController(boundaryPanel);
      resizeDragController.setBehaviorConstrainedToBoundaryPanel(false);
      resizeDragController.setConstrainWidgetToBoundaryPanel(false);
      resizeDragController.setBehaviorMultipleSelection(false);
      DragHandler glassHandler = new DragHandler() {
		@Override
		public void onDragEnd(DragEndEvent event) {
		  GlassPanel.setGlassPanelVisibility(false, 3);
		}
		@Override
		public void onDragStart(DragStartEvent event) {
		  GlassPanel.setGlassPanelVisibility(true, 3);
		}
		@Override
		public void onPreviewDragEnd(DragEndEvent event)
				throws VetoDragException {
		}
		@Override
		public void onPreviewDragStart(DragStartEvent event)
				throws VetoDragException {
		}
      };
      pickupDragController.addDragHandler(glassHandler);
      resizeDragController.addDragHandler(glassHandler);
    }

    /**
     * Retrieves the boundary panel.
     * 
     * @return the boundary panel
     */
    public AbsolutePanel getBoundaryPanel() {
      return boundaryPanel;
    }

    /**
     * Retrieves the drag controller.
     * 
     * @return the drag controller
     */
    public PickupDragController getPickupDragController() {
      return pickupDragController;
    }
    
    /**
     * Retrieves the resize controller.
     * 
     * @return the resize controller
     */
    public WindowResizeDragController getResizeDragController() {
      return resizeDragController;
    }
    
    /**
     * Makes a window instance resizable.
     * 
     * @param window the window to make resizable
     */
    public void makeResizable(final Window window) {
      windowController.getPickupDragController().makeDraggable(window, window.getHeaderWidget());
      window.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          AbsolutePanel boundaryPanel = windowController.getBoundaryPanel();
          if (boundaryPanel.getWidgetIndex(window) < boundaryPanel.getWidgetCount() - 1) {
            // force our panel to the top of our z-index context
            WidgetLocation location = new WidgetLocation(window, boundaryPanel);
            boundaryPanel.add(window, location.getLeft(), location.getTop());
          }
        }
      });
      windowController.getResizeDragController().makeDraggable(window.getBoundaryWidget(Direction.NORTH), Direction.NORTH);
      windowController.getResizeDragController().makeDraggable(window.getBoundaryWidget(Direction.NORTH_WEST), Direction.NORTH_WEST);
      windowController.getResizeDragController().makeDraggable(window.getBoundaryWidget(Direction.NORTH_EAST), Direction.NORTH_EAST);
      windowController.getResizeDragController().makeDraggable(window.getBoundaryWidget(Direction.WEST), Direction.WEST);
      windowController.getResizeDragController().makeDraggable(window.getBoundaryWidget(Direction.EAST), Direction.EAST);
      windowController.getResizeDragController().makeDraggable(window.getBoundaryWidget(Direction.SOUTH_WEST), Direction.SOUTH_WEST);
      windowController.getResizeDragController().makeDraggable(window.getBoundaryWidget(Direction.SOUTH), Direction.SOUTH);
      windowController.getResizeDragController().makeDraggable(window.getBoundaryWidget(Direction.SOUTH_EAST), Direction.SOUTH_EAST);
    }
  }
  private AbsolutePanel boundaryPanel;
  
  private WindowController windowController;
  
  /**
   * Constructs a WindowManager.
   * 
   * @param boundaryPanel the boundary panel
   */
  public WindowManager(AbsolutePanel boundaryPanel) {
    this.boundaryPanel = boundaryPanel;
    this.windowController = new WindowController(boundaryPanel);
  }
  
  /**
   * Retrieves the boundary panel.
   * 
   * @return the boundary panel
   */
  public AbsolutePanel getBoundaryPanel() {
    return boundaryPanel;
  }
  
  /**
   * Retrieves the window controller.
   * 
   * @return the window controller
   */
  public WindowController getWindowController() {
    return windowController;
  }
  
}
