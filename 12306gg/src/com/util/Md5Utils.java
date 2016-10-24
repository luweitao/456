package com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//M5加密实现类
public class Md5Utils {
	public final static String md5(String s){
		/*密码字段使用字符*/
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		try {
			byte[] btInput=s.getBytes();
			//创建M5对象，getInstance()函数保证一个类有且只有一个实例
			MessageDigest mdInst=MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			//生成M5字节数组
			byte[] md=mdInst.digest();
			int j=md.length;
			char str[]=new char[j*2];
			int k=0;
			//Md5算法的计算步骤
			for (int i = 0; i < j; i++) {
				byte byte0=md[i];
				str[k++]=hexDigits[byte0>>>4&0xf];
				str[k++]=hexDigits[byte0&0xf];
			}
			return new String(str);
			
			
		
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
