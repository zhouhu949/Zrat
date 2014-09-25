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
package com.zrat.transfer.model;

import java.util.Date;

public class RemoteFile extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String path;
	long size;
	Date created;
	long updated;
	boolean isFile;
	RemoteFile parent;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public long getUpdated() {
		return updated;
	}
	public void setUpdated(long updated) {
		this.updated = updated;
	}
	public boolean isFile() {
		return isFile;
	}
	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}
	/* (non-Javadoc)
	 * @see com.rat.client.model.BaseModel#getId()
	 */
	public String getId() {
		// TODO Auto-generated method stub
		return path;
	}
	/* (non-Javadoc)
	 * @see com.rat.client.model.BaseModel#setId(java.lang.Long)
	 */
	public void setId(String id) {
		path = id;
		
	}
	public RemoteFile getParent() {
		return parent;
	}
	public void setParent(RemoteFile parent) {
		this.parent = parent;
	}
	
}
