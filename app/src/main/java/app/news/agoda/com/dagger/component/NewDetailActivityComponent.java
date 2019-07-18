package app.news.agoda.com.dagger.component;

import dagger.Component;
import app.news.agoda.com.dagger.module.NewsDetailFragmentModule;
import app.news.agoda.com.dagger.scope.MainActivityScope;
import app.news.agoda.com.view.detail.fragment.NewsDetailFragment;

@Component(modules = {NewsDetailFragmentModule.class}, dependencies = NewsFeedComponent.class)
@MainActivityScope
public interface NewDetailActivityComponent {

    void injectNewsDetailFragment(NewsDetailFragment newsDetailFragment);
}
