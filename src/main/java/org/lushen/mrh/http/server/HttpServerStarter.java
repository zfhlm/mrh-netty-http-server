package org.lushen.mrh.http.server;

import java.util.ArrayList;
import java.util.List;

import org.lushen.mrh.http.server.boot.HttpServerBusiness;
import org.lushen.mrh.http.server.netty.HttpServer;
import org.lushen.mrh.http.server.netty.HttpServerConfig;
import org.lushen.mrh.http.server.netty.HttpFilter;
import org.lushen.mrh.http.server.netty.filter.HttpAllowMethodFilter;
import org.lushen.mrh.http.server.netty.filter.HttpDebugFilter;

import io.netty.handler.codec.http.HttpMethod;

/**
 * 以Java方式启动
 * 
 * @author hlm
 */
public class HttpServerStarter {

	public static void main(String[] args) throws Exception {

		List<HttpFilter> filters = new ArrayList<HttpFilter>();
		filters.add(new HttpDebugFilter());
		filters.add(new HttpAllowMethodFilter(new HttpMethod[]{HttpMethod.POST}));

		HttpServerConfig serverConfig = new HttpServerConfig();
		serverConfig.setPort(8080);

		HttpServer httpServer = new HttpServer(serverConfig, filters, new HttpServerBusiness());
		httpServer.run();
		httpServer.close();

	}

}
