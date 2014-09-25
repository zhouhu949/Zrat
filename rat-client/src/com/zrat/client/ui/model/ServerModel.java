package com.zrat.client.ui.model;

import java.util.Map;

import org.jboss.netty.channel.Channel;

import com.zrat.client.ui.CommandFrame;
import com.zrat.client.ui.FileFrame;
import com.zrat.client.ui.ScreenFrame;
import com.zrat.transfer.model.BaseModel;

public class ServerModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ip;
	private Channel channel;
	private ScreenFrame screenFrame;
	private FileFrame fileFrame;
	private CommandFrame commandFrame;
	private String id;
	private int processors; 
	private long memory;
	private String systemName;
	private boolean hasCamera;
	private String buildVer;
	private String area;
	private String country;
	private String password;
	private String hostName;
	private boolean isFileTransfer;
	private Map<String,Long> downloadFiles;
	private String currentFile;
	private boolean isSelected;
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public ScreenFrame getScreenFrame() {
		return screenFrame;
	}

	public ScreenFrame setScreenFrame(ScreenFrame screenFrame) {
		this.screenFrame = screenFrame;
		return this.screenFrame;
	}

	public String toString() {
		return ip;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public int getProcessors() {
		return processors;
	}

	public void setProcessors(int processors) {
		this.processors = processors;
	}

	public long getMemory() {
		return memory;
	}

	public void setMemory(long memory) {
		this.memory = memory;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public boolean isHasCamera() {
		return hasCamera;
	}

	public void setHasCamera(boolean hasCamera) {
		this.hasCamera = hasCamera;
	}

	public String getBuildVer() {
		return buildVer;
	}

	public void setBuildVer(String buildVer) {
		this.buildVer = buildVer;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public FileFrame getFileFrame() {
		return fileFrame;
	}

	public void setFileFrame(FileFrame fileFrame) {
		this.fileFrame = fileFrame;
	}

	public CommandFrame getCommandFrame() {
		return commandFrame;
	}

	public void setCommandFrame(CommandFrame commandFrame) {
		this.commandFrame = commandFrame;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public boolean isFileTransfer() {
		return isFileTransfer;
	}

	public void setFileTransfer(boolean isFileTransfer) {
		this.isFileTransfer = isFileTransfer;
	}

	public Map<String,Long> getDownloadFiles() {
		return downloadFiles;
	}

	public void setDownloadFiles(Map<String,Long> downloadFiles) {
		this.downloadFiles = downloadFiles;
	}

	public String getCurrentFile() {
		return currentFile;
	}

	public void setCurrentFile(String currentFile) {
		this.currentFile = currentFile;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}
