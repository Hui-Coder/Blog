package dh.com.blog.utils;

/**
 * Created by King on 2018/3/17.
 */

public class UrlString {

    public  static final String BUNDLE_NEWS_URL = "news_url";
    public  static final String BUNDLE_NEWS_TYPE = "news_type";
    public  static final String HOT_NEWS  = "hot_news";
    public  static final String RECENT_NEWS  = "recent_news";
    public  static final String RECOMMEND_NEWS  = "recommend_news";
    public  static final String ALL_BLOGS  = "all_blogs";
    //页码
    public static final int NEWS_PAGE = 1;
    //消息数量
    public static final int NEWS_COUNT = 20;

    //第一次加载的消息数量
    public static final int NEWS_NUMBER = 5;



    //热门新闻
    public static final String HOT_NEWS_URL = "http://wcf.open.cnblogs.com/news/hot/";
    //最新新闻  分页格式：页码+显示量
    public static final String RECENT_NEWS_URL= "http://wcf.open.cnblogs.com/news/recent/paged/1/20";
    //推荐新闻 分页格式：页码+显示量
    public static final String RECOMMEND_NEWS_URL= "http://wcf.open.cnblogs.com/news/recommend/paged/1/20";
    //所有博客 分页格式：页码+显示量
    public static final String ALL_BLOGS_URL = "http://wcf.open.cnblogs.com/blog/sitehome/paged/1/20";


    //获取新闻内容
    public static final String URL_CONTENT_NEWS = "http://wcf.open.cnblogs.com/news/item/newsid";
    //获取新闻评论
    public static final String URL_COMMENTS_NEWS = "http://wcf.open.cnblogs.com/news/item/newsid/comments/index/size";
    //获取博客内容
    public static final String URL_CONTENT_BLOG = "http://wcf.open.cnblogs.com/blog/post/body/blogid";
    //获取博客评论
    public static final String URL_COMMENTS_BLOG = "http://wcf.open.cnblogs.com/blog/post/blogid/comments/index/size";


}
