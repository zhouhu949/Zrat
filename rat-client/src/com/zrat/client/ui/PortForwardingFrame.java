package com.zrat.client.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.zrat.client.controller.PortForwardingService;
import com.zrat.client.controller.PortForwardingServiceImpl;
import com.zrat.client.ui.model.PortForwardingModel;
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
public class PortForwardingFrame extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel targetLabel;
	private JButton cancleButton;
	private JButton okButton;
	private JTextField localPortText;
	private JLabel localPortLabel;
	private JTextField targetPortText;
	private JLabel targetPortLabel;
	private JTextField targetIpText;
	private PortForwardingModel model;
	private ServerModel server;
	private PortForwardingService portService;
	
	public PortForwardingFrame(ServerModel server) {
		super(server.getIp());
		model = new PortForwardingModel();
		this.server = server;
		initGUI();
	}
	
	private void initGUI() {
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			this.setPreferredSize(new java.awt.Dimension(420, 132));
			thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1};
			thisLayout.rowHeights = new int[] {7, 7, 7};
			thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
			thisLayout.columnWidths = new int[] {74, 94, 80, 7};
			this.setLayout(thisLayout);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setSize(420, 132);
			{
				targetLabel = new JLabel();
				this.add(targetLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				targetLabel.setText("\u76ee\u6807\u5730\u5740:");
			}
			{
				targetIpText = new JTextField();
				this.add(targetIpText, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				targetIpText.setPreferredSize(new java.awt.Dimension(120, 22));
			}
			{
				targetPortLabel = new JLabel();
				this.add(targetPortLabel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				targetPortLabel.setText("\u76ee\u6807\u7aef\u53e3:");
			}
			{
				targetPortText = new JTextField();
				this.add(targetPortText, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				targetPortText.setPreferredSize(new java.awt.Dimension(120, 22));
			}
			{
				localPortLabel = new JLabel();
				this.add(localPortLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				localPortLabel.setText("\u672c\u5730\u7aef\u53e3:");
			}
			{
				localPortText = new JTextField();
				this.add(localPortText, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				localPortText.setPreferredSize(new java.awt.Dimension(120, 22));
			}
			{
				okButton = new JButton();
				this.add(okButton, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				okButton.setText("\u786e\u5b9a");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						model.setIp(server.getIp());
						model.setTargetIp(targetIpText.getText());
						model.setTargetPort(Integer.parseInt(targetPortText.getText().trim()));
						model.setLocalPort(Integer.parseInt(localPortText.getText().trim()));
						model.setLocalChannel(server.getChannel());
						portService = new PortForwardingServiceImpl(server.getChannel());
						portService.forward(model);
						dispose();
					}
				});
			}
			{
				cancleButton = new JButton();
				this.add(cancleButton, new GridBagConstraints(2, 2, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				cancleButton.setText("\u53d6\u6d88");
				cancleButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						dispose();
					}
				});
			}
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
