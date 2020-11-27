package org.lushen.mrh.http.server.netty.filter;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import org.lushen.mrh.http.server.netty.HttpFilter;
import org.lushen.mrh.http.server.netty.HttpFilterChain;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;

/**
 * http OPTIONS filter
 * 
 * @author hlm
 */
public class HttpOptionsMethodFilter implements HttpFilter {

	@Override
	public void doFilter(ChannelHandlerContext ctx, HttpRequest request, HttpFilterChain chain) throws Exception {
		if(request.method() == HttpMethod.OPTIONS) {
			DefaultFullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.EMPTY_BUFFER);
			response.headers().set(CONNECTION, KEEP_ALIVE);
			response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
			ctx.write(response);
			ctx.flush();
		} else {
			chain.invoke(ctx, request);
		}
	}

}
