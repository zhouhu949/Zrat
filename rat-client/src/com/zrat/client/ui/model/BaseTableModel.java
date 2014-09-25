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
 * Create on:Jun 29, 2010
 **/
package com.zrat.client.ui.model;

import java.util.List;

import com.zrat.transfer.model.BaseModel;


public interface BaseTableModel<T extends BaseModel> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public abstract int getColumnCount();

	public abstract void addRow(int index, T p);

	public abstract boolean deleteRow(int index);

	public abstract boolean saveRow(int index, T p);

	public abstract T getRow(int index);

	public abstract List<T> getNewRow();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public abstract int getRowCount();

	public abstract List<T> getList();

	public abstract void setList(List<T> list);

	public abstract String getColumnName(int i);

	public abstract void setColumn(String[] column);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public abstract Object getValueAt(int arg0, int arg1);

	public abstract Object getPropertyValueByCol(T p, int col);

	public abstract T setPropertyValueByCol(T p, Object value,
			int col);

	public abstract void setValueAt(Object aValue, int rowIndex, int columnIndex);

	public abstract boolean isCellEditable(int row, int column);

	public abstract String[] getColumn();
	
	public List<T> getChangedModels();

	public void setChangedModels(List<T> changedModels);
	
	public void removeObject(T obj);
	
//	public void refreshList();

}