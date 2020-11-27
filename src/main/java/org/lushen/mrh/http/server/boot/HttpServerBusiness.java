package org.lushen.mrh.http.server.boot;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import org.lushen.mrh.http.server.netty.HttpResolver;

/**
 * http business resolver
 * 
 * @author hlm
 */
public class HttpServerBusiness implements HttpResolver {

	@Override
	public byte[] resolve(byte[] body) {
		System.out.println(new String(body));
		if(new Random().nextInt(2) == 0) {
			throw new RuntimeException("出错了!");
		}
		return "success!".getBytes();
	}

	@Override
	public byte[] resolve(Throwable cause) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(bos);
		cause.printStackTrace(out);
		out.flush();
		out.close();
		return bos.toByteArray();
	}

}
