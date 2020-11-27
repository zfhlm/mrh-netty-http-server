package org.lushen.mrh.http.server.netty;

/**
 * http server resolver
 * 
 * @author hlm
 */
public interface HttpResolver {

	/**
	 * resolve http body
	 * 
	 * @param body
	 * @return
	 */
	byte[] resolve(byte[] body);

	/**
	 * resolver http error
	 * 
	 * @param cause
	 * @return
	 */
	byte[] resolve(Throwable cause);

}
