package org.lushen.mrh.http.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

/**
 * http server filter
 * 
 * @author hlm
 */
public interface HttpFilter {

	/**
	 * do filter
	 * 
	 * @param ctx
	 * @param request
	 * @param chain
	 * @throws Exception
	 */
	void doFilter(ChannelHandlerContext ctx, HttpRequest request, HttpFilterChain chain) throws Exception;

}
