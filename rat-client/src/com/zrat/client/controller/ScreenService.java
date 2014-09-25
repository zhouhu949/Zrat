package com.zrat.client.controller;

public interface ScreenService {
	public static final String COMMAND_CAPTRUE = "get";
	public static final String COMMAND_START = "start";
	public static final String COMMAND_STOP = "stop";
	
	public static final String Y = "y";
	public static final String X = "x";
	public static final String C_WIDTH = "c_width";
	public static final String C_HEIGHT = "c_height";
	public static final String SCREEN_WIDTH = "s_width";
	public static final String SCREEN_HEIGHT = "s_height";
	public static final String IMG = "img";
	
	public void startSendScreen();
	public void stopSendScreen();
	public void capture();
	public void sendMouse(int mousePressed, int x2, int y2, int button);
	public void sendKey(int type,int code);
}
