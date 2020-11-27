package org.lushen.mrh.http.server.netty.filter;

import static io.netty.handler.codec.http.HttpMethod.CONNECT;
import static io.netty.handler.codec.http.HttpMethod.DELETE;
import static io.netty.handler.codec.http.HttpMethod.GET;
import static io.netty.handler.codec.http.HttpMethod.HEAD;
import static io.netty.handler.codec.http.HttpMethod.OPTIONS;
import static io.netty.handler.codec.http.HttpMethod.PATCH;
import static io.netty.handler.codec.http.HttpMethod.POST;
import static io.netty.handler.codec.http.HttpMethod.PUT;
import static io.netty.handler.codec.http.HttpMethod.TRACE;

import java.util.Arrays;

import org.lushen.mrh.http.server.netty.HttpFilter;
import org.lushen.mrh.http.server.netty.HttpFilterChain;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;

/**
 * allow method filter
 * 
 * @author hlm
 */
public class HttpAllowMethodFilter implements HttpFilter {

	private HttpMethod[] allowMethods;

	public HttpAllowMethodFilter() {
		this(new HttpMethod[]{GET, POST, OPTIONS, HEAD, PUT, DELETE, PATCH, TRACE, CONNECT});
	}

	public HttpAllowMethodFilter(HttpMethod[] allowMethods) {
		super();
		this.allowMethods = (allowMethods == null? new HttpMethod[0] : allowMethods);
	}

	@Override
	public void doFilter(ChannelHandlerContext ctx, HttpRequest request, HttpFilterChain chain) throws Exception {
		if( ! Arrays.stream(this.allowMethods).filter(method -> method == request.method()).findFirst().isPresent()) {
			throw new Exception("Not allow method !");
		}
		chain.invoke(ctx, request);
	}

}
