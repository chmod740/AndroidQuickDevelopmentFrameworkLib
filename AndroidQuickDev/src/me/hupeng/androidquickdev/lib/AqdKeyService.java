package me.hupeng.androidquickdev.lib;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AqdKeyService {
	private static String salt = "hupeng@imudges.com";
	public static String Md5(String plainText) {
		StringBuffer buf = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			// System.out.println("result: " + buf.toString());//32位的加密

			// System.out.println("result: " +
			// buf.toString().substring(8,24));//16位的加密

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buf.toString();
	}
	
	
	public static String encryptKey(String password){
		return Md5(Md5(password)+"&"+salt);
	}
}
