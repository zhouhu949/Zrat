package com.zrat.client.ui;

import javax.swing.JPanel;
import javax.swing.JToolBar;

public class RatUIManager {
	private static MainPanel mainPanel;
	private static MainTabPanel tabPane;
	private static JToolBar toolBar;
	private static JPanel statusPanel;
	private static UpdateIpFrame updateIpFrame;
	
	public static MainPanel getMainPanel() {
		return mainPanel;
	}
	public static void setMainPanel(MainPanel mainPanel) {
		RatUIManager.mainPanel = mainPanel;
	}
	public static MainTabPanel getTabPane() {
		return tabPane;
	}
	public static void setTabPane(MainTabPanel tabPane) {
		RatUIManager.tabPane = tabPane;
	}
	public static JToolBar getToolBar() {
		return toolBar;
	}
	public static void setToolBar(JToolBar toolBar) {
		RatUIManager.toolBar = toolBar;
	}
	public static JPanel getStatusPanel() {
		return statusPanel;
	}
	public static void setStatusPanel(JPanel statusPanel) {
		RatUIManager.statusPanel = statusPanel;
	}
	public static UpdateIpFrame getUpdateIpFrame() {
		return updateIpFrame;
	}
	public static void setUpdateIpFrame(UpdateIpFrame updateIpFrame) {
		RatUIManager.updateIpFrame = updateIpFrame;
	}
	
	
}
