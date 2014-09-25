package com.zrat.client.nio.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;

import com.zrat.client.controller.FileService;
import com.zrat.client.ui.Client;
import com.zrat.client.util.Utils;
import com.zrat.transfer.model.TransferDataStruct;

public class FileTransferFutureListener implements ChannelFutureListener{
	private final ChannelBuffer buffer = ChannelBuffers.buffer(8192);
	private long offset = 0;
	private long begin ;	
	private String path;
	private ResponseMsg response =null;
	private FileInputStream fis = null;
	private int fileIndex = 0;
	private File file;
	private long fileLength = 0;
	private TransferDataStruct data = new TransferDataStruct(FileService.COMMAND_DOWNLOAD_BYTE);
//	RandomAccessFile raf;
	
	public FileTransferFutureListener(final ResponseMsg response,final int firstFileIndex,String path) throws FileNotFoundException {
		super();
		this.response = response;
		this.fileIndex = firstFileIndex;
		this.file =  new File(this.response.getSourceDownloadFile(this.fileIndex));
		this.fileLength = file.length();
		this.fis = new FileInputStream(this.file); 
		this.path = path;
//		try {
//			fis.seek(response.getFileSize(response.getSourceDownloadFile(this.fileIndex)));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		raf = new RandomAccessFile(file, "r");
//		raf.
	}
	@Override
	public void operationComplete(ChannelFuture future) throws Exception {
		if (!future.isSuccess()) {
			future.getCause().printStackTrace();
			future.getChannel().close();
			if(null != fis){
				this.fis.close();
				this.fis =  null;
				this.file = null;
			}
			return;
		}
		if(this.offset == 0){
			this.begin = System.currentTimeMillis();
		}
			
		this.buffer.clear();
		this.buffer.writeBytes(fis, (int) Math.min(this.fileLength - this.offset,this.buffer.writableBytes()));
		this.offset += this.buffer.writerIndex();
		data.setAction(FileService.COMMAND_DOWNLOAD_BYTE);
		data.setObject(buffer.array());
		ChannelFuture chunkWriteFuture =future.getChannel().write(data);
//		Client.getServer(future.getChannel().getId()).getFileFrame().getTransferTableModel().getById(path).setSize(this.fileLength);
		Client.getServer(future.getChannel().getId()).getFileFrame().getTransferTableModel().process(fileIndex,offset);
		if (this.offset < this.fileLength) {
			chunkWriteFuture.addListener(this);
		} else {
			this.fis.close();
			this.fis=null;
			this.file  = null;
			this.offset = 0;
			
			Client.getServer(future.getChannel().getId()).getFileFrame().getTransferTableModel().getRow(fileIndex).setStatus("上传完成");
			this.fileIndex++;
			if (this.fileIndex>= this.response.getDownloadFileCount())
				{
				while(this.fileIndex<this.response.getDownloadFileCount()){
					this.file = new File(this.response.getSourceDownloadFile(this.fileIndex));
					if (!this.file.exists() || !file.canRead() ){
						this.fileIndex++;
					}
					else
						break;
				}
				if(this.fileIndex<this.response.getDownloadFileCount()){
					data.setAction(FileService.COMMAND_UPLOADFILE);
					data.setObject(Utils.buildFilePath(future.getChannel().getId(),path,response.getSourceDownloadFile(fileIndex)));
					this.fis = new FileInputStream(this.file); 
					ChannelFuture f = future.getChannel().write(data);
					f.addListener(this);
				}
			}
		}
	}
}
