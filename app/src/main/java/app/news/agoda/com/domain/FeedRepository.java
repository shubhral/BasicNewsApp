package app.news.agoda.com.domain;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface FeedRepository {
    Observable<ResponseBody> getNewsFeed();
}
