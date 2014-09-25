package com.zrat.client.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;


public class ScreenLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage screen;
	private int cell_width=0;
	private int cell_height=0;
	int[] imageArray;
	public ScreenLabel(){
		super();
//		this.setPreferredSize(new Dimension(800,600));
	}
	
	public void paint(Graphics g){
		   super.paint(g);
			   g.drawImage(screen, 0, 0, this);
			g.dispose();  
			   
	}
	
	public synchronized void  refresh(int x,int y,InputStream input){
	    try {
	    	JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(input);
	        //将压缩后的数据存放到图像缓存区
	        BufferedImage image = decoder.decodeAsBufferedImage();
	        imageArray = image.getRGB(0, 0,cell_width,cell_height,imageArray,0,cell_width);
	        screen.setRGB(x *cell_width, y * cell_height,cell_width,cell_height,imageArray,0,cell_width);
//	        this.setPreferredSize(new Dimension(image.getWidth(),image.getHeight()));
	    	getGraphics().drawImage(image, x *cell_width, y * cell_height, this);
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
//		updateUI();
// updateUI();
	}
	
	public void refresh(InputStream input){
	    try {
	    	
	    	JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(input);
	        //将压缩后的数据存放到图像缓存区
	        BufferedImage image = decoder.decodeAsBufferedImage();
	        screen = image;
	        this.setPreferredSize(new Dimension(image.getWidth(),image.getHeight()));
	        getGraphics().drawImage(screen, 0, 0,this);
	    } catch (Exception e) {
			e.printStackTrace();
		}
//		updateUI();
	}

	public int getCell_width() {
		return cell_width;
	}

	public void setCell_width(int cellWidth) {
		cell_width = cellWidth;
	}

	public int getCell_height() {
		return cell_height;
	}

	public void savePic(){
		Calendar c = Calendar.getInstance();
		String saveDicPath = System.getProperty("user.dir") + File.separator
		+ Client.SCREEN_SAVE_DIC;
		File dic = new File(saveDicPath);
		if(!dic.exists()){
			dic.mkdirs();
		}
		saveDicPath +=File.separator+c.getTimeInMillis()+".jpg";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(saveDicPath);
			ImageIO.write(screen, "JPEG", fos);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fos!=null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	public void setCell_height(int cellHeight) {
		cell_height = cellHeight;
	}
	
	
	public void setScreenSize(int width,int height){
		screen = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		super.setSize(width, height);
		setPreferredSize(new  Dimension(width, height));
	}
	

}
