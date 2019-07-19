package news.agoda.com.sample;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import app.news.agoda.com.view.feed.fragment.NewsFeedFragmentPresenter;
import app.news.agoda.com.view.feed.fragment.NewsFeedView;
import domain.news.agoda.com.NewsFeedEntityDomainMapper;
import domain.news.agoda.com.NewsFeedInteractor;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.MediaType;
import okhttp3.ResponseBody;

public class NewsFeedPresenterTest {

    private NewsFeedFragmentPresenter newsFeedFragmentPresenter;
    @Mock
    NewsFeedInteractor newsFeedInteractor;
    @Mock
    NewsFeedEntityDomainMapper newsFeedEntityDomainMapper;
    @Mock
    NewsFeedView newsFeedView;

    @Before
    public void testSetUp() {
        MockitoAnnotations.initMocks(this);
        newsFeedFragmentPresenter = new NewsFeedFragmentPresenter(newsFeedView, newsFeedInteractor, newsFeedEntityDomainMapper);
        final Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(new Executor() {

                    @Override
                    public void execute(@android.support.annotation.NonNull Runnable command) {
                        command.run();
                    }
                });
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return immediate;
            }
        });
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return immediate;
            }
        });
    }

    @Test
    public void testBind() {
        ResponseBody responseBody = ResponseBody.create(MediaType.parse("application/json"), MockResponse.newsFeedResponse);
        Observable<ResponseBody> responseBodyObservable = Observable.just(responseBody);
        Mockito.when(newsFeedInteractor.execute()).thenReturn(responseBodyObservable);
        newsFeedFragmentPresenter.getNewsFeeds();
        Mockito.verify(newsFeedEntityDomainMapper).parseResponse(Matchers.anyString());
    }

}
