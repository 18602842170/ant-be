package com.ant.be.utils;

import com.ant.be.support.CollectionKit;
import com.ant.be.support.StrKit;

/**
 * 类型转换器
 * 
 */
public class Convert {
	private Convert() {
		// 静态类不可实例化
	}
	/**
	 * 转换为Integer数组<br>
	 * 
	 * @param split 被转换的值
	 * @return 结果
	 */
	public static Integer[] toIntArray(String str) {
		return toIntArray(",", str);
	}
	
	/**
	 * 转换为Integer数组<br>
	 * 
	 * @param split 分隔符
	 * @param split 被转换的值
	 * @return 结果
	 */
	public static Integer[] toIntArray(String split, String str) {
		if (StrKit.isEmpty(str)) {
			return new Integer[] {};
		}
		String[] arr = str.split(split);
		final Integer[] ints = new Integer[arr.length];
		for (int i = 0; i < arr.length; i++) {
			final Integer v = toInt(arr[i], 0);
			ints[i] = v;
		}
		return ints;
	}
	
	/**
	 * 转换为int<br>
	 * 如果给定的值为空，或者转换失败，返回默认值<br>
	 * 转换失败不会报错
	 * 
	 * @param value 被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static Integer toInt(Object value, Integer defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		if (value instanceof Integer) {
			return (Integer) value;
		}
		if (value instanceof Number) {
			return ((Number) value).intValue();
		}
		final String valueStr = toStr(value, null);
		if (StrKit.isBlank(valueStr)) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(valueStr.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * 转换为字符串<br>
	 * 如果给定的值为null，或者转换失败，返回默认值<br>
	 * 转换失败不会报错
	 * 
	 * @param value 被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static String toStr(Object value, String defaultValue) {
		if (null == value) {
			return defaultValue;
		}
		if (value instanceof String) {
			return (String) value;
		} else if (CollectionKit.isArray(value)) {
			return CollectionKit.toString(value);
		}
		return value.toString();
	}
}
