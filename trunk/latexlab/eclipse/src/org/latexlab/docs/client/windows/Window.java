package org.latexlab.docs.client.windows;

import org.latexlab.docs.client.resources.icons.Icons;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class Window extends Composite {

  public static enum Direction {
    NORTH(0x0001, "windowTopCenter", 1, 0),
    EAST(0x0002, "windowMiddleRight", 2, 1),
    SOUTH(0x0004, "windowBottomCenter", 1, 2),
    WEST(0x0008, "windowMiddleLeft", 0, 1),
    NORTH_EAST(Direction.NORTH.getBits() | Direction.EAST.getBits(),
        "windowTopRight", 2, 0),
    SOUTH_EAST(Direction.SOUTH.getBits() | Direction.EAST.getBits(),
        "windowBottomRight", 2, 2),
    NORTH_WEST(Direction.NORTH.getBits() | Direction.WEST.getBits(),
        "windowTopLeft", 0, 0),
    SOUTH_WEST(Direction.SOUTH.getBits() | Direction.WEST.getBits(),
        "windowBottomLeft", 0, 2);
    
    private int bits, x, y;
    private String style;
    
    private Direction(int bits, String style, int x, int y) {
      this.bits = bits;
      this.style = style;
      this.x = x;
      this.y = y;
    }
    
    public int getBits() {
      return bits;
    }
    
    public String getStyle() {
      return style;
    }
    
    public int getX(){
      return x;
    }
    
    public int getY() {
      return y;
    }
  }

  protected static final int WINDOW_ZINDEX = 3;
  protected HandlerManager manager;
  public PickupDragController registeredDragController;
  protected int contentHeight, contentWidth,
      minContentHeight = 0, minContentWidth = 0;
  protected Widget eastWidget,
      northWidget, southWidget, westWidget;
  protected Grid grid = new Grid(3, 3);
  protected FlexTable mainPanel;
  protected Label titleLabel;
  protected HorizontalPanel buttons;
  protected PushButton closeButton;
  protected Widget contentWidget;
  protected boolean acceptsDrops = false, initialLoad = false;
  protected int BORDER_THICKNESS = 6;
  protected String id;
  
  public Window(String title) {
    this(title, new VerticalPanel(), false);
  }
  
  public Window(String title, Widget content, boolean allowDropping) {
	manager = new HandlerManager(this);
	acceptsDrops = allowDropping;
	
    contentWidget = content;
    
    closeButton = new PushButton();
    closeButton = new PushButton(Icons.editorIcons.CloseBlue().createImage());
    closeButton.setTitle("Close");
    closeButton.setStylePrimaryName("CloseButton");
    
    buttons = new HorizontalPanel();
    buttons.setStylePrimaryName("Buttons");
    buttons.setWidth("16px");
    buttons.setSpacing(4);
    buttons.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
    buttons.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
    buttons.add(closeButton);
    
    titleLabel = new Label(title);
    titleLabel.setTitle(title);
    titleLabel.setWordWrap(false);
    titleLabel.getElement().getStyle().setOverflow(Overflow.HIDDEN);
    
    mainPanel = new FlexTable();
    mainPanel.setCellSpacing(0);
    mainPanel.setCellPadding(0);
    mainPanel.setWidth("100%");
    mainPanel.insertRow(0);
    mainPanel.insertCell(0, 0);
    mainPanel.insertCell(0, 1);
    mainPanel.insertRow(1);
    mainPanel.insertCell(1, 0);
    mainPanel.getRowFormatter().addStyleName(0, "Header");
    mainPanel.getCellFormatter().setHorizontalAlignment(0, 1, HorizontalPanel.ALIGN_RIGHT);
    mainPanel.getFlexCellFormatter().addStyleName(0, 0, "Title");
    mainPanel.getFlexCellFormatter().setWidth(0, 1, "16px");
    mainPanel.getFlexCellFormatter().addStyleName(1, 0, "Content");
    mainPanel.addStyleName("Base");
    mainPanel.setWidget(0, 0, titleLabel);
    mainPanel.setWidget(0, 1, buttons);
    mainPanel.setWidget(1, 0, contentWidget);
    mainPanel.getFlexCellFormatter().setColSpan(1, 0, 2);

    setupCell(Direction.NORTH_WEST);
    northWidget = setupCell(Direction.NORTH);
    setupCell(Direction.NORTH_EAST);

    westWidget = setupCell(Direction.WEST);
    grid.setWidget(1, 1, mainPanel);
    eastWidget = setupCell(Direction.EAST);

    setupCell(Direction.SOUTH_WEST);
    southWidget = setupCell(Direction.SOUTH);
    setupCell(Direction.SOUTH_EAST);

    grid.setCellSpacing(0);
    grid.setCellPadding(0);
    grid.setStylePrimaryName("lab-Window");
    initWidget(grid);
	this.getElement().getStyle().setZIndex(WINDOW_ZINDEX);
  }
  
  public void addClickHandler(ClickHandler handler) {
    closeButton.addClickHandler(handler);
  }
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getContentHeight() {
    return contentHeight;
  }

  public int getContentWidth() {
    return contentWidth;
  }
  
  public Widget getHeaderWidget() {
    return titleLabel;
  }
  
  public Widget getBoundaryWidget(Direction direction) {
    return grid.getWidget(direction.getY(), direction.getX());
  }
  
  public Widget getContentWidget() {
    return contentWidget;
  }
  
  public void setContentWidget(Widget w) {
    contentWidget = w;
    mainPanel.setWidget(1, 0, w);
  }
  
  public String getTitle() {
    return titleLabel.getText();
  }
  
  public void setTitle(String title) {
    titleLabel.setTitle(title);
  }
  
  public void hide() {
    grid.setVisible(false);
  }
  
  public void show() {
    grid.setVisible(true);
  }

  public void setContentSize(int width, int height) {
    if (width < minContentWidth) return;
    if (height < minContentHeight) return;
    if (width != contentWidth) {
      contentWidth = width;
      northWidget.setPixelSize(contentWidth, BORDER_THICKNESS);
      southWidget.setPixelSize(contentWidth, BORDER_THICKNESS);
    }
    if (height != contentHeight) {
      contentHeight = height;
      int headerHeight = titleLabel.getOffsetHeight();
      westWidget.setPixelSize(BORDER_THICKNESS, contentHeight + headerHeight);
      eastWidget.setPixelSize(BORDER_THICKNESS, contentHeight + headerHeight);
    }
    titleLabel.getElement().getStyle().setWidth(width - 20, Unit.PX);
    contentWidget.setPixelSize(contentWidth, contentHeight);
  }
  
  public void setContentSizeFinal() {
	int width = contentWidget.getOffsetWidth();
	int height = contentWidget.getOffsetHeight();
	setContentSize(width, height);
  }

  private Widget setupCell(Direction direction) {
    final FocusPanel widget = new FocusPanel();
    widget.setPixelSize(BORDER_THICKNESS, BORDER_THICKNESS);
    grid.setWidget(direction.getY(), direction.getX(), widget);
    grid.getCellFormatter().addStyleName(direction.getY(), direction.getX(),
        direction.getStyle());
    return widget;
  }
  
  @Override
  public void fireEvent(GwtEvent<?> event) {
	  manager.fireEvent(event);
  }

  @Override
  protected void onLoad() {
    super.onLoad();
    final Widget content = contentWidget;
    if (!initialLoad && content.getOffsetHeight() != 0) {
      initialLoad = true;
      setContentSize(content.getOffsetWidth(),
          content.getOffsetHeight());
    }
  }

  @Override
  protected void onUnload() {
    super.onUnload();
  }

}
