package com.nana.serviceengine.grammer.analyzer.impl;

import java.util.List;

import org.ansj.domain.Term;

import com.nana.serviceengine.grammer.analyzer.GrammerAnalyzer;
import com.nana.serviceengine.grammer.bean.SentenceGrammer;

public class MesGrammerAnalyzer implements GrammerAnalyzer {

	@Override
	public SentenceGrammer analysis(List<Term> terms) {
		SentenceGrammer sg = new SentenceGrammer();
		StringBuilder syntaxBuilder = new StringBuilder();
		for(Term term:terms){
			syntaxBuilder.append(term.getNatureStr().charAt(0)+",");
		}
		
		
		return null;
	}

}
