package com.nana.serviceengine.domain.commonapi.htmlcenter;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestBean bean = new TestBean();
		String res = HtmlCenter.getInstance().getHtml("frm.vm", bean);
		System.out.println(res);
	}

}
