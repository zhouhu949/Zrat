package com.zrat.client.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


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
public class MainTabPanel extends javax.swing.JPanel implements ChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTabbedPane jTabbedPane;

	private ServerListPanel serverPanel;
	private PortForwardingListPanel portPanel;
	
	public MainTabPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.1};
			thisLayout.rowHeights = new int[] {7};
			thisLayout.columnWeights = new double[] {0.1};
			thisLayout.columnWidths = new int[] {7};
			this.setLayout(thisLayout);
			{
				jTabbedPane = new JTabbedPane();
				serverPanel = new ServerListPanel();
				this.add(jTabbedPane, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jTabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
				jTabbedPane.add("主机列表",serverPanel);
			}
			{
				portPanel = new PortForwardingListPanel();
				jTabbedPane.add("端口转发列表",portPanel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public ServerListPanel getServerPanel() {
		return serverPanel;
	}

	public void setServerPanel(ServerListPanel serverPanel) {
		this.serverPanel = serverPanel;
	}

	public PortForwardingListPanel getPortPanel() {
		return portPanel;
	}

	public void setPortPanel(PortForwardingListPanel portPanel) {
		this.portPanel = portPanel;
	}
	

}
