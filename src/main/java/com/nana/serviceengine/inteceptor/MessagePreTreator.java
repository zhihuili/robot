package com.nana.serviceengine.inteceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ansj.domain.Term;

import com.nana.serviceengine.bean.DomainKeyWord;
import com.nana.serviceengine.dic.DomainDic;
import com.nana.serviceengine.util.ListDeepCopyer;

public class MessagePreTreator {
	private static MessagePreTreator ppt = new MessagePreTreator();

	private MessagePreTreator() {
	}

	public static MessagePreTreator getInstance() {
		return ppt;
	}
	
	public String[] getDomainKeys(String[] sortedWords){
		try{
			List<String> domains = new ArrayList<String>();
			for(int i = 0;i<sortedWords.length;i++){
				DomainKeyWord tmp  = DomainDic.domainKeyWord.get(sortedWords[i]);
				if(tmp!=null){
					domains.add(tmp.getDomain());
				}
			}
			return domains.toArray(new String[]{});
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取以排序的分词词组 
	 * @param terms
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public String[] getSortedWords(List<Term> terms)
			throws ClassNotFoundException, IOException {
		try {
			deleWTag(terms);
			deleUJWords(terms);
			int termSize = terms.size();
			List<Term> tmp = ListDeepCopyer.copy(terms);
			List<Term> tmp1 = new ArrayList<Term>(); 
			String[] keyWords = new String[termSize];
			Iterator iterator = tmp.iterator();
			while(iterator.hasNext()){
				Term term = (Term) iterator.next();
				if("n".equals(term.getNatureStr())){
					tmp1.add(term);
					iterator.remove();
				}
			}
			for(int i=0;i<tmp1.size();i++){
				keyWords[i] = tmp1.get(i).getRealName();
			}
			for(int i=0;i<tmp.size();i++){
				keyWords[i+tmp1.size()] = tmp.get(i).getRealName();		
			}
			return keyWords;
		} catch (Exception ex) {
			//TODO log
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除标点符号 并返回句子中名词的数量
	 * 
	 * @param terms
	 * @return
	 */
	private int deleWTag(List<Term> terms) {
		int count = 0;
		Iterator it = terms.iterator();
		while (it.hasNext()) {
			Term t = (Term) it.next();
			if ("w".equals(t.getNatureStr())) {
				it.remove();
			}
			if ("n".equals(t.getNatureStr())) {
				count++;
			}
		}
		return count;
	}
	
	private void deleUJWords(List<Term> terms){
		Iterator it = terms.iterator();
		while (it.hasNext()) {
			Term t = (Term) it.next();
			if ("uj".equals(t.getNatureStr())) {
				it.remove();
			}
		}
	}
	
	

}
