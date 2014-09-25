package com.zrat.client.controller;

public interface CommandService {
	public static final String COMMAND_RUNCOMAND = "runComand";
	public static final String COMMAND_CLOSECOMAND = "closeComand";
	
	public void runCommand(String command) ;
	
}
