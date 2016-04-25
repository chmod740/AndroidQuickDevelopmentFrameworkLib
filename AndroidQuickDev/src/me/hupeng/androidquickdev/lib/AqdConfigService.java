package me.hupeng.androidquickdev.lib;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


/**
 * 配置服务类
 * */
public class AqdConfigService {
	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;
	private Context context;
	/**
	 * 构造函数，进行初始化操作
	 * */
	public AqdConfigService(Context context){
		sharedPreferences = context.getSharedPreferences("aqdConfig", Activity.MODE_PRIVATE);
		editor = sharedPreferences.edit();
	}
	
	private String readString(String key){
		return sharedPreferences.getString(key, null);
	}
	
	private void writeString(String key,String value){
		editor.putString(key, value);
		editor.commit();
	}
	
	
	public String readURL(){
		return readString("URL");
	}
	
	public void writeURL(String URL){
		writeString("URL", URL);
	}
	
	public String readKEY(){
		return readString("KEY");
	}
	
	public void writeKEY(String KEY){
		writeString("KEY", KEY);
	}
	
}
