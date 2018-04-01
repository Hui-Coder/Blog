package dh.com.blog.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;

import dh.com.blog.bean.Hot;
import dh.com.blog.contract.Contract;
import dh.com.blog.model.ModelImpl;
import dh.com.blog.utils.HttpCallBack;
import dh.com.blog.utils.XmlUtils;

/**
 * Created by King on 2018/3/17.
 */

public class PresenterImpl implements Contract.Presenter {


    private static  final  String LogString = "Blog" ;
    private ModelImpl model;
    private Context context;
    private Contract.View view;
    private String newsType;

    public PresenterImpl(Context context, Contract.View view,String newsType) {
        this.context = context;
        this.view = view;
        this.newsType = newsType;
    }


    @Override
    public void getRequest(String url) {
        model = ModelImpl.getIntance();
        model.getSynchronized(url, new HttpCallBack() {
            @Override
            public void result(String result) {
                Log.d(LogString, "requested data from url：" + result);
                saveServerData(newsType,result);
                ArrayList<Hot.EntryBean> list = XmlUtils.parseXml(result);
                view.loaderFinshed(list);
            }
        });
    }


    private void saveServerData(String newsType, String data){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(newsType, data);
        editor.apply();
    }

    public String  loadDataFormLocal(String newsType){
        SharedPreferences prefs =PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(newsType, null);
    }
}
