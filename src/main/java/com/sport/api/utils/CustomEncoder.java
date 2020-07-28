package com.sport.api.utils;

public class CustomEncoder {
	private String Input = null;
	private int iLen = 0;
	private static final String G_FIXED_LIST = "1234567890AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz\\|~!@#$%^&*()_+-={}[]:;<>,./";
	private String G_LIST = G_FIXED_LIST;

	public CustomEncoder(String key) {
		setKey(key);
	}

	private void setKey(String sKey) {
		G_LIST = G_FIXED_LIST;
		int iAddUp = sKey.length();
		for (int i = 0; i < sKey.length(); i++) {
			for (int j = 0; j < G_LIST.length(); j++) {
				if (sKey.substring(i, i + 1).equals(G_LIST.substring(j, j + 1)))

					G_LIST = G_LIST.replace(sKey.substring(i, i + 1), "");
			}
			iAddUp = iAddUp + instr(G_FIXED_LIST, sKey.substring(i, i + 1));
		}
		iAddUp = iAddUp % G_FIXED_LIST.length();
		String sTemp = "";
		for (int i = 0; i < sKey.length(); i++) {
			if (sTemp.indexOf(sKey.substring(i, i + 1)) < 0)
				sTemp = sTemp + sKey.substring(i, i + 1);
		}
		G_LIST = sTemp + G_LIST;
		G_LIST = G_LIST.substring(iAddUp) + G_LIST.substring(0, iAddUp - 1);
	}

	private static String substr(String str, int idx1, int len) {
		return str.substring(idx1 - 1, idx1 - 1 + len);
	}

	private static String substr(String str, int idx1) {
		return str.substring(idx1 - 1);
	}

	private static int instr(String str1, String str2)

	{
		return str1.indexOf(str2) + 1;
	}

	private String eReplace(int idx1, int addup, String sChar) {
		int idx2 = instr(G_LIST, sChar);
		int index = (iLen + idx1 + idx2 + addup) % (G_LIST.length());
		if (index == 0)
			index = G_LIST.length();

		if (idx2 > 0) {
			return substr(G_LIST, index, 1);
		} else {
			return sChar;
		}
	}

	private String encode() {
		String sChar = "";
		String sResult = "";
		int iAddUp = 0;
		for (int iCount = 1; iCount <= iLen; iCount++) {
			sChar = substr(Input, iCount, 1);
			sResult = sResult + eReplace(iCount, iAddUp, sChar);
			iAddUp = iAddUp + instr(G_LIST, sChar); // G_LIST.indexOf(sChar);
		}
		return sResult;
	}

	public String getEncode(String textToEnCode) {
		Input = textToEnCode.trim();
		iLen = Input.length();
		Input = encode();
		Input = substr(Input, Input.length()) + substr(Input, 1, Input.length() - 1);
		return encode();
	}

	private String dReplace(int idx1, int addup, String sChar) {
		int idx2 = instr(G_LIST, sChar);
		String sReturn = "";
		int isrcidx = 0;
		int index = instr(G_LIST, sChar);
		if (index == G_LIST.length())
			index = 0;
		for (int i = 0; i < iLen; i++) {
			isrcidx = i * G_LIST.length() - (iLen + idx1 + addup) + index;
			if (isrcidx > 0)
				break;
		}
		if (isrcidx == 0)
			isrcidx = G_LIST.length();
		if (idx2 > 0)
			sReturn = substr(G_LIST, isrcidx, 1);
		else
			sReturn = sChar;
		return sReturn;
	}

	private String decode(String strInput) {
		String sChar = "";
		String sResult = "";
		int iAddup = 0;
		for (int iCount = 1; iCount <= iLen; iCount++) {
			sChar = substr(strInput, iCount, 1);
			String sChar2 = dReplace(iCount, iAddup, sChar);
			sResult = sResult + sChar2;
			iAddup = iAddup + instr(G_LIST, sChar2);
		}
		return sResult;
	}

	public String getDecode(String textToDecode) {
		Input = textToDecode.trim();
		iLen = Input.length();
		String strRet = decode(Input);
		strRet = substr(strRet, 2, iLen - 1) + substr(strRet, 1, 1);
		return decode(strRet);
	}
	
}
