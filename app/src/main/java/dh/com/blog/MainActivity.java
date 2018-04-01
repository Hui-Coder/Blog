package dh.com.blog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import dh.com.blog.utils.BottomNavigationViewHelper;
import dh.com.blog.utils.UrlString;
import dh.com.blog.view.NewsFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView message ;
    private Bundle bundle = new Bundle();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_hot:
                     StartNewsFragment(UrlString.HOT_NEWS,UrlString.HOT_NEWS_URL);
                    return true;
                case R.id.navigation_recent:
                     StartNewsFragment(UrlString.RECENT_NEWS,UrlString.RECENT_NEWS_URL);
                    return true;
                case R.id.navigation_recommend:
                    StartNewsFragment(UrlString.RECOMMEND_NEWS,UrlString.RECOMMEND_NEWS_URL);
                    return true;
                case R.id.navigation_blog:
                    StartNewsFragment(UrlString.ALL_BLOGS,UrlString.ALL_BLOGS_URL);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        message = (TextView) findViewById(R.id.message);
        BottomNavigationViewHelper.disableShiftMode(navigation);


        StartNewsFragment(UrlString.HOT_NEWS,UrlString.HOT_NEWS_URL);
    }


    private void StartNewsFragment(String newType, String url){
        bundle.putString(UrlString.BUNDLE_NEWS_URL,url); //
        bundle.putString(UrlString.BUNDLE_NEWS_TYPE,newType);
        NewsFragment newsFragment = new NewsFragment();
        newsFragment.setArguments(bundle);
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newsFragment).commit();
    }

}
