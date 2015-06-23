package com.nana.serviceengine.grammer.bean;
/**
 * 纪录句法分析后的主谓宾语在分词集合中的位置
 * @author wds
 *
 */
public class SentenceGrammer {
	
	private int subjectIndex;
	private int predicateIndex;
	private int objectIndex;
	
	public int getSubjectIndex() {
		return subjectIndex;
	}
	public void setSubjectIndex(int subjectIndex) {
		this.subjectIndex = subjectIndex;
	}
	public int getPredicateIndex() {
		return predicateIndex;
	}
	public void setPredicateIndex(int predicateIndex) {
		this.predicateIndex = predicateIndex;
	}
	public int getObjectIndex() {
		return objectIndex;
	}
	public void setObjectIndex(int objectIndex) {
		this.objectIndex = objectIndex;
	}
	
}
