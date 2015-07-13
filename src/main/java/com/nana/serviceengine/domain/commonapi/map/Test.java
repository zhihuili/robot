package com.nana.serviceengine.domain.commonapi.map;

import com.nana.serviceengine.common.bean.GPS;

public class Test {

	public static void main(String[] args) {

		GPS gps = new GPS();
		gps.setLat("39.983424051248");
		gps.setLon("116.32298703399");
		
		System.out.println(MapAPI.getInstance().getDetailInfoByGps(gps));
		
		
	}

}
