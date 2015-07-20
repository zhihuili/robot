package com.nana.serviceengine.daemonthread;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import com.nana.serviceengine.common.bean.UserDialog;
import com.nana.serviceengine.common.cacher.UserTheme;
/**
 * 这个线程用来收集对话缓存中过期的对话，在程序加载时要启动
 * @author wds
 *
 */
public class OutDateThemeGC implements Runnable {

	@Override
	public void run() {
		while (true) {
			long curTime = new Date().getTime();
			Iterator<Map.Entry<String, UserDialog>> it = UserTheme.UserDialog
					.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, UserDialog> entry = it.next();
				if (curTime - entry.getValue().getLastDialog().getTime() > 300000) {
					it.remove();
				}
			}
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
