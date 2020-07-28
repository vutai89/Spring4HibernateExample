package com.sport.api.utils;

import java.security.MessageDigest;

public class CommonFuncs {

	public static String ConvertToMD5(String str) {
		String md5 = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());

			byte byteData[] = md.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++)
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

			md5 = sb.toString();
		} catch (Exception e) {
			System.out.println("CommonFuncs ConvertToMD5 Exception: " + e.toString());
		}
		return md5;
	}

	public static String decryptedStr(String text) {
		String decodeStr = "";
		try {
			CustomEncoder decode = new CustomEncoder("2020");
			decodeStr = decode.getDecode(text);
		} catch (Exception e) {
			System.out.println("CommonFuncs DECODED Exception => " + e.toString());
		}
		return decodeStr;

	}

	public static String encryptedStr(String text) {
		String encodeStr = "";
		try {
			CustomEncoder encoder = new CustomEncoder("2020");
			encodeStr = encoder.getEncode(text);

		} catch (Exception e) {
			System.out.println("CommonFuncs ENCODED Exception => " + e.toString());
		}
		return encodeStr;
	}

}
