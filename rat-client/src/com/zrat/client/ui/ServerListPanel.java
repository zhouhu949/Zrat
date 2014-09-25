package com.zrat.client.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.zrat.client.ui.model.ChceckBoxTableCellRender;
import com.zrat.client.ui.model.ServerTableModel;
import com.zrat.client.ui.model.TableCheckboxManager;

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
public class ServerListPanel extends javax.swing.JPanel implements
		MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane;
	private JTable serverTable;
	private ServerTableModel serverTableModel;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */

	public ServerListPanel() {
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
					serverTableModel = new ServerTableModel();
					 
					serverTable = new JTable();
					serverTable.setDefaultRenderer(boolean.class, new ChceckBoxTableCellRender());
					
					serverTable.setRowSelectionAllowed(true);
					serverTable.setColumnSelectionAllowed(false);
					serverTable.setShowVerticalLines(true);// 设置是否显示单元格间的分割线
					serverTable.setShowHorizontalLines(true);
					jScrollPane.setViewportView(serverTable);
					serverTable.setModel(serverTableModel);
					serverTable.getColumnModel().getColumn(0).setMaxWidth(30);
					serverTable.getColumnModel().getColumn(0).setMinWidth(30);
					serverTable.getColumnModel().getColumn(0).setPreferredWidth(30);
					serverTable.getColumnModel().getColumn(0).setResizable(false);  
					serverTable
							.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					serverTable
							.setPreferredScrollableViewportSize(new Dimension(
									400, 310));
					serverTable.addMouseListener(this);
//					TableCheckboxManager tablesCheckBoxMgr = new TableCheckboxManager(serverTable);
//					tablesCheckBoxMgr.setHeaderShowCheckbox(0); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {// 单击鼠标左键
			if (e.getComponent() == serverTable)
				if (serverTable.getSelectedRow() >= 0) {
					serverTableModel.setPropertyValueByCol(serverTableModel.getSelectServer(serverTable
							.getSelectedRow()), !serverTableModel.getSelectServer(serverTable
									.getSelectedRow()).isSelected(), 0);
				}
		}

	}

	
	public ServerTableModel getServerTableModel() {
		return serverTableModel;
	}

	public void setServerTableModel(ServerTableModel serverTableModel) {
		this.serverTableModel = serverTableModel;
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

}
