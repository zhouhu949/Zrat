package com.zrat.transfer.model;

import java.io.Serializable;

public class TransferDataStruct implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String action;
	private Object object;
	
	public TransferDataStruct(String action){
		this.action = action;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
	
}
