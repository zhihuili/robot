package com.nana.serviceengine.ruleengine.analyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nana.serviceengine.ruleengine.cacher.RuleData;
import com.nana.serviceengine.util.ListDeepCopyer;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
			String[] in = new String[]{"weather","want"};
			String res = RuleDecisionMaker.getInstance().chooseDomainProcessor(in);
			System.out.println(res);
			
			
			
			
			
		

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
