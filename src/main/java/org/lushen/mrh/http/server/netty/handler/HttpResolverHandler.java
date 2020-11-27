package org.lushen.mrh.http.server.netty.handler;

import org.lushen.mrh.http.server.netty.HttpResolver;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * http server resolver handler
 * 
 * @author hlm
 */
@Sharable
public class HttpResolverHandler extends ChannelInboundHandlerAdapter {

	private HttpResolver serverResolver;

	public HttpResolverHandler(HttpResolver serverResolver) {
		super();
		this.serverResolver = serverResolver;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		byte[] body = this.serverResolver.resolve((byte[]) msg);
		ctx.writeAndFlush(body);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		byte[] body = this.serverResolver.resolve(cause);
		ctx.writeAndFlush(body);
	}

}
