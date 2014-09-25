package com.zrat.client.ui;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.zrat.client.controller.CommandService;

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
public class CommandFrame extends javax.swing.JFrame  implements KeyListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea jTextArea;
	private JScrollPane jScrollPane;
	private JTextField jTextField;
	private CommandService service;
	
	public CommandFrame(CommandService service,String title) {
		super();
		this.setTitle(title);
		this.service = service;
		initGUI();
	}
	
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(599, 410));
			{
				jScrollPane = new JScrollPane();
				this.add(jScrollPane, BorderLayout.CENTER);
				{
					jTextArea = new JTextArea();
					jScrollPane.setViewportView(jTextArea);
					jTextArea.setFocusTraversalKeysEnabled(false);
					jTextArea.setEditable(false);
					jTextArea.setBackground(new java.awt.Color(0,0,0));
					jTextArea.setForeground(new java.awt.Color(0,255,0));
				}
			}
			{
				jTextField = new JTextField();
				this.add(jTextField, BorderLayout.SOUTH);
				jTextField.addKeyListener(this);
			}
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			service.runCommand(jTextField.getText());
			jTextArea.append("\n");
			jTextArea.append(jTextField.getText());
			jTextField.setText(null);
		}
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public CommandService getService() {
		return service;
	}

	public void setService(CommandService service) {
		this.service = service;
	}
	
	public void closePanel(){
		//service.closeCommand();
	}

	public JTextArea getjTextArea() {
		return jTextArea;
	}

	public void setjTextArea(JTextArea jTextArea) {
		this.jTextArea = jTextArea;
	}

}
