package me.hupeng.androidquickdev.lib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.http.Header;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.R.integer;
import android.content.Context;


/**
 * 所有派生类的基类
 * @author HUPENG
 * */
public class AqdObject {
	private String id;
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void save(Context context,final AqdSaveListener aqdSaveListener){
		
		/**
		 * 反射，调用get方法
		 * */
		AqdJsonModel aqdJsonModel = new AqdJsonModel();
		Class myClass = this.getClass();
		//设置table名称
		aqdJsonModel.setTableName(myClass.getSimpleName());
		Method[] methods = myClass.getMethods();
		for(Method method : methods){
			//判断该方法是否要被反射,只反射get*的方法
			String methodName = method.getName();
			if (methodName.length() > 3) {
				//得到前三个字符
				if (methodName.substring(0, 3).equals("get")) {
					if (!methodName.substring(3).equals("Class")) {
						try {
							Object object = method.invoke(this, null);
							/**
							 * 目前支持类型：int , Integer , double ,Double , Boolean ,boolean 
							 * java.Util.Date
							 * */
							if (object == null) {
								continue; 
							}
							
							if (method.getReturnType().equals(int.class) || method.getReturnType().equals(Integer.class)) {
								AqdSimpleKVModel aqdSimpleKVModel = new AqdSimpleKVModel();
								aqdSimpleKVModel.setKey(methodName.substring(3));
								aqdSimpleKVModel.setValue(((Integer)method.invoke(this, null))+"");
								aqdJsonModel.getList().add(aqdSimpleKVModel);
							}
							if (method.getReturnType().equals(String.class)) {
								AqdSimpleKVModel aqdSimpleKVModel = new AqdSimpleKVModel();
								aqdSimpleKVModel.setKey(methodName.substring(3));
								aqdSimpleKVModel.setValue((String)method.invoke(this, null));
								aqdJsonModel.getList().add(aqdSimpleKVModel);
							}
							if (method.getReturnType().equals(double.class) || method.getReturnType().equals(Double.class)) {
								AqdSimpleKVModel aqdSimpleKVModel = new AqdSimpleKVModel();
								aqdSimpleKVModel.setKey(methodName.substring(3));
								aqdSimpleKVModel.setValue(((Double)method.invoke(this, null))+"");
								aqdJsonModel.getList().add(aqdSimpleKVModel);
							}
							if (method.getReturnType().equals(Boolean.class) || method.getReturnType().equals(boolean.class)) {
								AqdSimpleKVModel aqdSimpleKVModel = new AqdSimpleKVModel();
								aqdSimpleKVModel.setKey(methodName.substring(3));
								aqdSimpleKVModel.setValue(((Boolean)method.invoke(this, null))==true?"1":"0");
								aqdJsonModel.getList().add(aqdSimpleKVModel);
							}
							if (method.getReturnType().equals(Date.class)) {
								AqdSimpleKVModel aqdSimpleKVModel = new AqdSimpleKVModel();
								aqdSimpleKVModel.setKey(methodName.substring(3));
								aqdSimpleKVModel.setValue(((Date)method.invoke(this, null)).getTime() + "");
								aqdJsonModel.getList().add(aqdSimpleKVModel);
							}
							
							
							
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
							aqdSaveListener.onFailure("异常被抛出" + e.getMessage());
							return;
						}
					}
				}
			}
		}
		//aqdSaveListener.onFailure("成功" + new Gson().toJson(aqdJsonModel));
		
		RequestParams params = new RequestParams();
		params.add("json", new Gson().toJson(aqdJsonModel));
		AqdHttpRequester.get(context, "save.action", params, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				// TODO Auto-generated method stub
				aqdSaveListener.onFailure(new String(arg2));
				return;
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				// TODO Auto-generated method stub
				aqdSaveListener.onFailure("网络请求失败" + arg3.getMessage());
			}
		});
	}
}
