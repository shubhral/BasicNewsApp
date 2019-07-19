package app.news.agoda.com.domain;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface Interactor {
    Observable<ResponseBody> execute();
}
