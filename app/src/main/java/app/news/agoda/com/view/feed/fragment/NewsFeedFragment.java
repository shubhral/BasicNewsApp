package app.news.agoda.com.view.feed.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import app.news.agoda.com.MyApplication;
import app.news.agoda.com.R;
import app.news.agoda.com.dagger.component.DaggerNewFeedActivityComponent;
import app.news.agoda.com.dagger.component.NewFeedActivityComponent;
import app.news.agoda.com.dagger.module.ActivityModule;
import app.news.agoda.com.dagger.module.NewsFeedFragmentModule;
import app.news.agoda.com.util.PermissionUtils;
import app.news.agoda.com.view.adapter.NewsFeedClickListener;
import app.news.agoda.com.view.adapter.NewsListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import domain.news.agoda.com.model.NewsResponseDomain;

public class NewsFeedFragment extends Fragment implements NewsFeedFragView, NewsFeedClickListener {

    private Callbacks mCallbacks = sDummyCallbacks;

    public interface Callbacks {
        void onItemSelected(NewsResponseDomain id);
    }

    @BindView(R.id.rv_news_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar_news_feed)
    ProgressBar mProgressBar;

    @Inject
    NewsFeedFragmentPresenter newsFeedFragmentPresenter;
    @Inject
    NewsListAdapter mAdapter;
    @Inject
    @Named("activity_context")
    Context context;

    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(NewsResponseDomain id) {
        }
    };

    public NewsFeedFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_item_list, container, false);
        ButterKnife.bind(this, rootView);
        NewFeedActivityComponent mainActivityComponent = DaggerNewFeedActivityComponent.builder()
                .activityModule(new ActivityModule(getActivity()))
                .newsFeedFragmentModule(new NewsFeedFragmentModule(this))
                .newsFeedComponent(MyApplication.get(getActivity()).getNewFeedApplicationComponent())
                .build();
        mainActivityComponent.injectNewsFeedFragment(this);
        if (PermissionUtils.checkInternetPermission(getContext())) {
            newsFeedFragmentPresenter.getNewsFeeds();
        } else {
            Toast.makeText(getContext(), "Internet permission not granted", Toast.LENGTH_LONG).show();
        }
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException(activity.getString(R.string.exception_msg));
        }
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void newsFeedResponse(List<NewsResponseDomain> results) {
        if (results != null && results.size() > 0) {
            initializeAdapter(results);
        } else {
            Toast.makeText(getContext(), getString(R.string.no_results), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(android.view.View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    public void initializeAdapter(List<NewsResponseDomain> newsFeeds) {
        LinearLayoutManager linearLayoutManagerForNewsFeedList = new LinearLayoutManager(getActivity());
        linearLayoutManagerForNewsFeedList.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManagerForNewsFeedList);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setClickListener(this);
        mAdapter.setNewsFeedModel(newsFeeds);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setVerticalScrollBarEnabled(false);
        mRecyclerView.setHorizontalScrollBarEnabled(false);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void listItemClicked(NewsResponseDomain result) {
        mCallbacks.onItemSelected(result);
    }
}
