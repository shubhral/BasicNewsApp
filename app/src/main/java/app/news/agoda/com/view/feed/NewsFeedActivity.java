package app.news.agoda.com.view.feed;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import app.news.agoda.com.R;
import app.news.agoda.com.domain.model.NewsResponseDomain;
import app.news.agoda.com.view.BundleKeys;
import app.news.agoda.com.view.detail.NewsDetailActivity;
import app.news.agoda.com.view.detail.fragment.NewsDetailFragment;
import app.news.agoda.com.view.feed.fragment.NewsFeedFragment;

public class NewsFeedActivity extends FragmentActivity implements NewsFeedFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed_list);
        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }
    }

    @Override
    public void onItemSelected(NewsResponseDomain result) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(BundleKeys.RESULT, result);
            NewsDetailFragment fragment = new NewsDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, fragment).commit();

        } else {
            if (result != null) {
                Intent detailIntent = new Intent(this, NewsDetailActivity.class);
                detailIntent.putExtra(BundleKeys.RESULT, result);
                startActivity(detailIntent);
            }
        }

    }
}
