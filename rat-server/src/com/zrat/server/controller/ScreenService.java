package com.zrat.server.controller;

import java.awt.Robot;


public interface ScreenService {

	public static final String COMMAND_CAPTRUE = "get";
	
	public static final String COMMAND_START = "start";
	public static final String COMMAND_STOP = "stop";
	
	public static  byte[] COMMAND_CAPTRUE_BYTES = new byte[10];
	public static  byte[] COMMAND_START_BYTES = new byte[10];
	
	public static final String Y = "y";
	public static final String X = "x";
	public static final String C_WIDTH = "c_width";
	public static final String C_HEIGHT = "c_height";
	public static final String SCREEN_WIDTH = "s_width";
	public static final String SCREEN_HEIGHT = "s_height";
	public static final String IMG = "img";
	public static final String FORMATENAME = "jpeg";

	public void startSendScreen();
	public void capture();
	public void setId(String id);
	Robot getRobot();
	
}
