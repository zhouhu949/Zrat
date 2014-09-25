package com.zrat.client.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.zrat.client.nio.ClientNIO;
import com.zrat.client.ui.model.ServerModel;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class MainPanel extends javax.swing.JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel port;
	private JButton start;
	private JPasswordField passwordText;
	private JLabel password;
	private JTextField portText;
	private JPanel commonPanel;
	private MainTabPanel mainTabPanel;
	private static String COMMAND_START= "start";
	private static String COMMAND_STOP= "stop";
	
	public LinkedHashMap<Integer,ServerModel> servers = new LinkedHashMap<Integer,ServerModel> ();
	
	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	
	public MainPanel() {
		super();
		initGUI();
	}
	
	public MainTabPanel getMainTabPanel() {
		return mainTabPanel;
	}

	public void setMainTabPanel(MainTabPanel mainTabPanel) {
	}

	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setPreferredSize(new java.awt.Dimension(828, 557));
			this.setLayout(thisLayout);
			{
				commonPanel = new JPanel();
				GridBagLayout commonPanelLayout = new GridBagLayout();
				this.add(commonPanel, BorderLayout.SOUTH);
				commonPanelLayout.rowWeights = new double[] {0.1};
				commonPanelLayout.rowHeights = new int[] {7};
				commonPanelLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.1};
				commonPanelLayout.columnWidths = new int[] {99, 135, 116, 168, 20};
				commonPanel.setLayout(commonPanelLayout);
				{
					port = new JLabel();
					commonPanel.add(port, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					port.setText("\u7aef\u53e3:");
				}
				{
					portText = new JTextField();
					commonPanel.add(portText, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
					portText.setSize(20, 22);
					portText.setPreferredSize(new java.awt.Dimension(120, 22));
				}
				{
					password = new JLabel();
					commonPanel.add(password, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					password.setText("\u8fde\u63a5\u5bc6\u7801:");
				}
				{
					passwordText = new JPasswordField();
					commonPanel.add(passwordText, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
					passwordText.setPreferredSize(new java.awt.Dimension(120, 22));
				}
				{
					start = new JButton();
					commonPanel.add(start, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					start.setText("\u542f\u52a8");
					start.setActionCommand(COMMAND_START);
					start.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (COMMAND_START.equals(evt.getActionCommand())){
								
								Client.password = new String(passwordText.getPassword());
								int port = 0;
								try{
									port = Integer.parseInt(portText.getText()+"");
								}catch (NumberFormatException e) {
									JOptionPane.showMessageDialog(portText,"端口输入错误");
									portText.requestFocus();
								}
								ClientNIO.startListening(port);
								start.setActionCommand(COMMAND_STOP);
								start.setText("停止");
							}else{
								ClientNIO.stop();
								start.setActionCommand(COMMAND_START);
								start.setText("启动");
							}
						}
					});
				}
			}
			{
				mainTabPanel = new MainTabPanel();
				this.add(mainTabPanel, BorderLayout.CENTER);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public JPanel getCommonPanel() {
		return commonPanel;
	}

	public void setCommonPanel(JPanel commonPanel) {
		this.commonPanel = commonPanel;
	}
	
}
