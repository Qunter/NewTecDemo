package qunter.newtecdemo.util;

import io.reactivex.Flowable;
import qunter.newtecdemo.entity.DetailEntity;
import qunter.newtecdemo.entity.NewsEntity;
import qunter.newtecdemo.entity.StoryDetailsEntity;
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

    public Flowable<NewsEntity> getData() {
        return create(mComInterface.getNews())
                .compose(BaseComApi.<NewsEntity>background());
    }

    public Flowable<StoryDetailsEntity> getDetail(int id){
        return create(mComInterface.getDetail(id))
                .compose(BaseComApi.<StoryDetailsEntity>background());
    }
}