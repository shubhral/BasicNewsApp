package app.news.agoda.com.dagger.module;

import dagger.Module;
import dagger.Provides;
import app.news.agoda.com.dagger.scope.MainActivityScope;
import app.news.agoda.com.view.detail.fragment.NewsDetailFragment;
import app.news.agoda.com.view.detail.fragment.NewsDetailPresenter;

@Module
public class NewsDetailFragmentModule {
    private final NewsDetailFragment newsDetailFragment;

    public NewsDetailFragmentModule(NewsDetailFragment newsDetailFragment) {
        this.newsDetailFragment = newsDetailFragment;
    }

    @Provides
    @MainActivityScope
    public NewsDetailPresenter newsFeedPresenter() {
        return new NewsDetailPresenter(newsDetailFragment);
    }
}
