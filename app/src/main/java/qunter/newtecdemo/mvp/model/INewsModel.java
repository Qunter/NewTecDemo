package qunter.newtecdemo.mvp.model;

import java.util.List;

import qunter.newtecdemo.entity.NewsEntity;

/**
 * Created by Administrator on 2017/6/23.
 */

public interface INewsModel {
    void startGetData(onDataListener listener);
    interface onDataListener{//数据加载完成后的监听回调
        void onComplete(List<NewsEntity.StoriesBean> storyEntity);
    }
}
