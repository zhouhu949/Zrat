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

import com.zrat.client.ui.Client;

public class ServerTableModel extends BaseTableModelImpl<ServerModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ServerTableModel(){
		setColumn(new String[] { "","IP地址","国家","位置地址","操作系统","CPU","内存"});
	}
	public Class getColumnClass(int column) {
	        return (getValueAt(0, column).getClass());
	}

	public ServerModel getSelectServer(int index){
		return getList().get(index);
	}

	/* (non-Javadoc)
	 * @see com.rat.client.ui.model.BaseTableModelImpl#getPropertyValueByCol(com.rat.client.model.BaseModel, int)
	 */
	@Override
	public Object getPropertyValueByCol(ServerModel p, int col) {
		switch (col) {
		case 0:
			return new Boolean(false);
		case 1:
			return p.getIp();
		case 2:
			return p.getCountry();
		case 3:
			return p.getArea();
		case 4:
			return p.getSystemName();
		case 5:
			return p.getProcessors();
		case 6:
			return p.getMemory();
//		case 7:
//			return p.get();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.rat.client.ui.model.BaseTableModelImpl#setPropertyValueByCol(com.rat.client.model.BaseModel, java.lang.Object, int)
	 */
	
	@Override
	public ServerModel setPropertyValueByCol(ServerModel p, Object value,
			int col) {
		switch (col) {
		case 0:
			p.setSelected((Boolean) value);
			if(p.isSelected())
			Client.currentServer = p; 
		}
		return null;
	}

}
