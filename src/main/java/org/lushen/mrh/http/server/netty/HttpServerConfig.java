package org.lushen.mrh.http.server.netty;

import java.util.Optional;

/**
 * http 代理配置
 * 
 * @author hlm
 */
public class HttpServerConfig {

	private static final Integer PROCESSORS = Runtime.getRuntime().availableProcessors();	//服务器处理器个数

	private static final Integer DEFAULT_PORT = 8080;	//默认端口

	private static final Integer DEFAULT_ACCEPTORS = Optional.of(((int)PROCESSORS/8)).map(e -> e <= 1? 1:e).get();	//默认IO处理线程数

	private static final Integer DEFAULT_WORKERS = PROCESSORS*100;	//默认工作线程数

	private Integer port = DEFAULT_PORT;			//代理服务器监听端口号

	private Integer acceptors = DEFAULT_ACCEPTORS;	//代理服务器最大监听线程数

	private Integer workers = DEFAULT_WORKERS;		//代理服务器最大处理线程数

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getAcceptors() {
		return acceptors;
	}

	public void setAcceptors(Integer acceptors) {
		this.acceptors = acceptors;
	}

	public Integer getWorkers() {
		return workers;
	}

	public void setWorkers(Integer workers) {
		this.workers = workers;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[port=");
		builder.append(port);
		builder.append(", acceptors=");
		builder.append(acceptors);
		builder.append(", workers=");
		builder.append(workers);
		builder.append("]");
		return builder.toString();
	}

}
