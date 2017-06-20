package qunter.newtecdemo.api;

import okhttp3.OkHttpClient;
import qunter.newtecdemo.entity.NewsEntity;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/20.
 */

public interface ApiService {
    @GET("news/latest")
    Observable<NewsEntity> onLoad();
}
