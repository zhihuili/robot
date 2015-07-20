package com.nana.serviceengine.searcher.bean;

public class TagItem {
	//html标记
	private String tag;
	//html标记前部正则
	private String tagHead;
	//包含的子标记的。。
	private TagItem subTagItem;
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getTagHead() {
		return tagHead;
	}
	public void setTagHead(String tagHead) {
		this.tagHead = tagHead;
	}
	public TagItem getSubTagItem() {
		return subTagItem;
	}
	public void setSubTagItem(TagItem subTagItem) {
		this.subTagItem = subTagItem;
	}
	
	
}
