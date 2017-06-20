package qunter.newtecdemo.mvp.component;

import com.google.gson.Gson;
import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import qunter.newtecdemo.api.ApiService;
import qunter.newtecdemo.entity.NewsEntity;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * Created by 小鹿 on 2017/2/8.
 */
@Singleton
public class DataManager {
    private final ApiService apiService;
    private final Gson gson;

    @Inject
    public DataManager(ApiService apiService, Gson gson) {
        this.apiService = apiService;
        this.gson = gson;
    }

    //首页数据
    public Subscription getLatest(Subscriber<NewsEntity> subscriber) {
        return apiService.onLoad()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
