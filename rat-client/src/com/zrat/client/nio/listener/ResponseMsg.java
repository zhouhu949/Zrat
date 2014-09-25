package com.zrat.client.nio.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseMsg {
	
	private List<String> sourceDownloadFiles = new ArrayList<String>();
	private Map<String,Long> sourceDownloadSize = new HashMap<String,Long>();
	
	
	public String getSourceDownloadFile(int i){
		return sourceDownloadFiles.get(i);
	}
	
	public void putFileSize(String fileName,Long size){
		sourceDownloadSize.put(fileName, size);
	}
	
	public long getFileSize(String fileName){
		return sourceDownloadSize.get(fileName);
	}
	
	public int getDownloadFileCount(){
		return sourceDownloadFiles.size();
	}
	
	public byte[] getDestFilenameBuffer(int i){
		return sourceDownloadFiles.get(i).getBytes();
	}
	
	public void addFile(String fileName){
		sourceDownloadFiles.add(fileName);
	}

	public void setSourceDownloadFiles(List<String> sourceDownloadFiles){
		this.sourceDownloadFiles = sourceDownloadFiles;
	}
}
