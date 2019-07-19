package app.news.agoda.com.view.adapter;

import app.news.agoda.com.domain.model.NewsResponseDomain;

public interface NewsFeedClickListener {
    void listItemClicked(NewsResponseDomain result);
}
