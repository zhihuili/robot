package com.nana.serviceengine.common.util;

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
			data = new String(originData.getBytes(origin), aim);
			return data;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return originData;
		}
		
	}
}
