package com.name.project.common.utils;
/**   
 * @author ft
 * @version 1.0
 * @date 2015年11月13日 下午9:03:42 
 *
 */
public class StringUtils {

	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str != null && !str.trim().equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
}
