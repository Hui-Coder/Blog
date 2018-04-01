package dh.com.blog.utils;

import okhttp3.OkHttpClient;

/**
 * Created by King on 2018/3/18.
 */

public class OkHttp3 {

    //OkHttpClient 对象单例模式，不存在多个对象，造成资源浪费

    private static OkHttpClient intance= new OkHttpClient();

    public static OkHttpClient getInstance(){
        if (intance == null){
            intance = new OkHttpClient();
        }
        return  intance;
    }

}
