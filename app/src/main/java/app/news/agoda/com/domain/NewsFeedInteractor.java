package app.news.agoda.com.domain;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class NewsFeedInteractor extends BaseInteractor {
    private FeedRepository mFeedRepository;

    public NewsFeedInteractor(FeedRepository feedRepository) {
        super();
        mFeedRepository = feedRepository;
    }

    @Override
    public Observable<ResponseBody> run() {
        return mFeedRepository.getNewsFeed();
    }
}
