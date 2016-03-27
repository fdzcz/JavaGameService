package org.tont.core.global.ui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BusinessInfoChildPanel extends JPanel {

	private static final long serialVersionUID = -1913183277735751321L;

	public BusinessInfoChildPanel(String title) {
		this.setBorder(BorderFactory.createTitledBorder(title));
	}
}
