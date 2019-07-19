package app.news.agoda.com.domain.network;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("573c89f31100004a1daa8adb")
    Observable<ResponseBody> getNewsFeed();
}
