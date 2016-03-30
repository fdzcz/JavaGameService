package org.tont.core.global.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BusinessInfoChildPanel extends JPanel {

	private static final long serialVersionUID = -1913183277735751321L;
	
	private final JLabel handleTotalNumLabel = new JLabel("处理消息总数：");
	private final JLabel handleSpeedLabel = new JLabel("当前处理速度：");
	private final JLabel loginTotalNumLabel = new JLabel("登录请求数目：");
	private final JLabel memoryTotalLabel = new JLabel("全部内存：");
	private final JLabel osLabel = new JLabel("操作系统：");
	private final JLabel ipLabel = new JLabel("IP地址：");
	private final JLabel versionLabel = new JLabel("JRE版本：");
	private final JLabel startTimeLabel = new JLabel("启动时间：");
	private final JLabel updateTimeLabel = new JLabel("更新时间：");
	
	public JLabel handleTotalNum;
	public JLabel handleSpeed;
	public JLabel loginNum;
	public JLabel memoryTotal;
	public JLabel os;
	public JLabel ip;
	public JLabel version;
	public JLabel startTime;
	public JLabel updateTime;

	public BusinessInfoChildPanel(String title) {
		this.setBorder(BorderFactory.createTitledBorder(title));
		
		//面板布局
		GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0E-4 };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };
        this.setLayout(gridBagLayout);
        
        //控件布局
        //第一列
        {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(5, 30, 5, 5);
            constraints.gridx = 0;
            constraints.gridy = 0;
            add(handleTotalNumLabel, constraints);
        }
        {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(5, 30, 5, 5);
            constraints.gridx = 0;
            constraints.gridy = 1;
            add(handleSpeedLabel, constraints);
        }
        {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(5, 30, 5, 5);
            constraints.gridx = 0;
            constraints.gridy = 2;
            add(loginTotalNumLabel, constraints);
        }
        {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(5, 30, 5, 5);
            constraints.gridx = 0;
            constraints.gridy = 3;
            add(memoryTotalLabel, constraints);
        }
        
        {
        	handleTotalNum = new JLabel("136564987");
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(5, 5, 5, 5);
            constraints.gridx = 1;
            constraints.gridy = 0;
            add(handleTotalNum, constraints);
        }
        {
        	handleSpeed = new JLabel("4450/s");
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(5, 5, 5, 5);
            constraints.gridx = 1;
            constraints.gridy = 1;
            add(handleSpeed, constraints);
        }
        {
        	loginNum = new JLabel("1320");
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(5, 5, 5, 5);
            constraints.gridx = 1;
            constraints.gridy = 2;
            add(loginNum, constraints);
        }
        {
        	memoryTotal = new JLabel("2MB");
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(5, 5, 5, 5);
            constraints.gridx = 1;
            constraints.gridy = 3;
            add(memoryTotal, constraints);
        }
        
        //第二列
        {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(5, 60, 5, 5);
            constraints.gridx = 2;
            constraints.gridy = 0;
            add(osLabel, constraints);
        }
        {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(5, 60, 5, 5);
            constraints.gridx = 2;
            constraints.gridy = 1;
            add(ipLabel, constraints);
        }
        {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(5, 60, 5, 5);
            constraints.gridx = 2;
            constraints.gridy = 2;
            add(versionLabel, constraints);
        }
        {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(5, 60, 5, 5);
            constraints.gridx = 2;
            constraints.gridy = 3;
            add(startTimeLabel, constraints);
        }
        {
        	os = new JLabel("Windows 7");
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(5, 5, 5, 5);
            constraints.gridx = 3;
            constraints.gridy = 0;
            add(os, constraints);
        }
        {
        	ip = new JLabel("172.21.60.197");
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(5, 5, 5, 5);
            constraints.gridx = 3;
            constraints.gridy = 1;
            add(ip, constraints);
        }
        {
        	version = new JLabel("1.7.0_80");
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(5, 5, 5, 5);
            constraints.gridx = 3;
            constraints.gridy = 2;
            add(version, constraints);
        }
        {
        	startTime = new JLabel("2:28:29");
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(5, 5, 5, 5);
            constraints.gridx = 3;
            constraints.gridy = 3;
            add(startTime, constraints);
        }
        
        //时间戳
        {
        	GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(5, 60, 5, 5);
            constraints.gridx = 2;
            constraints.gridy = 6;
            add(updateTimeLabel, constraints);
        }
        {
        	updateTime = new JLabel("2:28:29");
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = new Insets(5, 5, 5, 5);
            constraints.gridx = 3;
            constraints.gridy = 6;
            add(updateTime, constraints);
        }
	}
}
