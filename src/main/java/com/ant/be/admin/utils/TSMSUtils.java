package com.ant.be.admin.utils;

public class TSMSUtils {

	/**
	 * 字符串非空检查
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static boolean isEmpty(String input) throws Exception {
		
		if(input != null && !"".equals(input)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 生成sql用like参数
	 * 
	 * @param _inParama
	 * @return
	 * @throws Exception
	 */
	public static String creaetLikeParama(String _inParama)  throws Exception {
		
		if(_inParama != null && !"".equals(_inParama)) {
			return "%" + _inParama + "%";
		}
		
		return "%%";
	}
	
}
