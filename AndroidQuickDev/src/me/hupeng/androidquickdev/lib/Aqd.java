package me.hupeng.androidquickdev.lib;

import android.content.Context;

/**
 * ��ʼ������
 * */
public class Aqd {
	
	/**
	 * ��ʼ������
	 * */
	public static void initialize(Context context,String url,String key){
		AqdConfigService aqdConfigService = new AqdConfigService(context);
		aqdConfigService.writeKEY(AqdKeyService.encryptKey(key));
		aqdConfigService.writeURL(url);
		AqdHttpRequester.setBaseUrl(url);
	}
}
