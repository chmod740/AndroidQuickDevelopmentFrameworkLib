package me.hupeng.androidquickdev.lib;

import android.content.Context;

/**
 * 初始化用类
 * */
public class Aqd {
	
	/**
	 * 初始化函数
	 * */
	public static void initialize(Context context,String url,String key){
		AqdConfigService aqdConfigService = new AqdConfigService(context);
		aqdConfigService.writeKEY(AqdKeyService.encryptKey(key));
		aqdConfigService.writeURL(url);
		AqdHttpRequester.setBaseUrl(url);
	}
}
