package domain.news.agoda.com;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public abstract class BaseInteractor implements Interactor {

    private volatile boolean mIsRunning;

    BaseInteractor() {
    }

    public abstract Observable<ResponseBody> run();

    public void cancel() {
        mIsRunning = false;
    }

    public boolean isRunning() {
        return mIsRunning;
    }

    private void onFinished() {
        mIsRunning = false;
    }

    public Observable<ResponseBody> execute() {

        // mark this interactor as running
        this.mIsRunning = true;

        // start running this interactor in a background thread
        // run the main logic
        Observable<ResponseBody> result = run();
        // mark it as finished
        onFinished();
        return result;
    }

}
