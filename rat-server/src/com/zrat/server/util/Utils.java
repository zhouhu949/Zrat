package com.zrat.server.util;

import java.awt.image.BufferedImage;
import java.io.File;

public class Utils {
	public static String buildFilePath(String path, String fileName) {
		if (path.endsWith(File.separatorChar + "")) {
			return path + fileName;
		} else {
			return path + File.separatorChar + fileName;
		}
	}

	public static byte[] addBytes(byte[] src1, byte[] src2) {
		byte[] dest = new byte[src1.length + src2.length];
		System.arraycopy(src1, 0, dest, 0, src1.length);
		System.arraycopy(src2, 0, dest, src1.length, src2.length);
		return dest;
	}

	public static byte[] intToByte(int i) {
		byte[] abyte0 = new byte[4];
		abyte0[0] = (byte) (0xff & i);
		abyte0[1] = (byte) ((0xff00 & i) >> 8);
		abyte0[2] = (byte) ((0xff0000 & i) >> 16);
		abyte0[3] = (byte) ((0xff000000 & i) >> 24);
		return abyte0;
	}

	public static boolean compare(BufferedImage image1, BufferedImage image2) {
		for (int y = 0; y < image1.getHeight(); y+=2) {
			for (int x = 0; x < image1.getWidth(); x+=2) {
				if (!compareColors(image1.getRGB(x, y), image2.getRGB(x, y))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Could be used to override the way of comparing colors. 88: * @param rgb1
	 * a color to compare. 89: * @param rgb2 a color to compare. 90: * @return
	 * true if colors are equal. 91:
	 */
	protected static boolean compareColors(int rgb1, int rgb2) {
		return (rgb1 == rgb2);
	}

	public static int bytesToInt(byte[] bytes) {
		int addr = bytes[0] & 0xFF;
		addr |= ((bytes[1] << 8) & 0xFF00);
		addr |= ((bytes[2] << 16) & 0xFF0000);
		addr |= ((bytes[3] << 24) & 0xFF000000);
		return addr;
	}
}
