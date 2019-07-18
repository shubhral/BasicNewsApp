package app.news.agoda.com.dagger.component;

import domain.news.agoda.com.network.APIInterface;

import dagger.Component;
import app.news.agoda.com.dagger.module.ContextModule;
import app.news.agoda.com.dagger.module.NewsFeedModule;
import app.news.agoda.com.dagger.scope.NewsFeedApplicationScope;

@NewsFeedApplicationScope
@Component(modules = {NewsFeedModule.class, ContextModule.class})
public interface NewsFeedComponent {
    APIInterface getApiClient();
}
