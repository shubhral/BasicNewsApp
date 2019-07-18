package app.news.agoda.com;

import android.app.Activity;
import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import app.news.agoda.com.dagger.component.DaggerNewsFeedComponent;
import app.news.agoda.com.dagger.component.NewsFeedComponent;
import app.news.agoda.com.dagger.module.ContextModule;

public class MyApplication extends Application {
    private NewsFeedComponent newsFeedComponent;

    public static MyApplication get(Activity activity) {
        return (MyApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        newsFeedComponent = DaggerNewsFeedComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public NewsFeedComponent getNewFeedApplicationComponent() {
        return newsFeedComponent;
    }
}
