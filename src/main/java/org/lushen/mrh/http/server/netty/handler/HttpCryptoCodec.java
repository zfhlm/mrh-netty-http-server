package org.lushen.mrh.http.server.netty.handler;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.lushen.mrh.http.server.netty.crypto.CryptoProvider;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

/**
 * 加解密 codec
 * 
 * @author hlm
 */
@Sharable
public class HttpCryptoCodec extends MessageToMessageCodec<byte[], byte[]> {

	private CryptoProvider cryptoProvider;

	public HttpCryptoCodec(CryptoProvider cryptoProvider) {
		super();
		this.cryptoProvider = cryptoProvider;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, byte[] msg, List<Object> out) throws Exception {
		if(ArrayUtils.isEmpty(msg)) {
			out.add(msg);
		} else {
			out.add(this.cryptoProvider.decrypt(msg));
		}
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, byte[] msg, List<Object> out) throws Exception {
		if(ArrayUtils.isEmpty(msg)) {
			out.add(msg);
		} else {
			out.add(this.cryptoProvider.encrypt(msg));
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.fireExceptionCaught(cause);
	}

}
