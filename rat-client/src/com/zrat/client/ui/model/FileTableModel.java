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
import java.util.ArrayList;
import java.util.List;

import com.zrat.client.ui.FileFrame;
import com.zrat.client.util.Utils;
import com.zrat.transfer.model.RemoteFile;

public class FileTableModel extends BaseTableModelImpl<RemoteFile> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RemoteFile parentFile = new RemoteFile();
	private String currentPath;
	
	public FileTableModel(){
		setColumn(new String[] { "","文件","大小","最后修改日期"});
	}
	public Class getColumnClass(int column) {
	        return (getValueAt(0, column).getClass());
	}

	public RemoteFile getParentFile() {
		return parentFile;
	}

	public void setParentFile(RemoteFile parentFile) {
		this.parentFile = parentFile;
	}
	
	public RemoteFile getSelectFile(int index){
		return getList().get(index);
	}
	
	public List<RemoteFile> getSelectFiles(int[] index){
		List<RemoteFile> result = new ArrayList<RemoteFile>();
		for(int i : index){
			result.add(getList().get(i));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.rat.client.ui.model.BaseTableModelImpl#getPropertyValueByCol(com.rat.client.model.BaseModel, int)
	 */
	@Override
	public Object getPropertyValueByCol(RemoteFile p, int col) {
		switch (col) {
		case 0:
			return p.isFile() ? Utils.createImageIcon("icons/file_16.png") : Utils.createImageIcon("icons/floder_16.png");
		case 1:
			return p.getName();
		case 2:
			return p.getSize();
		case 3:
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			return fm.format(p.getUpdated());
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.rat.client.ui.model.BaseTableModelImpl#setPropertyValueByCol(com.rat.client.model.BaseModel, java.lang.Object, int)
	 */
	@Override
	public RemoteFile setPropertyValueByCol(RemoteFile p, Object value, int col) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getCurrentPath() {
		return currentPath;
	}

	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
	}

}
