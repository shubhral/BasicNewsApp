package domain.news.agoda.com;

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
        Observable<ResponseBody> obj = mFeedRepository.getNewsFeed();
        return obj;
    }
}
