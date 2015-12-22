package com.name.project.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author ft
 * @version 1.0
 * @date 2015年12月3日 下午4:15:14
 *
 */
public class DesUtils {

	private final static String DEFAULT_KEY = "@@IsDefaultKey20151203@@";

	private final static String DEFAULT_CHARSET = "gbk";

	private final static String DEFAULT_ALGORITHM = "DESede"; // 定义加密算法,可用DES,DESede,Blowfish

	/** 加密工具 */
	private Cipher encryptCipher = null;
	/** 解密工具 */
	private Cipher decryptCipher = null;

	public DesUtils() {
		this(DEFAULT_KEY);
	}

	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public byte[] encrypt(byte[] sourceByte) {
		try {
			return encryptCipher.doFinal(sourceByte);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	public String encrypt(String sourceStr) {
		try {
			return byteArr2HexStr(encrypt(sourceStr.getBytes()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	public byte[] decrypt(byte[] sourceByte) {
		try {

			return decryptCipher.doFinal(sourceByte);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String decrypt(String sourceStr) {
		try {
			return new String(decrypt(hexStr2ByteArr(sourceStr)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public DesUtils(String strKey) {
		
		if (strKey == null || strKey.length() != 24) {
			throw new IllegalArgumentException();
		}
		byte[] keyBytes = null;
		try {
			keyBytes = strKey.getBytes(DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Key key = new SecretKeySpec(keyBytes, DEFAULT_ALGORITHM);

		try {
			encryptCipher = Cipher.getInstance(DEFAULT_ALGORITHM);
			encryptCipher.init(Cipher.ENCRYPT_MODE, key);

			decryptCipher = Cipher.getInstance(DEFAULT_ALGORITHM);
			decryptCipher.init(Cipher.DECRYPT_MODE, key);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
		DesUtils des = new DesUtils();
		System.out.println(des.encrypt("1234567890123456你好!。@#@"));
		System.out.println(des.decrypt(des.encrypt("1234567890123456你好!。@#@")));

	}
}
