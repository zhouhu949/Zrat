package com.zrat.server.nio.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;

import com.zrat.server.controller.FileService;
import com.zrat.transfer.model.TransferDataStruct;

public class FileTransferFutureListener implements ChannelFutureListener{
	private final ChannelBuffer buffer = ChannelBuffers.buffer(500*1024);
	private long offset = 0;
	private ResponseMsg response =null;
	private FileInputStream fis = null;
	private int fileIndex = 0;
	private File file;
	private long fileLength = 0;
	private TransferDataStruct data = new TransferDataStruct(FileService.COMMAND_DOWNLOAD_BYTE);
	
	public FileTransferFutureListener(final ResponseMsg response,final int firstFileIndex) throws FileNotFoundException {
		super();
		this.response = response;
		this.fileIndex = firstFileIndex;
		this.file =  new File(this.response.getSourceDownloadFile(this.fileIndex));
		this.fileLength = file.length();
		this.fis = new FileInputStream(this.file); 
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
			
		this.buffer.clear();
		this.buffer.writeBytes(fis, (int) Math.min(this.fileLength - this.offset,this.buffer.writableBytes()));
		this.offset += this.buffer.writerIndex();
		data.setAction(FileService.COMMAND_DOWNLOAD_BYTE);
		data.setObject(buffer.array());
		ChannelFuture chunkWriteFuture =future.getChannel().write(data);
		if (this.offset < this.fileLength) {
			chunkWriteFuture.addListener(this);
		} else {
			this.fis.close();
			this.fis=null;
			this.file  = null;
			this.offset = 0;
			this.fileIndex++;
//			System.out.println("下载完成");
			if (this.fileIndex< this.response.getDownloadFileCount())
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
					data.setAction(FileService.COMMAND_DOWNLOAD);
					data.setObject(response.getSourceDownloadFile(fileIndex));
					this.fis = new FileInputStream(this.file); 
					ChannelFuture f = future.getChannel().write(data);
					f.addListener(this);
				}
//				else{
//					chunkWriteFuture.addListener(ChannelFutureListener.CLOSE);
//				}
			}
		}
	}
}
