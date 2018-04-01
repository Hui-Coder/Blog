package dh.com.blog.model;

import java.io.IOException;

import dh.com.blog.contract.Contract;
import dh.com.blog.utils.HttpCallBack;
import dh.com.blog.utils.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by King on 2018/3/17.
 */

public class ModelImpl implements Contract.Model {

    private static final String TAG = "ModelImpl";
    private static  ModelImpl intance = new ModelImpl();

    public static ModelImpl getIntance(){
        if (intance == null){
            intance = new ModelImpl();
        }
        return intance;
    }

    @Override
    public void getSynchronized(String url,final HttpCallBack httpCallBack) {
        HttpUtil.sendHttpResponse(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpCallBack.result(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    httpCallBack.result(response.body().string());
                }
            }
        });
    }

}
