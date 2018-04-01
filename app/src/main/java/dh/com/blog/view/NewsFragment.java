package dh.com.blog.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import dh.com.blog.R;
import dh.com.blog.bean.Hot;
import dh.com.blog.contract.Contract;
import dh.com.blog.presenter.PresenterImpl;
import dh.com.blog.utils.NewsAdapter;
import dh.com.blog.utils.UrlString;
import dh.com.blog.utils.XmlUtils;

/**
 * Created by King on 2018/3/31.
 */

public class NewsFragment extends Fragment implements Contract.View{

    private static final String TAG = "NewsFragment";

    private RecyclerView rv_hot_news;
    private NewsAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PresenterImpl presenter ;
    private ArrayList<Hot.EntryBean> dataList;
    private ProgressDialog dialog;
    private  LinearLayoutManager llM;
    private  String url,type;
    private int moreNum ;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.news_fragment, container,false);
        rv_hot_news = (RecyclerView)view.findViewById(R.id.rv_hot_news);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        llM = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //设置RecyclerView 布局
        rv_hot_news.setLayoutManager(llM);
        dataList = new ArrayList<Hot.EntryBean>();
        /**
         * get local data first, then reqeust from server.
         */
        type = getArguments().getString(UrlString.BUNDLE_NEWS_TYPE);
        presenter = new PresenterImpl(getActivity(),this,type);
        String hotNewsString = presenter.loadDataFormLocal(type);
        if (hotNewsString != null){
            dataList = XmlUtils.parseXml(hotNewsString);
            Log.d(TAG,"local data is not null");
        }else{
            Log.d(TAG,"local data is  null");
            showProgressDialog();
            requestHotNews();
        }
        adapter = new NewsAdapter(dataList, getActivity());
        rv_hot_news.setAdapter(adapter);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rv_hot_news.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    if (llM.findLastVisibleItemPosition() >= llM.getItemCount()-1){
                        Log.d(TAG, "onScrollStateChanged: findLastVisibleItemPosition:" +llM.findLastVisibleItemPosition());
                        loadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        /**
         * 刷新数据
         */
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestHotNews();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void loaderFinshed(final ArrayList<Hot.EntryBean> result) {
        getActivity().runOnUiThread(new Runnable() {       //okhttp拿到的是异步数据。需要在主线程中更新RecycleView
            @Override
            public void run() {
                if (result == null) {
                    Toast.makeText(getActivity(), "获取数据为空", Toast.LENGTH_SHORT).show();
                } else {
                    dataList.clear();
                    dataList.addAll(result);
                    adapter.notifyDataSetChanged();
                    closeProgressDialog();
                    Log.d(TAG, "request data finished");
                }
            }
        });
    }


    private void loadMore(){
        Toast.makeText(getActivity(), "load more",Toast.LENGTH_SHORT).show();
        moreNum += 5;
        int num = UrlString.NEWS_NUMBER + moreNum;  //迭代修改加载数量
        Log.d(TAG, "onScrollStateChanged: moreNum:"+ moreNum );
        url = getArguments().getString(UrlString.BUNDLE_NEWS_URL) +num;
        presenter.getRequest(url);
    }

    private void showProgressDialog(){
        if (dialog == null){
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("正在加载...");
            dialog.show();
        }

    }

    private void closeProgressDialog(){
        if (dialog !=null){
            dialog.dismiss();
        }
    }

    /**
     * request data from server
     */
    private void requestHotNews(){
        url = getArguments().getString(UrlString.BUNDLE_NEWS_URL)+UrlString.NEWS_NUMBER;
        presenter.getRequest(url);
    }
}
