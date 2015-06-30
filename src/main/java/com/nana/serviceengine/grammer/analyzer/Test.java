package com.nana.serviceengine.grammer.analyzer;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("wdsds".indexOf("ds"));
		URL in = Test.class.getResource("/");
		System.out.println("qwert".substring(0, 5)); 
	//	Pattern pattern = Pattern.compile("v[a-z]*nv[a-z]*n");
		//Pattern pattern = Pattern.compile("v[a-z]*nc[a-z]*n");
		Pattern pattern = Pattern.compile("c[a-bd-z]*n");
	//	Pattern pattern = Pattern.compile("v[[a-z]*nc[a-z]*n]+");
	//	Pattern pattern = Pattern.compile("v[a-uw-z]*n");
	
		Matcher mather = pattern.matcher("rvvncancnnas");
		while(mather.find()){
			System.out.println(mather.group());
			System.out.println(mather.start()+":"+mather.end());
		}
	}

}
