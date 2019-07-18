package app.news.agoda.com.view.adapter;

import domain.news.agoda.com.model.NewsResponseDomain;

public interface NewsFeedClickListener {
    void listItemClicked(NewsResponseDomain result);
}
