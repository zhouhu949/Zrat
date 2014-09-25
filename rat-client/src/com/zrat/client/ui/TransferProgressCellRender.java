package com.zrat.client.ui;

import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JTable;


public class TransferProgressCellRender extends javax.swing.JProgressBar implements
		javax.swing.table.TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TransferProgressCellRender(int min, int max){
		
	}

	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object arg1,
			boolean arg2, boolean arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		setValue(Integer.valueOf(arg1+""));
		return this;
	}

}
