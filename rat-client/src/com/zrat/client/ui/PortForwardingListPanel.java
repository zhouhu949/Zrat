package com.zrat.client.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.zrat.client.controller.PortForwardingServiceImpl;
import com.zrat.client.ui.model.PortForwardingModel;
import com.zrat.client.ui.model.PortForwardingTableModel;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class PortForwardingListPanel extends javax.swing.JPanel implements
		MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane;
	private JTable portTable;
	private PortForwardingTableModel portTableModel;
	private PortForwardingModel port;
	private JTextField localPortText;
	private JLabel localPortLabel;
	private JTextField targetPortText;
	private JLabel targetPortLabel;
	private JTextField targetIpText;
	private JLabel targetIpLabel;
	private JButton stopButton;
	private JButton startButton;
	private JPanel buttonPanel;

	private PortForwardingModel currentPort;
	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */

	public PortForwardingListPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(594, 407));
			{
				jScrollPane = new JScrollPane();
				this.add(jScrollPane, BorderLayout.CENTER);
				{
					portTableModel = new PortForwardingTableModel();
					portTable = new JTable();
					portTable.setRowSelectionAllowed(true);
					portTable.setColumnSelectionAllowed(false);
					portTable.setShowVerticalLines(true);// 设置是否显示单元格间的分割线
					portTable.setShowHorizontalLines(true);
					jScrollPane.setViewportView(portTable);
					portTable.setModel(portTableModel);
					portTable
							.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					portTable.setPreferredScrollableViewportSize(new Dimension(
							400, 310));
					portTable.addMouseListener(this);
				}
			}
			{
				buttonPanel = new JPanel();
				GridBagLayout buttonPanelLayout = new GridBagLayout();
				this.add(getButtonPanel(), BorderLayout.SOUTH);
				buttonPanelLayout.rowWeights = new double[] {0.1, 0.1};
				buttonPanelLayout.rowHeights = new int[] {20, 20};
				buttonPanelLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
				buttonPanelLayout.columnWidths = new int[] {132, 152, 134, 7};
				buttonPanel.setLayout(buttonPanelLayout);
				{
					startButton = new JButton();
					buttonPanel.add(startButton, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					startButton.setText("\u5f00\u59cb\u8f6c\u53d1");
					startButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (isSelected()){
								PortForwardingServiceImpl portService = new PortForwardingServiceImpl(currentPort.getLocalChannel());
								
								portService.forward(currentPort);
							}
						}
					});
				}
				{
					stopButton = new JButton();
					buttonPanel.add(stopButton, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
					stopButton.setText("\u505c\u6b62\u8f6c\u53d1");
					stopButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (isSelected()){
								PortForwardingServiceImpl portService = new PortForwardingServiceImpl(currentPort.getLocalChannel());
								
								portService.stopForward();
							}
						}
					});
				}
				{
					targetIpLabel = new JLabel();
					buttonPanel.add(targetIpLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					targetIpLabel.setText("\u76ee\u6807\u4e3b\u673a");
				}
				{
					targetIpText = new JTextField();
					buttonPanel.add(targetIpText, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
					targetIpText.setPreferredSize(new java.awt.Dimension(150, 22));
				}
				{
					targetPortLabel = new JLabel();
					buttonPanel.add(targetPortLabel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					targetPortLabel.setText("\u76ee\u6807\u7aef\u53e3");
				}
				{
					targetPortText = new JTextField();
					buttonPanel.add(targetPortText, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
					targetPortText.setPreferredSize(new java.awt.Dimension(150, 22));
					targetPortText.setSize(150, 22);
				}
				{
					localPortLabel = new JLabel();
					buttonPanel.add(localPortLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					localPortLabel.setText("\u672c\u5730\u7aef\u53e3");
				}
				{
					localPortText = new JTextField();
					buttonPanel.add(localPortText, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
					localPortText.setPreferredSize(new java.awt.Dimension(150, 22));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {// 单击鼠标左键
			if (e.getComponent() == portTable)
				if (portTable.getSelectedRow() >= 0) {
					currentPort =  portTableModel.getSelectObject(portTable.getSelectedRow());
					targetIpText.setEditable(currentPort.getStatus() ==0);
					targetPortText.setEditable(currentPort.getStatus() ==0);
					targetPortText.setEditable(currentPort.getStatus() ==0);
						targetIpText.setText(currentPort.getTargetIp());
						targetPortText.setText(currentPort.getTargetPort()+"");
						localPortText.setText(currentPort.getLocalPort()+"");
						stopButton.setEnabled(currentPort.getStatus() ==1);
						startButton.setEnabled(currentPort.getStatus() ==0);
						
							
				}
		}

	}
	
	
	public boolean isSelected(){
		return currentPort!=null;
	}
	
	public PortForwardingTableModel getPortTableModel() {
		return portTableModel;
	}

	public void setPortTableModel(PortForwardingTableModel portTableModel) {
		this.portTableModel = portTableModel;
	}

	public PortForwardingModel getPort() {
		return port;
	}

	public void setPort(PortForwardingModel port) {
		this.port = port;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public JPanel getButtonPanel() {
		return buttonPanel;
	}

}
