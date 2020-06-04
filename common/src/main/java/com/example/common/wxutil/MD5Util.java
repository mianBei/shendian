/**
 * 
 */
package com.example.common.wxutil;

import java.security.MessageDigest;

public class MD5Util {

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	public static void main(String[] args) {
		String aa=MD5Encode("appid=wx70bee723e60acb28&body=111111&device_info=1000&mch_id=1298369001&nonce_str=e3dac8b97f8341ea98239058d0068741&key=486851440c274edf9488de564a146749");
		System.out.println(aa);
	}

	public static String MD5Encode(String origin) {
		  String resultString = null;
		  try {
		   resultString = origin;
		   MessageDigest md = MessageDigest.getInstance("MD5");
		   // resultString =
		   // byteArrayToHexString(md.digest(resultString.getBytes()));//原文件内容，可能原因是：win2003时系统缺省编码为GBK，win7为utf-8
		   resultString = byteArrayToHexString(md.digest(resultString
		     .getBytes("UTF-8")));// 正确的写法
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return resultString;
		 }
	
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
}
