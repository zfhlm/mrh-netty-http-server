package org.lushen.mrh.http.server.netty.crypto.none;

import org.lushen.mrh.http.server.netty.crypto.CryptoException;
import org.lushen.mrh.http.server.netty.crypto.CryptoProvider;

/**
 * 无加解密实现
 * 
 * @author hlm
 */
public class NoneCryptoProvider implements CryptoProvider {

	@Override
	public byte[] encrypt(byte[] buffer) throws CryptoException {
		return buffer;
	}

	@Override
	public byte[] decrypt(byte[] buffer) throws CryptoException {
		return buffer;
	}

}
