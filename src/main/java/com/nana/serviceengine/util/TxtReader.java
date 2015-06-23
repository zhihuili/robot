package com.nana.serviceengine.util;

import java.util.List;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TxtReader {
	private File file;

	public TxtReader(String path) {
		file = new File(path);
	}

	/**
	 * 获取文本中的字符串
	 * @return 文本中所有的字符串
	 * @throws IOException 
	 */
	public String getContent() {
		if (file.exists() && !file.isDirectory()) {
			BufferedInputStream fis = null;
			try {
				fis=new BufferedInputStream(new FileInputStream(file));
			    byte[] buffer = new byte[fis.available()];
			    fis.read(buffer);
			    String result =new String(buffer);
			    fis.close();
			    return result;
			} catch (Exception e) {
				e.printStackTrace();
				// to do log
				
				try {
					fis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return null;
			}
		}
		return null;
	}
	
	/**
	 * 获取file中的行的集合
	 * @return
	 */
	public List<String> getContentLineList() {
		if (file.exists() && !file.isDirectory()) {
			BufferedReader reader = null;
			try {
				reader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			    List<String> strList = new ArrayList<String>();
			    String str;
			    while((str = reader.readLine())!= null ){
			    	strList.add(str);
			    }
			    reader.close();
			    return strList;
			} catch (Exception e) {
				e.printStackTrace();
				// to do log
				
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return null;
			}
			
		}
		
		System.out.println(file.isDirectory());
		return null;
	}
}
