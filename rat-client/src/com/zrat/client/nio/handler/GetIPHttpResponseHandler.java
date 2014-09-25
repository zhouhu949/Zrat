package com.zrat.client.nio.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.util.CharsetUtil;

public class GetIPHttpResponseHandler extends SimpleChannelUpstreamHandler {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
//		if (!isProcessed) {
			HttpResponse response = (HttpResponse) e.getMessage();

			if (response.getStatus().getCode() == 200) {
				ChannelBuffer content = response.getContent();
				if (content.readable()) {
					String formateStr = content.toString(CharsetUtil.UTF_8);
//					System.out.println(formateStr);
					if (formateStr.indexOf('[') > 0
							&& formateStr.indexOf(']') > 0) {
						String ip = formateStr.substring(formateStr
								.indexOf('[') + 1, formateStr.indexOf(']'));
						System.out.println(ip);
						// RatUIManager.getUpdateIpFrame().getIpTextField().setText(ip);

					}
				}
			}
//		}
//		ctx.sendUpstream(e);
	}

}
