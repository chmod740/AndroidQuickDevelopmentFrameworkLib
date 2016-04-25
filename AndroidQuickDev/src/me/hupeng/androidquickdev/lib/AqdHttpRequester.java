package me.hupeng.androidquickdev.lib;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class AqdHttpRequester {
	/**
     * ��������,�˿ں���Ӧ������ɵĻ�����URL
     * */
    private static final String BASE_URL = "";
    private static AsyncHttpClient client = new AsyncHttpClient();

    /**
     * ����get����ķ���<br/>
     * @param context ������
     * @param url ����url��ַ
     * @param params ��װ�������������
     * @param responseHandler �ص��ӿ�
     * */
    public static void get(Context context,String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
    	client.get(context,getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * ����post����ķ���<br/>
     * @param context ������
     * @param url ����url��ַ
     * @param params ��װ�������������
     * @param responseHandler �ص��ӿ�
     * */
    public static void post(Context context,String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(context,getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * �ɻ�����url+����url ����ɵ�������url��ַ</br>
     * @param relativeUrl ����url��ַ
     * @return ��ɵ������������õ�url��ַ
     * */
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
