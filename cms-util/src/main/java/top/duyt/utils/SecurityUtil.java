package top.duyt.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {
	
	/**
	 * 生成指定加密算法生成的或带有盐值的加密字符串
	 * @param salt 盐值
	 * @param targetStr 待加密字符串
	 * @param algorithm 加密算法
	 * @param radix 加密后字符串的进制
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getEncryptString(String salt,String targetStr,String algorithm,int radix) throws NoSuchAlgorithmException{
		
		MessageDigest md = MessageDigest.getInstance(algorithm);
		
		String defaultStr = "";
		
		if(targetStr!=null&&!targetStr.equals("")){
			defaultStr = targetStr;
		}
		
		if(salt!=null&&!salt.equals("")){
			md.update(salt.getBytes());
		}
		
		md.update(defaultStr.getBytes());
		
		BigInteger bi = new BigInteger(1,md.digest());
		return bi.toString(radix);
	}

}
