package org.tont.core.global.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;

import org.tont.proto.ServerReport;

public class HardwarePanel extends JPanel {

	private static final long serialVersionUID = 2329271184658064412L;
	private HardwareInfoChildPanel info;
	private LogChildPanel logger;
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public HardwarePanel() {
		
		//面板布局
		GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0 };
        gridBagLayout.rowHeights = new int[] { 0, 0 };
        gridBagLayout.columnWeights = new double[] { 1.0E-4 };
        gridBagLayout.rowWeights = new double[] { 0.0, 1.0E-4 };
        this.setLayout(gridBagLayout);
        
        //子面板
        {
        	info = new HardwareInfoChildPanel("硬件信息");
        	info.setPreferredSize(new Dimension(410, 220));
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(10, 10, 10, 10);
            constraints.gridx = 0;
            constraints.gridy = 0;
            add(info, constraints);
        }
        {
        	logger = new LogChildPanel("硬件日志");
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
		info.cpuCount.setText(report.getCpuCount()+"");
		info.cpuRatio.setText(report.getCpuRatio()+"");
		info.memoryFree.setText(report.getMemoryFree()+"");
		info.memoryTotal.setText(report.getMemoryTotal()+"");
		info.os.setText(report.getOsName());
		info.ip.setText(report.getIpAddr());
		info.version.setText(report.getJavaVersion());
		info.startTime.setText(format.format(new Date(report.getStartTime())));
		info.updateTime.setText(format.format(new Date(report.getUpdateTime())));
	}
}
