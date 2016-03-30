package org.tont.core.global.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import org.tont.proto.ServerReport;

public class GatewayPanel extends JPanel {

	private static final long serialVersionUID = 18828486362329417L;
	private GridBagLayout gridBagLayout;
	private HardwarePanel hardwarePanel;
	private BusinessPanel businessPanel;
	
	public GatewayPanel() {
		
		//面板布局
		gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 0};
        gridBagLayout.rowHeights = new int[] { 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 1.0E-4 };
        gridBagLayout.rowWeights = new double[] { 1.0E-4 };
        this.setLayout(gridBagLayout);
        
        
        //子面板
        {
        	hardwarePanel = new HardwarePanel();
        	hardwarePanel.setPreferredSize(new Dimension(430, 480));
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(10, 10, 10, 10);
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = 1;
            add(hardwarePanel, constraints);
        }
        {
        	businessPanel = new BusinessPanel();
        	businessPanel.setPreferredSize(new Dimension(430, 480));
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(10, 10, 10, 10);
            constraints.gridx = 1;
            constraints.gridy = 0;
            constraints.gridwidth = 1;
            add(businessPanel, constraints);
        }
	}
	
	public void notice(ServerReport.ServerReportEntity report) {
		hardwarePanel.notice(report);
		businessPanel.notice(report);
	}

}
