package com.nana.serviceengine.searcher.baike;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//
//		try {
//			// 创建httpget.
//			HttpGet httpget = new HttpGet("http://zhidao.baidu.com/search?word=茜拉和邓紫棋谁更厉害");
//			// 执行get请求.
//			CloseableHttpResponse response = httpclient.execute(httpget);
//			try {
//				// 获取响应实体
//				HttpEntity entity = response.getEntity();
//				if (entity != null) {
//					String tmp = EntityUtils.toString(entity,"gbk");
//					System.out.println(tmp);
//				}
//			} finally {
//				response.close();
//			}
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("wds".indexOf('a'));
	}

	

}
