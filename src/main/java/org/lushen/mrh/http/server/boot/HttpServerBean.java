package org.lushen.mrh.http.server.boot;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.lushen.mrh.http.server.netty.HttpServer;
import org.lushen.mrh.http.server.netty.HttpServerConfig;
import org.lushen.mrh.http.server.netty.HttpFilter;
import org.lushen.mrh.http.server.netty.HttpResolver;
import org.lushen.mrh.http.server.netty.crypto.CryptoProvider;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * http server bean
 * 
 * @author hlm
 */
public class HttpServerBean extends HttpServer implements InitializingBean, DisposableBean {

	private final Log log = LogFactory.getLog(getClass());
	
	public HttpServerBean(HttpServerConfig serverConfig, CryptoProvider cryptoProvider, List<HttpFilter> serverFilters, HttpResolver serverResolver) {
		super(serverConfig, cryptoProvider, serverFilters, serverResolver);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("Begin to initialize http server.");
		new Thread(this).start();
	}

	@Override
	public void destroy() throws Exception {
		super.close();
	}

}
