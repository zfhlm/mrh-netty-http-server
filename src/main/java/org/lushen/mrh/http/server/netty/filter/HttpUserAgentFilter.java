package org.lushen.mrh.http.server.netty.filter;

import static io.netty.handler.codec.http.HttpHeaderNames.USER_AGENT;

import org.apache.commons.lang3.ArrayUtils;
import org.lushen.mrh.http.server.netty.HttpFilter;
import org.lushen.mrh.http.server.netty.HttpFilterChain;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

/**
 * 白名单User-Agent拦截器
 * 
 * @author hlm
 */
public class HttpUserAgentFilter implements HttpFilter {

	private String[] userAgents;

	public HttpUserAgentFilter(String[] userAgents) {
		super();
		this.userAgents = userAgents;
	}

	@Override
	public void doFilter(ChannelHandlerContext ctx, HttpRequest request, HttpFilterChain chain) throws Exception {
		if( ! ArrayUtils.contains(this.userAgents, request.headers().get(USER_AGENT))) {
			throw new Exception("非法客户端!");
		}
		chain.invoke(ctx, request);
	}

}
