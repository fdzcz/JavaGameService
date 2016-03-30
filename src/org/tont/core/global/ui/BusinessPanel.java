package org.tont.core.global.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import org.tont.proto.ServerReport;

public class BusinessPanel extends JPanel{
	
	private static final long serialVersionUID = 2889439458303943304L;
	private BusinessInfoChildPanel info;
	private LogChildPanel logger;
	
	//private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public BusinessPanel() {
		
		//面板布局
		GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0 };
        gridBagLayout.rowHeights = new int[] { 0, 0 };
        gridBagLayout.columnWeights = new double[] { 1.0E-4 };
        gridBagLayout.rowWeights = new double[] { 0.0, 1.0E-4 };
        this.setLayout(gridBagLayout);
        
        //子面板
        {
        	info = new BusinessInfoChildPanel("业务信息");
        	info.setPreferredSize(new Dimension(410, 220));
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(10, 10, 10, 10);
            constraints.gridx = 0;
            constraints.gridy = 0;
            add(info, constraints);
        }
        {
        	logger = new LogChildPanel("业务日志");
        	logger.setPreferredSize(new Dimension(410, 220));
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(10, 10, 10, 10);
            constraints.gridx = 0;
            constraints.gridy = 1;
            add(logger, constraints);
        }
	}
	
	public void notice(ServerReport.ServerReportEntity report) {
		info.handleTotalNum.setText(report.getHandleTotalNum()+"");
		info.handleSpeed.setText(report.getHandleSpeedNow()+"");
		info.loginNum.setText(report.getLoginNum()+"");
		info.handleTotalNum.setText(report.getHandleTotalNum()+"");
	}
}
