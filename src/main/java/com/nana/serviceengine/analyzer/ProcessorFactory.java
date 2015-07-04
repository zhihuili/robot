package com.nana.serviceengine.analyzer;

import java.util.List;

import org.ansj.domain.Term;

import com.nana.serviceengine.bean.UserMessage;
import com.nana.serviceengine.dic.pool.ChinaCity;
import com.nana.serviceengine.processor.ServiceProcessor;
import com.nana.serviceengine.processor.impl.WeatherServiceProcessor;
import com.nana.serviceengine.util.TxtReader;

public class ProcessorFactory {
	
	private static ProcessorFactory processFactory = new ProcessorFactory();
	
	private ProcessorFactory(){
		
	}
	
	public static ProcessorFactory getInstance(){
		return processFactory;
	} 
	
	/**
	 * 处理未分词的请求 过期
	 * @param input
	 * @return
	 */
	public ServiceProcessor analyze(UserMessage mes) {
		ServiceProcessor processor = null;
		if(judgeDomain("resources/weather", mes.getMessage())) {
			processor = new WeatherServiceProcessor(mes);
		}
		return processor;
	}
	/**
	 * 处理分词后的请求  过期
	 * @param inputs
	 * @return
	 */
	public ServiceProcessor analyze(List<Term> inputs) {
		ServiceProcessor processor = null;
		if (inputs != null && inputs.size() != 0) {
			for (int i = 0; i < inputs.size(); i++) {
				// 这里判断应该返回的处理器类型
				if (ChinaCity.CityMap.get(inputs.get(i).getRealName()) != null) {
					processor = new WeatherServiceProcessor();
				}
			}
		}
		return processor;
	}
	
	
	
	public ServiceProcessor createProcessorByClassName(String className,UserMessage mes){
		try {
			Class c = Class.forName(className);
			ServiceProcessor sp = (ServiceProcessor)c.newInstance();
			sp.setMes(mes);
			return sp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	

	/**
	 * 给定领域库路径，判断是否属于这个领域
	 * 
	 * @param path
	 *            库文件路径
	 * @return
	 */
	private boolean judgeDomain(String path, String input) {
		List<String> lines = new TxtReader(path).getContentLineList();
		if (lines != null && lines.size() != 0) {
			for (String line : lines) {
				if (input.contains(line)) {
					return true;
				}
			}
		}
		return false;
	}

}
