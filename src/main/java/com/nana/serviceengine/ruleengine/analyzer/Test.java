package com.nana.serviceengine.ruleengine.analyzer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
//			String[] in = new String[]{"weather","want"};
//			String res = RuleDecisionMaker.getInstance().chooseDomainProcessor(in);
//			System.out.println(Test.class.getName());
//			Map<String,String> tnp = new HashMap<String,String>();
//			System.out.println(tnp.get(null));
		Pattern pattern = Pattern.compile("vn");
		Matcher mather = pattern.matcher("abvn");
		if (mather.find()) {
			System.out.println(mather.start());
		}			
			
			
			
		

//		List<String> tt=new ArrayList<String>();
//		tt.add("1");
//		tt.add("2");
//		List<String> tt1=new ArrayList<String>();
//		tt1.add("1");
//		tt1.add("3");
//		tt1.add("2");
//		tt1.add("4");
//		List<String> tt2=ListDeepCopyer.copy(tt1);
//		System.out.println(tt1==tt2);
//		
//		
//		tt1.removeAll(tt);
//		tt.addAll(tt1);
//		System.out.println(tt);
		
	}

}
