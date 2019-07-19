package app.news.agoda.com.view.feed.fragment;

import android.annotation.SuppressLint;

import javax.inject.Inject;

import app.news.agoda.com.domain.NewsFeedEntityDomainMapper;
import app.news.agoda.com.domain.NewsFeedInteractor;
import app.news.agoda.com.domain.model.NewsEntity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class NewsFeedFragmentPresenter {

    private NewsFeedView newsFeedView;
    private NewsFeedInteractor newsFeedInteractor;
    private NewsFeedEntityDomainMapper newsFeedEntityDomainMapper;

    @Inject
    public NewsFeedFragmentPresenter(NewsFeedView newsFeedFragment, NewsFeedInteractor newsFeedInteractor, NewsFeedEntityDomainMapper newsFeedEntityDomainMapper) {
        this.newsFeedView = newsFeedFragment;
        this.newsFeedInteractor = newsFeedInteractor;
        this.newsFeedEntityDomainMapper = newsFeedEntityDomainMapper;
    }

    @SuppressLint("CheckResult")
    public void getNewsFeeds() {
        newsFeedView.showProgress();
        newsFeedInteractor.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody newsEntity) throws Exception {
                if (newsFeedView != null) {
                    newsFeedView.hideProgress();
                    NewsEntity parsedNewsEntity = newsFeedEntityDomainMapper.parseResponse(newsEntity.string());
                    newsFeedView.newsFeedResponse(newsFeedEntityDomainMapper.mapResults(parsedNewsEntity.getResults()));
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                newsFeedView.hideProgress();
                newsFeedView.showError(throwable.getMessage());
            }
        });
    }
}
