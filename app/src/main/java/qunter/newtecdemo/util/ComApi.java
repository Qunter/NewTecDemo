package qunter.newtecdemo.util;

import io.reactivex.Flowable;
import qunter.newtecdemo.entity.NewsEntity;
import retrofit2.Call;

import static qunter.newtecdemo.util.BaseComApi.create;

public class ComApi {

    public static ComApi comApi;
    private ComInterface mComInterface;

    public static ComApi getInstance() {
        synchronized (ComApi.class) {
            if (comApi == null) {
                comApi = new ComApi();
            }
        }
        return comApi;
    }

    private ComApi() {
        mComInterface = NetClient.newRetrofit().create(ComInterface.class);
    }
    /*
    public Call<NewsEntity> getData() {
        return mComInterface.getNews();
    }
    */
    public Flowable<NewsEntity> getData() {
        return create(mComInterface.getNews())
                .compose(BaseComApi.<NewsEntity>background());
    }
}