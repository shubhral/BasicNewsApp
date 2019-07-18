package app.news.agoda.com.dagger.module;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import app.news.agoda.com.dagger.scope.ApplicationContext;
import app.news.agoda.com.dagger.scope.NewsFeedApplicationScope;

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @ApplicationContext
    @NewsFeedApplicationScope
    @Provides
    public Context context() {
        return context.getApplicationContext();
    }
}
