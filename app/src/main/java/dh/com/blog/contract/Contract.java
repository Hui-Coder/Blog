package dh.com.blog.contract;

import java.util.ArrayList;

import dh.com.blog.bean.Hot;
import dh.com.blog.utils.HttpCallBack;

/**
 * Created by King on 2018/3/17.
 */

public interface Contract {

    /**
     * 方法模型层
     */
    interface Model {
        void getSynchronized(String url, HttpCallBack httpCallBack);
    }

    /**
     * view视图层
     */
    interface View {
        void loaderFinshed(final ArrayList<Hot.EntryBean> hotList);
    }

    /**
     * 方法操作层,直接操作model获取数据，通过接口返回给view即可
     */
    interface Presenter {
        void getRequest(String url);
    }
}

