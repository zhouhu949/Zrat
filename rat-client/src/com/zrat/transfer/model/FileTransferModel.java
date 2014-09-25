package com.zrat.transfer.model;

public class FileTransferModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String file;
	private String type;
	private String status;
	private String name;
	private long progress;
	private String speed;
	private long size;
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return file;
	}

	@Override
	public void setId(String id) {
		file = id;
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getProgress() {
		return progress;
	}

	public void setProgress(long progress) {
		this.progress = progress;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
