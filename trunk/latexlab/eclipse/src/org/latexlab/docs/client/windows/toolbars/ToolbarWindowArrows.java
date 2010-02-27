package org.latexlab.docs.client.windows.toolbars;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.resources.icons.latex.LatexIcons;
import org.latexlab.docs.client.windows.ToolbarWindow;

import com.google.gwt.user.client.ui.FlowPanel;

public class ToolbarWindowArrows extends ToolbarWindow{

  public ToolbarWindowArrows() {
	super("Arrows");
    buildToolBar();
  }

  private void buildToolBar() {
	FlowPanel panel = (FlowPanel) contentWidget;
    panel.setStyleName("gdbe-Toolbar");   
    panel.add(buildButton(LatexIcons.icons.Icon261(), "Left arrow", false, new SystemPasteCommand("\\leftarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon278(), "Right arrow", false, new SystemPasteCommand("\\rightarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon295(), "Left/Right arrow", false, new SystemPasteCommand("\\leftrightarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon312(), "Double left arrow", false, new SystemPasteCommand("\\Leftarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon329(), "Double right arrow", false, new SystemPasteCommand("\\Rightarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon346(), "Double left/right arrow", false, new SystemPasteCommand("\\Leftrightarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon363(), "Up arrow", false, new SystemPasteCommand("\\uparrow")));
    panel.add(buildButton(LatexIcons.icons.Icon380(), "Down arrow", false, new SystemPasteCommand("\\downarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon397(), "Up/Down arrow", false, new SystemPasteCommand("\\updownarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon58(), "Double up arrow", false, new SystemPasteCommand("\\Uparrow")));
    panel.add(buildButton(LatexIcons.icons.Icon75(), "Double down arrow", false, new SystemPasteCommand("\\Downarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon365(), "Double up/down arrow", false, new SystemPasteCommand("\\Updownarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon382(), "Left harpoon up", false, new SystemPasteCommand("\\leftharpoonup")));
    panel.add(buildButton(LatexIcons.icons.Icon399(), "Right harpoon up", false, new SystemPasteCommand("\\rightharpoonup")));
    panel.add(buildButton(LatexIcons.icons.Icon9(), "Left/Right harpoons", false, new SystemPasteCommand("\\rightleftharpoons")));
    panel.add(buildButton(LatexIcons.icons.Icon26(), "Left harpoon down", false, new SystemPasteCommand("\\leftharpoondown")));
    panel.add(buildButton(LatexIcons.icons.Icon77(), "Right harpoon down", false, new SystemPasteCommand("\\rightharpoondown")));
    panel.add(buildButton(LatexIcons.icons.Icon94(), "Maps to", false, new SystemPasteCommand("\\mapsto")));
    panel.add(buildButton(LatexIcons.icons.Icon111(), "Long maps to", false, new SystemPasteCommand("\\longmapsto")));
    panel.add(buildButton(LatexIcons.icons.Icon128(), "Hook left", false, new SystemPasteCommand("\\hookleftarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon145(), "Hook right", false, new SystemPasteCommand("\\hookrightarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon162(), "Northeast arrow", false, new SystemPasteCommand("\\nearrow")));
    panel.add(buildButton(LatexIcons.icons.Icon179(), "Southeast arrow", false, new SystemPasteCommand("\\searrow")));
    panel.add(buildButton(LatexIcons.icons.Icon196(), "Southwest arrow", false, new SystemPasteCommand("\\swarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon213(), "Northwest arrow", false, new SystemPasteCommand("\\nwarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon12(), "Long left arrow", false, new SystemPasteCommand("\\longleftarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon29(), "Long right arrow", false, new SystemPasteCommand("\\longrightarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon385(), "Long left/right arrow", false, new SystemPasteCommand("\\longleftrightarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon402(), "Long double left arrow", false, new SystemPasteCommand("\\Longleftarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon12(), "Long double right arrow", false, new SystemPasteCommand("\\Longrightarrow")));
    panel.add(buildButton(LatexIcons.icons.Icon29(), "Long double left/right arrow", false, new SystemPasteCommand("\\Longleftrightarrow")));
  }
}
