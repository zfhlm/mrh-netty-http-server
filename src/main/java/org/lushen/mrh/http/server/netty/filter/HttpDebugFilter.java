package org.lushen.mrh.http.server.netty.filter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.lushen.mrh.http.server.netty.HttpFilter;
import org.lushen.mrh.http.server.netty.HttpFilterChain;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;

/**
 * debug filter
 * 
 * @author hlm
 */
public class HttpDebugFilter implements HttpFilter {

	private final Log log = LogFactory.getLog(getClass().getSimpleName());

	@Override
	public void doFilter(ChannelHandlerContext ctx, HttpRequest request, HttpFilterChain chain) throws Exception {
		if(log.isDebugEnabled()) {
			String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
			HttpVersion version = request.protocolVersion();
			HttpMethod method = request.method();
			String uri = request.uri();
			log.debug(String.format("%s %s %s %s.", time, version, method, uri));
		}
		chain.invoke(ctx, request);
	}

}
