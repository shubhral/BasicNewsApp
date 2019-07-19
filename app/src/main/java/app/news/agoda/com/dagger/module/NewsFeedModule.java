package app.news.agoda.com.dagger.module;

import app.news.agoda.com.dagger.scope.NewsFeedApplicationScope;
import app.news.agoda.com.util.AppConstants;
import dagger.Module;
import dagger.Provides;
import domain.news.agoda.com.network.APIInterface;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class NewsFeedModule {
    @Provides
    public APIInterface apiClient(Retrofit retrofit) {
        return retrofit.create(APIInterface.class);
    }

    @NewsFeedApplicationScope
    @Provides
    public Retrofit retrofit(JacksonConverterFactory jacksonConverterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(jacksonConverterFactory)
                .build();
    }

    @NewsFeedApplicationScope
    @Provides
    public RxJava2CallAdapterFactory rxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @NewsFeedApplicationScope
    @Provides
    public JacksonConverterFactory jacksonConverterFactory() {
        return JacksonConverterFactory.create();
    }

}
