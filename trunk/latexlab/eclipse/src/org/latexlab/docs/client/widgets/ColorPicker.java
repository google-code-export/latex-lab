package org.latexlab.docs.client.widgets;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

/**
 * Implements a basic color picker.
 */
public class ColorPicker extends Composite {

  private static final String[] QUICK_COLORS = new String[] {
	  "FF0000", "00FF00", "0000FF", "FFFF00", "00FFFF", "FF00FF" };
  private static final int SWATCH_SIZE = 30;
  private static final String[] UNITS = new String[] { "00", "33", "66", "99", "CC", "FF" };
  private FocusPanel panel;
  private String selectedColor;
  private FlexTable swatchTable;
  
  /**
   * Cosntructs a new color picker panel.
   */
  public ColorPicker() {
	panel = new FocusPanel();
	swatchTable = new FlexTable();
	swatchTable.setPixelSize(21 * SWATCH_SIZE, 12 * SWATCH_SIZE);
	swatchTable.setBorderWidth(0);
	swatchTable.setCellPadding(0);
	swatchTable.setCellSpacing(0);
	for (int y=0; y<12; y++) {
      swatchTable.insertRow(y);
	  String d3 = UNITS[y % 6];
	  addColorCell(swatchTable, "000000", y);
	  if (y < 6) {
	    addColorCell(swatchTable, d3 + d3 + d3, y);
	  } else {
	    addColorCell(swatchTable, QUICK_COLORS[y - 6], y);
	  }
	  addColorCell(swatchTable, "000000", y);
	  for (int x1=0; x1<3; x1++) {
		String d1 = (y < 6 ? UNITS[x1] : UNITS[3 + x1]);
		for (int x2=0; x2<6; x2++) {
		  String color = d1 + UNITS[x2 % 6] + d3;
		  addColorCell(swatchTable, color, y);
		}
	  }
	}
	panel.add(swatchTable);
	panel.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		  int x = event.getNativeEvent().getClientX();
		  int y = event.getNativeEvent().getClientY();
		  x -= panel.getAbsoluteLeft() - 1;
		  y -= panel.getAbsoluteTop() - 1;
		  x = (int) Math.floor(x/(SWATCH_SIZE));
		  y = (int) Math.floor(y/(SWATCH_SIZE));
          switch (x){
		  case 1:
			if (y < 6) {
			  selectedColor = "#" + UNITS[y] + UNITS[y] + UNITS[y];
			} else {
			  selectedColor = "#" + QUICK_COLORS[y - 6];
			}
			break;
		  case 0:
		  case 2:
			selectedColor = "#000000";
			break;
	      default:
		    x -= 3;
		    int x1 = x / 6;
		    int x2 = x % 6;
	        String d3 = UNITS[y % 6];
		    String d1 = (y < 6 ? UNITS[x1] : UNITS[3 + x1]);
		    selectedColor = "#" + d1 + UNITS[x2 % 6] + d3;
	    	break;
		  }
		}
	});
	swatchTable.setStyleName("lab-ColorPicker");
	initWidget(panel);
  }
  
  /**
   * Adds a click handler.
   * 
   * @param handler the click handler
   * @return the handler registration
   */
  public HandlerRegistration addClickHandler(ClickHandler handler) {
	return panel.addClickHandler(handler);
  }
  
  /**
   * Adds a color selection cell.
   * 
   * @param table the table to add the cell to
   * @param color the cell's color
   * @param y the the table row to add the cell to
   */
  private void addColorCell(FlexTable table, String color, int y) {
	int x = table.getCellCount(y);
	table.insertCell(y, x);
	FlexCellFormatter fcf = table.getFlexCellFormatter();
	fcf.setWidth(y, x, SWATCH_SIZE + "px");
	fcf.setHeight(y, x, SWATCH_SIZE + "px");
	Element el = fcf.getElement(y, x);
	el.getStyle().setBackgroundColor(color);
  }
  
  /**
   * Retrieves the selected color.
   * 
   * @return the selected color
   */
  public String getSelectedColor() {
	return selectedColor;
  }
  
}
