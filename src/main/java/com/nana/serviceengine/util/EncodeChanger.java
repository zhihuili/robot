package com.nana.serviceengine.util;

import java.io.UnsupportedEncodingException;

public class EncodeChanger {
	private static EncodeChanger ec = new EncodeChanger();

	private EncodeChanger() {
	}

	public static EncodeChanger getInstance() {
		return ec;
	}
	
	public String changeCode(String originData,String origin,String aim){
		String data = null;
		try {
			data = new String(originData.getBytes("iso-8859-1"), "utf-8");
			return data;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return originData;
		}
		
	}
}
