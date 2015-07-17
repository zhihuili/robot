package com.nana.serviceengine.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MethodInvoker {
	/**
	 * 处理带0-1个参数方法的调用
	 * @param target
	 * @param methodName
	 * @param arg
	 * @return
	 */
	public static Object invokeOneParamMethod(Object target,String methodName,Object arg){
		try {
			Method method;
			if(arg == null){
				method = target.getClass().getMethod(methodName);
				return method.invoke(target);
			}
			method = target.getClass().getMethod(methodName, arg.getClass());
			return method.invoke(target, arg);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
