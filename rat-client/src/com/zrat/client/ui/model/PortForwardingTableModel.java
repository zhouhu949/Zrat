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
 * Create on:Sep 10, 2010
**/
package com.zrat.client.ui.model;

import java.text.SimpleDateFormat;

import javax.swing.JButton;

import com.zrat.client.ui.FileFrame;
import com.zrat.transfer.model.RemoteFile;

public class PortForwardingTableModel extends BaseTableModelImpl<PortForwardingModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PortForwardingTableModel(){
		setColumn(new String[] { "主机","目标地址","目标端口","本地端口","状态"});
	}
	public Class getColumnClass(int column) {
	        return (getValueAt(0, column).getClass());
	}

	
	public PortForwardingModel getSelectObject(int index){
		return getList().get(index);
	}

	/* (non-Javadoc)
	 * @see com.rat.client.ui.model.BaseTableModelImpl#getPropertyValueByCol(com.rat.client.model.BaseModel, int)
	 */
	@Override
	public Object getPropertyValueByCol(PortForwardingModel p, int col) {
		switch (col) {
		case 0:
			return p.getIp();
		case 1:
			return p.getTargetIp();
		case 2:
			return p.getTargetPort()+"";
		case 3:
			return p.getLocalPort()+"";
		case 4:
			return p.getStatus() == 0 ? "已停止":"转发中";
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.rat.client.ui.model.BaseTableModelImpl#setPropertyValueByCol(com.rat.client.model.BaseModel, java.lang.Object, int)
	 */
	@Override
	public PortForwardingModel setPropertyValueByCol(PortForwardingModel p, Object value, int col) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
