package com.nana.serviceengine.searcher.util;

public class TypeConvertor {
	private static TypeConvertor tc = new TypeConvertor();

	private TypeConvertor() {
	}

	public static TypeConvertor getInstance() {
		return tc;
	}

	/**
	 * 根据字符串指定的类型，把value转换为包装类，可能没有要的类型，使用前检查一下
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	public Object convert(String type, String value) {
		try {
			if ("Integer".equals(type)) {
				return Integer.parseInt(value);
			}
			if ("Double".equals(type)) {
				return Double.parseDouble(value);
			}
			if("int".equals(type)){
				return Integer.parseInt(value);
			}
		} catch (Exception ex) {
			return null;
		}
		return value;
	}
	
	public Class getType(String type) {
		try {
			if ("Integer".equals(type)) {
				return Integer.class;
			}
			if ("Double".equals(type)) {
				return Double.class;
			}
			if("int".equals(type)){
				return Integer.TYPE;
			}
			return String.class;
		} catch (Exception ex) {
			return null;
		}
	}

	public Object convertByRegex(String value, String regex) {
		try {
			String[] tmp = value.split(regex);
			return convert(tmp[0], tmp[1]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
