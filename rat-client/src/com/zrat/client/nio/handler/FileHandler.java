package com.zrat.client.nio.handler;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.List;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.DefaultFileRegion;
import org.jboss.netty.channel.FileRegion;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.ChannelHandler.Sharable;

import com.zrat.client.controller.FileService;
import com.zrat.transfer.model.RemoteFile;
import com.zrat.transfer.model.TransferDataStruct;

@Sharable
public class FileHandler extends ClientNIOHandler{

	@SuppressWarnings("unchecked")
	@Override
	public void processMessage(ChannelHandlerContext ctx, MessageEvent e) {
		if (e.getMessage() instanceof TransferDataStruct) {
			TransferDataStruct data = (TransferDataStruct) e.getMessage();
			if (FileService.COMMAND_COPYFILE.equals(data.getAction())) {
				List<RemoteFile> files= (List<RemoteFile>) data.getObject();
				server.getFileFrame().refreshTable(files);
			} else if (FileService.COMMAND_CREATEDIRECTORY.equals(data
					.getAction())) {
				List<RemoteFile> files= (List<RemoteFile>) data.getObject();
				server.getFileFrame().refreshTable(files);
			} else if (FileService.COMMAND_CREATEFILE.equals(data.getAction())) {
				List<RemoteFile> files= (List<RemoteFile>) data.getObject();
				server.getFileFrame().refreshTable(files);
			} else if (FileService.COMMAND_CUTFILE.equals(data.getAction())) {
				List<RemoteFile> files= (List<RemoteFile>) data.getObject();
				server.getFileFrame().refreshTable(files);
			} else if (FileService.COMMAND_DELETEFILE.equals(data.getAction())) {
				List<RemoteFile> files= (List<RemoteFile>) data.getObject();
				server.getFileFrame().refreshTable(files);
			} else if (FileService.COMMAND_DOWNLOAD.equals(data.getAction())) {
					String fileName = data.getObject().toString();
							server.getFileFrame().getTransferTableModel().setCurrentFile(fileName);
							
			}else if (FileService.COMMAND_DOWNLOAD_BYTE.equals(data.getAction())) {
				byte[] fileByte = (byte[]) data.getObject();
				
				String split = server.getSystemName().startsWith("Windows") ? "\\" : "/";
				RandomAccessFile raf= null;
				try {
					String currentFile = server.getFileFrame().getTransferTableModel().getCurrentFile();
					String filePath = System.getProperty("user.dir") + File.separator
					+ FileService.DOWNLOADDIC + File.separator + currentFile.substring(currentFile.lastIndexOf(split),currentFile.length());
					File f = new File(filePath);
					raf = new RandomAccessFile(f,"rw");
					raf.seek(raf.length());
					raf.write(fileByte);
					long fileSize = server.getDownloadFiles().get(currentFile);
//					server.getFileFrame().getTransferTableModel().getCurrentFile();
//					long size = server.getDownloadFiles().get(filePath);
					server.getFileFrame().getTransferTableModel().getRow(0).setSpeed(fileByte.length/1000+"KB/S");
					server.getFileFrame().getTransferTableModel().getRow(0).setProgress(raf.length()*100/fileSize);
					server.getFileFrame().getTransferTableModel().setValueAt(raf.length()*100/fileSize, 0, 3);
					server.getFileFrame().getTransferTableModel().setValueAt(fileByte.length/1000+"KB/S", 0, 5);
					server.getFileFrame().getTransferTableModel().setValueAt("正在下载", 0, 4);
					if(f.length() == fileSize){
						server.getFileFrame().getTransferTableModel().setValueAt("下载完成", 0, 4);
					}
					
				}catch (Exception ee) {
					ee.printStackTrace();
				}finally{
					
						try {
							if (raf != null)
							raf.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
				}
			}
			else if (FileService.COMMAND_UPLOADFILE.equals(data.getAction())) {
				List<RemoteFile> files= (List<RemoteFile>) data.getObject();
				server.getFileFrame().refreshTable(files);
			} else if (FileService.COMMAND_SHOWFILES.equals(data.getAction())) {
				List<RemoteFile> files= (List<RemoteFile>) data.getObject();
				server.getFileFrame().refreshTable(files);
			} else if (FileService.COMMAND_SHOWROOTS.equals(data.getAction())) {
				List<RemoteFile> files= (List<RemoteFile>) data.getObject();
				server.getFileFrame().refreshTable(files);
			} else if (FileService.COMMAND_UNZIPFILE.equals(data.getAction())) {
				List<RemoteFile> files= (List<RemoteFile>) data.getObject();
				server.getFileFrame().refreshTable(files);
			} else if (FileService.COMMAND_ZIPFILE.equals(data.getAction())) {
				List<RemoteFile> files= (List<RemoteFile>) data.getObject();
				server.getFileFrame().refreshTable(files);
			} else {
				ctx.sendUpstream(e);
			}

		}else if(e.getMessage() instanceof FileRegion){
			FileRegion fileRegion = (DefaultFileRegion) e.getMessage();
			
			FileChannel fileChannel = null;
			String filePath = System.getProperty("user.dir") + File.separator + FileService.DOWNLOADDIC + File.separator + new Date().toLocaleString();
			File file = new File(filePath);
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				fileChannel = new FileInputStream(file).getChannel();
				fileRegion.transferTo(fileChannel, 0);
			} catch (IOException en) {
				en.printStackTrace();
			} finally {
				try {
					fileChannel.close();
				} catch (IOException eo) {
					eo.printStackTrace();
				}
			}
		}else {
			ctx.sendUpstream(e);
		}
		
	}

}
