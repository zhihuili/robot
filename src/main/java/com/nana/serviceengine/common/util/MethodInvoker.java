package com.nana.serviceengine.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MethodInvoker {
	/**
	 * 带着参数值执行方法
	 * @param target
	 * @param methodName
	 * @param arg
	 * @return
	 */
	public static Object invokeMethod(Object target,String methodName,Object...args){
		try {
			Method method;
			if(args == null || args.length == 0){
				method = target.getClass().getMethod(methodName);
				return method.invoke(target);
			}
			Class[] tmp = new Class[args.length];
			for(int i=0;i<args.length;i++){
				tmp[i] = args[i].getClass();
			}
			method = target.getClass().getMethod(methodName, tmp);
			return method.invoke(target, args);
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
	/**
	 * 带着参数值和参数值的类型执行方法，当参数有基本类型的时候适合用本方法
	 * @param target
	 * @param methodName
	 * @param arg
	 * @return
	 */
	public static Object invokeMethod(Object target,String methodName,Class[] types,Object...args){
		try {
			Method method;
			if(args == null || args.length == 0){
				method = target.getClass().getMethod(methodName);
				return method.invoke(target);
			}
			method = target.getClass().getMethod(methodName, types);
			return method.invoke(target, args);
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
