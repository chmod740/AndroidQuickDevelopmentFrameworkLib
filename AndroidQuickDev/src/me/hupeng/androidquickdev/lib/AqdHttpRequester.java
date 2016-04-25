package me.hupeng.androidquickdev.lib;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class AqdHttpRequester {
	/**
     * 服务器地址,端口号与应用名组成的基本的URL
     * */
    private static String BASE_URL = null;
    private static AsyncHttpClient client = new AsyncHttpClient();
    
    public static void setBaseUrl(String baseUrl){
    	BASE_URL = baseUrl;
    }
    
    
    /**
     * 发送get请求的方法<br/>
     * @param context 上下文
     * @param url 部分url地址
     * @param params 封装了请求参数的类
     * @param responseHandler 回调接口
     * */
    public static void get(Context context,String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
    	client.get(context,getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * 发送post请求的方法<br/>
     * @param context 上下文
     * @param url 部分url地址
     * @param params 封装了请求参数的类
     * @param responseHandler 回调接口
     * */
    public static void post(Context context,String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(context,getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * 由基本的url+部分url 所组成的完整的url地址</br>
     * @param relativeUrl 部分url地址
     * @return 组成的完整的请求用的url地址
     * */
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
