package com.nana.serviceengine.domain.video.webapi;

import com.nana.serviceengine.common.util.HttpServiceRequest;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String data = HttpServiceRequest.httpGet("http://112.74.78.96:8080/query?words=父亲","utf-8");
		System.out.println(data);
	}

}
