package org.lushen.mrh.http.server.netty.filter;

import java.net.InetSocketAddress;

import org.apache.commons.lang3.ArrayUtils;
import org.lushen.mrh.http.server.netty.HttpFilter;
import org.lushen.mrh.http.server.netty.HttpFilterChain;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

/**
 * ip白名单拦截器
 * 
 * @author hlm
 */
public class HttpWhiteListIpsFilter implements HttpFilter {

	private String[] whiteListIps;

	public HttpWhiteListIpsFilter(String[] whiteListIps) {
		super();
		this.whiteListIps = whiteListIps;
	}

	@Override
	public void doFilter(ChannelHandlerContext ctx, HttpRequest request, HttpFilterChain chain) throws Exception {
		InetSocketAddress address = (InetSocketAddress) ctx.pipeline().channel().remoteAddress();
		String ipAddress = address.getAddress().getHostAddress();
		if( ! ArrayUtils.contains(this.whiteListIps, ipAddress)) {
			throw new Exception(String.format("非法IP地址[%s]!", ipAddress));
		}
		chain.invoke(ctx, request);
	}

}
