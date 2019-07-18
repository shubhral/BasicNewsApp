package app.news.agoda.com.dagger.component;

import dagger.Component;
import app.news.agoda.com.dagger.module.ActivityModule;
import app.news.agoda.com.dagger.module.NewsFeedFragmentModule;
import app.news.agoda.com.dagger.scope.MainActivityScope;
import app.news.agoda.com.view.feed.fragment.NewsFeedFragment;

@Component(modules = {NewsFeedFragmentModule.class, ActivityModule.class}, dependencies = NewsFeedComponent.class)
@MainActivityScope
public interface NewFeedActivityComponent {

    void injectNewsFeedFragment(NewsFeedFragment newsFeedFragment);
}
