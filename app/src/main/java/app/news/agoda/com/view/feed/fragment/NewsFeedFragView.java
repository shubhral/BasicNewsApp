package app.news.agoda.com.view.feed.fragment;

import domain.news.agoda.com.model.NewsResponseDomain;

import java.util.List;

import app.news.agoda.com.view.BaseView;

public interface NewsFeedFragView extends BaseView {
    void newsFeedResponse(List<NewsResponseDomain> results);
}
