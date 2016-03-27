package org.tont.core.global.ui;

import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class UIStyleMgr {
	
	public static Font normalFont = new Font("Dialog", Font.PLAIN, 11);
	public static Font smallFont = new Font("Dialog", Font.PLAIN, 10);

	public static void initUIStyle() {
        UIManager.put("ToolTip.font", normalFont);
        UIManager.put("Table.font", normalFont);
        UIManager.put("TableHeader.font", normalFont);
        UIManager.put("TextField.font", smallFont);
        UIManager.put("ComboBox.font", normalFont);
        UIManager.put("PasswordField.font", normalFont);
        UIManager.put("TextArea.font", normalFont);
        UIManager.put("TextPane.font", normalFont);
        UIManager.put("EditorPane.font", normalFont);
        UIManager.put("FormattedTextField.font", normalFont);
        UIManager.put("Button.font", normalFont);
        UIManager.put("CheckBox.font", normalFont);
        UIManager.put("RadioButton.font", normalFont);
        UIManager.put("ToggleButton.font", normalFont);
        UIManager.put("ProgressBar.font", normalFont);
        UIManager.put("DesktopIcon.font", normalFont);
        UIManager.put("TitledBorder.font", normalFont);
        UIManager.put("Label.font", normalFont);
        UIManager.put("List.font", normalFont);
        UIManager.put("TabbedPane.font", normalFont);
        UIManager.put("MenuBar.font", normalFont);
        UIManager.put("Menu.font", normalFont);
        UIManager.put("MenuItem.font", normalFont);
        UIManager.put("PopupMenu.font", normalFont);
        UIManager.put("CheckBoxMenuItem.font", normalFont);
        UIManager.put("RadioButtonMenuItem.font", normalFont);
        UIManager.put("Spinner.font", normalFont);
        UIManager.put("Tree.font", normalFont);
        UIManager.put("ToolBar.font", normalFont);
        UIManager.put("OptionPane.messageFont", normalFont);
        UIManager.put("OptionPane.buttonFont", normalFont);
        
        //使用Windows风格的样式
        String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
}
