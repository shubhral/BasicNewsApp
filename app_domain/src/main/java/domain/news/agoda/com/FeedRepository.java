package domain.news.agoda.com;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface FeedRepository {
    Observable<ResponseBody> getNewsFeed();
}
