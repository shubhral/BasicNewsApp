package app.news.agoda.com.view.feed.fragment;

import java.util.List;

import app.news.agoda.com.domain.model.NewsResponseDomain;
import app.news.agoda.com.view.BaseView;

public interface NewsFeedView extends BaseView {
    void newsFeedResponse(List<NewsResponseDomain> results);
}
