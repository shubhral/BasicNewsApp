package domain.news.agoda.com;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface Interactor {
    Observable<ResponseBody> execute();
}
