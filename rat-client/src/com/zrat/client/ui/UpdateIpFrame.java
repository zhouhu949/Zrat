package com.zrat.client.ui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import com.zrat.client.util.IpUtil;


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
public class UpdateIpFrame extends javax.swing.JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel ipLabel;
	private JComboBox DNSComboBox;
	private JButton getIpButton;
	private JTextField domainTextField;
	private JLabel domainLabel;
	private JButton UpdateButton;
	private JLabel DNSLabel;
	private JPasswordField jPasswordField;
	private JLabel passwordLabel;
	private JTextField userNameTextField;
	private JLabel userNameLabel;
	private JTextField ipTextField;

	public JTextField getIpTextField() {
		return ipTextField;
	}

	public void setIpTextField(JTextField ipTextField) {
		this.ipTextField = ipTextField;
	}

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UpdateIpFrame inst = new UpdateIpFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public UpdateIpFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1};
			thisLayout.rowHeights = new int[] {24, 17, 13, 9, 15, 21, 7};
			thisLayout.columnWeights = new double[] {0.0, 0.1};
			thisLayout.columnWidths = new int[] {104, 7};
			getContentPane().setLayout(thisLayout);
			{
				ipLabel = new JLabel();
				getContentPane().add(ipLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				ipLabel.setText("IP地址");
			}
			{
				ipTextField = new JTextField();
				getContentPane().add(ipTextField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				ipTextField.setText("0.0.0.0");
			}
			{
				userNameLabel = new JLabel();
				getContentPane().add(userNameLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				userNameLabel.setText("用户名");
			}
			{
				userNameTextField = new JTextField();
				getContentPane().add(userNameTextField, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
			}
			{
				passwordLabel = new JLabel();
				getContentPane().add(passwordLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				passwordLabel.setText("密码");
			}
			{
				jPasswordField = new JPasswordField();
				getContentPane().add(jPasswordField, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
			}
			{
				DNSLabel = new JLabel();
				getContentPane().add(DNSLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				DNSLabel.setText("动态DNS");
			}
			{
				ComboBoxModel DNSComboBoxModel = 
					new DefaultComboBoxModel(
							new String[] { "3322.org", "oray.com.cn" });
				DNSComboBox = new JComboBox();
				getContentPane().add(DNSComboBox, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				DNSComboBox.setModel(DNSComboBoxModel);
			}
			{
				getIpButton = new JButton();
				getContentPane().add(getIpButton, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				getIpButton.setText("获取外网IP地址");
				getIpButton.setActionCommand("getIp");
				getIpButton.addActionListener(this);
			}
			{
				UpdateButton = new JButton();
				getContentPane().add(UpdateButton, new GridBagConstraints(0, 6, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				UpdateButton.setText("更新域名");
				UpdateButton.setActionCommand("updateDomain");
				UpdateButton.addActionListener(this);
			}
			{
				domainLabel = new JLabel();
				getContentPane().add(domainLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				domainLabel.setText("域名");
			}
			{
				domainTextField = new JTextField();
				getContentPane().add(domainTextField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
			}
			pack();
			this.setSize(307, 277);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent btn) {
		if (btn.getActionCommand().equals("getIp")){
			ipTextField.setText(IpUtil.getIp());
		}else if ("updateDomain".equals(btn.getActionCommand())){
			IpUtil.updateDomain(userNameTextField.getText(), new String(jPasswordField.getPassword()), domainTextField.getText(),ipTextField.getText());
		}
	
		
	}

}
