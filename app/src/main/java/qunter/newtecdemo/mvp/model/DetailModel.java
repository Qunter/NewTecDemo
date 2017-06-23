package qunter.newtecdemo.mvp.model;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import qunter.newtecdemo.entity.DetailEntity;
import qunter.newtecdemo.entity.NewsEntity;
import qunter.newtecdemo.entity.StoryDetailsEntity;
import qunter.newtecdemo.util.ComApi;
import qunter.newtecdemo.util.HtmlUtils;

/**
 * Created by Administrator on 2017/6/23.
 */

public class DetailModel implements IDetailModel {


    @Override
    public void startGetDetail(int id, final onDetailListener listener) {
        ComApi.getInstance().getDetail(id)
                .doOnNext(new Consumer<StoryDetailsEntity>() {
                    @Override
                    public void accept(StoryDetailsEntity detailEntity) throws Exception {
                        listener.onComplete(detailEntity.getTitle(),HtmlUtils.structHtml(detailEntity));
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
