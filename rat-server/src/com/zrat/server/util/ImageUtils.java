package com.zrat.server.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtils {
	public static void ImageScale(String path,String fileName,String toFileName){
	    try {
	        Image image = javax.imageio.ImageIO.read(new File(path + fileName));
	        int imageWidth = image.getWidth(null);
	        int imageHeight = image.getHeight(null);
	        
	        image = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
	        BufferedImage mBufferedImage = new BufferedImage(imageWidth, imageHeight,BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2 = mBufferedImage.createGraphics();
	        g2.drawImage(image, 0, 0,null);
	        FileOutputStream out = new FileOutputStream(path + toFileName);
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	        encoder.encode(mBufferedImage);
	        out.close();
	    }catch (FileNotFoundException fnf){
	    }catch (IOException ioe){
	    }finally{
	    
	    }
	  }
}
