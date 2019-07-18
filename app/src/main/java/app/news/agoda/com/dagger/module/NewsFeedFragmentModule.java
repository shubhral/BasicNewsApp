package app.news.agoda.com.dagger.module;

import android.content.Context;

import domain.news.agoda.com.NewsFeedEntityDomainMapper;
import domain.news.agoda.com.network.APIInterface;
import domain.news.agoda.com.NewsFeedInteractor;
import domain.news.agoda.com.NewsFeedRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import app.news.agoda.com.dagger.scope.MainActivityScope;
import app.news.agoda.com.view.adapter.NewsListAdapter;
import app.news.agoda.com.view.feed.fragment.NewsFeedFragment;
import app.news.agoda.com.view.feed.fragment.NewsFeedFragmentPresenter;

@Module(includes = ActivityModule.class)
public class NewsFeedFragmentModule {
    private final NewsFeedFragment newsFeedFragment;

    public NewsFeedFragmentModule(NewsFeedFragment newsFeedActivity) {
        this.newsFeedFragment = newsFeedActivity;
    }

    @Provides
    @MainActivityScope
    public NewsListAdapter newsFeedAdapter(@Named("activity_context") Context context) {
        return new NewsListAdapter(context);
    }

    @Provides
    @MainActivityScope
    public NewsFeedFragmentPresenter newsFeedPresenter(NewsFeedInteractor newsFeedInteractor, NewsFeedEntityDomainMapper newsFeedEntityDomainMapper) {
        return new NewsFeedFragmentPresenter(newsFeedFragment, newsFeedInteractor, newsFeedEntityDomainMapper);
    }

    @Provides
    @MainActivityScope
    public NewsFeedInteractor newsFeedInteractor(NewsFeedRepository newsFeedRepository) {
        return new NewsFeedInteractor(newsFeedRepository);
    }

    @Provides
    @MainActivityScope
    public NewsFeedRepository newFeedRepository(APIInterface apiInterface) {
        return new NewsFeedRepository(apiInterface);
    }

    @Provides
    @MainActivityScope
    public NewsFeedEntityDomainMapper newsFeedEntityDomaninMapper() {
        return new NewsFeedEntityDomainMapper();
    }
}
