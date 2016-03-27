package org.tont.core.global.ui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class HardwareInfoChildPanel extends JPanel {

	private static final long serialVersionUID = -7053840546182644124L;
	
	public HardwareInfoChildPanel(String title) {
		this.setBorder(BorderFactory.createTitledBorder(title));
	}
}
