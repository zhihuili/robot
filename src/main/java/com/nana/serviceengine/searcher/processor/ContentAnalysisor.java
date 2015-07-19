package com.nana.serviceengine.searcher.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.nana.serviceengine.common.util.MethodInvoker;
import com.nana.serviceengine.searcher.bean.BeanItem;
import com.nana.serviceengine.searcher.bean.MethodItem;
import com.nana.serviceengine.searcher.bean.Model;
import com.nana.serviceengine.searcher.bean.Param;
import com.nana.serviceengine.searcher.util.TypeConvertor;

/**
 * 解析引擎
 * 
 * @author wds
 *
 */
public class ContentAnalysisor {
	private static ContentAnalysisor ca = new ContentAnalysisor();

	private ContentAnalysisor() {
	}

	public static ContentAnalysisor getInstance() {
		return ca;
	}

	public List<Object> getBeanFromItemHtml(Document doc, Model model,
			Class<?> beanClass) {
		try {
			List<Object> res = new ArrayList<Object>();
			Map<String, MethodItem> mis = model.getMethodItems();
			List<String> reducers = model.getReducer();
			List<BeanItem> beanItems = model.getBeanItems();
			
			Object content = doc;
			//去除外围标签
			for(int i=0;i<reducers.size();i++){
				content = invoke(content,model,reducers.get(i));			
			}
			//循环取html中的值
			Elements els = (Elements) content;
			for(int i=0;i<els.size();i++){
				Element element = els.get(i);
				//开始获取数据
				Object receiveObj = beanClass.newInstance();
				for(BeanItem bitem:model.getBeanItems()){
					Object tmp = element;
					String[] methodIndexs = bitem.getMethodIndex().split(",");		
					for(String methodIndex:methodIndexs){
						tmp = invoke(tmp,model,methodIndex);
					}	
					//获取结果后将结果存到bean中
					MethodInvoker.invokeMethod(receiveObj, bitem.getSetMethodName(),tmp);
				}
				//将取好数据的bean放到结果集合中
				res.add(receiveObj);
			}
			return res;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 用来执行Model头部定义的方法
	 * @param initData
	 * @param model
	 * @param indexKey
	 * @return
	 */
	private Object invoke(Object initData, Model model, String indexKey) {
		try {
			Map<String, MethodItem> mis = model.getMethodItems();
			List<BeanItem> beanItems = model.getBeanItems();
			Object content = initData;
			// 去外面的标签
			List<Param> params = mis.get(indexKey).getParams();
			if (params == null || params.size() == 0) {
				content = MethodInvoker.invokeMethod(content, mis.get(indexKey)
						.getMethodName(), null);
			} else {
				List<Object> obs = new ArrayList<Object>();
				List<Class> cs = new ArrayList<Class>();
				for (Param param : params) {
					Object tmp = TypeConvertor.getInstance().convert(
							param.getType(), param.getValue());
					Class c = TypeConvertor.getInstance().getType(param.getType());
					if (tmp != null && c != null){
						obs.add(tmp);
						cs.add(c);
					}
				}
				content = MethodInvoker.invokeMethod(content, mis.get(indexKey)
						.getMethodName(),cs.toArray(new Class[]{}),obs.toArray());

			}
			return content;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

}
