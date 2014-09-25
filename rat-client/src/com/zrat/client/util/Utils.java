package com.zrat.client.util;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.swing.ImageIcon;

import com.zrat.client.ui.Client;
import com.zrat.client.ui.FileFrame;

public class Utils {
	public static String buildFilePath(String path,String fileName){
		if(path.endsWith(File.separatorChar+"")){
			return path+fileName;
		}else{
			return path + File.separatorChar + fileName;
		}
	}
	
	public static String buildFilePath(Integer id,String path,String fileName){
		String split = Client.getServer(id).getSystemName()
		.startsWith("Windows") ? "\\" : "/";
		if(path.endsWith(split)){
			return path+fileName;
		}else{
			return path + split + fileName;
		}
	}
	
	public static String getFileSeparator(Integer id){
		return Client.getServer(id).getSystemName().startsWith("Windows") ? "\\" : "/";
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

    public static int bytesToInt(byte[] bytes) {
        int addr = bytes[0] & 0xFF;
        addr |= ((bytes[1] << 8) & 0xFF00);
        addr |= ((bytes[2] << 16) & 0xFF0000);
        addr |= ((bytes[3] << 24) & 0xFF000000);
        return addr;
    }
    /**
     * 从ip的字符串形式得到字节数组形式
     * @param ip 字符串形式的ip
     * @return 字节数组形式的ip
     */
    public static byte[] getIpByteArrayFromString(String ip) {
        byte[] ret = new byte[4];
        java.util.StringTokenizer st = new java.util.StringTokenizer(ip, ".");
        try {
            ret[0] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
            ret[1] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
            ret[2] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
            ret[3] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
    
    public static void main(String args[]){
         byte[] a=getIpByteArrayFromString(args[0]);
          for(int i=0;i< a.length;i++)
                System.out.println(a[i]);
          System.out.println(getIpStringFromBytes(a)); 
    }
    /**
     * 对原始字符串进行编码转换，如果失败，返回原始的字符串
     * @param s 原始字符串
     * @param srcEncoding 源编码方式
     * @param destEncoding 目标编码方式
     * @return 转换编码后的字符串，失败返回原始字符串
     */
    public static String getString(String s, String srcEncoding, String destEncoding) {
        try {
            return new String(s.getBytes(srcEncoding), destEncoding);
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }
    
    /**
     * 根据某种编码方式将字节数组转换成字符串
     * @param b 字节数组
     * @param encoding 编码方式
     * @return 如果encoding不支持，返回一个缺省编码的字符串
     */
    public static String getString(byte[] b, String encoding) {
        try {
            return new String(b, encoding);
        } catch (UnsupportedEncodingException e) {
            return new String(b);
        }
    }
    
    /**
     * 根据某种编码方式将字节数组转换成字符串
     * @param b 字节数组
     * @param offset 要转换的起始位置
     * @param len 要转换的长度
     * @param encoding 编码方式
     * @return 如果encoding不支持，返回一个缺省编码的字符串
     */
    public static String getString(byte[] b, int offset, int len, String encoding) {
        try {
            return new String(b, offset, len, encoding);
        } catch (UnsupportedEncodingException e) {
            return new String(b, offset, len);
        }
    }
    
    /**
     * @param ip ip的字节数组形式
     * @return 字符串形式的ip
     */
    public static String getIpStringFromBytes(byte[] ip) {
    	StringBuffer sb = new StringBuffer();
    	sb.append(ip[0] & 0xFF);
    	sb.append('.');   	
    	sb.append(ip[1] & 0xFF);
    	sb.append('.');   	
    	sb.append(ip[2] & 0xFF);
    	sb.append('.');   	
    	sb.append(ip[3] & 0xFF);
    	return sb.toString();
    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
	public static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = FileFrame.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}
