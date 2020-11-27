package org.lushen.mrh.http.server.netty.crypto.des3;

import org.apache.commons.lang3.StringUtils;

/**
 * 3DES加密配置
 * 
 * @author hlm
 */
public class Des3Properties {

	private String key;	//加解密秘钥

	private String iv;	//加解密向量

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}

	public void afterPropertiesSet() {

		if(StringUtils.isBlank(this.key)) {
			throw new IllegalArgumentException("key can't be null !");
		}
		if(StringUtils.length(this.key) > 24) {
			throw new IllegalArgumentException("key length can't be more than 24 !");
		}
		if(StringUtils.isBlank(this.iv)) {
			throw new IllegalArgumentException("iv can't be null !");
		}
		if(StringUtils.length(this.iv) > 8) {
			throw new IllegalArgumentException("iv length can't be more than 24 !");
		}

		// 位数填充，key不够24位，iv不够8位，右边填充0
		this.key = StringUtils.rightPad(this.key, 24, '0');
		this.iv = StringUtils.rightPad(this.iv, 8, '0');

	}

}
