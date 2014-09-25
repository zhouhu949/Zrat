package com.zrat.client.ui.model;

import com.zrat.transfer.model.FileTransferModel;

public class FileTransferTableModel  extends BaseTableModelImpl<FileTransferModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String currentFile;
	
	public FileTransferTableModel(){
		setColumn(new String[] { "","文件","传输类型","传输进度","状态","传输速度"});
	}

	@Override
	public Object getPropertyValueByCol(FileTransferModel p, int col) {
		switch (col) {
		case 0:
			return p.getId();
		case 1:
			return p.getName();
		case 2:
			return p.getType().equalsIgnoreCase("U")?"上传":"下载";
		case 3:
			return p.getProgress();
		case 4:
			return p.getStatus();
		case 5:
			return p.getSpeed();
		}
		return null;
	}

	
	public void process(int row,long value) {
	      setValueAt(value, row, 3);
	     // 传送变更事件给指定行列
	     fireTableCellUpdated(row, 3);
	    }
	
	@Override
	public FileTransferModel setPropertyValueByCol(FileTransferModel p,
			Object value, int col) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCurrentFile() {
		return currentFile;
	}

	public void setCurrentFile(String currentFile) {
		this.currentFile = currentFile;
	}

}
