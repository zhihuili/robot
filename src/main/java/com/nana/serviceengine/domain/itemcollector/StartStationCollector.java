package com.nana.serviceengine.domain.itemcollector;

import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Term;
import com.nana.serviceengine.common.bean.DomainKeyWord;
import com.nana.serviceengine.common.bean.UserMessage;
import com.nana.serviceengine.common.dic.DomainDic;
import com.nana.serviceengine.neuron.itemcollector.Collector;
import com.nana.serviceengine.neuron.processor.ServiceProcessor;

public class StartStationCollector implements Collector<String[]> {

	  private static  final StartStationCollector ssc = new StartStationCollector();
	  
	  private StartStationCollector() {
		  
	   }
	  
	   public static StartStationCollector getInstance(){
	        return ssc;	   
	   }
	   /**
	    * 
	    * @param terms 根据分词结找到起始站和终点站
	    * @return
	    */
	   public static String[] parsestation(List<Term> terms){
		   List<String> res = new ArrayList<String>();
		   
		   for(int i=0; i < terms.size(); i++){
			  DomainKeyWord dkw = DomainDic.domainKeyWord.get(terms.get(i).getRealName());   
			   if(dkw == null)
				   continue;
			    if(terms.get(i).getRealName().equals("从")){//起始站
			    	 DomainKeyWord dkw1 =	DomainDic.domainKeyWord.get(terms.get(i+1).getRealName());
			    	if("address".equals(dkw1.getDomain())){//判断是否是一个地名
						   res.add(dkw.getValue());
						   i++;//往后移一位
					   }
			    }
			    if(!terms.get(i).getRealName().equals("到")){//起始站
			    	 DomainKeyWord dkw1 =	DomainDic.domainKeyWord.get(terms.get(i+1).getRealName());
			    	if("address".equals(dkw1.getDomain())){//判断是否是一个地名
						   res.add(dkw.getValue());
						   i++;//往后移一位
					   }
			    }	
			    if(!terms.get(i).getRealName().equals("去")){//起始站
			    	 DomainKeyWord dkw1 =	DomainDic.domainKeyWord.get(terms.get(i+1).getRealName());
			    	if("address".equals(dkw1.getDomain())){//判断是否是一个地名
						   res.add(dkw.getValue());
						   i++;//往后移一位
					   }
			    }	
			   if(res.size() != 0){
				   return res.toArray(new String[]{});
			   }
		   }
		   return null;
	   }
	   
		@Override
		public String[] getParam(UserMessage message, ServiceProcessor processor) {
			return parsestation(message.getTerms());
		}
}
