package com.nana.serviceengine.domain.commonapi.htmlcenter;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.nana.serviceengine.common.config.ConfigCenter;
import com.nana.serviceengine.common.dic.HtmlDic;

public class HtmlCenter {
	private static HtmlCenter hc = new HtmlCenter();
	// 模版位置文件夹
	private static String path;

	private HtmlCenter() {
		path = ConfigCenter.getInstance().getProperty("vmdir");
	}

	public static HtmlCenter getInstance() {
		return hc;
	}

	/**
	 * 获取Html通过JavaBean
	 * 
	 * @param vmName
	 * @param object
	 * @return
	 */
	public String getHtmlByBean(String vmName, Object object, String htmlName) {
		String body = formatVM(vmName, object);
		return addHtmlTags(body, htmlName);
	}

	/**
	 * 获取Html通过List集合
	 * 
	 * @param vmName
	 * @param list
	 * @param tagName
	 * @return
	 */
	public String getHtmlByList(String vmName, Object list, String tagName,String htmlName) {
		String body = formatVMByList(vmName, list, tagName);
		return addHtmlTags(body,htmlName);
	}

	private String formatVMByList(String vmName, Object object, String tagName) {
		VelocityEngine ve = new VelocityEngine();
		ve.init();
		// 配置引擎上下文对象
		VelocityContext ctx = new VelocityContext();
		ctx.put(tagName, object);
		// 加载模板文件
		Template t = ve.getTemplate(path + vmName,"utf-8");
		StringWriter sw = new StringWriter();
		// 渲染模板
		t.merge(ctx, sw);
		return sw.toString();
	}

	private String formatVM(String vmName, Object object) {
		VelocityEngine ve = new VelocityEngine();
		ve.init();
		// 配置引擎上下文对象
		VelocityContext ctx = new VelocityContext();
		Class c = object.getClass();
		Method[] methods = c.getMethods();
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			ctx.put(fields[i].getName(),
					getFieldValue(methods, object, fields[i].getName()));
		}

		// 加载模板文件
		Template t = ve.getTemplate(path + vmName, "utf-8");
		StringWriter sw = new StringWriter();
		// 渲染模板
		t.merge(ctx, sw);
		return sw.toString();
	}

	private String addHtmlTags(String body, String htmlName) {
		if (body == null)
			return null;
		String htmlTmplate = HtmlDic.HTMLMAP.get(htmlName);
		return htmlTmplate.replace("body_content", body);
	}

	/**
	 * 指定方法名来获取对象中的field值，只适用于获取bean的属性，且属性必须指定get方法
	 * 
	 * @param methods
	 * @param target
	 * @param fieldName
	 * @return
	 */
	private Object getFieldValue(Method[] methods, Object target,
			String fieldName) {
		StringBuilder builder = new StringBuilder();
		builder.append("get");
		builder.append(fieldName.substring(0, 1).toUpperCase());
		builder.append(fieldName.substring(1));
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(builder.toString())) {
				try {
					return methods[i].invoke(target);
				} catch (Exception e) {
					return null;
				}
			}
		}
		return null;
	}
}
