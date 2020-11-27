package org.lushen.mrh.http.server.netty.handler;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.util.List;
import java.util.Map.Entry;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * 请求响应body codec
 * 
 * @author hlm
 */
@Sharable
public class HttpBodyCodec extends MessageToMessageCodec<HttpRequest, byte[]> {

	private HttpHeaders httpHeaders;
	
	public HttpBodyCodec() {
		this(new DefaultHttpHeaders());
	}

	public HttpBodyCodec(HttpHeaders httpHeaders) {
		super();
		this.httpHeaders = httpHeaders;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, HttpRequest msg, List<Object> out) throws Exception {
		if(msg instanceof FullHttpRequest) {
			FullHttpRequest request = (FullHttpRequest)msg;
			ByteBuf in = request.content();
			try {
				out.add(ByteBufUtil.getBytes(in));
			} finally {
				in.release();
			}
		} else if(msg instanceof HttpRequest) {
			out.add(new byte[0]);
		} else {
			ctx.close();
		}
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, byte[] msg, List<Object> out) throws Exception {
		FullHttpResponse response = null;
		if(msg != null) {
			ByteBuf buf = Unpooled.wrappedBuffer(msg);
			response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.OK, buf);
			response.headers().set(CONTENT_LENGTH, buf.readableBytes());
		} else {
			response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.OK);
			response.headers().set(CONTENT_LENGTH, 0);
		}
		if(this.httpHeaders != null && ! this.httpHeaders.isEmpty()) {
			for(Entry<String, String> header : this.httpHeaders) {
				response.headers().set(header.getKey(), header.getValue());
			}
		} else {
			response.headers().set(CONTENT_TYPE, "text/*;charset=utf-8");
		}
		ctx.write(response);
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.fireExceptionCaught(cause);
	}

}
