package com.along.common;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * MD5 密码 解密加密 常用类
 * @author Administrator
 *
 */
public class MD5Encode {
	private final static String salt = "ssm_test_blog";
	/**
	 * MD5 加密
	 * @param filed
	 * @return
	 */
	public static String encode(String filed){
		String hashAlgorithmName = "MD5";
        String credentials = filed;
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        SimpleHash obj =new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
		return obj+"";
	}
	
/*	public static void main(String[] args) {
		String a = MD5Encode.encode("123456")+"";
		
		System.out.println(a);
		
		System.out.println(a.equals(MD5Encode.encode("123456")+""));
	}*/
}
