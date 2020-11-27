package org.lushen.mrh.http.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

/**
 * http server filter chain
 * 
 * @author hlm
 */
public interface HttpFilterChain {

	/**
	 * do next filter
	 * 
	 * @param ctx
	 * @param request
	 * @throws Exception
	 */
	void invoke(ChannelHandlerContext ctx, HttpRequest request) throws Exception;

}
