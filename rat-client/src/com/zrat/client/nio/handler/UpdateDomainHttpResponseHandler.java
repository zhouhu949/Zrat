package com.zrat.client.nio.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.util.CharsetUtil;


public class UpdateDomainHttpResponseHandler extends SimpleChannelUpstreamHandler {

    private boolean readingChunks;

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)  {
        if (!readingChunks) {
            HttpResponse response = (HttpResponse) e.getMessage();


            if (response.getStatus().getCode() == 200 && response.isChunked()) {
                readingChunks = true;
            } else {
                ChannelBuffer content = response.getContent();
                if (content.readable()) {
                    String formateStr = content.toString(CharsetUtil.UTF_8);
                    System.out.println(formateStr);
//                    if (formateStr.indexOf('[') >0 && formateStr.indexOf(']') >0){
//                    	String ip = formateStr.substring(formateStr.indexOf('[')+1,formateStr.indexOf(']'));
//                    	IpUtil.updateIp(ip);
//                    }
                   
                }
            }
        } 
    }
    
}
