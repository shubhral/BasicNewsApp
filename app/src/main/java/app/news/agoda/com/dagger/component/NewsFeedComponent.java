package app.news.agoda.com.dagger.component;

import app.news.agoda.com.dagger.module.ContextModule;
import app.news.agoda.com.dagger.module.NewsFeedModule;
import app.news.agoda.com.dagger.scope.NewsFeedApplicationScope;
import app.news.agoda.com.domain.network.APIInterface;
import dagger.Component;

@NewsFeedApplicationScope
@Component(modules = {NewsFeedModule.class, ContextModule.class})
public interface NewsFeedComponent {
    APIInterface getApiClient();
}
