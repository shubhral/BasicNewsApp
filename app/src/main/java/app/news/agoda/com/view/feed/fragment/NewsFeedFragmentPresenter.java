package app.news.agoda.com.view.feed.fragment;

import domain.news.agoda.com.NewsFeedEntityDomainMapper;
import domain.news.agoda.com.NewsFeedInteractor;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import domain.news.agoda.com.model.NewsEntity;
import okhttp3.ResponseBody;

public class NewsFeedFragmentPresenter {

    private NewsFeedFragView newsFeedView;
    private NewsFeedInteractor newsFeedInteractor;
    private NewsFeedEntityDomainMapper newsFeedEntityDomainMapper;

    @Inject
    public NewsFeedFragmentPresenter(NewsFeedFragView newsFeedFragment, NewsFeedInteractor newsFeedInteractor, NewsFeedEntityDomainMapper newsFeedEntityDomainMapper) {
        this.newsFeedView = newsFeedFragment;
        this.newsFeedInteractor = newsFeedInteractor;
        this.newsFeedEntityDomainMapper = newsFeedEntityDomainMapper;
    }

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
