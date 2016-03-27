package org.tont.core.global.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LogChildPanel extends JPanel {

	private static final long serialVersionUID = -1014849886128696564L;
	private JTextArea logWin;

	public LogChildPanel(String title) {
		GridBagLayout bagLayout = new GridBagLayout();
		this.setLayout(bagLayout);
		
		logWin = new JTextArea();
		logWin.setBackground(new Color(0x303030));
		logWin.setForeground(new Color(0x00EE00));
		logWin.setLineWrap(true);
		logWin.setEditable(false);
		logWin.setText("日志窗口初始化完成\n");
		this.setBorder(BorderFactory.createTitledBorder(title));
		JScrollPane scroll = new JScrollPane(logWin);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
		add(scroll, constraints);
	}
}
