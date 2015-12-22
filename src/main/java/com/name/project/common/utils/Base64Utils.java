package com.name.project.common.utils;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**   
 * @author ft
 * @version 1.0
 * @date 2015年12月5日 下午2:11:24 
 *
 */
public class Base64Utils {

	public static String encode(String sourceStr) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(sourceStr.getBytes());
	}
	
	public static String decode(String sourceStr) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			return new String(decoder.decodeBuffer(sourceStr));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String [] args) {
		System.out.println(Base64Utils.encode("helloworld你好世界"));
		System.out.println(Base64Utils.decode(Base64Utils.encode("helloworld你好世界")));
	}
}
