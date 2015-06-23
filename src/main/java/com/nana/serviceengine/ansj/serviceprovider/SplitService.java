package com.nana.serviceengine.ansj.serviceprovider;

import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

public class SplitService {
	private static SplitService ss = new SplitService();
	private SplitService(){}
	public static SplitService getInstance(){
		return ss;
	}
	
	public List<Term> splitSentence(String input) {
		List<Term> parse = ToAnalysis.parse(input);
		if (parse != null && parse.size() != 0) {
			return parse;
		}
		return null;
	}
}
