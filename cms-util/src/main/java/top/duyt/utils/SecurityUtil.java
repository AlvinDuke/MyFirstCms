package top.duyt.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {
	
	/**
	 * 生成指定加密算法生成的加密字符串
	 * @param targetStr 待加密字符串
	 * @param algorithm 加密算法
	 * @param radix 加密后字符串的进制
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getEncryptString(String targetStr,String algorithm,int radix) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance(algorithm);
		md.update(targetStr.getBytes(), 0, targetStr.length());
		BigInteger bi = new BigInteger(1,md.digest());
		return bi.toString(radix);
	}

}
