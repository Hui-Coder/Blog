package dh.com.blog.utils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by King on 2018/3/29.
 */

public class HttpUtil {


    public static OkHttpClient client= OkHttp3.getInstance(); //通过单例拿到OkHttpClient实例
    public static  void sendHttpResponse(String url, Callback callback){
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);

    }
}
