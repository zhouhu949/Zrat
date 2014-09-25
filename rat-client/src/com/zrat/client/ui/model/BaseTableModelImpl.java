/**  
 * Copyright (c) 2010-2012 Bolu Soft, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Bolu Soft,
 * Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Bolu.
 *
 * BOLU MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. ERRY SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 * Oringinal Author:Alex Zhou
 * Create on:Jun 27, 2010
 **/
package com.zrat.client.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.zrat.transfer.model.BaseModel;

public abstract class BaseTableModelImpl<T extends BaseModel> extends
		AbstractTableModel implements BaseTableModel<T> {
	private String[] column;

	private List<T> list = new ArrayList<T>();

	private List<T> changedModels = new ArrayList<T>();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseTableModelImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bolusoft.rule.ui.SMSTableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return column.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bolusoft.rule.ui.SMSTableModel#addRow(int,
	 * com.bolusoft.rule.model.MDPreQuery)
	 */
	public void addRow(int index, T p) {
		if (index < 0 || index > list.size() - 1) {
			list.add(p);
			fireTableRowsInserted(list.size(), list.size());
		} else {
			list.add(index + 1, p);
			fireTableRowsInserted(index, index);
		}
	}

	public void addRow(T p) {
		list.add(p);
		fireTableRowsInserted(list.size(), list.size());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bolusoft.rule.ui.SMSTableModel#deleteRow(int)
	 */
	public boolean deleteRow(int index) {
		if (index >= 0 && index < list.size()) {
			list.remove(index);
			fireTableRowsDeleted(index, index);
			return true;
		} else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bolusoft.rule.ui.SMSTableModel#saveRow(int,
	 * com.bolusoft.rule.model.MDPreQuery)
	 */
	public boolean saveRow(int index, T p) {
		if (index >= 0 && index < list.size()) {
			list.set(index, p);
			fireTableRowsUpdated(index, index);
			return true;
		} else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bolusoft.rule.ui.SMSTableModel#getRow(int)
	 */
	public T getRow(int index) {
		if (index >= 0 && index < list.size()) {
			return list.get(index);

		} else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bolusoft.rule.ui.SMSTableModel#getNewRow()
	 */
	public List<T> getNewRow() {
		List<T> list = new ArrayList<T>();
		List<T> listPreQuerys = getList();
		for (T p : listPreQuerys) {
			if (!"".equals(p.getId())) {
				list.add(p);
			}
		}

		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bolusoft.rule.ui.SMSTableModel#getRowCount()
	 */
	public int getRowCount() {
		return list.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bolusoft.rule.ui.SMSTableModel#getList()
	 */
	public List<T> getList() {
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bolusoft.rule.ui.SMSTableModel#setList(java.util.List)
	 */
	public void setList(List<T> list) {
		this.list = list;
		fireTableDataChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bolusoft.rule.ui.SMSTableModel#getColumnName(int)
	 */
	public String getColumnName(int i) {
		return column[i];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bolusoft.rule.ui.SMSTableModel#setColumn(java.lang.String[])
	 */
	public void setColumn(String[] column) {
		this.column = column;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bolusoft.rule.ui.SMSTableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int arg0, int arg1) {
		return getPropertyValueByCol(list.get(arg0), arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bolusoft.rule.ui.SMSTableModel#getPropertyValueByCol(com.bolusoft
	 * .rule.model.MDPreQuery, int)
	 */
	public abstract Object getPropertyValueByCol(T p, int col);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bolusoft.rule.ui.SMSTableModel#setPropertyValueByCol(com.bolusoft
	 * .rule.model.MDPreQuery, java.lang.String, int)
	 */
	public abstract T setPropertyValueByCol(T p, Object value, int col);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bolusoft.rule.ui.SMSTableModel#setValueAt(java.lang.Object, int,
	 * int)
	 */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		T p = list.get(rowIndex);
		setPropertyValueByCol(p, aValue, columnIndex);
		list.remove(rowIndex);
		list.add(rowIndex, p);
		getChangedModels().add(p);
		// fireTableDataChanged();
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public List<T> getChangedModels() {
		return changedModels;
	}

	public void setChangedModels(List<T> changedPreQuerys) {
		this.changedModels = changedPreQuerys;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bolusoft.rule.ui.SMSTableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int row, int column) {
		return 0==column;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bolusoft.rule.ui.SMSTableModel#getColumn()
	 */
	public String[] getColumn() {
		return column;
	}
	
	public T getById(String id){
		for(T t: list){
			if (t.getId().equals(id)){
				return t;
			}
		}
		return null;
	}
	
	public void removeObject(T obj){
		
		int i = list.indexOf(obj);
		list.remove(obj);
		fireTableRowsDeleted(i, i);
	}

}
