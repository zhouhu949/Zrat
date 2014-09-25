package com.zrat.server;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;

public class GuiCamera {
	private String fileName;

	private String defaultName = "GuiCamera";

	static int serialNum = 0;

	private String imageFormat;

	private String defaultImageFormat = "png";

	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	public GuiCamera() {
		fileName = defaultName;
		imageFormat = defaultImageFormat;
	}

	public GuiCamera(String s, String format) {
		fileName = s;
		imageFormat = format;
	}

	public void snapShot() {
		try {
			BufferedImage screenshot = (new Robot())
					.createScreenCapture(new Rectangle(0, 0,
							(int) d.getWidth(), (int) d.getHeight()));
			serialNum++;
			String name = fileName + String.valueOf(serialNum) + "."
					+ imageFormat;
			File f = new File(name);
			System.out.print("Save File " + name);
			ImageIO.write(screenshot, imageFormat, f);
			System.out.print("..Finished! ");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static void main(String[] args) {
//		GuiCamera cam = new GuiCamera("Test", "png");
//		cam.snapShot();
		
		String computerName ="";
		try {
			String str = InetAddress.getLocalHost().getHostName();
			computerName = str.substring(0,str.indexOf("."));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(computerName);
	}
}
