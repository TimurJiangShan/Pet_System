package com.example.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;


public class SimpleHashUtil {

	/**
	 * encrypt
	 * @param hashAlgorithmName
	 * @param credentials
	 * @param salt
	 * @param hashIterations
	 * @return
	 */
	public static SimpleHash simpleHash(String hashAlgorithmName,String credentials,Object salt,int hashIterations) {
		return new SimpleHash(hashAlgorithmName, credentials, ByteSource.Util.bytes(salt), hashIterations);
	}
}
