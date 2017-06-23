package qunter.newtecdemo.mvp.model;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import qunter.newtecdemo.entity.NewsEntity;
import qunter.newtecdemo.util.ComApi;

/**
 * Created by Administrator on 2017/6/23.
 */

public class NewsModel implements INewsModel{

    @Override
    public void startGetData(final onDataListener listener) {
        ComApi.getInstance().getData()
                .doOnNext(new Consumer<NewsEntity>() {
                    @Override
                    public void accept(NewsEntity newsEntity) throws Exception {
                        List<NewsEntity.StoriesBean> storyEntity = new ArrayList<NewsEntity.StoriesBean>();
                        storyEntity = newsEntity.getStories();
                        listener.onComplete(storyEntity);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                })
                .subscribe();
    }
}
