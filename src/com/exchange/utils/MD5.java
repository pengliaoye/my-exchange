package com.exchange.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static final String encode(String s) throws NoSuchAlgorithmException {
		char hexdigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		byte[] strTemp = s.getBytes();
		MessageDigest mdTemp = MessageDigest.getInstance("MD5");
		mdTemp.update(strTemp);
		byte[] md = mdTemp.digest();
		//System.out.println(new BASE64Encoder().encode(md)); 用BASE64编码
		int j = md.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			str[k++] = hexdigits[byte0 >>> 4 & 0xf];
			str[k++] = hexdigits[byte0 & 0xf];
		}
		return new String(str);
	}
}