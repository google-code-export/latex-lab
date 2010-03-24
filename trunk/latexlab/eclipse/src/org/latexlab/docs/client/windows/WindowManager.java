package org.latexlab.docs.client.windows;

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

import org.latexlab.docs.client.widgets.GlassPanel;
import org.latexlab.docs.client.windows.Window.Direction;

public class WindowManager {
  
  private AbsolutePanel boundaryPanel;
  private WindowController windowController;
  
  public WindowManager(AbsolutePanel boundaryPanel) {
    this.boundaryPanel = boundaryPanel;
    this.windowController = new WindowController(boundaryPanel);
  }
  
  public AbsolutePanel getBoundaryPanel() {
    return boundaryPanel;
  }
  
  public WindowController getWindowController() {
    return windowController;
  }
  
  public class WindowController {
    private final AbsolutePanel boundaryPanel;

    private PickupDragController pickupDragController;

    private WindowResizeDragController resizeDragController;

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

    public AbsolutePanel getBoundaryPanel() {
      return boundaryPanel;
    }

    public PickupDragController getPickupDragController() {
      return pickupDragController;
    }

    public WindowResizeDragController getResizeDragController() {
      return resizeDragController;
    }
    
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
    
    public final class WindowResizeDragController extends AbstractDragController {

      private static final int MIN_WIDGET_SIZE = 10;
      private HashMap<Widget, Direction> directionMap = new HashMap<Widget, Direction>();
      private Window windowPanel = null;
      
      public WindowResizeDragController(AbsolutePanel boundaryPanel) {
        super(boundaryPanel);
      }

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
      
      @Override
      public void dragEnd() {
        super.dragEnd();
        windowPanel = (Window) context.draggable.getParent().getParent();
        windowPanel.removeStyleName(DragClientBundle.INSTANCE.css().dragging());
        windowPanel.setContentSizeFinal();
      }

      public void makeDraggable(Widget widget, Direction direction) {
        super.makeDraggable(widget);
        directionMap.put(widget, direction);
      }

      protected BoundaryDropController newBoundaryDropController(AbsolutePanel boundaryPanel,
          boolean allowDroppingOnBoundaryPanel) {
        if (allowDroppingOnBoundaryPanel) {
          throw new IllegalArgumentException();
        }
        return new BoundaryDropController(boundaryPanel, false);
      }

      private void moveBy(Window win, int right, int down) {
        AbsolutePanel parent = (AbsolutePanel) win.getParent();
        Location location = new WidgetLocation(win, parent);
        int finalX = location.getLeft() + right;
        int finalY = location.getTop() + down;
        parent.setWidgetPosition(win, finalX, finalY);
      }

      private Direction getDirection(Widget draggable) {
        return directionMap.get(draggable);
      }
    }
  }
  
}
