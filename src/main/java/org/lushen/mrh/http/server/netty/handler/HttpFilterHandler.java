package org.lushen.mrh.http.server.netty.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.lushen.mrh.http.server.netty.HttpFilter;
import org.lushen.mrh.http.server.netty.HttpFilterChain;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

/**
 * http filter handler
 * 
 * @author hlm
 */
@Sharable
public class HttpFilterHandler extends ChannelInboundHandlerAdapter {

	private final List<HttpFilter> httpServerFilters;

	public HttpFilterHandler(List<HttpFilter> httpServerFilters) {
		super();
		this.httpServerFilters = Collections.unmodifiableList(httpServerFilters);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		List<HttpFilter> filters = new ArrayList<HttpFilter>(this.httpServerFilters.size()+1);
		filters.addAll(this.httpServerFilters);
		filters.add(new HttpFilter() {
			@Override
			public void doFilter(ChannelHandlerContext ctx, HttpRequest request, HttpFilterChain chain) throws Exception {
				// 通过过滤，执行下一个handler
				ctx.fireChannelRead(request);
			}
		});
		new InnerHttpServerFilterChain(filters).invoke(ctx, (HttpRequest)msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// 异常传递到下一个handler
		ctx.fireExceptionCaught(cause);
	}

	public static final class InnerHttpServerFilterChain implements HttpFilterChain {

		private final AtomicInteger offset = new AtomicInteger(0);

		private final List<HttpFilter> filters;

		public InnerHttpServerFilterChain(List<HttpFilter> filters) {
			super();
			this.filters = Collections.unmodifiableList(filters);
		}

		@Override
		public void invoke(ChannelHandlerContext ctx, HttpRequest request) throws Exception {
			if(offset.get() < filters.size()) {
				this.filters.get(offset.getAndIncrement()).doFilter(ctx, request, this);
			}
		}

	}

}
