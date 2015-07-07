package com.nana.serviceengine.common.config;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigCenter {
	private Properties apiProp = new Properties();
	private static ConfigCenter cc  = new ConfigCenter();
	
	private ConfigCenter(){		
		try {
			// 读取系统配置文件
			InputStream apiIn = new BufferedInputStream(new FileInputStream(
					"config/sysconfig.properties"));
			apiProp.load(apiIn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	public static ConfigCenter getInstance(){
		return cc;
	}
	
	public String getProperty(String key){
		return apiProp.getProperty(key);
	}
}
