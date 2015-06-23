package com.nana.serviceengine.grammer.analyzer;

import java.util.List;

import org.ansj.domain.Term;

import com.nana.serviceengine.grammer.bean.SentenceGrammer;

public interface GrammerAnalyzer {
	SentenceGrammer analysis(List<Term> terms);
}
