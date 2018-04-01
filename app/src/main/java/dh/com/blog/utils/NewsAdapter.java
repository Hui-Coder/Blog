package dh.com.blog.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dh.com.blog.R;
import dh.com.blog.bean.Hot;
import dh.com.blog.view.CustomImageViewWithText;

/**
 * Created by King on 2018/3/18.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private static final String LogString = "Blog";
    private ArrayList<Hot.EntryBean> hotList;
    private Context context;
    private Hot.EntryBean entryBean = new Hot.EntryBean();

    public NewsAdapter(ArrayList<Hot.EntryBean> hotList, Context context) {
        this.hotList = hotList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_fragment_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        entryBean = hotList.get(position);
        holder.title.setText(entryBean.getTitle());
        holder.summary.setText(entryBean.getSummary());
        holder.published_time.setText(entryBean.getPublished());
        holder.views.setText(entryBean.getViews()+"");
        holder.comments.setText(entryBean.getComments()+"");

    }

    @Override
    public int getItemCount() {
        return hotList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView title, summary, published_time;
        ImageView topic_icon;
        CustomImageViewWithText views, comments;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            summary = (TextView) itemView.findViewById(R.id.summary);
            published_time = (TextView) itemView.findViewById(R.id.published_time);
            topic_icon = (ImageView) itemView.findViewById(R.id.topic_icon);
            views = (CustomImageViewWithText) itemView.findViewById(R.id.views);
            comments = (CustomImageViewWithText) itemView.findViewById(R.id.comments);

        }
    }
}
