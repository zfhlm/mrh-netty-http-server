package org.lushen.mrh.http.server.netty.crypto;

/**
 * 加解密接口
 * 
 * @author hlm
 */
public interface CryptoProvider {

	/**
	 * 加密
	 * 
	 * @param buffer
	 * @return
	 * @throws CryptoException
	 */
	public byte[] encrypt(byte[] buffer) throws CryptoException;

	/**
	 * 解密
	 * 
	 * @param buffer
	 * @return
	 * @throws CryptoException
	 */
	public byte[] decrypt(byte[] buffer) throws CryptoException;

}
