package com.zrat.client.ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


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
public class ButtonPanel extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton createFile;
	private JButton createDic;
	private JButton uploadFile;
	private JButton downloadFile;
	private JButton deleteFile;
	
	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new ButtonPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public ButtonPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			GridLayout thisLayout = new GridLayout(1, 1);
			thisLayout.setColumns(1);
			thisLayout.setHgap(5);
			thisLayout.setVgap(5);
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(378, 24));
			{
				createFile = new JButton();
				this.add(createFile);
				createFile.setText("\u65b0\u5efa\u6587\u4ef6");
				createFile.setPreferredSize(new java.awt.Dimension(64, 26));
			}
			{
				createDic = new JButton();
				this.add(createDic);
				createDic.setText("\u65b0\u5efa\u76ee\u5f55");
			}
			{
				deleteFile = new JButton();
				this.add(deleteFile);
				deleteFile.setText("\u5220\u9664\u6587\u4ef6");
			}
			{
				uploadFile = new JButton();
				this.add(uploadFile);
				uploadFile.setText("\u4e0a\u4f20\u6587\u4ef6");
			}
			{
				downloadFile = new JButton();
				this.add(downloadFile);
				downloadFile.setText("\u4e0b\u8f7d\u6587\u4ef6");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
