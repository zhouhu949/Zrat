package com.zrat.server.nio.handler;

import java.awt.Robot;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.zrat.server.controller.ScreenServiceImpl;

public class SendScreenHandler extends SimpleChannelUpstreamHandler {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		if (e.getMessage() instanceof ChannelBuffer) {
			ChannelBuffer buffer = ((ChannelBuffer) e.getMessage());
			ScreenServiceImpl service = ScreenServiceImpl.getInstance();
			byte[] the = buffer.array();
			int n = buffer.arrayOffset();
			Robot robot = service.getRobot();
			String command = new String(the,n,20).trim();
			if (command.equalsIgnoreCase("REFRESH")) {
				service.capture();
			} else {
				
				int x = Integer.parseInt(new String(the, n + 20, 10).trim());
				int y = Integer.parseInt(new String(the, n + 30, 10).trim());
				int button = Integer.parseInt(new String(the, n + 40, 10)
						.trim());
				if (command.equalsIgnoreCase("MousePressed")) {
					robot.mousePress(button);
				} else if (command.equalsIgnoreCase("MouseReleased")) {
					robot.mouseRelease(button);
				} else if (command.equalsIgnoreCase("MouseMoved")) {
					robot.mouseMove(x, y);
				} else if (command.equalsIgnoreCase("MouseWheel")) {
					robot.mouseWheel(button);
				} else if (command.equalsIgnoreCase("KeyPressed")) {
					robot.keyPress(x);
				} else if (command.equalsIgnoreCase("KeyReleased")) {
					robot.keyRelease(x);
				}else{
					 ctx.sendUpstream(e);
				}

			}
		}

	}
}
